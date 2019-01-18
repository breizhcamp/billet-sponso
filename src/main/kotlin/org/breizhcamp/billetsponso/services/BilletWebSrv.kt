package org.breizhcamp.billetsponso.services

import org.breizhcamp.billetsponso.AppProps
import org.breizhcamp.billetsponso.dto.Attendee
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

/**
 * Call BilletWeb API
 */
@Service
class BilletWebSrv(final val appProps: AppProps) {

    private val webclient = WebClient.create(appProps.billetweb.url)


    /**
     * Retrieve list of attendees, group by ticket label (ex: "Exposant")
     */
    fun listAttendeesByTickets(order: String) : Map<String, List<Attendee>> {

        val fluxes = appProps.ticketsTypeId.map { listAttendees(appProps.billetweb.eventId, it) }
        val attendees = Flux.merge(fluxes).collectList().block() ?: return emptyMap()

        return attendees.filter { it.orderExtId == order }.groupBy { it.ticket }
    }

    /**
     * Retrieve attendees for a ticket type
     */
    private fun listAttendees(eventId: String, ticket: String): Flux<Attendee> {
        return webclient.get()
                .uri { builder ->
                    builder.path("/api/event/{eventId}/attendees")
                            .queryParam("ticket", ticket)
                            .build(eventId)
                }
                .retrieve()
                .bodyToFlux(Attendee::class.java)
    }

}