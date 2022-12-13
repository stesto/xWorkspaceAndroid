package de.hwrberlinviler.xworkspace

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import de.hwrberlinviler.xworkspace.data.XWorkspaceAPI
import de.hwrberlinviler.xworkspace.data.model.Raum
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationFilter : AppCompatActivity() {
    var selectedDate: LocalDate? = null
    var selectedFromTime: LocalTime? = null
    var selectedToTime: LocalTime? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_filter)

        val editDate = findViewById<EditText>(R.id.editDate)
        val editFromTime = findViewById<EditText>(R.id.editFromTime)
        val editToTime = findViewById<EditText>(R.id.editToTime)

        editDate.setOnClickListener { showDatePicker() }
        editDate.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    showDatePicker()
                }
            }
        }

        editFromTime.setOnClickListener { showTimePicker(editFromTime, true) }
        editFromTime.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    showTimePicker(editFromTime, true)
                }
            }
        }

        editToTime.setOnClickListener { showTimePicker(editToTime, false) }
        editToTime.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    showTimePicker(editToTime, false)
                }
            }
        }

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener {
            search()
        }
    }

    private fun showDatePicker() {
        val picker = DatePickerDialog(this)
        picker.setOnDateSetListener {
            view, year, month, dayOfMonth ->
            run {
                selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                val editDate = findViewById<EditText>(R.id.editDate)
                editDate.setText(selectedDate?.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
            }
        }
        picker.show()
    }

    private fun showTimePicker(textField: EditText, isFrom: Boolean) {
        val picker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener {
            view, hourOfDay, minute ->
            run {
                if (isFrom) {
                    selectedFromTime = LocalTime.of(hourOfDay, minute)
                    textField.setText(selectedFromTime?.format(DateTimeFormatter.ofPattern("HH:mm")) + " Uhr")
                }
                else {
                    selectedToTime = LocalTime.of(hourOfDay, minute)
                    textField.setText(selectedToTime?.format(DateTimeFormatter.ofPattern("HH:mm")) + " Uhr")
                }

            }
        }, 0, 0, true)
        picker.show()
    }

    private fun search() {
        if (selectedDate == null || selectedFromTime == null || selectedToTime == null)  {
            return
        }

        val intent = Intent(this, ReservationResults::class.java)
        intent.putExtra("selectedDate", selectedDate?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))!!)
        intent.putExtra("selectedFromTime", selectedFromTime?.format(DateTimeFormatter.ofPattern("HH:mm"))!!)
        intent.putExtra("selectedToTime", selectedToTime?.format(DateTimeFormatter.ofPattern("HH:mm"))!!)
        startActivity(intent);
    }
}