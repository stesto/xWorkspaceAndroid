package de.hwrberlinviler.xworkspace.data

import de.hwrberlinviler.xworkspace.api.IXWorkspace
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class XWorkspaceAPI {
    companion object {
        private val XWS_BASE_URL= "http://10.50.200.50/xws/api/"
        val client: IXWorkspace = Retrofit.Builder()
            .baseUrl(XWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IXWorkspace::class.java)
    }
}