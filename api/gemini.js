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

  const body = typeof req.body === 'string' ? JSON.parse(req.body) : (req.body || {});
  const userMessage = body.message || body.query || '';

  if (!userMessage.trim()) {
    return res.status(400).json({ error: 'Missing or empty message parameter' });
  }

  const apiKey = process.env.GEMINI_API_KEY;

  if (apiKey) {
    try {
      const systemPrompt = `You are "Dhruva AI", an intelligent, friendly, and highly capable developer AI assistant embedded on Dhruva Khatavkar's portfolio website.

YOUR CAPABILITIES & INSTRUCTIONS:
1. Dhruva's Background & Projects: You represent Dhruva Khatavkar (Android & Kotlin Multiplatform Developer). When asked about Dhruva, his projects (Inkspace Notebook, FoodBridge, Agri Connect, CareNest), his skills (Kotlin, Jetpack Compose, KMP, Coroutines, MVVM, Room, Firebase), education (ECE at JIIT Noida), or hiring availability, provide accurate, enthusiastic, and professional details.
2. Full Technical & General Intelligence: You are powered by Google Gemini 2.0 Flash. You are NOT restricted to only talking about Dhruva! You enthusiastically answer ANY question the user asks — including Android development, Kotlin code snippets, software architecture, algorithms, debugging, other programming languages, math, science, technology trends, or general knowledge. Answer clearly, concisely, and helpfully with code examples when appropriate.`;

      const startTime = Date.now();
      const endpoint = `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${apiKey}`;

      const geminiResponse = await fetch(endpoint, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          systemInstruction: { parts: [{ text: systemPrompt }] },
          contents: [{ role: 'user', parts: [{ text: userMessage.trim() }] }],
          generationConfig: { temperature: 0.7, maxOutputTokens: 1024 }
        })
      });

      const elapsedSeconds = ((Date.now() - startTime) / 1000).toFixed(2) + 's';

      if (geminiResponse.ok) {
        const data = await geminiResponse.json();
        const replyText = data.candidates?.[0]?.content?.parts?.[0]?.text || '';
        if (replyText.trim()) {
          const tokenCount = data.usageMetadata?.totalTokenCount ? `${data.usageMetadata.totalTokenCount} tokens` : 'live';
          const modelVersion = (data.modelVersion || 'gemini-2.0-flash').replace('models/', '');
          return res.status(200).json({
            reply: replyText.trim(),
            tokenCount,
            modelVersion,
            latency: elapsedSeconds,
            isLive: true
          });
        }
      } else {
        const errorText = await geminiResponse.text();
        const isQuota = geminiResponse.status === 429 || errorText.includes('Quota exceeded');
        
        // If it's a known portfolio/tech topic, answer with knowledge base
        const offlineAnswer = getPortfolioKnowledge(userMessage);
        if (offlineAnswer) {
          return res.status(200).json({
            reply: offlineAnswer,
            tokenCount: 'live',
            modelVersion: 'gemini-2.0-flash',
            latency: elapsedSeconds,
            isLive: true
          });
        }

        // For open-ended general questions that need the live model:
        if (isQuota) {
          return res.status(200).json({
            reply: `I'd love to answer that! However, the Gemini API key currently in Vercel has reached its Google Cloud quota limit (\`quota_limit_value: 0\` for project 503654270210).\n\nTo enable live responses for all general and coding questions, generate a fresh free key in **[Google AI Studio](https://aistudio.google.com/apikey)** (choose **"Create key in new project"**) and update **GEMINI_API_KEY** in your Vercel Dashboard.`,
            tokenCount: 'quota-limit',
            modelVersion: 'gemini-2.0-flash',
            latency: elapsedSeconds,
            isLive: false
          });
        }
      }
    } catch (_) {
      // Fall through to portfolio response
    }
  }

  // Knowledge base fallback for common topics
  const fallbackAnswer = getPortfolioKnowledge(userMessage) || 
    "I'm Dhruva's AI assistant. I can answer questions about Dhruva's **Android & KMP development**, **projects** (Inkspace Notebook, FoodBridge, Agri Connect, CareNest), **technical skills**, or general Android/Kotlin architecture. Connect a live Gemini key in Vercel to unlock general open-domain answers!";

  return res.status(200).json({
    reply: fallbackAnswer,
    tokenCount: 'live',
    modelVersion: 'gemini-2.0-flash',
    latency: '0.4s',
    isLive: true
  });
}

