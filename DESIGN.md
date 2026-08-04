# DESIGN.md — Synthetic Noir Design System
## Dhruva AI Android Portfolio

---

## Brand & Style

This design system embodies a premium, high-tech aesthetic tailored for developer portfolios and AI-centric products. The visual narrative is built upon a **"Deep Space"** concept—using a near-black foundation to allow vibrant, electric accents to pop with luminous intensity.

The style is a refined evolution of **Glassmorphism**, moving away from chaotic transparency toward structured, functional translucency. It leverages Jetpack Compose-inspired depth, emphasizing smooth state transitions and a sense of physical weightlessness. The emotional response should be one of **"Technical Sophistication"**—feeling both cutting-edge and meticulously polished.

---

## Color Palette

### Core Background
| Token                     | Hex       | Role                                |
|---------------------------|-----------|-------------------------------------|
| `--background`            | `#131313` | Main app background                 |
| `--surface`               | `#131313` | Surface base                        |
| `--surface-dim`           | `#131313` | Dimmed surface                      |
| `--surface-bright`        | `#3A3939` | Bright surface for elevated areas   |
| `--surface-container-lowest`  | `#0E0E0E` | Deepest container level         |
| `--surface-container-low`     | `#1C1B1B` | Low container level             |
| `--surface-container`         | `#201F1F` | Default container level         |
| `--surface-container-high`    | `#2A2A2A` | High container level            |
| `--surface-container-highest` | `#353534` | Highest container level         |
| `--surface-variant`           | `#353534` | Variant surface for chips/tags  |

### Primary (Electric Blue)
| Token                      | Hex       | Role                                |
|----------------------------|-----------|-------------------------------------|
| `--primary`                | `#BBC3FF` | Primary text/icon accent            |
| `--primary-container`      | `#3D5AFE` | Primary action fill (buttons, CTA)  |
| `--on-primary`             | `#001D93` | Text on primary backgrounds         |
| `--on-primary-container`   | `#F1F0FF` | Text on primary containers          |
| `--inverse-primary`        | `#2848EE` | Hover state / inverted              |

### Secondary (Deep Purple)
| Token                      | Hex       | Role                                |
|----------------------------|-----------|-------------------------------------|
| `--secondary`              | `#CFBCFF` | Secondary accent text               |
| `--secondary-container`    | `#6200EA` | Secondary container fill            |
| `--on-secondary`           | `#3A0092` | Text on secondary backgrounds       |
| `--on-secondary-container` | `#CFBCFF` | Text on secondary containers        |

### Tertiary (Neon Cyan)
| Token                      | Hex       | Role                                |
|----------------------------|-----------|-------------------------------------|
| `--tertiary`               | `#00DAF3` | Status indicators, neon glow        |
| `--tertiary-container`     | `#007987` | Tertiary container                  |
| `--on-tertiary`            | `#00363D` | Text on tertiary backgrounds        |

### Semantic
| Token                       | Hex       | Role                               |
|-----------------------------|-----------|-------------------------------------|
| `--on-surface`              | `#E5E2E1` | Default text on surface             |
| `--on-surface-variant`      | `#C5C5D9` | Secondary text / descriptions       |
| `--outline`                 | `#8E8FA2` | Borders, dividers                   |
| `--outline-variant`         | `#444656` | Subtle borders                      |
| `--error`                   | `#FFB4AB` | Error state                         |

### Glassmorphism
| Token                | Value                         | Role                      |
|----------------------|-------------------------------|---------------------------|
| Glass Background     | `rgba(255, 255, 255, 0.03)`   | Card fill                 |
| Glass Border         | `rgba(255, 255, 255, 0.10)`   | 1px edge stroke           |
| Backdrop Blur        | `blur(16px)`                  | Frosted glass effect      |
| Canvas Base          | `#0A0A0A`                     | Deepest background        |

---

## Typography

