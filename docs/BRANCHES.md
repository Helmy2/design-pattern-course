# Branch Catalog & Learning Objectives Guide

This document indexes all 40 concept branches (20 starter/solution pairs) in the repository, outlining the exact learning objectives, key classes, code smells, and automated tests for each.

---

## Module 1: Clean Code Foundations & Refactoring

### Concept 01: Meaningful Naming & Readability
- **Starter Branch:** `01-clean-naming-starter`
- **Solution Branch:** `01-clean-naming-solution`
- **Focus:** Intent-revealing names, eliminate disinformation (Set named `userList`), pronounceable/searchable names, Class names (nouns) vs Method names (verbs), one word per concept, no redundant prefixes.
- **Key Files:** `com.course.cleancode.naming.*`
- **Tests:** `CleanNamingTest`

### Concept 02: Functions, Arguments & Command-Query Separation (CQS)
- **Starter Branch:** `02-functions-and-cqs-starter`
- **Solution Branch:** `02-functions-and-cqs-solution`
- **Focus:** Small & focused methods, Single Level of Abstraction (SLA), Monadic/Dyadic vs Polyadic arguments with Parameter Objects, eliminate flag arguments (`isUrgent`), Command-Query Separation (`isExpired` query vs `evictSession` command).
- **Key Files:** `com.course.cleancode.functions.*`
- **Tests:** `FunctionsAndCqsTest`

### Concept 03: Code Smells & Antipatterns
- **Starter Branch:** `03-code-smells-starter`
- **Solution Branch:** `03-code-smells-solution`
- **Focus:** Bloaters (Primitive Obsession with `Email` & `PhoneNumber` records), OO Abusers (Refused Bequest, switch over type codes), Couplers (Feature Envy in `OrderReceiptPrinter`, Law of Demeter message chains).
- **Key Files:** `com.course.cleancode.smells.*`
- **Tests:** `CodeSmellsTest`

### Concept 04: Core Refactoring Techniques
- **Starter Branch:** `04-refactoring-techniques-starter`
- **Solution Branch:** `04-refactoring-techniques-solution`
- **Focus:** Extract Method & Variable (`printInvoice`), Replace Temp with Query (`getPrice()`, `basePrice()`), Introduce Parameter Object (`DateRange`), Replace Conditional with Polymorphism (`Employee` bonus calculation).
- **Key Files:** `com.course.cleancode.refactoring.*`
- **Tests:** `RefactoringTechniquesTest`

### Concept 05: Architecture Smells & ArchUnit Enforcement
- **Starter Branch:** `05-architecture-and-archunit-starter`
- **Solution Branch:** `05-architecture-and-archunit-solution`
- **Focus:** Package-by-Layer vs Package-by-Feature (vertical slicing), cyclic dependencies, avoiding over-engineering (1:1 interfaces with `Impl`), automated ArchUnit rules.
- **Key Files:** `com.course.architecture.*`
- **Tests:** `ArchitectureEnforcementArchUnitTest`

---

## Module 2: Creational Design Patterns

### Concept 06: Singleton Pattern & Modern Evolution
- **Starter Branch:** `06-pattern-singleton-starter`
- **Solution Branch:** `06-pattern-singleton-solution`
- **Focus:** Thread-safe Singleton evolution: Unsafe Lazy, Double-Checked Locking (`volatile`), Initialization-on-demand Holder, Enum Singleton (`GlobalAppConfig`). Kotlin `object` & Spring `@Service` bean singleton.
- **Key Files:** `com.course.patterns.singleton.*`
- **Tests:** `SingletonConcurrencyTest`

