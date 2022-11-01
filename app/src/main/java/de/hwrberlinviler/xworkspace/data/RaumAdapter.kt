package de.hwrberlinviler.xworkspace.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hwrberlinviler.xworkspace.R
import de.hwrberlinviler.xworkspace.data.model.Raum

class RaumAdapter(val raumList: List<Raum>): RecyclerView.Adapter<RaumAdapter.RaumViewHolder>() {

    class RaumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtRaumNummer: TextView = itemView.findViewById(R.id.txtRaumNummer)
        private val txtRaumOrt: TextView = itemView.findViewById(R.id.txtRaumOrt)

        fun bind(raum: Raum) {
            txtRaumNummer.text = "Raum " + raum.Nummer
            txtRaumOrt.text = raum.Straße + " " + raum.HausNr + ", " + raum.PLZ + " " + raum.Ort
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_entry, parent, false)
        return RaumViewHolder(view);
    }

    override fun onBindViewHolder(holder: RaumViewHolder, position: Int) {
        holder.bind(raumList[position]);
    }

    override fun getItemCount(): Int {
        return raumList.size
    }

}