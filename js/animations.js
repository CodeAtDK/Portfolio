/* ============================================================
   Synthetic Noir — Animations
   Premium motion layer for the portfolio
   ============================================================ */

// ---- 1. Cursor Glow (ambient light follower) ----
function initCursorGlow() {
  const glow = document.createElement('div');
  glow.id = 'cursor-glow';
  glow.style.cssText = `
    position: fixed;
    width: 400px;
    height: 400px;
    border-radius: 50%;
    pointer-events: none;
    z-index: 0;
    background: radial-gradient(circle, rgba(61,90,254,0.08) 0%, rgba(0,218,243,0.03) 40%, transparent 70%);
    transform: translate(-50%, -50%);
    transition: opacity 0.3s ease;
    will-change: transform;
  `;
  document.body.appendChild(glow);

  let mouseX = window.innerWidth / 2;
  let mouseY = window.innerHeight / 2;
  let glowX = mouseX;
  let glowY = mouseY;

  document.addEventListener('mousemove', (e) => {
    mouseX = e.clientX;
    mouseY = e.clientY;
  });

  document.addEventListener('mouseleave', () => {
    glow.style.opacity = '0';
  });

  document.addEventListener('mouseenter', () => {
    glow.style.opacity = '1';
  });

  function animateGlow() {
    glowX += (mouseX - glowX) * 0.06;
    glowY += (mouseY - glowY) * 0.06;
    glow.style.left = glowX + 'px';
    glow.style.top = glowY + 'px';
    requestAnimationFrame(animateGlow);
  }
  animateGlow();
}

// ---- 2. Scroll Progress Bar ----
function initScrollProgress() {
  const bar = document.createElement('div');
  bar.id = 'scroll-progress';
  bar.style.cssText = `
    position: fixed;
    top: 0;
    left: 0;
    height: 2px;
    width: 0%;
    z-index: 9999;
    background: linear-gradient(to right, var(--primary-container), var(--tertiary), var(--secondary));
    box-shadow: 0 0 8px rgba(61,90,254,0.6), 0 0 20px rgba(0,218,243,0.3);
    transition: width 0.1s linear;
    border-radius: 0 2px 2px 0;
  `;
  document.body.appendChild(bar);

  window.addEventListener('scroll', () => {
    const scrollTop = window.scrollY;
    const docHeight = document.documentElement.scrollHeight - window.innerHeight;
    const progress = docHeight > 0 ? (scrollTop / docHeight) * 100 : 0;
    bar.style.width = progress + '%';
  });
}

// ---- 3. 3D Card Tilt on Hover ----
function initCardTilt() {
  const cards = document.querySelectorAll(
    '.project-card, .glass-panel, .experience-card, .timeline__card'
  );

  cards.forEach(card => {
    card.addEventListener('mousemove', (e) => {
      const rect = card.getBoundingClientRect();
      const x = e.clientX - rect.left;
      const y = e.clientY - rect.top;
      const centerX = rect.width / 2;
      const centerY = rect.height / 2;
      const rotateX = ((y - centerY) / centerY) * -5;
      const rotateY = ((x - centerX) / centerX) * 5;

      card.style.transform = `perspective(1000px) rotateX(${rotateX}deg) rotateY(${rotateY}deg) translateZ(4px)`;
      card.style.transition = 'transform 0.1s ease';

      // Inner glow follows cursor
      const glowX = (x / rect.width) * 100;
      const glowY = (y / rect.height) * 100;
      card.style.setProperty('--mouse-x', glowX + '%');
      card.style.setProperty('--mouse-y', glowY + '%');
    });

    card.addEventListener('mouseleave', () => {
      card.style.transform = '';
      card.style.transition = 'transform 0.5s ease, box-shadow 0.3s ease';
    });
  });
}

// ---- 4. Staggered Fade-In for Chips / Grid items ----
function initStaggeredReveal() {
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        const children = entry.target.querySelectorAll('.tech-chip, .project-card, .glass-panel, .timeline__item, .experience-card');
        children.forEach((child, i) => {
          child.style.animationDelay = `${i * 80}ms`;
          child.classList.add('stagger-in');
        });
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.1 });

  document.querySelectorAll('.bento-grid, .grid-2, .grid-3, .timeline__items, .flex.flex-col.gap-8').forEach(el => {
    observer.observe(el);
  });
}

