package com.katofuji.challengeone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity()
{
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        mNavController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean
    {
        return mNavController.navigateUp() || super.onSupportNavigateUp()
    }
}