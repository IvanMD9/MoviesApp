package com.example.moviesapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.main_container, TabsFragment())
            .commit()
    }
}