package nextrace.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import nextrace.app.fragments.MainPageFragment
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)

        drawerToggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.subtitle = getDate()
    }

    private fun getDate(): String{
        val cal = Calendar.getInstance()
        val date = SimpleDateFormat("LLLL", Locale.forLanguageTag("EN"))
        val month = date.format(cal.time)
        return month.substring(0, 1).toUpperCase() + month.substring(1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.home -> {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }else{
                    false
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.main_page -> { if(isValidDestination(R.id.main_page)) navController.navigate(R.id.main_page_fragment) }
            R.id.all_races -> { if(isValidDestination(R.id.all_races)) navController.navigate(R.id.all_races_fragment) }
            R.id.search -> { if(isValidDestination(R.id.search)) navController.navigate(R.id.search_fragment) }
            R.id.formula_1 -> { if(isValidDestination(R.id.formula_1)) navController.navigate(R.id.formula_1_fragment) }
            R.id.formula_2 -> { if(isValidDestination(R.id.formula_2)) navController.navigate(R.id.formula_2_fragment) }
            R.id.formula_3 -> { if(isValidDestination(R.id.formula_3)) navController.navigate(R.id.formula_3_fragment) }
            R.id.formula_e -> { if(isValidDestination(R.id.formula_e)) navController.navigate(R.id.formula_e_fragment) }
            R.id.dtm -> { if(isValidDestination(R.id.dtm)) navController.navigate(R.id.dtm_fragment) }
            R.id.previous_races -> { if(isValidDestination(R.id.previous_races)) navController.navigate(R.id.previous_races_fragment) }
            R.id.about -> { if(isValidDestination(R.id.about)) navController.navigate(R.id.about_fragment) }
            R.id.donate -> { if(isValidDestination(R.id.donate)) navController.navigate(R.id.donate_fragment) }
        }
        item.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isValidDestination(destination: Int): Boolean{
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination!!.id
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout)
    }
}
