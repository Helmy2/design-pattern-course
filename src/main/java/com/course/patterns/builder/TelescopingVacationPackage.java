package com.course.patterns.builder;

import java.time.LocalDate;
import java.util.List;

/**
 * STARTER CODE:
 * The Telescoping Constructor Anti-Pattern.
 */
public class TelescopingVacationPackage {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String hotel;
    private final List<String> tickets;
    private final boolean travelInsurance;

    public TelescopingVacationPackage(LocalDate start, LocalDate end) {
        this(start, end, "Standard Room");
    }

    public TelescopingVacationPackage(LocalDate start, LocalDate end, String hotel) {
        this(start, end, hotel, List.of());
    }

    public TelescopingVacationPackage(LocalDate start, LocalDate end, String hotel, List<String> tickets) {
        this(start, end, hotel, tickets, false);
    }

    public TelescopingVacationPackage(LocalDate start, LocalDate end, String hotel, List<String> tickets, boolean travelInsurance) {
        this.startDate = start;
        this.endDate = end;
        this.hotel = hotel;
        this.tickets = tickets;
        this.travelInsurance = travelInsurance;
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getHotel() { return hotel; }
    public List<String> getTickets() { return tickets; }
    public boolean hasTravelInsurance() { return travelInsurance; }
}
