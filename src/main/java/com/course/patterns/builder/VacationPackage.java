package com.course.patterns.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the Telescoping Constructor and JavaBeans Anti-Patterns before applying Builder:
 *
 * 1. Telescoping Constructors:
 *    Multiple overloaded constructors with varying parameters lead to positional confusion
 *    (e.g., passing tickets where hotel was expected, or swapping boolean flags).
 *
 * 2. Inability to enforce cross-field invariants:
 *    Constructors cannot easily guard rules like "travel insurance requires at least one event ticket"
 *    without duplicating validation in every constructor overload.
 *
 * 3. Mutable / Inconsistent State:
 *    If setters are used, the object is exposed in a half-initialized state before all fields are set.
 */
public class VacationPackage {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String hotel;
    private final List<String> tickets;
    private final boolean travelInsurance;

    public VacationPackage(LocalDate start, LocalDate end) {
        this(start, end, "Standard Room");
    }

    public VacationPackage(LocalDate start, LocalDate end, String hotel) {
        this(start, end, hotel, List.of());
    }

    public VacationPackage(LocalDate start, LocalDate end, String hotel, List<String> tickets) {
        this(start, end, hotel, tickets, false);
    }

    public VacationPackage(LocalDate start, LocalDate end, String hotel, List<String> tickets, boolean travelInsurance) {
        this.startDate = start;
        this.endDate = end;
        this.hotel = hotel;
        this.tickets = tickets != null ? new ArrayList<>(tickets) : new ArrayList<>();
        this.travelInsurance = travelInsurance;
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getHotel() { return hotel; }
    public List<String> getTickets() { return tickets; }
    public boolean hasTravelInsurance() { return travelInsurance; }
}
