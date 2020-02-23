package org.breizhcamp.billetsponso.services

import com.fasterxml.jackson.databind.ObjectMapper
import org.breizhcamp.billetsponso.AppProps
import org.breizhcamp.billetsponso.dto.Attendee
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.stereotype.Service
import org.springframework.util.MimeType
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.nio.charset.StandardCharsets


/**
 * Call BilletWeb API
 */
@Service
class BilletWebSrv(final val appProps: AppProps, val objectMapper: ObjectMapper) {

    /** In order to override incorrect text/html returned by BilletWeb... */
    private val strategies = ExchangeStrategies.builder().codecs { clientCodecConfigurer ->
        clientCodecConfigurer.customCodecs().decoder(
                Jackson2JsonDecoder(objectMapper, MimeType("text", "html", StandardCharsets.UTF_8)))
    }.build()

    private val webclient = WebClient.builder()
            .exchangeStrategies(strategies)
            .baseUrl(appProps.billetweb.url).build()

    /**
     * Retrieve list of attendees, group by ticket label (ex: "Exposant")
     */
    fun listAttendeesByTickets(order: String) : Map<String, List<Attendee>> {

        val attendees = listAttendees(appProps.billetweb.eventId).collectList().block() ?: return emptyMap()

        return attendees.filter { it.orderId == order }.groupBy { it.ticket }
    }

    /**
     * Retrieve attendees for a ticket type
     */
    private fun listAttendees(eventId: String): Flux<Attendee> {
        return webclient.get()
                .uri { builder ->
                    builder.path("/api/event/{eventId}/attendees")
                            .queryParam("user", appProps.billetweb.user)
                            .queryParam("key", appProps.billetweb.key)
                            .queryParam("version", "1")
                            .build(eventId)
                }
                .retrieve()
                .bodyToFlux(Attendee::class.java)
    }
}