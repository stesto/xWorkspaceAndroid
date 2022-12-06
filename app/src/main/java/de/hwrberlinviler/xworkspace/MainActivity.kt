package de.hwrberlinviler.xworkspace

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import de.hwrberlinviler.xworkspace.data.RaumAdapter
import de.hwrberlinviler.xworkspace.data.XWorkspaceAPI
import de.hwrberlinviler.xworkspace.data.model.Raum
import de.hwrberlinviler.xworkspace.data.model.StaticUser
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val roomsResponse = XWorkspaceAPI.client.getReservierungen(StaticUser.User!!.ID);
            if (roomsResponse.isSuccessful) {
                Log.d("Raum", roomsResponse.body().toString())
                showRooms(roomsResponse.body()!!)
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rooms_recycler_view);

        recyclerView.setOnClickListener {
            startActivity(Intent(this, RoomDetails::class.java))
        }
    }

    private fun showRooms(rooms: List<Raum>) {
        val recyclerView: RecyclerView = findViewById(R.id.rooms_recycler_view)
        recyclerView.adapter = RaumAdapter(rooms)
    }
}