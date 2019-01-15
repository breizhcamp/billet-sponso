package org.breizhcamp.billetsponso

import spark.kotlin.Http
import spark.kotlin.ignite

fun main(args: Array<String>) {
    val http: Http = ignite()
    http.port(9080)
            .threadPool(4, 2, 3600)

    http.get("/api/event/:eventId/attendees") {
        val ticket = request.queryParams("ticket")
        javaClass.getResourceAsStream("/attendees/$ticket.json").copyTo(response.raw().outputStream)
        ""
    }
}