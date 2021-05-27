package nextrace.app.fragments.search

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
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
import kotlin.collections.ArrayList

class SearchByTrackFragment : Fragment(), ClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_by_track, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val editText: EditText = view.findViewById(R.id.editText_track)
        val textView: TextView = view.findViewById(R.id.search_track_error_message)
        val button: Button = view.findViewById(R.id.button_find_track)
        button.setOnClickListener {
            val input: String = editText.text.toString()
            if(input.isNotEmpty()){
                getData(view, input)
            }else{
                clearRecyclerView(view)
                textView.text = context?.getString(R.string.search_error_message)
            }
            dismissKeyboard(requireActivity())
        }
    }

    private fun getData(view: View, input: String) {
        val raceApi: RaceApi = RaceApiClient().buildService(RaceApi::class.java)
        val textView: TextView = view.findViewById(R.id.search_track_error_message)
        val call: Call<List<Race>> = raceApi.getRacesByTrackName(input)
        call.enqueue(object : Callback<List<Race>>, ClickListener {
            override fun onFailure(call: Call<List<Race>>, t: Throwable) {
                Log.d("TAG", "Response = $t")
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<List<Race>>, response: Response<List<Race>>) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.item_race_list_by_track)
                val layoutManager = LinearLayoutManager(view.context)
                val raceList = response.body() as MutableList<Race>
                val finalRaceList: ArrayList<Race> = ArrayList()

                if(raceList.isNotEmpty()){
                    textView.text = context?.getString(R.string.null_string)
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
                }else{
                    textView.text = context?.getString(R.string.search_error_message)
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

    private fun clearRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.item_race_list_by_track)
        val layoutManager = LinearLayoutManager(view.context)
        val raceList = ArrayList<Race>()
        val raceAdapter = RecyclerViewRaceAdapter(raceList, this, context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = raceAdapter
    }

    private fun dismissKeyboard(activity: Activity){
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(null != activity.currentFocus)
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

    override fun onClick(pos: Int) {
        TODO("Not yet implemented")
    }

}
