function esc(s) {
  return String(s)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;');
}

function linkify(html) {
  return html.replace(/https?:\/\/[^\s<>"]+/g, url => {
    const stripped = url.replace(/[.,;:!?)]+$/, '');
    const tail = url.slice(stripped.length);
    return `<a class="modal-link" href="${stripped}" target="_blank" rel="noopener">${stripped} ↗</a>${tail}`;
  });
}

function formatContent(text) {
  const chunks = text
    .replace(/([.)]\s*)(LINK|TYPE|REPO|BESKRIVELSE|STACK|HVAD JEG LÆRTE|UDDANNELSE|TECH STACK|ERHVERVSERFARING):/g, '$1\n\n$2:')
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
        + (rest ? `<p class="modal-para">${linkify(esc(rest))}</p>` : '');
    }
    return `<p class="modal-para">${linkify(esc(chunk))}</p>`;
  }).join('\n');
}

function openCv() {
  const body = `
    <p class="modal-para" style="color:var(--muted)">Download mit CV som PDF.</p>
    <a class="cv-download" href="/cv.pdf" download="Nima_Salami_CV.pdf">Download CV ↓</a>
    <embed src="/cv.pdf#pagemode=none&view=FitH" type="application/pdf" style="width:100%;height:60vh;margin-top:1.5rem;border:1px solid var(--border)">
  `;
  openModal('CV – Nima Salami', body);
}

async function init() {
  try {
    const talents = await fetch('/talent').then(r => r.json());
    const talent  = talents[0];
    renderHero(talent);

    const docs = await fetch(`/talent/${talent.id}/documents`).then(r => r.json());
    renderDocs(docs, talent.id);

    const andreas = talents.find(t => t.name === 'Andreas Gabel');
    if (andreas) renderPartnerEndpoint(andreas);
  } catch (e) {
    console.error('Init fejlede:', e);
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
    ${t.profile_text ? `<p class="hero-bio">${esc(t.profile_text)}</p>` : ''}
    ${metaItems.length ? `<div class="hero-meta">${metaItems.map(i => `<span class="hero-meta-item">${i}</span>`).join('')}</div>` : ''}
    <div class="hero-links">
      ${links.join('')}
      <button class="cv-btn" onclick="openCv()">Downlaod CV ↓</button>
    </div>
    <a class="doc-endpoint" href="/talent" style="margin-top:1.25rem">GET /talent ↗</a>
    <a class="doc-endpoint" href="/talent/${esc(t.id)}" style="margin-top:0.4rem">GET /talent/${esc(t.id.slice(0, 8))}… ↗</a>
  `);
}

function renderPartnerEndpoint(t) {
  const card = document.querySelector('.partner-card');
  if (!card) return;
  card.insertAdjacentHTML('afterend',
    `<a class="doc-endpoint" href="/talent/${esc(t.id)}" target="_blank" rel="noopener" style="margin-top:0.75rem">GET /talent/${esc(t.id.slice(0, 8))}… ↗</a>`
  );
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
    el.querySelector('.doc-endpoint').addEventListener('click', e => {
      e.stopPropagation();
      e.preventDefault();
      openEndpoint(e.currentTarget.getAttribute('href'));
    });
    list.appendChild(el);
  });

  const section = document.getElementById('docs-section');
  section.insertAdjacentHTML('beforeend',
    `<div style="text-align:center;display:flex;flex-direction:column;align-items:center;justify-content:center;padding:1.5rem 0 0;margin-bottom:-1.5rem">
       <p style="font-size:var(--text-xs);color:var(--muted);letter-spacing:0.08em">Se alle dokumenter som rå JSON via API'et:</p>
       <a class="doc-endpoint" href="/talent/${esc(talentId)}/documents" style="margin-top:0.4rem">GET /talent/${talentId.slice(0, 8)}…/documents ↗</a>
     </div>`
  );
}

async function openDoc(talentId, docId) {
  try {
    const r = await fetch(`/talent/${talentId}/documents/${docId}`);
    if (!r.ok) throw new Error(`HTTP ${r.status}`);
    const doc = await r.json();
    openModal(doc.name, formatContent(doc.content || ''));
  } catch (e) {
    console.error('Kunne ikke hente dokument:', e);
    openModal('Fejl', '<p class="modal-para">Dokumentet kunne ikke hentes.</p>');
  }
}

async function openEndpoint(url) {
  try {
    const r = await fetch(url);
    if (!r.ok) throw new Error(`HTTP ${r.status}`);
    const data = await r.json();
    const json = JSON.stringify(data, null, 2);
    const html = `<pre class="modal-json">${esc(json)}</pre>`;
    openModal(url, html, url);
  } catch (e) {
    console.error('Kunne ikke hente endpoint:', e);
    openModal('Fejl', '<p class="modal-para">Endpoint kunne ikke hentes.</p>');
  }
}

let toastTimer = null;

function showToast() {
  const toast = document.getElementById('toast');
  clearTimeout(toastTimer);
  toast.classList.add('show');
  toastTimer = setTimeout(() => toast.classList.remove('show'), 5000);
}

function openModal(title, bodyHtml, openTabUrl = null) {
  document.getElementById('modal-title').textContent = title;
  document.getElementById('modal-body').innerHTML = bodyHtml;
  const tabLink = document.getElementById('modal-open-tab');
  const modal = document.querySelector('.modal');
  if (openTabUrl) {
    tabLink.href = openTabUrl;
    tabLink.hidden = false;
    modal.classList.add('modal--api');
  } else {
    tabLink.hidden = true;
    modal.classList.remove('modal--api');
  }
  document.getElementById('modal-overlay').classList.add('open');
  document.body.style.overflow = 'hidden';
}

function closeModal() {
  document.getElementById('modal-overlay').classList.remove('open');
  document.body.style.overflow = '';
  showToast();
}

document.getElementById('modal-close').addEventListener('click', closeModal);
document.getElementById('modal-overlay').addEventListener('click', e => {
  if (e.target === e.currentTarget) closeModal();
});
document.addEventListener('keydown', e => { if (e.key === 'Escape') closeModal(); });

document.addEventListener('click', e => {
  const link = e.target.closest('.doc-endpoint');
  if (!link) return;
  e.preventDefault();
  openEndpoint(link.getAttribute('href'));
});

init();

// Toast close button
document.getElementById('toast-close').addEventListener('click', () => {
  clearTimeout(toastTimer);
  document.getElementById('toast').classList.remove('show');
});


