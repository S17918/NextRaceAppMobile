package nextrace.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import nextrace.app.R

class DonateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        clearSubtitle()
        return inflater.inflate(R.layout.fragment_donate, container, false)
    }

    private fun clearSubtitle() {
        val subtitle: String = resources.getString(R.string.null_string)
        (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
    }

}
