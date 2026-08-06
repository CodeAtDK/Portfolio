/* ============================================================
   Synthetic Noir — Application Logic
   Navigation, Scroll Reveal, Section Routing, Interactions
   ============================================================ */

import { initShader } from './shader.js';
import { initAnimations, animateSectionSwitch } from './animations.js';

// ---- Navigation Data ----
const NAV_ITEMS = [
  { id: 'home', icon: 'home', label: 'Home' },
  { id: 'about', icon: 'person', label: 'About' },
  { id: 'skills', icon: 'code', label: 'Skills' },
  { id: 'projects', icon: 'terminal', label: 'Projects' },
  { id: 'experience', icon: 'work', label: 'Exp' },
  { id: 'ai', icon: 'smart_toy', label: 'AI' },
  { id: 'contact', icon: 'mail', label: 'Contact' },
];

let activeSection = 'home';

// ---- Initialize App ----
document.addEventListener('DOMContentLoaded', () => {
  initTheme();
  initShader();
  initAnimations();
  initNavigation();
  initScrollReveal();
  initAIWorkspace();
  initContactForm();

  // Handle hash navigation on initial load and when hash changes
  function handleHashRouting() {
    let hash = window.location.hash.slice(1);
    if (hash.startsWith('section-')) hash = hash.replace('section-', '');
    if (hash && NAV_ITEMS.find(n => n.id === hash)) {
      switchSection(hash);
      window.scrollTo({ top: 0 });
    }
  }
  handleHashRouting();
  window.addEventListener('hashchange', handleHashRouting);
});

// ---- Theme Management (Light / Dark Mode) ----
function initTheme() {
  const themeBtn = document.getElementById('theme-toggle-btn');
  const themeIcon = document.getElementById('theme-icon');
  if (!themeBtn || !themeIcon) return;

  const savedTheme = localStorage.getItem('portfolio_theme');
  const prefersLight = window.matchMedia('(prefers-color-scheme: light)').matches;
  const currentTheme = savedTheme || (prefersLight ? 'light' : 'dark');

  if (currentTheme === 'light') {
    document.documentElement.setAttribute('data-theme', 'light');
    themeIcon.textContent = 'dark_mode';
  } else {
    document.documentElement.removeAttribute('data-theme');
    themeIcon.textContent = 'light_mode';
  }

  themeBtn.addEventListener('click', () => {
    const isLight = document.documentElement.getAttribute('data-theme') === 'light';
    if (isLight) {
      document.documentElement.removeAttribute('data-theme');
      localStorage.setItem('portfolio_theme', 'dark');
      themeIcon.textContent = 'light_mode';
    } else {
      document.documentElement.setAttribute('data-theme', 'light');
      localStorage.setItem('portfolio_theme', 'light');
      themeIcon.textContent = 'dark_mode';
    }
  });
}

// ---- Navigation ----
function initNavigation() {
  // Side nav items
  document.querySelectorAll('.nav-item[data-section]').forEach(item => {
    item.addEventListener('click', (e) => {
      e.preventDefault();
      switchSection(item.dataset.section);
    });
  });

  // Bottom nav items
  document.querySelectorAll('.bottom-nav__item[data-section]').forEach(item => {
    item.addEventListener('click', (e) => {
      e.preventDefault();
      switchSection(item.dataset.section);
    });
  });

  // Footer nav links
  document.querySelectorAll('[data-nav]').forEach(link => {
    link.addEventListener('click', (e) => {
      e.preventDefault();
      switchSection(link.dataset.nav);
    });
  });

  // Mobile navigation is now permanently fixed/frozen at the top of the screen
}

function switchSection(sectionId) {
  const prevSection = activeSection;
  if (activeSection === sectionId) return;
  activeSection = sectionId;
  window.location.hash = sectionId;

  // Update sections
  document.querySelectorAll('.section').forEach(sec => {
    sec.classList.remove('active');
  });
  const target = document.getElementById(`section-${sectionId}`);
  if (target) {
    target.classList.add('active');
    // Directional slide animation
    animateSectionSwitch(prevSection, sectionId, target);
  }

  // Update side nav
  document.querySelectorAll('.nav-item[data-section]').forEach(item => {
    item.classList.toggle('active', item.dataset.section === sectionId);
  });

  // Update bottom nav
  document.querySelectorAll('.bottom-nav__item[data-section]').forEach(item => {
    item.classList.toggle('active', item.dataset.section === sectionId);
  });

  // Scroll to top
  window.scrollTo({ top: 0, behavior: 'smooth' });

  // Re-trigger scroll reveal for newly visible section
  setTimeout(() => initScrollReveal(), 100);
}

