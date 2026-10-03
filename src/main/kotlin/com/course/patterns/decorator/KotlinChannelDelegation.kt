package com.course.patterns.decorator

interface NotificationChannel {
    fun send(message: String): String
    fun getStatus(): String
    fun getChannelId(): String
}

class StandardEmailChannel : NotificationChannel {
    override fun send(message: String): String = "Email sent: $message"
    override fun getStatus(): String = "ONLINE"
    override fun getChannelId(): String = "SMTP_PROD"
}

/**
 * MODERN KOTLIN EVOLUTION:
 * Class delegation via `by` keyword:
 * All methods of NotificationChannel are forwarded to `delegate` automatically.
 * We override ONLY the specific method that requires decoration!
 */
class AuditedNotificationChannel(
    private val delegate: NotificationChannel
) : NotificationChannel by delegate {

    override fun send(message: String): String {
        val originalResult = delegate.send(message)
        return "[AUDIT_LOG] $originalResult"
    }
}
