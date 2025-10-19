package com.moonnieyy.anmpproject.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.moonnieyy.anmpproject.R
import com.moonnieyy.anmpproject.databinding.ActivityMainBinding
import com.moonnieyy.anmpproject.databinding.FragmentDataBinding
import com.moonnieyy.anmpproject.databinding.FragmentUkurBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.hostFragment) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }
}
