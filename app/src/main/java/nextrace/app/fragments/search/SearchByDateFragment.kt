package nextrace.app.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import nextrace.app.R
import java.time.LocalDate
import java.util.*

class SearchByDateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_by_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setSpinner(view)
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

}
