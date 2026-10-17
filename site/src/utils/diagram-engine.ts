import mermaid from 'mermaid';

interface DiagramItem {
	type: 'class' | 'sequence' | 'general';
	title: string;
	code: string;
	svg?: string;
}

interface DiagramGroup {
	id: string;
	title: string;
	items: DiagramItem[];
	activeIndex: number;
	scale: number;
	panX: number;
	panY: number;
	isDragging: boolean;
	startX: number;
	startY: number;
}

let diagramCounter = 0;
const groupsMap = new Map<string, DiagramGroup>();

// Detect current theme
export function getIsLightTheme(): boolean {
	if (typeof document === 'undefined') return false;
	const theme = document.documentElement.getAttribute('data-theme');
	return theme === 'light';
}

// Configure and initialize Mermaid with technical blueprint colors
export function setupMermaid(isLight = false) {
	mermaid.initialize({
		startOnLoad: false,
		securityLevel: 'loose',
		fontFamily: "'JetBrains Mono', 'Plus Jakarta Sans', monospace",
		theme: 'base',
		themeVariables: isLight
			? {
				primaryColor: '#f1f5f9',
				primaryTextColor: '#0f172a',
				primaryBorderColor: '#0284c7',
				lineColor: '#0284c7',
				secondaryColor: '#f8fafc',
				tertiaryColor: '#e0f2fe',
				mainBkg: '#ffffff',
				nodeBorder: '#cbd5e1',
				clusterBkg: '#f8fafc',
				clusterBorder: '#cbd5e1',
				titleColor: '#0284c7',
				edgeLabelBackground: '#ffffff',
				actorBkg: '#f1f5f9',
				actorBorder: '#0284c7',
				actorTextColor: '#0f172a',
				actorLineColor: '#0284c7',
				signalColor: '#0284c7',
				signalTextColor: '#0f172a',
				labelBoxBkgColor: '#f1f5f9',
				labelBoxBorderColor: '#0284c7',
				labelTextColor: '#0f172a',
				loopTextColor: '#0f172a',
				noteBorderColor: '#f59e0b',
				noteBkgColor: '#fffbeb',
				noteTextColor: '#92400e',
				activationBorderColor: '#0284c7',
				activationBkgColor: '#e0f2fe',
				sequenceNumberColor: '#ffffff',
			}
			: {
				primaryColor: '#0d1322',
				primaryTextColor: '#f8fafc',
				primaryBorderColor: '#38bdf8',
				lineColor: '#38bdf8',
				secondaryColor: '#111726',
				tertiaryColor: '#182032',
				mainBkg: '#0d1322',
				nodeBorder: '#1f2a3f',
				clusterBkg: '#090d16',
				clusterBorder: '#1f2a3f',
				titleColor: '#38bdf8',
				edgeLabelBackground: '#0d1322',
				actorBkg: '#0f172a',
				actorBorder: '#38bdf8',
				actorTextColor: '#f8fafc',
				actorLineColor: '#38bdf8',
				signalColor: '#38bdf8',
				signalTextColor: '#cbd5e1',
				labelBoxBkgColor: '#111726',
				labelBoxBorderColor: '#1f2a3f',
				labelTextColor: '#38bdf8',
				loopTextColor: '#cbd5e1',
				noteBorderColor: '#f59e0b',
				noteBkgColor: '#1e1b10',
				noteTextColor: '#fbbf24',
				activationBorderColor: '#38bdf8',
				activationBkgColor: '#1e293b',
				sequenceNumberColor: '#070a12',
			},
	});
}

// Infer diagram type and default title from Mermaid code
export function inspectDiagram(code: string): { type: 'class' | 'sequence' | 'general'; title: string } {
	const trimmed = code.trim();
	if (trimmed.startsWith('classDiagram')) {
		return { type: 'class', title: '🏛️ UML Class Diagram (Participants & Contracts)' };
	}
	if (trimmed.startsWith('sequenceDiagram')) {
		return { type: 'sequence', title: '⚡ Interaction Lifecycle (Runtime Sequence)' };
	}
	return { type: 'general', title: '📐 Architectural Diagram' };
}

