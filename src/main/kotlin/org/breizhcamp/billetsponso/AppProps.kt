package org.breizhcamp.billetsponso

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Configuration for application
 */
@ConfigurationProperties("billets")
class AppProps {

    lateinit var sponsorsFile: String
    lateinit var ticketsTypeId: List<String>
    val billetweb = BilletWeb()

    class BilletWeb {
        var url = "https://www.billetweb.fr/api/"
        lateinit var user: String
        lateinit var key: String
    }

}