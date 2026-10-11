package com.course.patterns.iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IteratorPatternTest {

    @Test
    @DisplayName("Verify Waitress polymorphically traverses heterogeneous collections via Iterator")
    void testWaitressUnifiedIteration() {
        Menu pancakeHouseMenu = new PancakeHouseMenu();
        Menu dinerMenu = new DinerMenu();

        Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
        List<String> names = waitress.getAllItemNames();

        assertThat(names).containsExactly(
                "K&B's Pancake Breakfast",
                "Regular Pancake Breakfast",
                "Blueberry Pancakes",
                "Vegetarian BLT",
                "BLT",
                "Soup of the day"
        );
    }

    @Test
    @DisplayName("Verify DinerMenuIterator stops cleanly before null array slots")
    void testDinerMenuIteratorBounds() {
        DinerMenu dinerMenu = new DinerMenu();
        var it = dinerMenu.createIterator();

        int count = 0;
        while (it.hasNext()) {
            MenuItem item = it.next();
            assertThat(item).isNotNull();
            count++;
        }
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("Verify modern Java internal iteration via Streams filter")
    void testJavaStreamsEvolution() {
        List<MenuItem> items = List.of(
                new MenuItem("Pancake", "Fresh", true, 2.99),
                new MenuItem("Bacon", "Crispy", false, 3.99),
                new MenuItem("Salad", "Greens", true, 4.50)
        );

        List<String> vegetarianNames = items.stream()
                .filter(MenuItem::vegetarian)
                .map(MenuItem::name)
                .toList();

        assertThat(vegetarianNames).containsExactly("Pancake", "Salad");
    }
}