// Build the HTML DOM element for an interactive diagram group
export async function createInteractiveWidget(
	items: DiagramItem[],
	groupTitle?: string
): Promise<HTMLElement> {
	const groupId = `diag-group-${++diagramCounter}`;
	const isLight = getIsLightTheme();
	setupMermaid(isLight);

	// Pre-render all SVGs
	for (let i = 0; i < items.length; i++) {
		const item = items[i];
		const renderId = `${groupId}-svg-${i}`;
		try {
			const { svg } = await mermaid.render(renderId, item.code);
			item.svg = svg;
		} catch (err) {
			console.error(`Failed to render diagram [${renderId}]:`, err);
			item.svg = `<div class="diagram-error">Diagram render failed: ${err instanceof Error ? err.message : String(err)}</div>`;
		}
	}

	const group: DiagramGroup = {
		id: groupId,
		title: groupTitle || (items.length > 1 ? 'Architecture & Interaction Lifecycles' : items[0].title),
		items,
		activeIndex: 0,
		scale: 1,
		panX: 0,
		panY: 0,
		isDragging: false,
		startX: 0,
		startY: 0,
	};
	groupsMap.set(groupId, group);

	const container = document.createElement('div');
	container.className = 'interactive-diagram-container';
	container.id = groupId;

	renderWidgetUI(container, group);
	return container;
}

// Render or update widget HTML
function renderWidgetUI(container: HTMLElement, group: DiagramGroup) {
	const currentItem = group.items[group.activeIndex];
	const hasMultiple = group.items.length > 1;

	container.innerHTML = `
		<div class="diag-header">
			<div class="diag-header-left">
				<div class="diag-title-area">
					<span class="diag-badge-icon">📐</span>
					<span class="diag-group-title">${group.title}</span>
				</div>
				${hasMultiple
			? `
					<div class="diag-tabs" role="tablist">
						${group.items
				.map(
					(item, idx) => `
							<button 
								type="button" 
								class="diag-tab-btn ${idx === group.activeIndex ? 'active' : ''}" 
								data-tab-index="${idx}"
								role="tab"
								aria-selected="${idx === group.activeIndex}"
							>
								${item.type === 'class' ? '🏛️ Class Diagram' : item.type === 'sequence' ? '⚡ Sequence Lifecycle' : '📐 Diagram'}
							</button>
						`
				)
				.join('')}
					</div>
				`
			: ''
		}
			</div>
			<div class="diag-toolbar">
				<div class="diag-zoom-group">
					<button type="button" class="diag-tool-btn btn-zoom-out" title="Zoom Out (–)">
						<svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="5" y1="12" x2="19" y2="12"></line></svg>
					</button>
					<span class="diag-zoom-level" title="Click to Reset Zoom">100%</span>
					<button type="button" class="diag-tool-btn btn-zoom-in" title="Zoom In (+)">
						<svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><line x1="12" y1="5" x2="12" y2="19"></line><line x1="5" y1="12" x2="19" y2="12"></line></svg>
					</button>
					<button type="button" class="diag-tool-btn btn-reset" title="Fit to Viewport (⟲)">
						<svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><path d="M3 12a9 9 0 1 0 9-9 9.75 9.75 0 0 0-6.74 2.74L3 8"></path><polyline points="3 3 3 8 8 8"></polyline></svg>
						<span>Reset</span>
					</button>
				</div>
				<div class="diag-action-group">
					<button type="button" class="diag-tool-btn btn-copy" title="Copy Mermaid Code to Clipboard">
						<svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="9" y="9" width="13" height="13" rx="2" ry="2"></rect><path d="M5 15H4a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2h9a2 2 0 0 1 2 2v1"></path></svg>
						<span class="copy-label">Copy Code</span>
					</button>
					<button type="button" class="diag-tool-btn btn-fullscreen" title="Toggle Fullscreen Lightbox (F)">
						<svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M8 3H5a2 2 0 0 0-2 2v3m18 0V5a2 2 0 0 0-2-2h-3m0 18h3a2 2 0 0 0 2-2v-3M3 16v3a2 2 0 0 0 2 2h3"></path></svg>
						<span class="fullscreen-label">Fullscreen</span>
					</button>
				</div>
			</div>
		</div>

		<div class="diag-stage" tabindex="0" title="Click and drag to pan, scroll wheel to zoom">
			<div class="diag-viewport">
				<div class="diag-panzoom-target">
					${currentItem.svg || ''}
				</div>
			</div>
			<div class="diag-stage-hint">
				<span><kbd>Drag</kbd> to Pan · <kbd>Scroll</kbd> to Zoom · <kbd>Esc</kbd> Exit Fullscreen</span>
			</div>
		</div>
	`;

	attachWidgetEvents(container, group);
}

