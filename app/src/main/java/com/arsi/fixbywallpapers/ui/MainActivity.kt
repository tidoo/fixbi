package com.arsi.fixbywallpapers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.arsi.fixbywallpapers.R
import com.arsi.fixbywallpapers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        /**
         * using new splash screen api with backward compatibility
         * this splash screen is handled by a theme only and nothing else
         * there is a difference between show time of the screen,
         * ----depending on weather its a cold start/hot start/warm start.
         * ----which is handled internally and its not in our control
         * instance can be used to perform other handling such as animation ending listener
         */
        val splashScreen = installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

    }

    fun init() {

    }
}

