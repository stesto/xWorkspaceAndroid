package de.hwrberlinviler.xworkspace.api

import de.hwrberlinviler.xworkspace.data.model.Raum
import de.hwrberlinviler.xworkspace.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IXWorkspace {
    @GET("get_raum.php")
    suspend fun getRooms(): Response<List<Raum>>

    @GET("user.php")
    suspend fun loginUser(@Query("Name") Name: String, @Query("Password") Password: String): Response<List<User>>
}