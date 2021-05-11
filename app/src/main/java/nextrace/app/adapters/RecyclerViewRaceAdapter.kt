package nextrace.app.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import nextrace.app.R
import nextrace.app.listeners.ClickListener
import nextrace.app.models.Event
import nextrace.app.models.Race
import nextrace.app.transporters.RaceTransporter

class RecyclerViewRaceAdapter(val raceList: List<Race>, val clickListener: ClickListener): RecyclerView.Adapter<RecyclerViewRaceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_race, parent, false))
    }

    override fun getItemCount(): Int {
        return raceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val race: Race = raceList[position]
        holder.bind(race)

        holder.itemView.setOnClickListener {
            val raceObject = RaceTransporter(race)
            val controller: NavController = Navigation.findNavController(it)
            controller.navigate(R.id.race_details_fragment)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var raceName: TextView? = null
        private var trackName: TextView? = null
        private var raceDate: TextView? = null
        private var raceLocalTime: TextView? = null
        private var raceCETTime: TextView? = null
        private var raceLaps: TextView? = null
        private var categoryLogo: ImageView? = null
        private var countryFlag: ImageView? = null
        private var color1: FrameLayout? = null
        private var color2: FrameLayout? = null
        private var cardView: CardView? = null
        private var raceEvents: MutableList<Event>? = null

        init {
            raceName = itemView.findViewById(R.id.race_name)
            trackName = itemView.findViewById(R.id.track_name)
            raceDate = itemView.findViewById(R.id.race_date)
            raceLocalTime = itemView.findViewById(R.id.race_local_time)
            raceCETTime = itemView.findViewById(R.id.race_cet_time)
            raceLaps = itemView.findViewById(R.id.race_laps)
            categoryLogo = itemView.findViewById(R.id.race_category_logo)
            countryFlag = itemView.findViewById(R.id.race_country_flag)
            cardView = itemView.findViewById(R.id.item_race_view)
            color1 = itemView.findViewById(R.id.color_1)
            color2 = itemView.findViewById(R.id.color_2)
        }

        fun bind(race: Race){
            raceEvents = race.eventList.events

            raceEvents!!.forEach { it ->
                if(it.type == "Race"){
                    raceDate?.text = it.date
                    raceLocalTime?.text = it.localTime
                    raceCETTime?.text = it.cetTime
                }
            }

            Picasso.get().load(race.category.categoryLogo.toString()).into(categoryLogo)
            Picasso.get().load(race.track.country.getLink()).into(countryFlag)
            raceName?.text = race.raceName
            trackName?.text = race.track.name
            raceLaps?.text = race.laps.toString()
            color1?.setBackgroundColor(Color.parseColor(race.category.categoryHexColor))
            color2?.setBackgroundColor(Color.parseColor(race.category.categoryHexColor))
        }
    }
}