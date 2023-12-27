package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.os.Bundle
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.databinding.ActivityListingImagesBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity

class ListingImagesActivity : BaseActivity() {
    private lateinit var binding: ActivityListingImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init() {

    }

    private fun onClick() {

    }
}