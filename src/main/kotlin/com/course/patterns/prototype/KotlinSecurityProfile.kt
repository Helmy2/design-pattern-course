package com.course.patterns.prototype

/**
 * MODERN KOTLIN EVOLUTION:
 * Kotlin data classes synthesize .copy() out of the box, allowing targeted property mutation
 * while creating a fresh instance.
 */
data class KotlinSecurityProfile(
    val profileName: String,
    val maxBandwidthMbps: Int,
    val firewallRules: List<String>
)
