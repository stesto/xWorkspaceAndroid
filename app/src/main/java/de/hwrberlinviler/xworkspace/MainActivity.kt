package de.hwrberlinviler.xworkspace

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hwrberlinviler.xworkspace.data.RaumAdapter
import de.hwrberlinviler.xworkspace.data.XWorkspaceAPI
import de.hwrberlinviler.xworkspace.data.model.Raum
import de.hwrberlinviler.xworkspace.data.model.StaticUser
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchRooms()

        val addReservationButton = findViewById<FloatingActionButton>(R.id.fabReservation)
        addReservationButton.setOnClickListener {
            startActivity(Intent(this, ReservationFilter::class.java))
        }
    }

    private fun fetchRooms() {
        lifecycleScope.launch {
            val roomsResponse = XWorkspaceAPI.client.getReservierungen(StaticUser.User!!.ID);
            if (roomsResponse.isSuccessful) {
                Log.d("Raum", roomsResponse.body().toString())
                showRooms(roomsResponse.body()!!)
            }
        }
    }

    private fun showRooms(rooms: List<Raum>) {
        val recyclerView: RecyclerView = findViewById(R.id.rooms_recycler_view)
        recyclerView.adapter = RaumAdapter(rooms, this::onRoomClicked, this::onRoomLongClicked)
    }

    private fun onRoomClicked(raum: Raum) {

    }

    private fun onRoomLongClicked(raum: Raum) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Stornieren")
            .setMessage("Möchtest du den Raum " + raum.Nummer + " stornieren?")
            .setPositiveButton("Ja") { dialog, id ->

                lifecycleScope.launch {
                    val response = XWorkspaceAPI.client.deleteReservation(raum.ReservierungID)
                }

                Toast.makeText(this, "Raum wurde storniert", Toast.LENGTH_SHORT).show()
                fetchRooms()
                //startActivity(Intent(this, MainActivity::class.java))
            }
            .setNegativeButton("Nein") { dialog, id ->
                dialog.dismiss()
            }
            .show()
    }
}