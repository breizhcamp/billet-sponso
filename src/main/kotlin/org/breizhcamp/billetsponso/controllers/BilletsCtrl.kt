package org.breizhcamp.billetsponso.controllers

import org.breizhcamp.billetsponso.AppProps
import org.breizhcamp.billetsponso.services.BilletWebSrv
import org.breizhcamp.billetsponso.services.SponsoSrv
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.FileNotFoundException

@Controller
class BilletsCtrl(val billetWebSrv: BilletWebSrv, val sponsoSrv: SponsoSrv, val props: AppProps) {

    @GetMapping("/billets/{token}")
    fun list(@PathVariable token: String, model: Model) : String {
        val sponsor = sponsoSrv.getSponsor(token) ?: throw FileNotFoundException()
        val attendees = billetWebSrv.listAttendeesByTickets(sponsor.order)

        model["limitDate"] = props.limitDate
        model["sponsor"] = sponsor
        model["attendees"] = attendees

        val management = attendees.values.first().first().orderManagement
        model["management_url"] = management

        return "billets"
    }

    @ExceptionHandler(FileNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(): String {
        return "not-found"
    }
}