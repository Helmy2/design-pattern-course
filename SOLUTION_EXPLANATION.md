# Solution Explanation: Capstone Clean Architecture & SOLID Design

## 🔍 Key Implementation Decisions

1. **Eliminating the Monolithic God Class**:
   - The 150-line legacy monolith (`LegacyOrderManagementMonolith`) was decomposed into a modular, testable **Hexagonal Clean Architecture**.
   - Responsibilities are cleanly separated:
     - **Domain Value Objects (`OrderId`, `Email`, `Money`)**: Enforce immutability and self-validation. Illegal values can never enter domain logic.
     - **Domain Entity (`Order`)**: Employs **Tell, Don't Ask**. It encapsulates business subtotal, bulk discounts, and tax application.
     - **Domain Policy (`TaxPolicy`)**: Encapsulates country-specific tax rules.
     - **Driven Ports (`PaymentGatewayPort`, `NotificationPort`, `OrderRepositoryPort`)**: Define abstract contracts decoupling business orchestration from external infrastructure.
     - **Infrastructure Adapters (`StripePaymentAdapter`, `PayPalPaymentAdapter`, `EmailNotificationAdapter`)**: Implement the ports and encapsulate third-party vendor APIs.
     - **Application Service (`OrderFulfillmentService`)**: Coordinates the workflow, leveraging the **Strategy Map** pattern for O(1) payment provider dispatch.

2. **SOLID Principles Manifested**:
   - **S (SRP)**: Each class has a single, cohesive reason to change.
   - **O (OCP)**: Adding a new payment provider (e.g. Fawry) requires only writing a new `PaymentGatewayPort` implementation, without altering `OrderFulfillmentService`.
   - **L (LSP)**: All gateway adapters are completely interchangeable.
   - **I (ISP)**: Notification and persistence ports are segregated into lean, focused contracts.
   - **D (DIP)**: `OrderFulfillmentService` depends solely on abstract ports, never on concrete Stripe or SMTP infrastructure.

3. **ArchUnit Automated Architectural Enforcement**:
   - Automated tests verify:
     - The domain layer (`com.course.capstone.domain..`) has zero dependencies on infrastructure (`..infrastructure..`).
     - Dependency direction flows strictly inward.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.capstone.*"
```
All assertions pass green!