// ---- 5. Typewriter effect for hero heading ----
function initTypewriter() {
  const heading = document.getElementById('hero-heading');
  if (!heading) return;

  const originalHTML = heading.innerHTML;
  const words = ['Intelligent', 'Fluid', 'Powerful'];
  let wordIndex = 0;
  let isDeleting = false;
  let charIndex = words[0].length; // start fully written

  const span = heading.querySelector('.text-primary');
  if (!span) return;

  function tick() {
    const currentWord = words[wordIndex];

    if (!isDeleting) {
      span.textContent = currentWord.slice(0, charIndex);
      charIndex++;
      if (charIndex > currentWord.length) {
        isDeleting = true;
        setTimeout(tick, 2000); // pause before deleting
        return;
      }
    } else {
      span.textContent = currentWord.slice(0, charIndex);
      charIndex--;
      if (charIndex < 0) {
        isDeleting = false;
        wordIndex = (wordIndex + 1) % words.length;
        charIndex = 0;
        setTimeout(tick, 300);
        return;
      }
    }

    const speed = isDeleting ? 60 : 100;
    setTimeout(tick, speed);
  }

  // Start after 3 seconds
  setTimeout(tick, 3000);
}

// ---- 6. Timeline Line Draw Animation ----
function initTimelineDraw() {
  const line = document.querySelector('.timeline__line');
  if (!line) return;

  line.style.background = 'none';
  line.style.backgroundImage = 'linear-gradient(to bottom, var(--primary-container), var(--surface-variant), transparent)';
  line.style.backgroundSize = '100% 0%';
  line.style.backgroundRepeat = 'no-repeat';
  line.style.transition = 'background-size 1.5s ease-out';

  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        setTimeout(() => {
          line.style.backgroundSize = '100% 100%';
        }, 200);
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.1 });

  observer.observe(line.parentElement || line);
}

