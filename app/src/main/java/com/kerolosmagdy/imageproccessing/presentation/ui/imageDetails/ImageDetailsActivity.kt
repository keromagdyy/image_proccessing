package com.kerolosmagdy.imageproccessing.presentation.ui.imageDetails

import android.os.Bundle
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.databinding.ActivityImageDetailsBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity

class ImageDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityImageDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init() {

    }

    private fun onClick() {

    }
}