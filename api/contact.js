export default async function handler(req, res) {
  // CORS configuration
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'POST, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Accept');

  if (req.method === 'OPTIONS') {
    return res.status(200).end();
  }

  if (req.method !== 'POST') {
    return res.status(405).json({ error: 'Method Not Allowed' });
  }

  try {
    const body = typeof req.body === 'string' ? JSON.parse(req.body) : (req.body || {});

    const formspreeResponse = await fetch('https://formspree.io/f/xyegykdg', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify({
        name: body.name || '',
        email: body.email || '',
        message: body.message || '',
        _subject: body._subject || 'New message from KMP Portfolio 🚀'
      })
    });

    if (formspreeResponse.ok) {
      const data = await formspreeResponse.json();
      return res.status(200).json({ ok: true, data });
    } else {
      const errorText = await formspreeResponse.text();
      return res.status(formspreeResponse.status).json({ ok: false, error: errorText });
    }
  } catch (err) {
    return res.status(500).json({ ok: false, error: err.message || 'Internal proxy error' });
  }
}
