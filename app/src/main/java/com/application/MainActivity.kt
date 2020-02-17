package com.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initNavGraph()
    }

    private fun initNavGraph(){
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.application_navigation)

        graph.startDestination = R.id.nav_swipe

        navController.graph = graph
    }
}