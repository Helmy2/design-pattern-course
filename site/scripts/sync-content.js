import fs from 'node:fs';
import path from 'node:path';

const DOCS_DIR = path.resolve('../docs');
const TARGET_DIR = path.resolve('./src/content/docs');

if (!fs.existsSync(TARGET_DIR)) {
	fs.mkdirSync(TARGET_DIR, { recursive: true });
}

const modules = [
	{
		source: '01. Clean Code Fundamentals.md',
		target: 'clean-code.md',
		title: '01. Clean Code Fundamentals',
		description: 'Core Clean Code principles, readability rules, code smells, and refactoring techniques.',
		slug: 'clean-code',
	},
	{
		source: '02. Creational Design Patterns.md',
		target: 'creational-patterns.md',
		title: '02. Creational Design Patterns',
		description: 'Creational patterns: Singleton, Factory Method, Abstract Factory, Builder, and Prototype.',
		slug: 'creational-patterns',
	},
	{
		source: '03. Structural Design Patterns.md',
		target: 'structural-patterns.md',
		title: '03. Structural Design Patterns',
		description: 'Structural patterns: Adapter, Decorator, Composite, Facade, and Proxy.',
		slug: 'structural-patterns',
	},
	{
		source: '04. Behavioral Design Patterns.md',
		target: 'behavioral-patterns.md',
		title: '04. Behavioral Design Patterns',
		description: 'Behavioral patterns: Strategy, Observer, Command, Iterator, and Capstone Architecture.',
		slug: 'behavioral-patterns',
	},
];

for (const mod of modules) {
	// Clean up any old .mdx file with the same base name
	const oldMdx = path.join(TARGET_DIR, mod.target.replace('.md', '.mdx'));
	if (fs.existsSync(oldMdx)) {
		fs.unlinkSync(oldMdx);
	}

	const srcPath = path.join(DOCS_DIR, mod.source);
	let content = fs.readFileSync(srcPath, 'utf-8');

	// Strip initial frontmatter if width: "1600"
	if (content.startsWith('---')) {
		const secondDelim = content.indexOf('---', 3);
		if (secondDelim !== -1) {
			content = content.slice(secondDelim + 3).trim();
		}
	}

	// Normalize code fence languages to lowercase (e.g. ```Java -> ```java, ```Kotlin -> ```kotlin)
	content = content.replace(/```([A-Za-z0-9_-]+)/g, (match, lang) => {
		return '```' + lang.toLowerCase();
	});

	// Count slides
	const slideCount = content.split(/\n---\s*\n/).filter((s) => s.trim().length > 0).length;

	const md = `---
title: "${mod.title}"
description: "${mod.description}"
---

${content}
`;

	fs.writeFileSync(path.join(TARGET_DIR, mod.target), md, 'utf-8');
	console.log(`Synced ${mod.target} (${slideCount} slides)`);
}

// Ensure reference directory exists
const refDir = path.join(TARGET_DIR, 'reference');
if (!fs.existsSync(refDir)) {
	fs.mkdirSync(refDir, { recursive: true });
}

// Reference: Branches
const branchesMdx = `---
title: "Course Branch Catalog"
description: "Interactive directory of all 40 starter and solution branches across the 20 course concepts."
---

import BranchCatalog from '../../../components/BranchCatalog.astro';

Explore the starter and solution branches for each concept. Click **Copy** to grab the \`git switch\` command and jump straight into the hands-on exercise in your IDE!

<BranchCatalog />
`;
fs.writeFileSync(path.join(refDir, 'branches.mdx'), branchesMdx, 'utf-8');
console.log('Generated reference/branches.mdx');

// Reference: Master Index
const masterIndexMdx = `---
title: "Principles Master Index"
description: "Unified matrix of SOLID principles, foundational OO rules, and GoF design pattern manifestations."
---

import PrinciplesMatrix from '../../../components/PrinciplesMatrix.astro';

This interactive index cross-references object-oriented design principles, common architectural smells, and the corresponding GoF patterns that resolve them.

<PrinciplesMatrix />
`;
fs.writeFileSync(path.join(refDir, 'master-index.mdx'), masterIndexMdx, 'utf-8');
console.log('Generated reference/master-index.mdx');
