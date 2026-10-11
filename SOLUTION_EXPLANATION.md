# Solution Explanation: Iterator Pattern & Java Streams Evolution

## 🔍 Key Implementation Decisions

1. **Encapsulating Aggregate Traversal**:
   - `DinerMenu` is backed by a fixed-size `MenuItem[]` array, while `PancakeHouseMenu` is backed by an `ArrayList<MenuItem>`.
   - Without the Iterator pattern, client code would be forced to write separate loops, bind to array indexing, and defensively skip null slots.
   - By creating `DinerMenuIterator implements Iterator<MenuItem>`, the client (`Waitress`) receives a uniform iterator contract.

2. **Single Responsibility Principle (SRP)**:
   - A collection class has one primary responsibility: managing its elements (inserting, deleting, holding).
   - If that class also manages iteration state (current index, boundary checks), it now has two reasons to change:
     1. If internal storage changes (e.g. array ➔ hash map).
     2. If the traversal algorithm changes (e.g. filtering, reverse order).
   - Extracting `Iterator` cleanly isolates traversal into its own dedicated object.

3. **Modern Java & Streams Evolution**:
   - In modern Java backends, external explicit iteration (`while (it.hasNext())`) is largely complemented by internal iteration via the Stream API:
     ```java
     menuItems.stream()
              .filter(MenuItem::vegetarian)
              .map(MenuItem::name)
              .toList();
     ```
   - In database access layers, Spring Data cursor pagination (`Slice<T>` and `Page<T>`) applies the Iterator concept to avoid loading millions of records into JVM heap memory at once.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.iterator.*"
```
All assertions pass green!
