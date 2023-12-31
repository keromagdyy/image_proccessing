package com.kerolosmagdy.imageproccessing.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.databinding.ActivitySplashBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity
import com.kerolosmagdy.imageproccessing.presentation.ui.listingImages.ListingImagesActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    private var logoAnim: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        logoAnim = AnimationUtils.loadAnimation(baseContext, R.anim.logo_anim)
        binding.imLogo.animation = logoAnim

        lifecycleScope.launch {
            delay(2000)
            startActivity(
                Intent(
                    this@SplashActivity,
                    ListingImagesActivity::class.java
                )
            )
            finish()
        }
    }
}