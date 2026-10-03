package com.course.patterns.composite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CompositePatternTest {

    @Test
    @DisplayName("Verify MenuItem leaf nodes throw UnsupportedOperationException on child management operations")
    void testLeafSafetyTradeoff() {
        MenuComponent pancake = new MenuItem("Pancake", "Fresh pancakes", true, 2.99);

        // Transparency vs Safety: Leaves throw when mutating children
        assertThatThrownBy(() -> pancake.add(new MenuItem("Sub", "Sub", true, 1.0)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Problem Demonstration: Client forced to handle flat items and sub-menus with separate logic")
    void testInflexibleMenuHierarchyProblem() {
        var item1 = new com.course.patterns.composite.problem.InflexibleMenuHierarchyProblem.FlatMenuItem("Coffee", 1.99);
        var subMenu = new com.course.patterns.composite.problem.InflexibleMenuHierarchyProblem.NestedSubMenu("Desserts");
        subMenu.addItem(new com.course.patterns.composite.problem.InflexibleMenuHierarchyProblem.FlatMenuItem("Cheesecake", 4.50));

        String output = com.course.patterns.composite.problem.InflexibleMenuHierarchyProblem.RigidMenuPrinter
                .printMenus(java.util.List.of(item1), java.util.List.of(subMenu));

        assertThat(output).contains("Coffee: 1.99").contains("Desserts").contains("Cheesecake: 4.5");
    }

    @Test
    @DisplayName("Verify MenuItem properties and leaf print")
    void testMenuItemLeaf() {
        MenuItem item = new MenuItem("Pasta", "Spaghetti with Marinara", true, 3.89);
        StringBuilder out = new StringBuilder();
        item.print(out);

        assertThat(out.toString())
                .contains("Pasta(v), 3.89")
                .contains("Spaghetti with Marinara");
    }
}