// ---- 7. Section Transition with slide ----
function initSectionTransitions() {
  const style = document.createElement('style');
  style.textContent = `
    .section {
      animation: none;
    }
    .section.slide-in-right {
      animation: slideInRight 0.45s cubic-bezier(0.22,1,0.36,1) both;
    }
    .section.slide-in-left {
      animation: slideInLeft 0.45s cubic-bezier(0.22,1,0.36,1) both;
    }
    @keyframes slideInRight {
      from { opacity: 0; transform: translateX(40px) scale(0.98); }
      to   { opacity: 1; transform: translateX(0) scale(1); }
    }
    @keyframes slideInLeft {
      from { opacity: 0; transform: translateX(-40px) scale(0.98); }
      to   { opacity: 1; transform: translateX(0) scale(1); }
    }

    /* Stagger in for child elements */
    @keyframes staggerIn {
      from { opacity: 0; transform: translateY(20px) scale(0.96); }
      to   { opacity: 1; transform: translateY(0) scale(1); }
    }
    .stagger-in {
      animation: staggerIn 0.5s cubic-bezier(0.22,1,0.36,1) both;
    }

    /* Magnetic button glow pulse */
    @keyframes btnPulse {
      0%   { box-shadow: 0 4px 12px rgba(61,90,254,0.2); }
      50%  { box-shadow: 0 0 28px rgba(61,90,254,0.5), 0 0 60px rgba(61,90,254,0.15); }
      100% { box-shadow: 0 4px 12px rgba(61,90,254,0.2); }
    }
    .btn-primary:hover {
      animation: btnPulse 1.5s ease-in-out infinite;
    }

    /* Animated gradient border on project cards */
    .project-card {
      position: relative;
    }
    .project-card::after {
      content: '';
      position: absolute;
      inset: -1px;
      border-radius: inherit;
      background: linear-gradient(135deg,
        rgba(61,90,254,0) 0%,
        rgba(61,90,254,0.6) 40%,
        rgba(0,218,243,0.4) 60%,
        rgba(61,90,254,0) 100%
      );
      background-size: 300% 300%;
      opacity: 0;
      z-index: -1;
      transition: opacity 0.4s ease;
      animation: borderRotate 3s linear infinite paused;
    }
    .project-card:hover::after {
      opacity: 1;
      animation-play-state: running;
    }
    @keyframes borderRotate {
      0%   { background-position: 0% 50%; }
      50%  { background-position: 100% 50%; }
      100% { background-position: 0% 50%; }
    }

    /* Glowing dot pulse on status badge */
    .status-badge__dot {
      animation: dotPulse 2s ease-in-out infinite;
    }
    @keyframes dotPulse {
      0%, 100% { box-shadow: 0 0 4px #4CAF50; transform: scale(1); }
      50% { box-shadow: 0 0 12px #4CAF50, 0 0 24px rgba(76,175,80,0.4); transform: scale(1.3); }
    }

    /* Nav item ripple effect */
    .nav-item {
      overflow: hidden;
    }
    .nav-item::after {
      content: '';
      position: absolute;
      inset: 0;
      border-radius: inherit;
      background: radial-gradient(circle, rgba(187,195,255,0.3) 0%, transparent 70%);
      transform: scale(0);
      transition: transform 0.4s ease;
      pointer-events: none;
    }
    .nav-item:hover::after {
      transform: scale(1);
    }

    /* Hero image shimmer */
    .hero__image {
      position: relative;
    }
    .hero__image::after {
      content: '';
      position: absolute;
      inset: 0;
      border-radius: inherit;
      background: linear-gradient(135deg, rgba(61,90,254,0.15), transparent, rgba(0,218,243,0.1));
      animation: heroShimmer 4s ease-in-out infinite;
    }
    @keyframes heroShimmer {
      0%, 100% { opacity: 0.3; }
      50% { opacity: 0.7; }
    }

    /* Floating particles background effect */
    .particle {
      position: fixed;
      border-radius: 50%;
      pointer-events: none;
      z-index: 0;
      animation: particleFloat linear infinite;
      opacity: 0;
    }
    @keyframes particleFloat {
      0%   { transform: translateY(100vh) translateX(0); opacity: 0; }
      10%  { opacity: 1; }
      90%  { opacity: 0.5; }
      100% { transform: translateY(-20px) translateX(var(--drift, 20px)); opacity: 0; }
    }

    /* Gradient text shimmer on headline */
    .gradient-shimmer {
      background: linear-gradient(90deg, var(--primary) 0%, var(--tertiary) 50%, var(--primary) 100%);
      background-size: 200% auto;
      -webkit-background-clip: text;
      -webkit-text-fill-color: transparent;
      background-clip: text;
      animation: textShimmer 4s linear infinite;
    }
    @keyframes textShimmer {
      to { background-position: 200% center; }
    }

    /* Card inner spotlight effect */
    .glass-panel {
      background-image: radial-gradient(
        circle at var(--mouse-x, 50%) var(--mouse-y, 50%),
        rgba(61,90,254,0.06) 0%,
        transparent 50%
      ),
      linear-gradient(145deg, rgba(32,31,31,0.4) 0%, rgba(14,14,14,0.2) 100%);
    }

    /* Bento card accent hover animation */
    .bento-grid .glass-panel {
      transition: transform 0.4s cubic-bezier(0.22,1,0.36,1), box-shadow 0.4s ease, border-color 0.4s ease;
    }
    .bento-grid .glass-panel:hover {
      transform: translateY(-6px) scale(1.01);
      box-shadow: 0 20px 60px rgba(61,90,254,0.15), 0 0 0 1px rgba(61,90,254,0.2);
    }

    /* Skill chip bounce in */
    @keyframes chipBounceIn {
      0%   { transform: scale(0.7) translateY(10px); opacity: 0; }
      60%  { transform: scale(1.1) translateY(-3px); opacity: 1; }
      100% { transform: scale(1) translateY(0); }
    }
    .chip-animate {
      animation: chipBounceIn 0.4s cubic-bezier(0.22,1,0.36,1) both;
    }

    /* Loading entrance for page */
    @keyframes pageEnter {
      from { opacity: 0; transform: translateY(8px); }
      to   { opacity: 1; transform: translateY(0); }
    }
    body {
      animation: pageEnter 0.6s ease-out both;
    }

    /* Top bar scroll shrink */
    .top-bar {
      transition: padding 0.3s ease, backdrop-filter 0.3s ease, box-shadow 0.3s ease;
    }
    .top-bar.scrolled {
      padding-top: 10px;
      padding-bottom: 10px;
      background: rgba(19,19,19,0.7);
      box-shadow: 0 4px 24px rgba(0,0,0,0.4);
      backdrop-filter: blur(20px);
      -webkit-backdrop-filter: blur(20px);
    }
  `;
  document.head.appendChild(style);
}

