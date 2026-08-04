/* ============================================================
   Synthetic Noir — WebGL Mesh Gradient Shader
   ============================================================ */

export function initShader() {
  const container = document.getElementById('shader-bg');
  if (!container) return;

  const canvas = document.createElement('canvas');
  canvas.id = 'shader-canvas';
  canvas.style.display = 'block';
  canvas.style.width = '100%';
  canvas.style.height = '100%';
  container.appendChild(canvas);

  function syncSize() {
    const w = canvas.clientWidth || 1280;
    const h = canvas.clientHeight || 720;
    if (canvas.width !== w || canvas.height !== h) {
      canvas.width = w;
      canvas.height = h;
    }
  }

  if (typeof ResizeObserver !== 'undefined') {
    new ResizeObserver(syncSize).observe(canvas);
  }
  syncSize();

  const gl = canvas.getContext('webgl') || canvas.getContext('experimental-webgl');
  if (!gl) return;

  // Vertex shader
  const vertexSource = `
    attribute vec2 a_position;
    varying vec2 v_texCoord;
    void main() {
      v_texCoord = a_position * 0.5 + 0.5;
      gl_Position = vec4(a_position, 0.0, 1.0);
    }
  `;

  // Fragment shader — organic mesh gradient
  const fragmentSource = `
    precision highp float;
    varying vec2 v_texCoord;
    uniform float u_time;
    uniform vec2 u_resolution;
    uniform vec2 u_mouse;

    void main() {
      vec2 uv = v_texCoord;

      float t = u_time * 0.4;

      // Color palette
      vec3 electricBlue = vec3(0.039, 0.353, 1.0);
      vec3 deepPurple   = vec3(0.384, 0.0, 0.918);
      vec3 nearBlack    = vec3(0.039, 0.039, 0.039);

      // Animated gradient centers using sine/cosine
      float dist1 = length(uv - vec2(0.5 + 0.3 * sin(t), 0.5 + 0.3 * cos(t * 0.8)));
      float dist2 = length(uv - vec2(0.2 + 0.2 * cos(t * 1.2), 0.8 + 0.2 * sin(t * 0.9)));
      float dist3 = length(uv - vec2(0.8 + 0.2 * sin(t * 0.7), 0.2 + 0.2 * cos(t * 1.1)));

      // Mouse influence (subtle)
      vec2 mouseNorm = u_mouse / u_resolution;
      float distMouse = length(uv - mouseNorm);
      float mouseInfluence = smoothstep(0.5, 0.0, distMouse) * 0.15;

      // Mix colors based on distance
      vec3 color = mix(nearBlack, electricBlue, smoothstep(0.8, 0.0, dist1));
      color = mix(color, deepPurple, smoothstep(0.7, 0.0, dist2) * 0.5);
      color = mix(color, electricBlue, smoothstep(0.6, 0.0, dist3) * 0.3);
      color += electricBlue * mouseInfluence;

      // Subtle grain noise
      float noise = fract(sin(dot(uv + u_time * 0.01, vec2(12.9898, 78.233))) * 43758.5453);
      color += noise * 0.02;

      gl_FragColor = vec4(color, 1.0);
    }
  `;

  function compileShader(type, source) {
    const shader = gl.createShader(type);
    gl.shaderSource(shader, source);
    gl.compileShader(shader);
    if (!gl.getShaderParameter(shader, gl.COMPILE_STATUS)) {
      console.error('Shader compile error:', gl.getShaderInfoLog(shader));
      gl.deleteShader(shader);
      return null;
    }
    return shader;
  }

  const vertexShader = compileShader(gl.VERTEX_SHADER, vertexSource);
  const fragmentShader = compileShader(gl.FRAGMENT_SHADER, fragmentSource);
  if (!vertexShader || !fragmentShader) return;

  const program = gl.createProgram();
  gl.attachShader(program, vertexShader);
  gl.attachShader(program, fragmentShader);
  gl.linkProgram(program);

  if (!gl.getProgramParameter(program, gl.LINK_STATUS)) {
    console.error('Program link error:', gl.getProgramInfoLog(program));
    return;
  }

  gl.useProgram(program);

  // Fullscreen quad
  const buffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, buffer);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array([-1, -1, 1, -1, -1, 1, 1, 1]), gl.STATIC_DRAW);

  const posAttr = gl.getAttribLocation(program, 'a_position');
  gl.enableVertexAttribArray(posAttr);
  gl.vertexAttribPointer(posAttr, 2, gl.FLOAT, false, 0, 0);

  const uTime = gl.getUniformLocation(program, 'u_time');
  const uRes = gl.getUniformLocation(program, 'u_resolution');
  const uMouse = gl.getUniformLocation(program, 'u_mouse');

  let mouse = { x: canvas.width / 2, y: canvas.height / 2 };

  window.addEventListener('mousemove', (e) => {
    const rect = canvas.getBoundingClientRect();
    if (rect.width && rect.height) {
      mouse.x = ((e.clientX - rect.left) / rect.width) * canvas.width;
      mouse.y = (1.0 - (e.clientY - rect.top) / rect.height) * canvas.height;
    }
  });

  function render(timestamp) {
    if (typeof ResizeObserver === 'undefined') syncSize();
    gl.viewport(0, 0, canvas.width, canvas.height);
    if (uTime) gl.uniform1f(uTime, timestamp * 0.001);
    if (uRes) gl.uniform2f(uRes, canvas.width, canvas.height);
    if (uMouse) gl.uniform2f(uMouse, mouse.x, mouse.y);
    gl.drawArrays(gl.TRIANGLE_STRIP, 0, 4);
    requestAnimationFrame(render);
  }

  requestAnimationFrame(render);
}
