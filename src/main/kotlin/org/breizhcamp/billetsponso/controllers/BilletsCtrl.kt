package org.breizhcamp.billetsponso.controllers

import org.breizhcamp.billetsponso.services.BilletWebSrv
import org.breizhcamp.billetsponso.services.SponsoSrv
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.FileNotFoundException

@Controller
class BilletsCtrl(val billetWebSrv: BilletWebSrv, val sponsoSrv: SponsoSrv) {

    @GetMapping("/billets/{token}")
    fun list(@PathVariable token: String, model: Model) : String {
        val sponsor = sponsoSrv.getSponsor(token) ?: throw FileNotFoundException()
        val attendees = billetWebSrv.listAttendeesByTickets()

        model.addAttribute("sponsor", sponsor)
        model.addAttribute("attendees", attendees)

        return "billets"
    }

    @ExceptionHandler(FileNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(): String {
        return "not-found"
    }
}