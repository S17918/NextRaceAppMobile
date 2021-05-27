package nextrace.app.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import nextrace.app.R

class SearchByDateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_by_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val spinnerYear: Spinner = view.findViewById(R.id.spinner_years)
        val spinnerMonth: Spinner = view.findViewById(R.id.spinner_months)

        ArrayAdapter.createFromResource(this, R.s)
    }

}
