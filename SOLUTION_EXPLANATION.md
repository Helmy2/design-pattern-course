# Solution Explanation: Meaningful Names & Readability

## 🔍 Key Refactoring Decisions

1. **Eliminating Cryptic Names & Encodings**:
   - `DtaRcrd102` was renamed to `CustomerRecord`, a conversational, pronounceable domain entity.
   - Cryptic timestamps `genymdhms` and `modymdhms` were replaced with `generationTimestamp` and `modificationTimestamp`.
   - Magic numbers `7` and `86400` were extracted into public constants:
     - `ROLE_ADMIN = 7`
     - `SECONDS_IN_A_DAY = 86400`
   - Fixed disinformation: `userList` was changed to `activeUsers` (since it was typed as a `Set`, not a `List`), and `d` was replaced with `activeUserCount`.

2. **Class Names (Nouns) vs. Method Names (Verbs)**:
   - `ProcessOrder` was a verb phrase masquerading as a class. It was refactored to `OrderProcessor`.
   - Methods became action verbs (`processPayment`) and predicates conforming to standard Java conventions (`hasExpired`).

3. **Eliminating Redundant Context**:
   - Inside the `Address` class, properties `addrStreet`, `addrCity`, and `addrZipCode` were simplified to `street`, `city`, and `zipCode`. The enclosing class provides the necessary context.

4. **One Word per Concept**:
   - Instead of fragmented query conventions across multiple services (`fetchUser`, `retrieveOrder`, `getPayment`, `findItem`), all services now consistently expose `findById(String id)`.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.cleancode.naming.*"
```
All assertions pass green!
