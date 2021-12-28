package com.wonjoong.gitgle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.wonjoong.gitgle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setNavigation()
    }

//    private fun setNavigation() {
//        val navController = findNavController(R.id.main_navigation)
//        binding.bnv.setupWithNavController(navController)
//    }
}