### Concept 07: Factory Method & Simple Factory Pattern
- **Starter Branch:** `07-pattern-factory-method-starter`
- **Simple Factory Solution Branch:** `07-pattern-simple-factory-solution`
- **Factory Method Solution Branch:** `07-pattern-factory-method-solution`
- **Focus:** 3-step linear progression: (1) Monolithic PizzaStore with inline instantiation anti-pattern; (2) Simple Factory extraction idiom; (3) GoF Factory Method framework with Creator hierarchy (`PizzaStore`, `NYPizzaStore`, `ChicagoPizzaStore`) and Product hierarchy (`Pizza`, `NYStyleCheesePizza`, `ChicagoStyleCheesePizza`).
- **Key Files:** `com.course.patterns.factorymethod.*`
- **Tests:** `FactoryMethodTest`

### Concept 08: Abstract Factory Pattern
- **Starter Branch:** `08-pattern-abstract-factory-starter`
- **Solution Branch:** `08-pattern-abstract-factory-solution`
- **Focus:** Product families (`Dough`, `Sauce`, `Cheese`, `Veggies`, `Clams`), `PizzaIngredientFactory` interface, `NYPizzaIngredientFactory`, `ChicagoPizzaIngredientFactory`. Refactored `Pizza` using composition.
- **Key Files:** `com.course.patterns.abstractfactory.*`
- **Tests:** `AbstractFactoryTest`

### Concept 09: Builder Pattern & Modern Kotlin
- **Starter Branch:** `09-pattern-builder-starter`
- **Solution Branch:** `09-pattern-builder-solution`
- **Focus:** Telescoping constructors and JavaBeans pitfalls, fluent static inner `Builder`, invariant validation, immutability (`VacationPackage`). Modern Kotlin named arguments and default parameters.
- **Key Files:** `com.course.patterns.builder.*`
- **Tests:** `BuilderPatternTest`

### Concept 10: Prototype Pattern & Modern Kotlin
- **Starter Branch:** `10-pattern-prototype-starter`
- **Solution Branch:** `10-pattern-prototype-solution`
- **Focus:** `Prototype<T>` interface, shallow vs deep copy hazard, avoiding `Cloneable`, `NetworkSecurityProfile` with deep copy constructor, `SecurityProfileRegistry` cache. Kotlin data class `.copy()`.
- **Key Files:** `com.course.patterns.prototype.*`
- **Tests:** `PrototypePatternTest`

---

## Module 3: Structural Design Patterns

### Concept 11: Adapter Pattern & Hexagonal Ports & Adapters
- **Starter Branch:** `11-pattern-adapter-starter`
- **Solution Branch:** `11-pattern-adapter-solution`
- **Focus:** Target (`Duck`), Adaptee (`Turkey`), `TurkeyAdapter` (Object Adapter), `EnumerationIterator`, Hexagonal Architecture Driven Adapter (`PaymentGatewayPort` -> `StripePaymentAdapter`).
- **Key Files:** `com.course.patterns.adapter.*`
- **Tests:** `AdapterPatternTest`

### Concept 12: Decorator Pattern & Kotlin Delegation
- **Starter Branch:** `12-pattern-decorator-starter`
- **Solution Branch:** `12-pattern-decorator-solution`
- **Focus:** Starbuzz Coffee (`Beverage`, `CondimentDecorator`, `Espresso`, `DarkRoast`, `Mocha`, `Whip`, `Soy`), Open/Closed Principle (OCP), Java I/O `LowerCaseInputStream`, Kotlin first-class interface delegation (`by`).
- **Key Files:** `com.course.patterns.decorator.*`
- **Tests:** `DecoratorPatternTest`

### Concept 13: Composite Pattern
- **Starter Branch:** `13-pattern-composite-starter`
- **Solution Branch:** `13-pattern-composite-solution`
- **Focus:** Part-whole tree hierarchies, `MenuComponent` base, `MenuItem` leaf, `Menu` composite with child items & sub-menus, polymorphic recursion in `print()`, Transparency vs Safety trade-offs.
- **Key Files:** `com.course.patterns.composite.*`
- **Tests:** `CompositePatternTest`

