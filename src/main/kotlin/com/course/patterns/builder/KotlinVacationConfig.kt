package com.course.patterns.builder

import java.time.LocalDate

/**
 * MODERN KOTLIN EVOLUTION:
 * Named arguments and default parameter values make the GoF Builder pattern obsolete
 * for domain data classes.
 */
data class KotlinVacationConfig(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val hotel: String = "Standard Room",
    val tickets: List<String> = emptyList(),
    val travelInsurance: Boolean = false
) {
    init {
        require(!startDate.isAfter(endDate)) { "Start date cannot be after end date" }
        if (travelInsurance) {
            require(tickets.isNotEmpty()) { "Travel insurance requires at least one event ticket" }
        }
    }
}
