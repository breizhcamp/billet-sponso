package org.breizhcamp.billetsponso.dto

import java.time.LocalDateTime

/**
 * A BilletWeb attendee
 */
data class Attendee(
        val id: String,
        val firstname: String,
        val name: String,
        val email: String,
        val lastUpdate: LocalDateTime,
        val productManagement: String,
        val orderId: String,
        val orderExtId: String,
        val orderFirstname: String,
        val orderName: String,
        val orderEmail: String,
        val orderManagement: String
)