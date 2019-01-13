package org.breizhcamp.billetsponso

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppProps::class)
class BilletSponsoApplication

fun main(args: Array<String>) {
	runApplication<BilletSponsoApplication>(*args)
}

