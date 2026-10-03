package com.course.patterns.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * STARTER CODE:
 * TODO: Implement static inner Builder with fluent chaining and cross-field validation.
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
        private List<String> tickets = new ArrayList<>();
        private boolean travelInsurance = false;

        public Builder(LocalDate startDate, LocalDate endDate) {
            // TODO: Step 1 - Validate valid vacation dates (startDate <= endDate)
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Builder hotel(String hotel) {
            this.hotel = hotel;
            return this;
        }

        public Builder addTicket(String eventTicket) {
            this.tickets.add(eventTicket);
            return this;
        }

        public Builder travelInsurance(boolean travelInsurance) {
            this.travelInsurance = travelInsurance;
            return this;
        }

        public VacationPackage build() {
            // TODO: Step 2 - Validate cross-field invariant:
            // If travelInsurance is true, tickets must not be empty!
            return new VacationPackage(this);
        }
    }
}
