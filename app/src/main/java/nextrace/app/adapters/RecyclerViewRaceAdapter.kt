package nextrace.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nextrace.app.R
import nextrace.app.listeners.ClickListener
import nextrace.app.models.Event
import nextrace.app.models.Race

class RecyclerViewRaceAdapter(raceList: MutableList<Race>, clickListener: ClickListener): RecyclerView.Adapter<RecyclerViewRaceAdapter.ViewHolder>() {

    private lateinit var raceList: ArrayList<Race>
    private lateinit var eventList: MutableList<Event>
    private lateinit var clickListener: ClickListener

    fun setRaceList(races: ArrayList<Race>){
        this.raceList = races
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_race, parent, false))
    }

    override fun getItemCount(): Int {
        return raceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}