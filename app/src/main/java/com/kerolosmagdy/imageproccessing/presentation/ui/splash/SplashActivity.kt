package com.kerolosmagdy.imageproccessing.presentation.ui.splash

import android.os.Bundle
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.databinding.ActivitySplashBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init() {

    }

    private fun onClick() {

    }
}