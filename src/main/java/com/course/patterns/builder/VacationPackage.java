package com.course.patterns.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * The Builder Pattern:
 * - Immutability: all fields are final and collections are unmodifiable.
 * - Private constructor prevents direct instantiation outside the Builder.
 * - Fluent chaining provides clear, self-documenting call sites.
 * - Cross-field invariant validation is enforced before the instance escapes build().
 */
public class VacationPackage {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String hotel;
    private final List<String> tickets;
    private final boolean travelInsurance;

    private VacationPackage(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.hotel = builder.hotel;
        this.tickets = Collections.unmodifiableList(new ArrayList<>(builder.tickets));
        this.travelInsurance = builder.travelInsurance;
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getHotel() { return hotel; }
    public List<String> getTickets() { return tickets; }
    public boolean hasTravelInsurance() { return travelInsurance; }

    public static class Builder {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private String hotel = "Standard Room";
        private final List<String> tickets = new ArrayList<>();
        private boolean travelInsurance = false;

        public Builder(LocalDate startDate, LocalDate endDate) {
            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                throw new IllegalArgumentException("Valid vacation dates are required: start must not be after end");
            }
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Builder hotel(String hotel) {
            if (hotel != null && !hotel.isBlank()) {
                this.hotel = hotel;
            }
            return this;
        }

        public Builder addTicket(String eventTicket) {
            if (eventTicket != null && !eventTicket.isBlank()) {
                this.tickets.add(eventTicket);
            }
            return this;
        }

        public Builder travelInsurance(boolean travelInsurance) {
            this.travelInsurance = travelInsurance;
            return this;
        }

        public VacationPackage build() {
            // Cross-field invariant check
            if (travelInsurance && tickets.isEmpty()) {
                throw new IllegalStateException("Travel insurance cannot be purchased without event tickets");
            }
            return new VacationPackage(this);
        }
    }
}
