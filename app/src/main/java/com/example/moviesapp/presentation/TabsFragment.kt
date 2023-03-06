package com.example.moviesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.moviesapp.R
import com.example.moviesapp.data.storage.SessionManager
import com.example.moviesapp.databinding.FragmentTabsBinding
import com.example.moviesapp.presentation.profile.not_auth.ProfileViewModel
import com.example.moviesapp.presentation.profile.not_auth.StateAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabsFragment : Fragment() {

    private lateinit var binding: FragmentTabsBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val sessionManager: SessionManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.tabs_container) as NavHostFragment
        val navController = navHost.navController
        val navGraph = navController.navInflater.inflate(R.navigation.profile_graph)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        val authState = viewModel.stateAuth
        sessionManager?.getSessionId()

        when (authState.value) {
            is StateAuth.Auth -> {
                navGraph.setStartDestination(R.id.authFragment)
            }
            is StateAuth.NotAuth -> {
                navGraph.setStartDestination(R.id.profileFragment)
            }
            is StateAuth.Initial -> {}
        }
    }
}