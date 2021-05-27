package nextrace.app.fragments.race.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import nextrace.app.R
import nextrace.app.transporters.RaceTransporter

class RaceDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setSubtitle()
        return inflater.inflate(R.layout.fragment_race_details, container, false)
    }

    private fun setSubtitle() {
        (activity as AppCompatActivity).supportActionBar?.subtitle = RaceTransporter.raceObject.raceName
    }
}
