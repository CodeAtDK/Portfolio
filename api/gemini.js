export default async function handler(req, res) {
  // CORS configuration
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Accept');

  if (req.method === 'OPTIONS') {
    return res.status(200).end();
  }

  if (req.method === 'GET') {
    const isConfigured = Boolean(process.env.GEMINI_API_KEY);
    return res.status(200).json({
      status: isConfigured ? 'ready' : 'missing_api_key',
      model: 'gemini-2.0-flash'
    });
  }

  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method Not Allowed' });
  }

  const apiKey = process.env.GEMINI_API_KEY;
  if (!apiKey) {
    return res.status(503).json({
      error: 'GEMINI_API_KEY environment variable is not configured in Vercel.',
      isLive: false
    });
  }

  try {
    const body = typeof req.body === 'string' ? JSON.parse(req.body) : (req.body || {});
    const userMessage = body.message || body.query;

    if (!userMessage || !userMessage.trim()) {
      return res.status(400).json({ error: 'Missing or empty message parameter' });
    }

    const systemPrompt = `You are "Dhruva AI", an upbeat, highly intelligent, and concise professional AI assistant embedded in Dhruva Khatavkar's Android Developer portfolio website.
Here is Dhruva's complete profile and career details to answer user inquiries accurately:
- Role & Focus: Android Developer specializing in Kotlin, Jetpack Compose, Android SDK, MVVM architecture, Coroutines/Flow, Hilt, Room, and AI integrations (Gemini AI).
- Current Exploration: Kotlin Multiplatform (KMP), Compose Multiplatform, and Ktor to bridge Android, iOS, and Web.
- Experience: Developing mobile applications independently and open-source since August 2023. Built 4+ production-quality multiplatform and Android apps.
- Featured Projects:
  1) Inkspace Notebook: Cross-Platform Digital Inking, Multi-Page PDF & Cloud Studio on Kotlin Multiplatform (Compose Multiplatform, Supabase, SQLDelight, Skiko Canvas, Ktor). Sub-8ms stroke latency with Bézier smoothing, 11 inking tools, audio recording. Available on Google Play (https://play.google.com/store/apps/details?id=dev.dhruva.notebook) and Live Demo (https://notes.dhruva.website).
  2) FoodBridge: A food rescue platform connecting surplus food donors with NGOs using location-based services and real-time matching (Kotlin, Compose, Firebase, Maps API).
  3) Agri Connect: A direct farmer-to-consumer marketplace without middlemen featuring real-time pricing and crop cataloging (Kotlin, MVVM, Retrofit, Room).
  4) CareNest: An elderly care management app with medication tracking and health monitoring dashboards (Kotlin, Compose, Hilt, Gemini AI).
  5) This Portfolio: A developer-crafted portfolio site with terminal-themed design, 60 FPS Wasm engine, and embedded Gemini AI chat.
- Education: B.Tech in Electronics & Communication Engineering (ECE) from Jaypee Institute of Information Technology, Noida (Graduating 2026).
- Location & Availability: Based in Beed, India. Fully open and available for Android and Multiplatform developer opportunities, internships, and full-time roles!`;

    const startTime = Date.now();
    const endpoint = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${apiKey}`;

    const geminiResponse = await fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        systemInstruction: {
          parts: [{ text: systemPrompt }]
        },
        contents: [
          {
            role: 'user',
            parts: [{ text: userMessage.trim() }]
          }
        ],
        generationConfig: {
          temperature: 0.7,
          maxOutputTokens: 1024
        }
      })
    });

    const elapsedSeconds = ((Date.now() - startTime) / 1000).toFixed(2) + 's';

    if (!geminiResponse.ok) {
      const errorText = await geminiResponse.text();
      let errorJson = null;
      try { errorJson = JSON.parse(errorText); } catch(e) {}
      const errMsg = errorJson?.error?.message || errorText;
      return res.status(200).json({
        reply: `⚠️ **Gemini API Key Notice (${geminiResponse.status})**:\n${errMsg}\n\n👉 **How to fix**:\n1. Open [Google AI Studio](https://aistudio.google.com/apikey).\n2. Click **"Create API key"** and choose **"Create key in new project"** (free tier with 15 requests/min).\n3. Update **GEMINI_API_KEY** in your Vercel Dashboard (or enter it in the **[ ⚡ Set Key ]** button above).`,
        tokenCount: "quota-limit",
        modelVersion: "gemini-2.0-flash",
        latency: elapsedSeconds,
        isLive: false
      });
    }

    const data = await geminiResponse.json();
    const replyText = data.candidates?.[0]?.content?.parts?.[0]?.text || '';
    const tokenCount = data.usageMetadata?.totalTokenCount ? `${data.usageMetadata.totalTokenCount} tokens` : 'live';
    const modelVersion = (data.modelVersion || 'gemini-2.0-flash').replace('models/', '');

    return res.status(200).json({
      reply: replyText.trim(),
      tokenCount,
      modelVersion,
      latency: elapsedSeconds,
      isLive: true
    });
  } catch (err) {
    return res.status(500).json({
      error: err.message || 'Internal proxy error',
      isLive: false
    });
  }
}
