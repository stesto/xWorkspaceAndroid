package de.hwrberlinviler.xworkspace.api

import de.hwrberlinviler.xworkspace.data.model.Raum
import de.hwrberlinviler.xworkspace.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IXWorkspace {
    @GET("get_raum.php")
    suspend fun getRooms(): Response<List<Raum>>

    @GET("get_raum.php")
    suspend fun getRooms(@Query("datum") datum: String, @Query("von") von: String, @Query("bis") bis: String): Response<List<Raum>>

    @GET("set_reservierung.php")
    suspend fun addReservation(@Query("benutzerId") userId: Int, @Query("raumId") raumId: Int, @Query("datum") datum: String, @Query("von") von: String, @Query("bis") bis: String, @Query("cmd") cmd: String = "new"): Response<Unit>

    @GET("get_reservierung.php")
    suspend fun getReservierungen(@Query("benutzerId") userId: Int): Response<List<Raum>>

    @GET("user.php")
    suspend fun loginUser(@Query("Name") Name: String, @Query("Password") Password: String): Response<List<User>>
}