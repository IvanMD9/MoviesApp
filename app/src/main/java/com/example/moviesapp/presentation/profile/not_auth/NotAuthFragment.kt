package com.example.moviesapp.presentation.profile.not_auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.data.storage.SessionManager
import com.example.moviesapp.databinding.FragmentNotAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotAuthFragment : Fragment() {

    private var _binding: FragmentNotAuthBinding? = null
    private val binding: FragmentNotAuthBinding
        get() = _binding ?: throw RuntimeException("FragmentNotAuthBinding == null")

    private val viewModel: ProfileViewModel by viewModels()
    private val sessionManager: SessionManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotAuthBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateData()
        clickEnter()
    }
    private fun validateData() {
        binding.etAuthLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(login: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onAuthEvent(AuthEvent.ChangedLogin(login.toString()))
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.etAuthPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(password: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onAuthEvent(AuthEvent.ChangedPassword(password.toString()))
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }
    @SuppressLint("ResourceType")
    private fun clickEnter() {
        binding.btnAuthSignUp.setOnClickListener {
            viewModel.onAuthEvent(AuthEvent.ClickEnter)
            val sessionId = sessionManager?.getSessionId()
            if (sessionId == null) {

            } else {
                Toast.makeText(context, "SessionId = $sessionId", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}