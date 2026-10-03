package com.course.patterns.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BuilderPatternTest {

    @Test
    @DisplayName("Verify Telescoping constructor works but is fragile")
    void testTelescopingConstructor() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 7);
        TelescopingVacationPackage pkg = new TelescopingVacationPackage(
                start, end, "Grand Resort", List.of("Museum Pass"), true
        );

        assertThat(pkg.getHotel()).isEqualTo("Grand Resort");
        assertThat(pkg.hasTravelInsurance()).isTrue();
    }

    @Test
    @DisplayName("Verify Kotlin data class provides native builder semantics")
    void testKotlinVacationConfig() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 7);

        // Named arguments & default values
        KotlinVacationConfig config = new KotlinVacationConfig(
                start, end, "Seaside Villa", List.of("Snorkeling"), true
        );

        assertThat(config.getHotel()).isEqualTo("Seaside Villa");
        assertThat(config.getTravelInsurance()).isTrue();

        assertThatThrownBy(() -> new KotlinVacationConfig(end, start, "Hotel", List.of(), false))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