// Attach pan, zoom, tab, fullscreen, and copy event handlers
function attachWidgetEvents(container: HTMLElement, group: DiagramGroup) {
	const stage = container.querySelector<HTMLElement>('.diag-stage')!;
	const target = container.querySelector<HTMLElement>('.diag-panzoom-target')!;
	const zoomLevelEl = container.querySelector<HTMLElement>('.diag-zoom-level')!;
	const btnZoomIn = container.querySelector<HTMLButtonElement>('.btn-zoom-in')!;
	const btnZoomOut = container.querySelector<HTMLButtonElement>('.btn-zoom-out')!;
	const btnReset = container.querySelector<HTMLButtonElement>('.btn-reset')!;
	const btnCopy = container.querySelector<HTMLButtonElement>('.btn-copy')!;
	const btnFullscreen = container.querySelector<HTMLButtonElement>('.btn-fullscreen')!;

	function updateTransform() {
		target.style.transform = `translate(${group.panX}px, ${group.panY}px) scale(${group.scale})`;
		zoomLevelEl.textContent = `${Math.round(group.scale * 100)}%`;
	}

	function resetPanZoom() {
		group.scale = 1;
		group.panX = 0;
		group.panY = 0;
		updateTransform();
	}

	function zoom(delta: number) {
		const newScale = Math.min(3.0, Math.max(0.4, group.scale * delta));
		group.scale = newScale;
		updateTransform();
	}

	// Tab switcher
	container.querySelectorAll<HTMLButtonElement>('.diag-tab-btn').forEach((btn) => {
		btn.addEventListener('click', (e) => {
			e.stopPropagation();
			const idx = parseInt(btn.getAttribute('data-tab-index') || '0', 10);
			if (idx !== group.activeIndex) {
				group.activeIndex = idx;
				resetPanZoom();
				renderWidgetUI(container, group);
			}
		});
	});

	// Zoom buttons
	btnZoomIn?.addEventListener('click', (e) => {
		e.stopPropagation();
		zoom(1.2);
	});
	btnZoomOut?.addEventListener('click', (e) => {
		e.stopPropagation();
		zoom(0.83);
	});
	btnReset?.addEventListener('click', (e) => {
		e.stopPropagation();
		resetPanZoom();
	});
	zoomLevelEl?.addEventListener('click', (e) => {
		e.stopPropagation();
		resetPanZoom();
	});

	// Copy Mermaid Code
	btnCopy?.addEventListener('click', async (e) => {
		e.stopPropagation();
		const currentItem = group.items[group.activeIndex];
		try {
			await navigator.clipboard.writeText(currentItem.code);
			const label = btnCopy.querySelector('.copy-label');
			if (label) {
				const oldText = label.textContent;
				label.textContent = '✓ Copied!';
				setTimeout(() => {
					label.textContent = oldText;
				}, 2000);
			}
		} catch (err) {
			console.error('Clipboard copy failed:', err);
		}
	});

	// Fullscreen Lightbox
	btnFullscreen?.addEventListener('click', (e) => {
		e.stopPropagation();
		container.classList.toggle('is-fullscreen');
		const label = btnFullscreen.querySelector('.fullscreen-label');
		if (container.classList.contains('is-fullscreen')) {
			if (label) label.textContent = 'Exit';
			document.body.style.overflow = 'hidden';
		} else {
			if (label) label.textContent = 'Fullscreen';
			document.body.style.overflow = '';
		}
		resetPanZoom();
	});

	// Mouse Drag to Pan
	stage.addEventListener('mousedown', (e) => {
		if (e.button !== 0) return;
		group.isDragging = true;
		group.startX = e.clientX - group.panX;
		group.startY = e.clientY - group.panY;
		stage.classList.add('is-panning');
	});

	window.addEventListener('mousemove', (e) => {
		if (!group.isDragging) return;
		group.panX = e.clientX - group.startX;
		group.panY = e.clientY - group.startY;
		updateTransform();
	});

	window.addEventListener('mouseup', () => {
		if (group.isDragging) {
			group.isDragging = false;
			stage.classList.remove('is-panning');
		}
	});

	// Mouse Wheel to Zoom
	stage.addEventListener(
		'wheel',
		(e) => {
			e.preventDefault();
			e.stopPropagation();
			const zoomFactor = e.deltaY < 0 ? 1.1 : 0.9;
			zoom(zoomFactor);
		},
		{ passive: false }
	);

	// Keyboard shortcut for Esc when fullscreen
	window.addEventListener('keydown', (e) => {
		if (e.key === 'Escape' && container.classList.contains('is-fullscreen')) {
			container.classList.remove('is-fullscreen');
			document.body.style.overflow = '';
			const label = btnFullscreen.querySelector('.fullscreen-label');
			if (label) label.textContent = 'Fullscreen';
			resetPanZoom();
		}
	});
}

