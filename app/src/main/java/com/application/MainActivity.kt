package com.application

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.common_ui.NAV_PROFILE_DEEPLINK
import com.example.common_ui.NAV_SWIPE_DEEPLINK
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initNavGraph()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation(){
        main_activity_navigation.selectedItemId = R.id.swipe

        main_activity_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    if (navController.currentDestination?.id != R.id.profile) {
                        navController.navigate(Uri.parse(NAV_PROFILE_DEEPLINK))
                    }
                    true
                }
                R.id.swipe -> {
                    if (navController.currentDestination?.id != R.id.swipe) {
                        navController.navigate(Uri.parse(NAV_SWIPE_DEEPLINK))
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun initNavGraph(){
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.application_navigation)

        graph.startDestination = R.id.nav_swipe

        navController.graph = graph
    }
}