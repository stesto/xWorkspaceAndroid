package de.hwrberlinviler.xworkspace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import de.hwrberlinviler.xworkspace.data.RaumAdapter
import de.hwrberlinviler.xworkspace.data.XWorkspaceAPI
import de.hwrberlinviler.xworkspace.data.model.Raum
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val roomsResponse = XWorkspaceAPI.client.getRooms()
            if (roomsResponse.isSuccessful) {
                showRooms(roomsResponse.body()!!)
            }
        }
    }

    private fun showRooms(rooms: List<Raum>) {
        val recyclerView: RecyclerView = findViewById(R.id.rooms_recycler_view)
        recyclerView.adapter = RaumAdapter(rooms)
    }
}