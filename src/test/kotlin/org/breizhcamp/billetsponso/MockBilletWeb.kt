package org.breizhcamp.billetsponso

import spark.kotlin.Http
import spark.kotlin.ignite

fun main() {
    val http: Http = ignite()
    http.port(9080)
            .threadPool(4, 2, 3600)

    http.get("/api/event/:eventId/attendees") {
        //BilletWeb always respond with text/html content even for json :(
        response.header("Content-Type", "text/html;charset=utf-8")

        val user = request.queryParams("user")
        val key = request.queryParams("key")
        val version = request.queryParams("version")
        if (user == null || key == null || version == null) return@get "NOT AUTHENTICATED"

        val ticket = request.queryParams("ticket")
        javaClass.getResourceAsStream("/attendees/$ticket.json").copyTo(response.raw().outputStream)
        ""
    }
}