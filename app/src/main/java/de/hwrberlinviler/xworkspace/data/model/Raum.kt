package de.hwrberlinviler.xworkspace.data.model

import java.util.Date

data class Raum(
    val ID: Int,
    val Nummer: String,
    val Straße: String,
    val HausNr: String,
    val Ort: String,
    val PLZ: String,
    val features: List<Feature>,

    //Resrvierungsstuff
    val Datum: String,
    val Von: String,
    val Bis: String,
)
