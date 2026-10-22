# Comprehensive Design Principles Master Index

This document provides a single, unified reference for all software design principles, SOLID rules, architectural guidelines, and design pattern mappings covered across the course.

---

## 1. Foundational Object-Oriented Design Principles

| # | Principle | Definition | Core Pattern Manifestations |
|---|-----------|------------|-----------------------------|
| 1 | **Encapsulate What Varies** | Identify aspects of your application that change and separate them from what stays constant. | Strategy, Factory Method, Iterator |
| 2 | **Program to Interfaces, Not Implementations** | Declare reference types using abstract contracts or supertypes so clients remain decoupled from concrete classes. | Strategy, Factory Method, Abstract Factory, Adapter, Proxy |
| 3 | **Favor Composition Over Inheritance** | Assemble behavior dynamically at runtime (`HAS-A`) rather than hardcoding static compile-time class hierarchies (`IS-A`). | Strategy, Decorator, Adapter, Composite |
| 4 | **Loosely Coupled Designs** | Communicate through high-level abstractions so that changes to one component do not ripple into others. | Observer, Command |
| 5 | **Open/Closed Principle (OCP)** | Classes should be open for extension, but closed for modification. | Decorator, Strategy, Factory Method |
| 6 | **Dependency Inversion Principle (DIP)** | High-level modules and low-level modules must both depend on abstractions. | Factory Method, Abstract Factory, Hexagonal Ports |
| 7 | **Principle of Least Knowledge (Law of Demeter)** | Talk only to your immediate friends. Avoid chained calls (`a.getB().getC().getD()`). | Facade |
| 8 | **The Hollywood Principle** | "Don't call us, we'll call you." High-level components direct control flow and invoke hooks. | Template Method, Factory Method, Spring IoC |

---

## 2. The SOLID Principles Matrix

| Principle | Core Meaning | Common Anti-Pattern / Smell | Pattern / Solution |
| :--- | :--- | :--- | :--- |
| **S — Single Responsibility (SRP)** | A class should have only one reason to change. | God Object, mixing business logic with formatting or persistence. | Iterator (separating iteration from storage), Command, Hexagonal Ports. |
| **O — Open/Closed (OCP)** | Open for extension, closed for modification. | Modifying complex `switch/case` or `if/else` ladders on every release. | Decorator, Strategy, Factory Method. |
| **L — Liskov Substitution (LSP)** | Subtypes must be substitutable for their base types without altering correctness. | Refused Bequest: overriding methods to throw `UnsupportedOperationException`. | Favoring composition, Interface Segregation. |
| **I — Interface Segregation (ISP)** | Clients should not be forced to depend on methods they do not use. | "Fat" interfaces requiring empty method implementations. | Role interfaces, Adapter pattern. |
| **D — Dependency Inversion (DIP)** | Depend on abstractions, not concretes. High/low modules depend on interfaces. | Hardcoded `new ConcreteService()` calls deep in domain code. | Factory Method, Abstract Factory, Spring IoC container. |

---

## 3. Pragmatic Software Engineering & Over-Engineering Safeguards

1. **YAGNI (You Aren't Gonna Need It):**
   - Do not write speculative generic abstractions, plugin engines, or premature interfaces for hypothetical future needs.
2. **KISS (Keep It Simple, Stupid):**
   - The simplest workable design that satisfies current requirements and automated tests is the optimal design.
3. **The Rule of Three:**
   - Allow code duplication once or twice. Extract a shared abstraction only when the third distinct occurrence emerges with clear variation.
4. **Optimize for Deletion, Not Just Extension:**
   - Simple, direct code can be deleted cleanly. Speculative multi-layered generic abstractions resist deletion.
5. **Essential vs. Accidental Complexity:**
   - **Essential:** Inherent complexity of the business problem.
   - **Accidental:** Self-inflicted complexity introduced by design choices (1:1 interfaces for CRUD, excessive micro-methods).

---

## 4. Modern Enterprise & System Design Principles

1. **Hexagonal Architecture (Ports and Adapters):**
   - Core domain logic has zero external dependencies.
   - External systems (Stripe, PostgreSQL, Kafka) plug into the domain via Driven Adapters implementing Domain Ports.
2. **Command Query Responsibility Segregation (CQRS):**
   - Read models (Queries) are optimized for data retrieval and side-effect free.
   - Write models (Commands) encapsulate mutations and validate business invariants.
3. **AI-Assisted Engineering Rule of Thumb:**
   - AI code assistants accelerate syntax, tests, and boilerplates, but suffer from **sycophancy bias** and lack architectural context. The engineer maintains ownership of system boundaries.

---

## 5. Design Patterns Master Reference Matrix

| Pattern | Type | Core Metaphor / Scenario | Primary Intent | Modern JVM / Enterprise Reality |
| :--- | :--- | :--- | :--- | :--- |
| **Singleton** | Creational | Connection Pool / App Config | Ensure a class has only one instance and global access. | Spring container singletons (`@Service`), Kotlin `object`. |
| **Factory Method** | Creational | PizzaStore Franchises | Defer concrete instantiation to subclasses. | Declarative Spring `@Bean`, factory functions. |
| **Abstract Factory** | Creational | Regional Pizza Ingredients | Create families of related or dependent objects. | Multi-tenant cloud client factories. |
| **Builder** | Creational | Vacation Package | Step-by-step assembly of complex immutable objects. | Lombok `@Builder`, Kotlin named arguments & default values. |
| **Prototype** | Creational | Network Security Profile | Clone existing objects without coupling to concrete classes. | Spring `@Scope("prototype")`, Kotlin `.copy()`. |
| **Adapter** | Structural | Duck to Turkey / European Plug | Convert an incompatible interface to match client expectations. | Hexagonal Architecture Driven Adapters (Stripe SDK). |
| **Decorator** | Structural | Starbuzz Coffee Condiments | Attach dynamic responsibilities without subclass explosion. | Java I/O Streams, Kotlin delegation (`by`). |
| **Composite** | Structural | Nested Restaurant Menus | Tree hierarchies treating leaves and composites uniformly. | GUI widget trees (Swing, Android Views, DOM), permission nodes. |
| **Facade** | Structural | Home Theater Subsystem | Provide a simplified high-level interface to a complex subsystem. | Service Facades, API Gateways. |
| **Proxy** | Structural | Matchmaking Profile Security | Surrogate controlling access (security, lazy loading, remote). | Spring AOP (`@Transactional`, `@Async`, `@Cacheable`). |
| **Strategy** | Behavioral | SimUDuck Simulator | Encapsulate interchangeable algorithms. | Spring Boot Strategy Map injection (`Map<Provider, Strategy>`), Kotlin function types (`() -> Unit`). |
| **Observer** | Behavioral | Weather Station | One-to-many state change notification. | Spring `ApplicationEventPublisher`, Reactive Streams (`Flow`). |
| **Command** | Behavioral | Smart Home Remote Control | Encapsulate requests as objects (undo, queues, macros). | Thread pools (`Runnable`), CQRS Command Bus. |
| **Iterator** | Behavioral | Diner vs Pancake House | Sequential access without exposing underlying collection structure. | Java `Iterable<T>`, Streams, cursor pagination. |
