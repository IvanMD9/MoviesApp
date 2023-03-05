package com.example.moviesapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moviesapp.R
import com.example.moviesapp.data.storage.SessionManager
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.presentation.profile.not_auth.ProfileViewModel
import com.example.moviesapp.presentation.profile.not_auth.StateAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val sessionManager: SessionManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.btnNavigation.setupWithNavController(navController)

        val authState = viewModel.stateAuth
        sessionManager?.getSessionId()

        when (authState.value) {
            is StateAuth.Auth -> {
               navController.setGraph(R.navigation.nav_graph_auth)
            }
            is StateAuth.NotAuth -> {
                navController.setGraph(R.navigation.nav_graph)
            }
            is StateAuth.Initial -> {}
        }
    }
}