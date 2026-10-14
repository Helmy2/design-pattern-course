# Lesson 01: Meaningful Names & Readability

> "Names are the primary communication channel between engineers. Poor names hide bugs and slow down onboarding."

---

## 🎯 Learning Objectives
1. **Intent-Revealing Names**: Name variables, methods, and classes so they state *why* they exist, *what* they do, and *how* they are used.
2. **Avoid Disinformation & Encodings**: Never name a collection `userList` if it is a `Set`. Never use single-letter variables (`d`) or hungarian notation (`strName`).
3. **Pronounceable & Searchable Names**: Avoid acronym hell like `DtaRcrd102` and magic numbers like `86400` or `7`.
4. **Class Names (Nouns) vs. Method Names (Verbs)**: Classes represent entities (`OrderProcessor`), methods represent actions (`processPayment()`).
5. **One Word per Concept**: Standardize query methods across services (use `findById` everywhere instead of mixing `fetch`, `retrieve`, `get`, `find`).
6. **Meaningful Context Without Redundant Prefixes**: Inside `Address`, use `street`, `city`, `zipCode` instead of `addrStreet`, `addrCity`.

---

## 🏗️ The Problem: Legacy Code Smells
In this starter branch, examine `src/main/java/com/course/cleancode/naming/`:
- `DtaRcrd102.java`: Unpronounceable record class with cryptic fields (`genymdhms`, `modymdhms`, `pszrId`) and unsearchable magic numbers (`86400`, `7`).
- `ProcessOrder.java`: Class named with a verb phrase, method named with a noun (`transaction()`) and vague flag (`flag()`).
- `Address.java`: Redundant `addr*` prefixes everywhere.
- `LegacyQueryServices.java`: Four services each using different words for the exact same ID query (`fetchUser`, `retrieveOrder`, `getPayment`, `findItem`).

---

## 📝 Refactoring Tasks
1. Refactor `DtaRcrd102.java` to `CustomerRecord.java`: Rename fields to `generationTimestamp`, `modificationTimestamp`, `recordId`. Replace magic numbers with named constants `ROLE_ADMIN = 7` and `SECONDS_IN_A_DAY = 86400`.
2. Refactor `ProcessOrder.java` to `OrderProcessor.java`: Rename `transaction()` to `processPayment()`, `flag()` to `hasExpired()`.
3. Refactor `Address.java`: Remove redundant `addr` prefixes.
4. Refactor `LegacyQueryServices.java` to `StandardQueryServices.java`: Standardize query methods across all services to `findById(String id)`.

---

## 🧪 Verification
Run tests using:
```bash
./gradlew test --tests "com.course.cleancode.naming.*"
```
All assertions pass to verify starter behavior!