// Transform raw mermaid blocks in a container into rich interactive widgets
export async function enhanceMermaidInContainer(root: HTMLElement): Promise<void> {
	const mermaidBlocks: HTMLElement[] = [];

	// Expressive code or standard Starlight
	const expressiveBlocks = root.querySelectorAll<HTMLElement>(
		'figure.frame pre[data-language="mermaid"], pre[data-language="mermaid"], pre.language-mermaid, pre.mermaid'
	);
	expressiveBlocks.forEach((el) => mermaidBlocks.push(el));

	if (mermaidBlocks.length === 0) return;

	// Group adjacent mermaid blocks (e.g. Class Diagram followed by Sequence Diagram)
	const visited = new Set<HTMLElement>();
	const diagramClusters: { parentNode: Node; referenceNode: Node; codes: string[]; groupTitle?: string }[] = [];

	for (let i = 0; i < mermaidBlocks.length; i++) {
		const block = mermaidBlocks[i];
		if (visited.has(block)) continue;
		visited.add(block);

		const outerElement = block.closest('figure.frame') || block;
		const codes: string[] = [block.innerText.trim()];

		let next = outerElement.nextElementSibling;
		let groupTitle: string | undefined;

		const prev = outerElement.previousElementSibling;
		if (prev && /^H[2-5]$/i.test(prev.tagName)) {
			groupTitle = prev.textContent?.trim();
		}

		while (next) {
			if (/^H[3-6]$/i.test(next.tagName) && /sequence|lifecycle|interaction/i.test(next.textContent || '')) {
				const afterHeading = next.nextElementSibling;
				const nextMermaid = afterHeading?.querySelector('pre[data-language="mermaid"], pre.language-mermaid') ||
					(afterHeading?.matches('pre[data-language="mermaid"], pre.language-mermaid') ? afterHeading : null);
				if (nextMermaid) {
					visited.add(nextMermaid as HTMLElement);
					codes.push((nextMermaid as HTMLElement).innerText.trim());
					const toRemoveHeading = next;
					const toRemoveOuter = afterHeading?.closest('figure.frame') || afterHeading;
					next = toRemoveOuter?.nextElementSibling || null;
					toRemoveHeading.remove();
					toRemoveOuter?.remove();
					continue;
				}
			}

			const directMermaid = next.querySelector('pre[data-language="mermaid"], pre.language-mermaid') ||
				(next.matches('pre[data-language="mermaid"], pre.language-mermaid') ? next : null);
			if (directMermaid) {
				visited.add(directMermaid as HTMLElement);
				codes.push((directMermaid as HTMLElement).innerText.trim());
				const toRemove = next.closest('figure.frame') || next;
				next = toRemove.nextElementSibling;
				toRemove.remove();
				continue;
			}
			break;
		}

		if (outerElement.parentNode) {
			diagramClusters.push({
				parentNode: outerElement.parentNode,
				referenceNode: outerElement,
				codes,
				groupTitle,
			});
		}
	}

	for (const cluster of diagramClusters) {
		const items: DiagramItem[] = cluster.codes.map((code) => {
			const info = inspectDiagram(code);
			return {
				type: info.type,
				title: info.title,
				code,
			};
		});

		try {
			const widget = await createInteractiveWidget(items, cluster.groupTitle);
			cluster.parentNode.replaceChild(widget, cluster.referenceNode);
		} catch (err) {
			console.error('Failed to create interactive diagram widget:', err);
		}
	}
}
