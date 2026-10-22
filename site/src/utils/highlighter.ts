import { createHighlighter } from 'shiki';
import { Marked } from 'marked';

// Singleton highlighter instance for the build
const highlighter = await createHighlighter({
	themes: ['one-dark-pro', 'one-light'],
	langs: ['java', 'kotlin', 'groovy', 'bash', 'sql', 'xml', 'json', 'yaml', 'markdown', 'text'],
});

const supportedLangs = new Set([
	'java', 'kotlin', 'groovy', 'bash', 'sql', 'xml', 'json', 'yaml', 'markdown', 'text'
]);

export function parseMarkdownWithHighlight(markdown: string): string {
	const marked = new Marked({
		renderer: {
			code(token) {
				const text = typeof token === 'object' ? token.text : token;
				const rawLang = typeof token === 'object' ? token.lang : arguments[1];
				const lang = (rawLang || 'text').toLowerCase().trim().split(/\s+/)[0];

				if (lang === 'mermaid') {
					return `<div class="slide-mermaid-raw" data-mermaid="${encodeURIComponent(text)}"><pre class="mermaid">${text}</pre></div>`;
				}

				const safeLang = supportedLangs.has(lang) ? lang : 'text';

				return highlighter.codeToHtml(text, {
					lang: safeLang,
					themes: {
						light: 'one-light',
						dark: 'one-dark-pro',
					},
					defaultColor: false,
				});
			},
		},
	});

	return marked.parse(markdown) as string;
}
