package com.example.myandroidpjforwork.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myandroidpjforwork.R
import com.example.myandroidpjforwork.bean.Person
import com.example.myandroidpjforwork.databinding.ActivityNavigationGraphViewBinding
import org.koin.android.ext.android.inject

class NavigationGraphViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationGraphViewBinding

    val person: Person by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationGraphViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = findNavController(R.id.nav_host_fragment_activity_navigation_graph_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        person.speak()
    }
}