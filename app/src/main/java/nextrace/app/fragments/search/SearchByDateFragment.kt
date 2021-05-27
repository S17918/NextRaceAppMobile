package nextrace.app.fragments.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
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

class SearchByDateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_by_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setSpinner(view)
        val button: Button = view.findViewById(R.id.button_find_date)
        button.setOnClickListener{
            getData(view)
            dismissKeyboard(requireActivity())
        }
    }

    private fun getData(view: View) {
        val spinnerYear: Spinner = view.findViewById(R.id.spinner_years)
        val spinnerMonth: Spinner = view.findViewById(R.id.spinner_months)
        val raceApi: RaceApi = RaceApiClient().buildService(RaceApi::class.java)
        val textView: TextView = view.findViewById(R.id.search_date_error_message)
        val call: Call<List<Race>> = raceApi.getRacesByDate(spinnerYear.selectedItem.toString(), getMonthInt(spinnerMonth.selectedItem.toString()))
        call.enqueue(object : Callback<List<Race>>, ClickListener {
            override fun onFailure(call: Call<List<Race>>, t: Throwable) {
                Log.d("TAG", "Response = $t")
            }

            override fun onResponse(call: Call<List<Race>>, response: Response<List<Race>>) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.item_race_list_by_date)
                val layoutManager = LinearLayoutManager(view.context)
                val raceList = response.body() as MutableList<Race>

                if (raceList.isNotEmpty()) {
                    textView.text = context?.getString(R.string.null_string)
                } else {
                    textView.text = context?.getString(R.string.search_error_message)
                }

                val raceAdapter = RecyclerViewRaceAdapter(raceList, this, context)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = raceAdapter
            }

            override fun onClick(pos: Int) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setSpinner(view: View){
        val spinnerYear: Spinner = view.findViewById(R.id.spinner_years)
        val spinnerMonth: Spinner = view.findViewById(R.id.spinner_months)

        ArrayAdapter.createFromResource(view.context, R.array.years, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerYear.adapter = adapter
            spinnerYear.setSelection(adapter.getPosition(Calendar.getInstance().get(Calendar.YEAR).toString()))
        }

        ArrayAdapter.createFromResource(view.context, R.array.months, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMonth.adapter = adapter
            spinnerMonth.setSelection(adapter.getPosition(getMonth()))
        }
    }

    private fun getMonth(): String{
        println(Calendar.getInstance().get(Calendar.MONTH))
        when(Calendar.getInstance().get(Calendar.MONTH)){
            0 -> return "January"
            1 -> return "February"
            2 -> return "March"
            3 -> return "April"
            4 -> return "May"
            5 -> return "June"
            6 -> return "July"
            7 -> return "August"
            8 -> return "September"
            9 -> return "October"
            10 -> return "November"
            11 -> return "December"
        }
        return ""
    }

    private fun getMonthInt(input: String): String{
        when(input){
            "January" -> return "01"
            "February" -> return "02"
            "March" -> return "03"
            "April" -> return "04"
            "May" -> return "05"
            "June" -> return "06"
            "July" -> return "07"
            "August" -> return "08"
            "September" -> return "09"
            "October" -> return "10"
            "November" -> return "11"
            "December" -> return "12"
        }
        return ""
    }

    private fun dismissKeyboard(activity: Activity){
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(null != activity.currentFocus)
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
    }

}