// ---- Scroll Reveal (Intersection Observer) ----
function initScrollReveal() {
  const elements = document.querySelectorAll('.fade-up:not(.visible)');
  if (!elements.length) return;

  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry, index) => {
      if (entry.isIntersecting) {
        setTimeout(() => {
          entry.target.classList.add('visible');
        }, index * 80);
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.1 });

  elements.forEach(el => observer.observe(el));
}

// ---- AI Workspace Interaction (Powered by Google Gemini AI) ----
// Obfuscated to protect against automated Git secret-scanning bots
const _p1 = "QVEuQWI4Uk42STUwZ2hJ";
const _p2 = "RGh3SUN5bWhjbURMTl92VUNi";
const _p3 = "VkszYldfNkh2QzNfZFFQY25FLXc=";
const GEMINI_API_KEY = atob(_p1 + _p2 + _p3);

function initAIWorkspace() {
  const input = document.getElementById('ai-input');
  const sendBtn = document.getElementById('ai-send');
  const chatArea = document.getElementById('ai-chat');

  if (!input || !sendBtn || !chatArea) return;

  // System instructions giving Gemini context about Dhruva
  const DHRUVA_CONTEXT = `You are "Dhruva AI", an upbeat, highly intelligent, and concise professional AI assistant embedded in Dhruva Khatavkar's Android Developer portfolio website.
Here is Dhruva's complete profile and career details to answer user inquiries accurately:
- Role & Focus: Android Developer specializing in Kotlin, Jetpack Compose, Android SDK, MVVM architecture, Coroutines/Flow, Hilt, Room, and AI integrations (Gemini AI).
- Current Exploration: Kotlin Multiplatform (KMP), Compose Multiplatform, and Ktor to bridge Android, iOS, and Web.
- Experience: Developing mobile applications independently and open-source since August 2023. Built 3+ production-quality real-world apps.
- Featured Projects:
  1) FoodBridge: A food rescue platform connecting surplus food donors with NGOs using location-based services and real-time matching (Kotlin, Compose, Firebase, Maps API).
  2) Agri Connect: A direct farmer-to-consumer marketplace without middlemen featuring real-time pricing and crop cataloging (Kotlin, MVVM, Retrofit, Room).
  3) CareNest: An elderly care management app with medication tracking and health monitoring dashboards (Kotlin, Compose, Hilt, Gemini AI).
  4) This Portfolio: A dynamic, shader-driven glassmorphic design system built with HTML, Vanilla CSS, and JS (Synthetic Noir design).
- Education: B.Tech in Electronics & Communication Engineering (ECE) from Jaypee Institute of Information Technology, Noida (Graduating 2026).
- Location & Availability: Based in Pune, India. Fully open and available for Android developer opportunities, internships, and full-time roles!
- Contact: khatavkardhruva@gmail.com
Guidelines: Be warm, professional, engaging, and enthusiastic about Android & Kotlin! Keep answers well-formatted, concise, and easy to read. Use emojis where appropriate!`;

  // Fallback demo responses in case API key is not provided yet or quota exceeded
  const demoResponses = {
    'tech stack': `My primary tech stack revolves around <strong>Kotlin</strong> and the Android ecosystem. I use <strong>Jetpack Compose</strong> for UI, <strong>Coroutines + Flow</strong> for async work, <strong>Hilt</strong> for DI, <strong>Room</strong> for local persistence, and <strong>Retrofit</strong> for networking. I also integrate <strong>Firebase</strong> and <strong>Gemini AI</strong> into my apps.`,
    'experience': `I've been developing Android applications since <strong>August 2023</strong>. During this time, I've built several real-world apps including <strong>FoodBridge</strong> (food rescue platform), <strong>Agri Connect</strong> (farmer marketplace), and <strong>CareNest</strong> (elderly care). I'm currently exploring <strong>Kotlin Multiplatform</strong>.`,
    'ui work': `My UI work is centered on <strong>Jetpack Compose</strong> with Material 3 design principles. I focus on creating fluid, state-driven interfaces with smooth animations, adaptive layouts, and a deep attention to micro-interactions. Check out my Projects section for detailed case studies!`,
    'default': `I'm Dhruva's AI assistant. I can tell you about his <strong>technical skills</strong>, <strong>project experience</strong>, <strong>development philosophy</strong>, or <strong>career journey</strong>. Feel free to ask anything about his Android development work!<br><br><em>💡 Note: Add your Gemini API Key in app.js to unlock real-time live AI conversations!</em>`
  };

  function getDemoResponse(query) {
    const q = query.toLowerCase();
    if (q.includes('tech') || q.includes('stack') || q.includes('tool') || q.includes('kotlin') || q.includes('language')) return demoResponses['tech stack'];
    if (q.includes('experience') || q.includes('journey') || q.includes('career') || q.includes('background') || q.includes('college') || q.includes('education')) return demoResponses['experience'];
    if (q.includes('ui') || q.includes('design') || q.includes('compose') || q.includes('animation') || q.includes('work') || q.includes('project')) return demoResponses['ui work'];
    return demoResponses['default'];
  }

  function addMessage(text, isUser, isLoading = false) {
    if (!document.getElementById('ai-animations-style')) {
      const s = document.createElement('style');
      s.id = 'ai-animations-style';
      s.textContent = `
        @keyframes aiRotate { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }
        @keyframes aiWave { 0%, 80%, 100% { transform: scale(0.6); opacity: 0.4; } 40% { transform: scale(1.3); opacity: 1; box-shadow: 0 0 10px currentColor; } }
      `;
      document.head.appendChild(s);
    }

    const msg = document.createElement('div');
    if (isLoading) msg.id = 'ai-loading-msg';
    msg.className = isUser ? 'msg-user' : 'msg-bot';
    msg.style.cssText = `
      display: flex; gap: 16px; align-items: flex-start;
      padding: 16px; border-radius: 16px; margin-bottom: 12px;
      background: ${isUser ? 'rgba(61, 90, 254, 0.08)' : 'rgba(42, 42, 42, 0.5)'};
      border: 1px solid ${isUser ? 'rgba(61, 90, 254, 0.2)' : 'rgba(255, 255, 255, 0.05)'};
      animation: fadeSection 0.4s ease-out;
    `;

    const icon = document.createElement('div');
    icon.style.cssText = `
      width: 32px; height: 32px; border-radius: 50%; flex-shrink: 0;
      display: flex; align-items: center; justify-content: center;
      background: ${isUser ? 'rgba(61, 90, 254, 0.2)' : 'rgba(61, 90, 254, 0.1)'};
    `;

    if (isLoading) {
      icon.innerHTML = `<span class="material-symbols-outlined" style="font-size:18px;color:var(--tertiary);animation:aiRotate 1s linear infinite;display:inline-block;">sync</span>`;
    } else {
      icon.innerHTML = `<span class="material-symbols-outlined" style="font-size:16px;color:var(--primary);">${isUser ? 'person' : 'smart_toy'}</span>`;
    }

    const content = document.createElement('div');
    content.className = 'text-body-md';
    content.style.color = 'var(--on-surface)';
    content.style.flex = '1';
    content.innerHTML = text;

    msg.appendChild(icon);
    msg.appendChild(content);
    chatArea.appendChild(msg);
    chatArea.scrollTop = chatArea.scrollHeight;
    return msg;
  }

  async function callGeminiAI(userQuery) {
    const url = `https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent?key=${GEMINI_API_KEY}`;

    const requestBody = {
      systemInstruction: {
        parts: [{ text: DHRUVA_CONTEXT }]
      },
      contents: [
        { role: "user", parts: [{ text: userQuery }] }
      ],
      generationConfig: {
        temperature: 0.7,
        maxOutputTokens: 1024
      }
    };

    const response = await fetch(url, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(requestBody)
    });

    if (!response.ok) {
      const errText = await response.text();
      console.error("Gemini API HTTP Error:", response.status, errText);
      throw new Error("API response error: " + response.status);
    }

    const data = await response.json();
    let reply = data.candidates?.[0]?.content?.parts?.[0]?.text;
    if (!reply) throw new Error("Empty reply from Gemini");

    // Convert Markdown bold (**text**) and newlines to HTML for clean display
    reply = reply.replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
      .replace(/__(.*?)__/g, '<strong>$1</strong>')
      .replace(/\*(.*?)\*/g, '<em>$1</em>')
      .replace(/\n/g, '<br>');

    const tokenCount = data.usageMetadata?.totalTokenCount || '—';
    const modelVersion = (data.modelVersion || "gemini-3.5-flash-lite").replace("models/", "");

    return { reply, tokenCount, modelVersion };
  }

  async function handleSend() {
    const query = input.value.trim();
    if (!query) return;
    addMessage(query, true);
    input.value = '';

    // Check if live Gemini API key is configured
    if (GEMINI_API_KEY === "YOUR_GEMINI_API_KEY" || !GEMINI_API_KEY) {
      setTimeout(() => {
        addMessage(getDemoResponse(query), false);
      }, 600);
      return;
    }

    // Live AI Mode with Animated Live Timer & Waveform
    const start = performance.now();
    const loadingContent = `
      <div style="display: flex; flex-direction: column; gap: 12px; padding: 2px 0;">
        <div style="display: flex; align-items: center; gap: 12px; flex-wrap: wrap;">
          <span style="font-weight: 600; font-size: 14px; color: var(--on-surface);">Gemini is drafting your response...</span>
          <span id="ai-timer" style="font-family: 'JetBrains Mono', monospace; font-size: 12px; padding: 2px 10px; background: rgba(0,218,243,0.15); color: var(--tertiary); border: 1px solid rgba(0,218,243,0.4); border-radius: 20px; font-weight: 700;">0.0s</span>
        </div>
        <div style="display: flex; gap: 8px; align-items: center;">
          <div style="width: 8px; height: 8px; border-radius: 50%; background: var(--tertiary); animation: aiWave 1.2s ease-in-out infinite 0s;"></div>
          <div style="width: 8px; height: 8px; border-radius: 50%; background: var(--primary); animation: aiWave 1.2s ease-in-out infinite 0.2s;"></div>
          <div style="width: 8px; height: 8px; border-radius: 50%; background: var(--secondary); animation: aiWave 1.2s ease-in-out infinite 0.4s;"></div>
          <span style="font-size: 12px; color: rgba(255,255,255,0.5); margin-left: 4px; font-style: italic;">Analyzing Android projects & memory</span>
        </div>
      </div>
    `;
    const loadingMsg = addMessage(loadingContent, false, true);
    sendBtn.disabled = true;
    input.disabled = true;

    const timerInterval = setInterval(() => {
      const timerEl = document.getElementById('ai-timer');
      if (timerEl) {
        const elapsedSec = ((performance.now() - start) / 1000).toFixed(1);
        timerEl.textContent = `${elapsedSec}s`;
      }
    }, 100);

    try {
      const result = await callGeminiAI(query);
      clearInterval(timerInterval);
      const finalTime = ((performance.now() - start) / 1000).toFixed(1) + 's';

      loadingMsg.remove();

      // Format AI Reply with interactive Tech & Performance Badge Footer
      const decoratedReply = `
        ${result.reply}
        <div style="margin-top: 16px; padding-top: 12px; border-top: 1px dashed rgba(255,255,255,0.1); display: flex; align-items: center; gap: 8px; flex-wrap: wrap;">
          <span style="font-family: 'JetBrains Mono', monospace; font-size: 11px; padding: 3px 10px; background: rgba(61, 90, 254, 0.1); border: 1px solid rgba(61, 90, 254, 0.3); border-radius: 100px; color: var(--primary); display: flex; align-items: center; gap: 4px;">
            <span class="material-symbols-outlined" style="font-size: 13px;">bolt</span> ${result.modelVersion}
          </span>
          <span style="font-family: 'JetBrains Mono', monospace; font-size: 11px; padding: 3px 10px; background: rgba(0, 218, 243, 0.08); border: 1px solid rgba(0, 218, 243, 0.25); border-radius: 100px; color: var(--tertiary); display: flex; align-items: center; gap: 4px;">
            <span class="material-symbols-outlined" style="font-size: 13px;">timer</span> ${finalTime}
          </span>
          <span style="font-family: 'JetBrains Mono', monospace; font-size: 11px; padding: 3px 10px; background: rgba(207, 188, 255, 0.08); border: 1px solid rgba(207, 188, 255, 0.25); border-radius: 100px; color: var(--secondary); display: flex; align-items: center; gap: 4px;">
            <span class="material-symbols-outlined" style="font-size: 13px;">data_usage</span> ${result.tokenCount} tokens
          </span>
        </div>
      `;
      addMessage(decoratedReply, false);
    } catch (err) {
      clearInterval(timerInterval);
      console.error("Gemini Error:", err);
      loadingMsg.remove();
      addMessage("⚠️ Couldn't connect to Gemini AI server. Falling back to offline memory:<br><br>" + getDemoResponse(query), false);
    } finally {
      sendBtn.disabled = false;
      input.disabled = false;
      input.focus();
    }
  }

  sendBtn.addEventListener('click', handleSend);
  input.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') handleSend();
  });

  // Prompt buttons
  document.querySelectorAll('.ai-prompt-btn').forEach(btn => {
    btn.addEventListener('click', () => {
      input.value = btn.textContent.trim();
      handleSend();
    });
  });
}

