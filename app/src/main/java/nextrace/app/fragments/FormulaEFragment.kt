package nextrace.app.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import nextrace.app.R
import nextrace.app.adapters.RecyclerViewRaceAdapter
import nextrace.app.api.RaceApi
import nextrace.app.api.RaceApiClient
import nextrace.app.listeners.ClickListener
import nextrace.app.models.Event
import nextrace.app.models.Race
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*

class FormulaEFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        clearSubtitle()
        return inflater.inflate(R.layout.fragment_formula_e, container, false)
    }

    private fun clearSubtitle() {
        val subtitle: String = resources.getString(R.string.null_string)
        (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getData(view)
    }

    private fun getData(view: View) {
        val raceApi: RaceApi = RaceApiClient().buildService(RaceApi::class.java)
        val call: Call<List<Race>> = raceApi.getRacesFormulaE()
        call.enqueue(object : Callback<List<Race>>, ClickListener {
            override fun onFailure(call: Call<List<Race>>, t: Throwable) {
                Log.d("TAG", "Response = $t")
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<List<Race>>, response: Response<List<Race>>) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.item_formulae_race_list)
                val layoutManager = LinearLayoutManager(view.context)
                val raceList = response.body() as MutableList<Race>
                val finalRaceList: ArrayList<Race> = ArrayList()

                raceList.forEach { race ->
                    val raceEvents: MutableList<Event> = race.eventList.events
                    raceEvents.forEach { event ->
                        val date = LocalDate.of(Integer.parseInt(event.date.substring(0,4)), Integer.parseInt(event.date.substring(5, 7)), Integer.parseInt(event.date.substring(8,10)))
                        val today = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH)+1, Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        if(date.isAfter(today)){
                            if(event.type == "Race"){
                                finalRaceList.add(race)
                            }
                        }
                    }
                }

                val raceAdapter = RecyclerViewRaceAdapter(finalRaceList, this, context)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = raceAdapter
            }

            override fun onClick(pos: Int) {
                TODO("Not yet implemented")
            }

        })
    }

}
