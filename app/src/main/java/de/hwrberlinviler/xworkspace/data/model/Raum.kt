package de.hwrberlinviler.xworkspace.data.model

data class Raum(
    val ID: Int,
    val Nummer: String,
    val Straße: String,
    val HausNr: String,
    val Ort: String,
    val PLZ: String,
    val features: List<Feature>
)
