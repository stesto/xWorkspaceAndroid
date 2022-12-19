package de.hwrberlinviler.xworkspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import de.hwrberlinviler.xworkspace.data.RaumAdapter
import de.hwrberlinviler.xworkspace.data.XWorkspaceAPI
import de.hwrberlinviler.xworkspace.data.model.Raum
import de.hwrberlinviler.xworkspace.data.model.StaticUser
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

class ReservationResults : AppCompatActivity() {
    lateinit var date: String;
    lateinit var from: String;
    lateinit var to: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_results)

        date = intent.getStringExtra("selectedDate")!!
        from = intent.getStringExtra("selectedFromTime")!!
        to = intent.getStringExtra("selectedToTime")!!

        lifecycleScope.launch {
            val reservResponse = XWorkspaceAPI.client.getRooms(
                date,
                from,
                to
            )

            if (reservResponse.isSuccessful) {
                Log.d("Raum", reservResponse.body().toString())
                showResults(reservResponse.body()!!)
            }
        }
    }

    private fun showResults(rooms: List<Raum>) {
        if (rooms.isEmpty()) {
            // TODO: Show Info to user
        }

        val recyclerView: RecyclerView = findViewById(R.id.rooms_recycler_view)
        recyclerView.adapter = RaumAdapter(rooms, this::onRoomClicked, this::onRoomLongClicked)
    }

    private fun onRoomClicked(raum: Raum) {
        AlertDialog.Builder(this)
            .setTitle("Reservieren")
            .setMessage("Möchtest du den Raum " + raum.Nummer + " reservieren?")
            .setPositiveButton("Ja") { dialog, id ->

                lifecycleScope.launch {
                    val response = XWorkspaceAPI.client.addReservation(StaticUser.User!!.ID, raum.ID, date, from, to)
                }

                Toast.makeText(this, "Raum wurde reserviert", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
            .setNegativeButton("Nein") { dialog, id ->
                dialog.dismiss()
            }
            .show()
    }

    private fun onRoomLongClicked(raum: Raum) {

    }
}