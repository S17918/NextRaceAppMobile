package nextrace.app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import nextrace.app.R
import nextrace.app.adapters.RecyclerViewRaceAdapter
import nextrace.app.api.RaceApi
import nextrace.app.api.RaceApiClient
import nextrace.app.listeners.ClickListener
import nextrace.app.models.Race
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllRacesFragment : Fragment(), ClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_races, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getData(view)
    }

    private fun getData(view: View) {
        val raceApi: RaceApi = RaceApiClient().buildService(RaceApi::class.java)
        val call: Call<List<Race>> = raceApi.getRaces()
        call.enqueue(object : Callback<List<Race>>, ClickListener {
            override fun onFailure(call: Call<List<Race>>, t: Throwable) {
                Log.d("TAG", "Response = $t")
            }

            override fun onResponse(call: Call<List<Race>>, response: Response<List<Race>>) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.item_all_race_list)
                val layoutManager = LinearLayoutManager(view.context)
                val raceList = response.body() as MutableList<Race>
                val raceAdapter = RecyclerViewRaceAdapter(raceList, this)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = raceAdapter
            }

            override fun onClick(pos: Int) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onClick(pos: Int) {
        TODO("Not yet implemented")
    }
}
