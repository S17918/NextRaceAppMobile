package nextrace.app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import nextrace.app.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MainPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setSubtitle()
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onPause() {
        super.onPause()
        clearSubtitle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearSubtitle()
    }

    private fun getDate(): String{
        val cal = Calendar.getInstance()
        val date = SimpleDateFormat("LLLL", Locale.forLanguageTag("EN"))
        val month = date.format(cal.time)
        return month.substring(0, 1).toUpperCase() + month.substring(1)
    }

    private fun setSubtitle(){
        (activity as AppCompatActivity).supportActionBar!!.subtitle = getDate()
    }

    private fun clearSubtitle(){
        val subtitle: String = resources.getString(R.string.null_string)
        (activity as AppCompatActivity).supportActionBar!!.subtitle = subtitle
    }

}
