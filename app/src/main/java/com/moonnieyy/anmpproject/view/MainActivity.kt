package com.moonnieyy.anmpproject.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.moonnieyy.anmpproject.R
import com.moonnieyy.anmpproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        navController = navHost.navController

        // set toolbar agar bisa muncul tombol drawer
        setSupportActionBar(binding.toolbar)

        // fragment yang jadi top-level (tidak tampil tombol back)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentUkur, R.id.fragmentData, R.id.fragmentProfilAnak),
            binding.drawerLayout
        )

        // hubungkan toolbar dengan navController dan drawer
        setupActionBarWithNavController(navController, appBarConfiguration)

        // hubungkan bottomNav dan navView (drawer) dengan navController
        binding.bottomNav.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)

        // sembunyikan bottomNav & toolbar di halaman login / register
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.fragmentLoginActivity || destination.id == R.id.fragmentRegisterActivity) {
                binding.bottomNav.visibility = View.GONE
                supportActionBar?.hide()
            } else {
                binding.bottomNav.visibility = View.VISIBLE
                supportActionBar?.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
