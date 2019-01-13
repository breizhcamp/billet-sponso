package org.breizhcamp.billetsponso.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.breizhcamp.billetsponso.AppProps
import org.breizhcamp.billetsponso.dto.Sponsor
import org.springframework.stereotype.Service
import java.io.File
import javax.annotation.PostConstruct

/**
 * Retrieve sponsors and commands
 */
@Service
class SponsoSrv(val appProps: AppProps, val objectMapper: ObjectMapper) {

    private val sponsors: MutableList<Sponsor> = ArrayList()

    @PostConstruct
    fun loadSponsors() {
        sponsors.addAll(objectMapper.readValue<List<Sponsor>>(File(appProps.sponsorsFile)))
    }

    /**
     * @return Sponsor from his token or null if not found
     */
    fun getSponsor(token: String) = sponsors.firstOrNull { it.token == token }
}