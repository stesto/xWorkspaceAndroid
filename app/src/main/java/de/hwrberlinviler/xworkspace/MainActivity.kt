package de.hwrberlinviler.xworkspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import de.hwrberlinviler.xworkspace.ui.login.LoginActivity
import retrofit2.Retrofit
import com.google.gson.Gson
import de.hwrberlinviler.xworkspace.api.IXWorkspace
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val XWS_BASE_URL= "http://10.50.200.50/xws/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAllRooms()
    }

    fun getAllRooms() {
        val retro = Retrofit.Builder()
            .baseUrl(XWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IXWorkspace::class.java)

        GlobalScope.launch {
            val rooms = retro.getRooms()
            if (rooms != null) {
                Log.d("Rooms code:", rooms.code().toString())
                Log.d("Rooms json: ", rooms.body().toString())
                Log.d("Rooms Count:", rooms.body()?.count().toString())
            }
        }
    }
}