function esc(s) {
  return String(s)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;');
}

function formatContent(text) {
  const chunks = text
    .replace(/\.\s+(LINK|TYPE|REPO|BESKRIVELSE|STACK|HVAD JEG LÆRTE|UDDANNELSE|TECH STACK|ERHVERVSERFARING):/g, '.\n\n$1:')
    .split('\n\n')
    .map(s => s.trim())
    .filter(Boolean);

  return chunks.map(chunk => {
    const isHeader = /^[A-ZÆØÅ][A-ZÆØÅ\s–&-]+:/.test(chunk);
    if (isHeader) {
      const colon = chunk.indexOf(':');
      const label = chunk.slice(0, colon + 1);
      const rest  = chunk.slice(colon + 1).trim();
      return `<div class="modal-section-label">${esc(label)}</div>`
        + (rest ? `<p class="modal-para">${esc(rest)}</p>` : '');
    }
    return `<p class="modal-para">${esc(chunk)}</p>`;
  }).join('\n');
}

async function init() {
  try {
    const talents = await fetch('/talent').then(r => r.json());
    const talent  = talents[0];
    renderHero(talent);

    const docs = await fetch(`/talent/${talent.id}/documents`).then(r => r.json());
    renderDocs(docs, talent.id);
  } catch {
    document.getElementById('hero-loading').textContent = 'API ikke tilgængeligt.';
    document.getElementById('docs-loading').textContent = '';
  }
}

function renderHero(t) {
  document.getElementById('hero-loading').remove();
  const section = document.getElementById('hero-section');

  const metaItems = [
    t.city && t.country ? `${esc(t.city)}, ${esc(t.country)}` : null,
    t.phone ? esc(t.phone) : null,
    t.email ? `<a class="hero-link" href="mailto:${esc(t.email)}">${esc(t.email)}</a>` : null,
  ].filter(Boolean);

  const links = [
    t.github   ? `<a class="hero-link" href="${esc(t.github)}"   target="_blank" rel="noopener">GitHub ↗</a>` : '',
    t.linkedin ? `<a class="hero-link" href="${esc(t.linkedin)}" target="_blank" rel="noopener">LinkedIn ↗</a>` : '',
  ].filter(Boolean);

  section.insertAdjacentHTML('beforeend', `
    <h1 class="hero-name">${esc(t.name)}</h1>
    <p class="hero-title">${esc(t.title)}</p>
    ${t.profileText ? `<p class="hero-bio">${esc(t.profileText)}</p>` : ''}
    ${metaItems.length ? `<div class="hero-meta">${metaItems.map(i => `<span class="hero-meta-item">${i}</span>`).join('')}</div>` : ''}
    ${links.length    ? `<div class="hero-links">${links.join('')}</div>` : ''}
    <a class="doc-endpoint" href="/talent/${esc(t.id)}" target="_blank" rel="noopener" style="margin-top:1.25rem">GET /talent/${esc(t.id.slice(0, 8))}… ↗</a>
  `);
}

function renderDocs(docs, talentId) {
  const list = document.getElementById('doc-list');
  list.innerHTML = '';

  if (!docs.length) {
    list.innerHTML = '<p class="loading" style="animation:none">Ingen dokumenter fundet.</p>';
    return;
  }

  const shortId      = id => id.slice(0, 8) + '…';
  const endpointPath = (tId, dId) => `/talent/${tId}/documents/${dId}`;
  const endpointLabel = (tId, dId) =>
    `GET /talent/${shortId(tId)}/documents/${shortId(dId)}`;

  docs.forEach((doc, i) => {
    const excerpt = doc.content
      ? doc.content.replace(/\s+/g, ' ').slice(0, 110) + (doc.content.length > 110 ? '…' : '')
      : '';

    const el = document.createElement('div');
    el.className = 'doc-item';
    el.dataset.talentId = talentId;
    el.dataset.docId    = doc.id;
    el.innerHTML = `
      <span class="doc-num">${String(i + 1).padStart(2, '0')}</span>
      <div class="doc-body">
        <div class="doc-name">${esc(doc.name)}</div>
        ${excerpt ? `<div class="doc-excerpt">${esc(excerpt)}</div>` : ''}
        <a class="doc-endpoint" href="${esc(endpointPath(talentId, doc.id))}" target="_blank" rel="noopener">${esc(endpointLabel(talentId, doc.id))} ↗</a>
      </div>
      <span class="doc-arrow">→</span>
    `;
    el.addEventListener('click', () => openDoc(talentId, doc.id));
    el.querySelector('.doc-endpoint').addEventListener('click', e => e.stopPropagation());
    list.appendChild(el);
  });
}

async function openDoc(talentId, docId) {
  try {
    const doc = await fetch(`/talent/${talentId}/documents/${docId}`).then(r => r.json());
    document.getElementById('modal-title').textContent = doc.name;
    document.getElementById('modal-body').innerHTML = formatContent(doc.content || '');
    document.getElementById('modal-overlay').classList.add('open');
    document.body.style.overflow = 'hidden';
  } catch {
    /* silently ignore */
  }
}

function closeModal() {
  document.getElementById('modal-overlay').classList.remove('open');
  document.body.style.overflow = '';
}

document.getElementById('modal-close').addEventListener('click', closeModal);
document.getElementById('modal-overlay').addEventListener('click', e => {
  if (e.target === e.currentTarget) closeModal();
});
document.addEventListener('keydown', e => { if (e.key === 'Escape') closeModal(); });

init();
