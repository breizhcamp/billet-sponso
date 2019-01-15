package org.breizhcamp.billetsponso

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration for application
 */
@ConfigurationProperties("billets")
class AppProps {

    var sponsorsFile = "src/test/resources/sponsors.json"
    var ticketsTypeId = listOf("combo", "exposants")

    val billetweb = BilletWeb()
    class BilletWeb {
        var url = "http://localhost:9080/"
        var eventId = "12345"

        lateinit var user: String
        lateinit var key: String
    }

}