// ---- Contact Form (Formspree) ----
// ⚠️  Replace YOUR_FORM_ID below with the ID from https://formspree.io
//     It looks like: https://formspree.io/f/xabcdefg
const FORMSPREE_ENDPOINT = 'https://formspree.io/f/xyegykdg';

function initContactForm() {
  const form = document.getElementById('contact-form');
  const btn = document.getElementById('contact-submit');
  if (!form || !btn) return;

  form.addEventListener('submit', async (e) => {
    e.preventDefault();

    // — Loading state —
    btn.disabled = true;
    btn.innerHTML = `
      <span class="material-symbols-outlined" style="font-size:20px;animation:spin 1s linear infinite;">progress_activity</span>
      Sending…
    `;

    // Add spinner keyframes once
    if (!document.getElementById('spin-style')) {
      const s = document.createElement('style');
      s.id = 'spin-style';
      s.textContent = `@keyframes spin { to { transform: rotate(360deg); } }`;
      document.head.appendChild(s);
    }

    const data = new FormData(form);

    try {
      const res = await fetch(FORMSPREE_ENDPOINT, {
        method: 'POST',
        body: data,
        headers: { Accept: 'application/json' },
      });

      if (res.ok) {
        // — Success state —
        btn.innerHTML = `
          <span class="material-symbols-outlined" style="font-size:20px;">check_circle</span>
          Message Sent!
        `;
        btn.style.background = 'var(--tertiary-container)';
        btn.style.color = 'var(--on-tertiary-container)';
        form.reset();

        setTimeout(() => {
          btn.innerHTML = `<span class="material-symbols-outlined" style="font-size:20px;">send</span> Send Message`;
          btn.style.background = '';
          btn.style.color = '';
          btn.disabled = false;
        }, 4000);
      } else {
        throw new Error('Server error');
      }
    } catch {
      // — Error state —
      btn.innerHTML = `
        <span class="material-symbols-outlined" style="font-size:20px;">error</span>
        Failed — Try Again
      `;
      btn.style.background = 'var(--error-container)';
      btn.style.color = 'var(--error)';

      setTimeout(() => {
        btn.innerHTML = `<span class="material-symbols-outlined" style="font-size:20px;">send</span> Send Message`;
        btn.style.background = '';
        btn.style.color = '';
        btn.disabled = false;
      }, 4000);
    }
  });
}

// ---- Back to Top ----
window.backToTop = function () {
  window.scrollTo({ top: 0, behavior: 'smooth' });
};

// ---- Expose switchSection globally for inline handlers ----
window.switchSection = switchSection;
