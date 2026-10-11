package com.course.patterns.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BuilderPatternTest {

    @Test
    @DisplayName("Verify telescoping constructors work for basic cases but are fragile and positional")
    void testTelescopingConstructor() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 7);
        VacationPackage pkg = new VacationPackage(
                start, end, "Grand Resort", List.of("Museum Pass"), true
        );

        assertThat(pkg.getHotel()).isEqualTo("Grand Resort");
        assertThat(pkg.hasTravelInsurance()).isTrue();
    }

    @Test
    @DisplayName("Problem Demonstration: Telescoping constructors fail to enforce cross-field invariants")
    void testTelescopingConstructorAllowsInvalidInvariant() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 7);

        // Flaw: Allows purchasing travel insurance with zero event tickets because telescoping constructors don't validate cross-field rules!
        VacationPackage invalidPkg = new VacationPackage(
                start, end, "Grand Resort", List.of(), true
        );

        assertThat(invalidPkg.hasTravelInsurance()).isTrue();
        assertThat(invalidPkg.getTickets()).isEmpty();
    }

    @Test
    @DisplayName("Verify Kotlin data class provides native builder semantics")
    void testKotlinVacationConfig() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 7);

        KotlinVacationConfig config = new KotlinVacationConfig(
                start, end, "Seaside Villa", List.of("Snorkeling"), true
        );

        assertThat(config.getHotel()).isEqualTo("Seaside Villa");
        assertThat(config.getTravelInsurance()).isTrue();

        assertThatThrownBy(() -> new KotlinVacationConfig(end, start, "Hotel", List.of(), false))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
