package org.breizhcamp.billetsponso.services

import org.breizhcamp.billetsponso.AppProps
import org.breizhcamp.billetsponso.dto.Attendee
import org.springframework.stereotype.Service

/**
 * Call BilletWeb API
 */
@Service
class BilletWebSrv(val appProps: AppProps) {

    fun listAttendees(eventId: String) : List<Attendee> {
        return emptyList()
    }

}