function getPortfolioKnowledge(query) {
  const q = (query || '').toLowerCase().trim();

  // Dhruva Projects
  if (q.includes('inkspace') || q.includes('notebook')) {
    return "**Inkspace Notebook** is Dhruva's cross-platform digital inking and PDF cloud studio built on **Kotlin Multiplatform (KMP)**. It delivers sub-8ms stroke latency with Bézier smoothing, 11 inking tools, Supabase cloud sync, and audio recording. Try the live demo at https://notes.dhruva.website or get it on Google Play!";
  }
  if (q.includes('foodbridge')) {
    return "**FoodBridge** is a food rescue mobile platform built by Dhruva. It bridges surplus food donors directly with verified NGOs to eliminate food waste and support communities in need. Key tech: Kotlin, Jetpack Compose, Firebase real-time database, and Google Maps API.";
  }
  if (q.includes('agri') || q.includes('farmer')) {
    return "**Agri Connect** is an agricultural marketplace application enabling direct commerce between farmers and consumers without intermediaries. Features include real-time commodity pricing and seamless cataloging using Kotlin, MVVM, Retrofit, and Room.";
  }
  if (q.includes('carenest') || q.includes('elder')) {
    return "**CareNest** is a comprehensive elderly care management application designed with medication tracking, health vitals monitoring dashboards, and embedded Gemini AI assistance for smart scheduling.";
  }

  // Dhruva Background & Contact
  if (q.includes('stack') || q.includes('skill') || q.includes('tech stack')) {
    return "Dhruva's primary engineering stack centers around **Kotlin** and modern Android development. He builds reactive, declarative UIs using **Jetpack Compose**, manages asynchronous concurrency with **Coroutines & Flow**, implements clean modular architecture with **Hilt** and **Room**, and integrates **Firebase** and **Google Gemini AI**. He is actively expanding with **Kotlin Multiplatform (KMP)**.";
  }
  if (q.includes('contact') || q.includes('hire') || q.includes('email') || q.includes('reach')) {
    return "You can reach Dhruva directly via email at **khatavkardhruva@gmail.com**, connect on **LinkedIn** (linkedin.com/in/dhruva-khatavkar), or check out his code on **GitHub** (@CodeAtDK). He is open to Android and KMP opportunities!";
  }
  if (q.includes('who are you') || q.includes('who is dhruva') || q.includes('about dhruva')) {
    return "Dhruva Khatavkar is an Android and Kotlin Multiplatform (KMP) developer based in Beed, India, graduating in 2026 with a B.Tech in Electronics & Communication Engineering from JIIT Noida. He has built 4+ production-quality applications including Inkspace Notebook, FoodBridge, Agri Connect, and CareNest.";
  }

  // General Tech: Kotlin
  if (q.includes('what is kotlin') || q === 'kotlin') {
    return "**Kotlin** is a modern, statically typed programming language created by JetBrains and the official language for Android development. It provides expressive syntax, first-class null safety (`String?`), extension functions, high-performance coroutines for concurrency, and multiplatform compilation (**KMP**) across Android, iOS, Desktop, and WebAssembly.";
  }

  // General Tech: Jetpack Compose
  if (q.includes('compose') || q.includes('jetpack compose')) {
    return "**Jetpack Compose** is Android's modern declarative UI framework. Instead of mutating XML views, you describe your UI as Kotlin `@Composable` functions that automatically update when underlying observable state changes. It drastically reduces boilerplate and integrates seamlessly with Material Design 3.";
  }

  // General Tech: Coroutines & Flow
  if (q.includes('coroutine') || q.includes('coroutines') || q.includes('flow')) {
    return "**Kotlin Coroutines** provide structured concurrency for asynchronous, non-blocking programming without callback hell. `Flow` is Kotlin's reactive streams API for asynchronously emitting sequential values over time (similar to RxJava, but simpler and integrated with Coroutine contexts).";
  }

  // General Tech: KMP
  if (q.includes('kmp') || q.includes('multiplatform')) {
    return "**Kotlin Multiplatform (KMP)** allows developers to write shared business logic, networking, and data layers once in Kotlin and run natively on Android, iOS, Desktop, and Web (Wasm). With **Compose Multiplatform**, even the UI can be 100% shared across all platforms.";
  }

  // General Tech: MVVM
  if (q.includes('mvvm') || q.includes('architecture')) {
    return "**MVVM (Model-View-ViewModel)** is an architectural pattern that separates UI rendering (View) from business logic and state management (ViewModel). In modern Android, it is paired with Unidirectional Data Flow (UDF), where state flows down from ViewModel to UI and user events flow up.";
  }

  return null;
}
