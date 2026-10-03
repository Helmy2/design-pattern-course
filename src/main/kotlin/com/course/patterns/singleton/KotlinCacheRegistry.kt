package com.course.patterns.singleton

/**
 * MODERN KOTLIN EVOLUTION:
 * Kotlin replaces 25+ lines of Double-Checked Locking boilerplate with a single `object` declaration.
 * The JVM guarantees thread-safe, classloader-level lazy initialization out of the box.
 */
object KotlinCacheRegistry {
    private val cache = mutableMapOf<String, String>()

    fun put(key: String, value: String) {
        cache[key] = value
    }

    fun get(key: String): String? = cache[key]
}
