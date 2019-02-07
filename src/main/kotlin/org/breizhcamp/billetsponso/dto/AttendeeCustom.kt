package org.breizhcamp.billetsponso.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AttendeeCustom(
        @JsonProperty("Twitter")
        val twitter: String,

        @JsonProperty("Entreprise")
        val entreprise: String,

        @JsonProperty("Taille T-Shirt")
        val tailleTShirt: String,

        @JsonProperty("Coupe T-Shirt")
        val coupeTShirt: String,

        @JsonProperty("Participation au meet'n'greet (repas jeudi soir) ?")
        val meetAndGreet: String,

        @JsonProperty("Repas végétarien")
        val vegetarien: String,

        @JsonProperty("Code postal")
        val codePostal: String,

        @JsonProperty("Pas de goodies")
        val noGoodies: String?
)