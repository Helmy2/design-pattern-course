import fs from 'node:fs';
import path from 'node:path';

const DOCS_DIR = path.resolve('../docs');
const TARGET_DIR = path.resolve('./src/content/docs');

if (!fs.existsSync(TARGET_DIR)) {
	fs.mkdirSync(TARGET_DIR, { recursive: true });
}

const modules = [
	{
		source: '00. Architectural Prologue.md',
		target: 'prologue.md',
		title: '00. Architectural Prologue: The Silicon Pendulum',
		description: 'The historical rivalry of CISC vs. RISC, the collapse of mutable state under concurrency, and the modern synthesis of Clean Architecture in Kotlin and Spring Boot.',
		slug: 'prologue',
	},
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
	{
		source: '05. Functional Programming in Kotlin.md',
		target: 'functional-programming.md',
		title: '05. Functional Programming in Kotlin',
		description: 'Core FP principles, migrating OOP patterns to FP, architectural trade-offs, and modern Kotlin FP features.',
		slug: 'functional-programming',
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

// Reference: Pattern Trade-offs Guide
const patternWizardMdx = `---
title: "Pattern Trade-offs: Pros & Cons Guide"
description: "Comprehensive architectural breakdown of GoF design patterns with tangible pros, drawbacks, over-engineering warnings, and simpler alternatives."
---

import PatternWizard from '../../../components/PatternWizard.astro';

When designing software systems, every design pattern is a deliberate trade-off: you gain decoupling, extensibility, or runtime flexibility in exchange for indirection, boilerplate, or added complexity.

Browse the architectural guide below to evaluate the **pros, cons, over-engineering risks, and simpler alternatives** for every pattern in the curriculum.

<PatternWizard />

---

### Architectural Principles Behind Pattern Selection

1. **Encapsulate What Varies:** Isolate the aspects of your application that change from the aspects that stay static.
2. **Favor Composition Over Inheritance:** Build dynamic runtime object graphs rather than rigid compile-time inheritance hierarchies.
3. **YAGNI (You Aren't Gonna Need It):** Never introduce a pattern based on speculative future requirements. Wait until the second or third concrete variation appears before extracting a Strategy, Factory, or Abstract Factory.
`;
fs.writeFileSync(path.join(refDir, 'pattern-wizard.mdx'), patternWizardMdx, 'utf-8');
console.log('Generated reference/pattern-wizard.mdx');
