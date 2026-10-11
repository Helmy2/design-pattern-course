package com.course.patterns.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BuilderPatternTest {

    @Test
    @DisplayName("Verify VacationPackage.Builder constructs immutable object with fluent chaining")
    void testVacationPackageBuilder() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 7);

        VacationPackage pkg = new VacationPackage.Builder(start, end)
                .hotel("Grand Resort & Spa")
                .addTicket("Museum Pass")
                .addTicket("Theme Park Admission")
                .travelInsurance(true)
                .build();

        assertThat(pkg.getStartDate()).isEqualTo(start);
        assertThat(pkg.getEndDate()).isEqualTo(end);
        assertThat(pkg.getHotel()).isEqualTo("Grand Resort & Spa");
        assertThat(pkg.getTickets()).containsExactly("Museum Pass", "Theme Park Admission");
        assertThat(pkg.hasTravelInsurance()).isTrue();

        // Immutability: tickets list cannot be mutated from outside
        assertThatThrownBy(() -> pkg.getTickets().add("Hacker Pass"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Verify Builder enforces date order invariant")
    void testBuilderDateValidation() {
        LocalDate start = LocalDate.of(2026, 10, 10);
        LocalDate end = LocalDate.of(2026, 10, 5);

        assertThatThrownBy(() -> new VacationPackage.Builder(start, end))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Valid vacation dates are required");
    }

    @Test
    @DisplayName("Verify Builder enforces cross-field invariant: insurance requires tickets")
    void testBuilderCrossFieldValidation() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 7);

        VacationPackage.Builder builder = new VacationPackage.Builder(start, end)
                .travelInsurance(true); // No tickets added!

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Travel insurance cannot be purchased without event tickets");
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
    }
}
