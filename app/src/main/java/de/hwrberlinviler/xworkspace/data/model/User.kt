package de.hwrberlinviler.xworkspace.data.model

data class User(val ID: Int, val Name: String)

class StaticUser {
    companion object {
        var User: User? = null;
    }
}