### Concept 14: Facade Pattern
- **Starter Branch:** `14-pattern-facade-starter`
- **Solution Branch:** `14-pattern-facade-solution`
- **Focus:** Home Theater automation (`Amplifier`, `StreamingPlayer`, `Projector`, `TheaterLights`, `Screen`, `Popper`), `HomeTheaterFacade`, Principle of Least Knowledge / Law of Demeter. Enterprise service facades & API Gateways.
- **Key Files:** `com.course.patterns.facade.*`
- **Tests:** `FacadePatternTest`

### Concept 15: Proxy Pattern & Spring AOP Under the Hood
- **Starter Branch:** `15-pattern-proxy-starter`
- **Solution Branch:** `15-pattern-proxy-solution`
- **Focus:** Dynamic Protection Proxy via `java.lang.reflect.Proxy`, `OwnerInvocationHandler`, `NonOwnerInvocationHandler`, Spring AOP `@Transactional` proxy mechanics, self-invocation gotcha.
- **Key Files:** `com.course.patterns.proxy.*`
- **Tests:** `ProxyPatternTest`

---

## Module 4: Behavioral Design Patterns & Capstone

### Concept 16: Strategy Pattern & Spring Strategy Map
- **Starter Branch:** `16-pattern-strategy-starter`
- **Solution Branch:** `16-pattern-strategy-solution`
- **Focus:** SimUDuck (`Duck`, `FlyBehavior`, `QuackBehavior`), dynamic runtime swapping (`FlyRocketPowered`), Spring Boot Strategy Map pattern (`PaymentStrategy`, `StripePaymentStrategy`, `PayPalPaymentStrategy`).
- **Key Files:** `com.course.patterns.strategy.*`
- **Tests:** `StrategyPatternTest`

### Concept 17: Observer Pattern & Spring Event Publisher
- **Starter Branch:** `17-pattern-observer-starter`
- **Solution Branch:** `17-pattern-observer-solution`
- **Focus:** Weather Station (`Subject`, `Observer`, `WeatherData`, `CurrentConditionsDisplay`), loose coupling, Push vs Pull architecture evolution. Modern Spring `ApplicationEventPublisher` (`OrderPlacedEvent`, `@EventListener`).
- **Key Files:** `com.course.patterns.observer.*`
- **Tests:** `ObserverPatternTest`

### Concept 18: Command Pattern & CQRS
- **Starter Branch:** `18-pattern-command-starter`
- **Solution Branch:** `18-pattern-command-solution`
- **Focus:** Smart home `RemoteControl` (7 slots, ON/OFF, Undo), `LightOnCommand`, `StereoOnWithCDCommand`, `CeilingFanMediumCommand` (state-tracking undo), `NoCommand` (Null Object), `MacroCommand` ("Party Mode"), Task queues & CQRS command bus.
- **Key Files:** `com.course.patterns.command.*`
- **Tests:** `CommandPatternTest`

### Concept 19: Iterator Pattern & Java Streams
- **Starter Branch:** `19-pattern-iterator-starter`
- **Solution Branch:** `19-pattern-iterator-solution`
- **Focus:** Mel's Diner (`MenuItem[]` array) vs Lou's Pancake House (`List<MenuItem>`), custom `DinerMenuIterator`, unified `Menu` interface, `Waitress` traversal, Single Responsibility Principle (SRP), comparison with Java Streams & `Iterable<T>`.
- **Key Files:** `com.course.patterns.iterator.*`
- **Tests:** `IteratorPatternTest`

### Concept 20: Capstone Clean Architecture
- **Starter Branch:** `20-capstone-clean-architecture-starter`
- **Solution Branch:** `20-capstone-clean-architecture-solution`
- **Focus:** End-to-end integration combining Clean Code, Refactoring, SOLID principles, and Patterns in a realistic enterprise module.
- **Key Files:** `com.course.capstone.*`
- **Tests:** `CapstoneArchitectureTest`
