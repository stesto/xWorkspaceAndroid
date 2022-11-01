package de.hwrberlinviler.xworkspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Retrofit
import de.hwrberlinviler.xworkspace.api.IXWorkspace
import de.hwrberlinviler.xworkspace.data.RaumAdapter
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val XWS_BASE_URL= "http://10.50.200.50/xws/api/"

    //val current_rooms: Array<Raum> = Array<Raum>(0) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            getAllRooms()
        }
    }

    suspend fun getAllRooms() {
        val retro = Retrofit.Builder()
            .baseUrl(XWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IXWorkspace::class.java)

        val roomsResponse = retro.getRooms()
        if (roomsResponse == null || !roomsResponse.isSuccessful)
            return

        Log.d("Got Rooms:", roomsResponse.body()?.size.toString())

        val recyclerView: RecyclerView = findViewById(R.id.rooms_recycler_view)
        recyclerView.adapter = RaumAdapter(roomsResponse.body()!!)


        /*GlobalScope.launch {
            val rooms = retro.getRooms()
            if (rooms != null && rooms.isSuccessful) {
                val roomsList = rooms.body()

                val recyclerView: RecyclerView = findViewById(R.id.rooms_recycler_view)
                recyclerView.adapter = roomsList?.let { RaumAdapter(it) }
            }
        }*/
    }
}