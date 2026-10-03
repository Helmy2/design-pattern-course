# Lesson 20: Capstone Project — End-to-End Clean Architecture & Refactoring

> "Any fool can write code that a computer can understand. Good programmers write code that humans can understand."

---

## 🎯 Capstone Objectives
In this final capstone challenge, you synthesize everything learned across the entire course:
1. **Clean Code Foundations**:
   - Meaningful domain naming (no `d`, `temp`, `data`, `addrStreet`).
   - Small, focused methods with Single Level of Abstraction (SLA).
   - Eliminating Primitive Obsession with Domain Value Objects (`Email`, `Money`, `OrderId`).
   - Command-Query Separation (CQS).
2. **SOLID Principles**:
   - **S**: Break apart the God Class into single-purpose components.
   - **O**: Strategy pattern for payment gateways without `if/else`.
   - **L**: Substitutable interfaces for payment and notification adapters.
   - **I**: Lean, focused port contracts.
   - **D**: High-level domain logic depends on ports, not third-party SDKs.
3. **Hexagonal Architecture (Ports and Adapters)**:
   - Pure domain core with zero infrastructure or framework dependencies.
   - Driven ports implemented by infrastructure adapters.
4. **Automated Architectural Guardrails (ArchUnit)**:
   - ArchUnit tests ensuring domain purity and zero circular dependencies.

---

## 🏗️ The Problem: Legacy Monolithic God Class
Examine `src/main/java/com/course/capstone/legacy/LegacyOrderManagementMonolith.java`:
- 150-line God method `processAndFulfillOrder(...)`.
- Takes raw primitives, does regex validation inline, calculates taxes using hardcoded country switches, instantiates third-party payment SDKs directly with `new`, prints raw SMTP headers, and commits to a database.
- It exhibits **Bloaters**, **Feature Envy**, **Demeter violations**, **Primitive Obsession**, and **Divergent Change**.

---

## 📝 Refactoring Tasks
1. Extract Value Objects: `Email`, `Money`, `OrderId`.
2. Extract Domain Ports: `PaymentGatewayPort`, `NotificationPort`.
3. Extract Strategy: `PaymentStrategy` routing Stripe vs PayPal.
4. Build `OrderService` as a clean coordinator that delegates to domain entities and ports.
5. Verify that all behavioral regression tests pass, and ArchUnit rules validate architectural purity!

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.capstone.*"
```