// ---- 8. Floating Particles ----
function initParticles() {
  const colors = ['rgba(61,90,254,0.5)', 'rgba(0,218,243,0.4)', 'rgba(207,188,255,0.3)'];

  function spawnParticle() {
    const p = document.createElement('div');
    p.className = 'particle';
    const size = Math.random() * 4 + 2;
    const duration = Math.random() * 12 + 10;
    const left = Math.random() * 100;
    const drift = (Math.random() - 0.5) * 100;
    const color = colors[Math.floor(Math.random() * colors.length)];
    const delay = Math.random() * 3;

    p.style.cssText = `
      width: ${size}px;
      height: ${size}px;
      left: ${left}%;
      background: ${color};
      box-shadow: 0 0 ${size * 3}px ${color};
      animation-duration: ${duration}s;
      animation-delay: ${delay}s;
      --drift: ${drift}px;
    `;
    document.body.appendChild(p);

    setTimeout(() => {
      p.remove();
      spawnParticle();
    }, (duration + delay) * 1000);
  }

  // Spawn 12 particles
  for (let i = 0; i < 12; i++) {
    setTimeout(spawnParticle, i * 800);
  }
}

// ---- 9. Magnetic Button Effect ----
function initMagneticButtons() {
  document.querySelectorAll('.btn-primary, .btn-secondary').forEach(btn => {
    btn.addEventListener('mousemove', (e) => {
      const rect = btn.getBoundingClientRect();
      const x = e.clientX - rect.left - rect.width / 2;
      const y = e.clientY - rect.top - rect.height / 2;
      btn.style.transform = `translate(${x * 0.15}px, ${y * 0.2}px) translateY(-2px)`;
    });

    btn.addEventListener('mouseleave', () => {
      btn.style.transform = '';
      btn.style.transition = 'transform 0.4s cubic-bezier(0.22,1,0.36,1), box-shadow 0.3s ease, background 0.3s ease';
    });
  });
}

// ---- 10. Top Bar Scroll Behavior ----
function initTopBarScroll() {
  const topBar = document.querySelector('.top-bar');
  if (!topBar) return;

  window.addEventListener('scroll', () => {
    if (window.scrollY > 20) {
      topBar.classList.add('scrolled');
    } else {
      topBar.classList.remove('scrolled');
    }
  }, { passive: true });
}

// ---- 11. Chip Stagger Animate on Reveal ----
function initChipAnimations() {
  const chipObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        const chips = entry.target.querySelectorAll('.tech-chip');
        chips.forEach((chip, i) => {
          setTimeout(() => {
            chip.classList.add('chip-animate');
          }, i * 60);
        });
        chipObserver.unobserve(entry.target);
      }
    });
  }, { threshold: 0.2 });

  document.querySelectorAll('.flex.flex-wrap').forEach(el => {
    chipObserver.observe(el);
  });
}

// ---- 12. Gradient shimmer on section headings ----
function initHeadingEffects() {
  document.querySelectorAll('h1.text-display-lg-mobile').forEach((h, i) => {
    if (i === 0) return; // skip hero (has typewriter)
    // wrap first word with gradient
    const text = h.innerHTML;
    if (!h.querySelector('.text-primary') && !h.querySelector('.gradient-shimmer')) {
      const firstWord = text.split(' ')[0];
      const rest = text.slice(firstWord.length);
      h.innerHTML = `<span class="gradient-shimmer">${firstWord}</span>${rest}`;
    }
  });
}

// ---- 13. Enhanced section switch with direction ----
const sectionOrder = ['home', 'about', 'skills', 'projects', 'experience', 'ai', 'contact'];

export function animateSectionSwitch(fromId, toId, targetEl) {
  const fromIdx = sectionOrder.indexOf(fromId);
  const toIdx = sectionOrder.indexOf(toId);
  const direction = toIdx > fromIdx ? 'slide-in-right' : 'slide-in-left';

  targetEl.classList.remove('slide-in-right', 'slide-in-left');
  // Force reflow
  void targetEl.offsetWidth;
  targetEl.classList.add(direction);

  // Stagger children after slide
  setTimeout(() => {
    initCardTilt();
    initMagneticButtons();
  }, 50);
}

// ---- Init All ----
export function initAnimations() {
  initSectionTransitions();
  initCursorGlow();
  initScrollProgress();
  initCardTilt();
  initMagneticButtons();
  initParticles();
  initTopBarScroll();
  initTypewriter();
  initStaggeredReveal();
  initChipAnimations();
  initHeadingEffects();
  initTimelineDraw();
}
