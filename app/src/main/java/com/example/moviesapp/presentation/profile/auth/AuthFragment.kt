package com.example.moviesapp.presentation.profile.auth

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviesapp.databinding.FragmentAuthBinding
import com.example.moviesapp.domain.model.AuthUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    private val viewModel: AuthUserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
    }
    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.state.collect { result ->
                if (result.loading) {
                    binding.progressBarAuthUser.visibility = View.VISIBLE
                } else {
                    binding.progressBarAuthUser.visibility = View.GONE
                    result.authData?.let { initDetailView(it) }
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun initDetailView(authUser: AuthUser) {
        binding.firstSymbolAuthUserName.text = authUser.username.first().toString()
        binding.authUserName.text = authUser.username
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}