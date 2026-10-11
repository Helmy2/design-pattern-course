# Solution Explanation: Composite Pattern

## 🔍 Key Implementation Decisions

1. **Part-Whole Hierarchies**:
   - `MenuComponent` establishes a single abstract interface for both individual leaf items (`MenuItem`) and composite containers (`Menu`).
   - Enables arbitrary tree nesting: A `Dessert Menu` (Composite) can be added as a child of `Dinner Menu` (Composite), which is a child of `All Menus` (Root Composite).

2. **Polymorphic Recursion**:
   - In `Menu.print(out)`:
     ```java
     for (MenuComponent component : menuComponents) {
         component.print(out);
     }
     ```
   - When a child is a `MenuItem`, it prints its price and description.
   - When a child is another `Menu`, it prints its header and recursively prints its own sub-children.
   - The client (`Waitress`) invokes `allMenus.print()` with a single call, completely unaware of the underlying tree depth or complexity.

3. **Transparency vs. Safety Trade-Off**:
   - **Transparency (chosen in GoF / Head First)**: Putting `add()`, `remove()`, and `getChild()` in `MenuComponent` ensures leaves and composites share the exact same interface. The client doesn't need to typecast. The trade-off is runtime `UnsupportedOperationException` if `add()` is invoked on a `MenuItem`.
   - **Safety**: Moving child operations strictly to `Menu` prevents runtime exceptions on leaves, but forces clients to use `instanceof` and explicit casts before adding items.

4. **Real-World JVM Applications**:
   - GUI component hierarchies: Swing (`Container` holding `Component`), Android Views (`ViewGroup` holding `View`), and Jetpack Compose composable trees.
   - File system tree: Directories (Composites) and Files (Leaves) supporting uniform operations like `calculateSize()`.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.composite.*"
```
All assertions pass green!