| Style              | Family           | Size   | Weight | Line Height | Letter Spacing |
|--------------------|------------------|--------|--------|-------------|----------------|
| `display-lg`       | Inter            | 48px   | 700    | 56px        | -0.02em        |
| `display-lg-mobile`| Inter            | 32px   | 700    | 40px        | -0.02em        |
| `headline-md`      | Inter            | 24px   | 600    | 32px        | -0.01em        |
| `body-lg`          | Inter            | 18px   | 400    | 28px        | —              |
| `body-md`          | Inter            | 16px   | 400    | 24px        | —              |
| `label-sm`         | JetBrains Mono   | 12px   | 500    | 16px        | 0.05em         |
| `code-snippet`     | JetBrains Mono   | 14px   | 400    | 20px        | —              |

**Font Sources:**
- Inter: `https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700`
- JetBrains Mono: `https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@400;500`

---

## Layout & Spacing

### Grid System (8px base)
| Token            | Value    |
|------------------|----------|
| `--spacing-xs`   | 4px      |
| `--spacing-base` | 8px      |
| `--spacing-sm`   | 12px     |
| `--spacing-md`   | 24px     |
| `--spacing-lg`   | 40px     |
| `--spacing-xl`   | 64px     |
| `--container-max`| 1280px   |
| `--gutter`       | 24px     |

### Border Radius
| Token               | Value    |
|----------------------|----------|
| `--rounded-sm`       | 0.25rem  |
| `--rounded-default`  | 0.5rem   |
| `--rounded-md`       | 0.75rem  |
| `--rounded-lg`       | 1rem     |
| `--rounded-xl`       | 1.5rem   |
| `--rounded-full`     | 9999px   |

### Responsive Breakpoints
- **Desktop** (≥768px): 12-column grid, 80px fixed side nav rail, 24px gutters, 1280px max container
- **Mobile** (<768px): 4-column grid, 16px margins, 64px fixed bottom nav bar

---

## Elevation & Depth

| Level | Name          | Style                                                                  |
|-------|---------------|------------------------------------------------------------------------|
| 0     | Base          | `#0A0A0A` flat background                                             |
| 1     | Sub-surface   | Slightly lighter fills (`surface-container-low`)                       |
| 2     | Glass         | `backdrop-filter: blur(16px)`, 1px `white/10` border, 5% primary glow |
| 3     | Interaction   | Animated gradient "shimmer" border on hover                            |

---

## Components

### Floating Navigation Rail (Desktop)
- Width: 80px, fixed left, full height
- Background: `background/80` with `backdrop-blur-xl`, right border `white/10`
- Icons: 48×48 touch targets, centered
- Active state: `primary-container` fill, `on-primary-container` icon, rounded-full, glow shadow `0 0 15px rgba(187,195,255,0.4)`

### Bottom Navigation Bar (Mobile)
- Height: 64px, fixed bottom, full width
- Background: `background/60` with `backdrop-blur-md`, top border `white/10`
- Active tab: `secondary-container/30` fill, `primary` icon, rounded-xl

### Glass Cards
- Background: `rgba(255, 255, 255, 0.03)` or `rgba(255, 255, 255, 0.02)`
- Backdrop blur: 16px
- Border: 1px `rgba(255, 255, 255, 0.1)`
- Border radius: 24px–32px (xl–2xl)
- Hover: border transitions to `primary/30`, subtle blue glow

### Buttons
- **Primary**: `#3D5AFE` fill, white text, rounded-xl, hover → `#2848EE` + glow
- **Secondary/Glass**: transparent, 1px `white/10` border, hover → blue glow
- **Tertiary**: text-only, underline on hover in neon blue

### Tech Chips
- Font: JetBrains Mono `code-snippet`
- Background: `rgba(255,255,255,0.05)`, 1px `white/10` border
- Border-radius: 100px (pill)
- Hover: `primary/10` bg, `primary/50` border, primary text + soft glow

### Top App Bar
- Fixed, full width, transparent background with `backdrop-blur-sm`
- Logo text: headline-md, primary color, black weight, tight tracking
- Right: dark mode + profile icon buttons

---

## Shader Background

WebGL GLSL fragment shader rendering an organic, flowing mesh gradient:
- Color 1: Electric Blue `rgb(0.039, 0.353, 1.0)` 
- Color 2: Deep Purple `rgb(0.384, 0.0, 0.918)`
- Color 3: Near Black `rgb(0.039, 0.039, 0.039)`
- Animated sine/cosine wave centers
- Subtle procedural grain noise overlay
- Canvas: fixed, full viewport, z-index -1, 50% opacity
