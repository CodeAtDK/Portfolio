/* ============================================================
   Synthetic Noir — Application Logic
   Navigation, Scroll Reveal, Section Routing, Interactions
   ============================================================ */

import { initShader } from './shader.js';

// ---- Navigation Data ----
const NAV_ITEMS = [
  { id: 'home',       icon: 'home',      label: 'Home' },
  { id: 'about',      icon: 'person',    label: 'About' },
  { id: 'skills',     icon: 'code',      label: 'Skills' },
  { id: 'projects',   icon: 'terminal',  label: 'Projects' },
  { id: 'experience', icon: 'work',      label: 'Exp' },
  { id: 'ai',         icon: 'smart_toy', label: 'AI' },
  { id: 'contact',    icon: 'mail',      label: 'Contact' },
];

let activeSection = 'home';

// ---- Initialize App ----
document.addEventListener('DOMContentLoaded', () => {
  initShader();
  initNavigation();
  initScrollReveal();
  initAIWorkspace();
  initContactForm();

  // Handle hash navigation
  const hash = window.location.hash.slice(1);
  if (hash && NAV_ITEMS.find(n => n.id === hash)) {
    switchSection(hash);
  }
});

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
}

function switchSection(sectionId) {
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

// ---- AI Workspace Interaction ----
function initAIWorkspace() {
  const input = document.getElementById('ai-input');
  const sendBtn = document.getElementById('ai-send');
  const chatArea = document.getElementById('ai-chat');

  if (!input || !sendBtn || !chatArea) return;

  const responses = {
    'tech stack': `My primary tech stack revolves around <strong>Kotlin</strong> and the Android ecosystem. I use <strong>Jetpack Compose</strong> for UI, <strong>Coroutines + Flow</strong> for async work, <strong>Hilt</strong> for DI, <strong>Room</strong> for local persistence, and <strong>Retrofit</strong> for networking. I also integrate <strong>Firebase</strong> and <strong>Gemini AI</strong> into my apps.`,
    'experience': `I've been developing Android applications since <strong>August 2023</strong>. During this time, I've built several real-world apps including <strong>FoodBridge</strong> (food rescue platform), <strong>Agri Connect</strong> (farmer marketplace), and <strong>CareNest</strong> (elderly care). I'm currently exploring <strong>Kotlin Multiplatform</strong>.`,
    'ui work': `My UI work is centered on <strong>Jetpack Compose</strong> with Material 3 design principles. I focus on creating fluid, state-driven interfaces with smooth animations, adaptive layouts, and a deep attention to micro-interactions. Check out my Projects section for detailed case studies!`,
    'default': `I'm Dhruva's AI assistant. I can tell you about his <strong>technical skills</strong>, <strong>project experience</strong>, <strong>development philosophy</strong>, or <strong>career journey</strong>. Feel free to ask anything about his Android development work!`
  };

  function getResponse(query) {
    const q = query.toLowerCase();
    if (q.includes('tech') || q.includes('stack') || q.includes('tool')) return responses['tech stack'];
    if (q.includes('experience') || q.includes('journey') || q.includes('career')) return responses['experience'];
    if (q.includes('ui') || q.includes('design') || q.includes('compose')) return responses['ui work'];
    return responses['default'];
  }

  function addMessage(text, isUser) {
    const msg = document.createElement('div');
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
    icon.innerHTML = `<span class="material-symbols-outlined" style="font-size:16px;color:var(--primary);">${isUser ? 'person' : 'smart_toy'}</span>`;

    const content = document.createElement('div');
    content.className = 'text-body-md';
    content.style.color = 'var(--on-surface)';
    content.innerHTML = text;

    msg.appendChild(icon);
    msg.appendChild(content);
    chatArea.appendChild(msg);
    chatArea.scrollTop = chatArea.scrollHeight;
  }

  function handleSend() {
    const query = input.value.trim();
    if (!query) return;
    addMessage(query, true);
    input.value = '';
    
    // Simulate typing delay
    setTimeout(() => {
      addMessage(getResponse(query), false);
    }, 600);
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

// ---- Contact Form ----
function initContactForm() {
  const form = document.getElementById('contact-form');
  if (!form) return;

  form.addEventListener('submit', (e) => {
    e.preventDefault();
    const btn = form.querySelector('button[type="submit"]');
    const originalText = btn.innerHTML;
    btn.innerHTML = '<span class="material-symbols-outlined">check_circle</span> Message Sent!';
    btn.style.background = '#007987';
    
    setTimeout(() => {
      btn.innerHTML = originalText;
      btn.style.background = '';
      form.reset();
    }, 3000);
  });
}

// ---- Back to Top ----
window.backToTop = function() {
  window.scrollTo({ top: 0, behavior: 'smooth' });
};

// ---- Expose switchSection globally for inline handlers ----
window.switchSection = switchSection;
