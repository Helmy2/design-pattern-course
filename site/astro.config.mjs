// @ts-check
import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';

export default defineConfig({
	site: 'https://helmy.is-a.dev',
	base: '/design-pattern-course/',
	integrations: [
		starlight({
			title: 'Design Patterns & Clean Code',
			description: 'Hands-on interactive course on Clean Code, SOLID Principles, and GoF Design Patterns in Java & Kotlin.',
			social: [
				{ icon: 'github', label: 'GitHub', href: 'https://github.com/Helmy2/design-pattern-course' },
			],
			customCss: ['./src/styles/custom.css'],
			components: {
				Header: './src/components/CustomHeader.astro',
				PageTitle: './src/components/CustomPageTitle.astro',
				Footer: './src/components/CustomFooter.astro',
			},
			sidebar: [
				{
					label: 'Course Overview',
					items: [
						{ label: 'Introduction & Quick Start', slug: 'index' },
						{ label: '00. Architectural Prologue', slug: 'prologue' },
					],
				},
				{
					label: 'Core Modules',
					items: [
						{ label: '01. Clean Code Fundamentals', slug: 'clean-code' },
						{ label: '02. Creational Patterns', slug: 'creational-patterns' },
						{ label: '03. Structural Patterns', slug: 'structural-patterns' },
						{ label: '04. Behavioral Patterns', slug: 'behavioral-patterns' },
						{ label: '05. Functional Programming in Kotlin', slug: 'functional-programming' },
					],
				},
				{
					label: 'Reference & Hands-on',
					items: [
						{ label: 'Pattern Trade-offs (Pros & Cons)', slug: 'reference/pattern-wizard' },
						{ label: 'Branch Catalog (40 Branches)', slug: 'reference/branches' },
						{ label: 'Principles Master Index', slug: 'reference/master-index' },
					],
				},
			],
		}),
	],
});
