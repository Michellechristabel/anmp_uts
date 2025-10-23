package com.moonnieyy.anmpproject.view

import android.content.Context
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

        // setup listener untuk drawer menu items
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_logout -> {
                    performLogout()
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    binding.drawerLayout.closeDrawers()
                    true
                }
            }
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.fragmentLoginActivity || destination.id == R.id.fragmentRegisterActivity) {
                binding.bottomNav.visibility = View.GONE
                supportActionBar?.hide()
                binding.drawerLayout.closeDrawers()
                binding.drawerLayout.setDrawerLockMode(
                    androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
                )
            } else {
                binding.bottomNav.visibility = View.VISIBLE
                supportActionBar?.show()
                binding.drawerLayout.setDrawerLockMode(
                    androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
                )
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun performLogout() {
        // hapus status login dari SharedPreferences
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putBoolean("isLoggedIn", false)
            apply()
        }

        // navigasi ke login fragment
        navController.navigate(R.id.fragmentLoginActivity)
        binding.drawerLayout.closeDrawers()
    }
}
