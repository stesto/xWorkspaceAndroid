package de.hwrberlinviler.xworkspace.api

import de.hwrberlinviler.xworkspace.data.model.Raum
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

public interface IXWorkspace {
    @GET("get_raum.php")
    suspend fun getRooms(): Response<List<Raum>>
}