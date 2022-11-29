package de.hwrberlinviler.xworkspace.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.marginRight
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import de.hwrberlinviler.xworkspace.MainActivity
import de.hwrberlinviler.xworkspace.R
import de.hwrberlinviler.xworkspace.RoomDetails
import de.hwrberlinviler.xworkspace.data.model.Feature
import de.hwrberlinviler.xworkspace.data.model.Raum


class RaumAdapter(val raumList: List<Raum>): RecyclerView.Adapter<RaumAdapter.RaumViewHolder>() {

    class RaumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val layoutRaumEntry: ConstraintLayout = itemView.findViewById(R.id.layoutRaumEntry);
        private val txtRaumNummer: TextView = itemView.findViewById(R.id.txtRaumNummer)
        private val txtRaumOrt: TextView = itemView.findViewById(R.id.txtRaumOrt)
        private val linearlayout_feature: LinearLayout = itemView.findViewById(R.id.linearlayout_feature)

        fun bind(raum: Raum) {
            txtRaumNummer.text = "Raum " + raum.Nummer
            txtRaumOrt.text = raum.Straße + " " + raum.HausNr + ", " + raum.PLZ + " " + raum.Ort
            linearlayout_feature.removeAllViews()
            for (feature in raum.features) {

                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(0, 0, 15, 0)

                val feature_dynamic = TextView(linearlayout_feature.context)
                feature_dynamic.textSize = 12f
                feature_dynamic.setTextColor(ContextCompat.getColor(feature_dynamic.context,R.color.white))
                feature_dynamic.setPadding(15,5,15,5)
                feature_dynamic.setBackgroundResource(R.drawable.room_feature_frame)
                feature_dynamic.text = feature.Name
                //txtRaumFeature.text = "Feature:" + feature.Name
                linearlayout_feature.addView(feature_dynamic,layoutParams)
            }

            layoutRaumEntry.setOnClickListener {
                startActivity(linearlayout_feature.context, Intent(linearlayout_feature.context, RoomDetails::class.java), null);
            }
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