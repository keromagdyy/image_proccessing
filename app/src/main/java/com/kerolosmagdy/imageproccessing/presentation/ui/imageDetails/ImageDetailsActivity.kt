package com.kerolosmagdy.imageproccessing.presentation.ui.imageDetails

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.core.net.toUri
import androidx.palette.graphics.Palette
import coil.load
import com.bumptech.glide.Glide
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.model.Result
import com.kerolosmagdy.imageproccessing.databinding.ActivityImageDetailsBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity
import java.net.URL
import com.bumptech.glide.request.target.Target
import com.kerolosmagdy.imageproccessing.shared.convertImageUrlToBitmap
import java.util.concurrent.ExecutionException


class ImageDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityImageDetailsBinding
    private lateinit var imageModel: Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init() {
        imageModel = intent.getSerializableExtra("image") as Result

        val imgLink = "${imageModel.thumbnail.path}.${imageModel.thumbnail.extension}"
        if(checkPathOrLink(imgLink)) {
            binding.img.load(fixLink(imgLink)) {
                crossfade(true)
                placeholder(R.drawable.ic_logo)
            }
        } else {
            binding.img.setImageURI(imageModel.thumbnail.localPath!!.toUri())
            binding.txtTitle.setTextColor(getColor(R.color.black))
        }
        binding.txtTitle.text = imageModel.description

        try {
            convertImageUrlToBitmap(fixLink(imgLink)!!) { bitmap ->
                // Use the bitmap here
                if (bitmap != null) {
                    val palette = Palette.from(bitmap!!).generate()
                    val dominantColor = palette.getDominantColor(getColor(R.color.white))
                    binding.root.setBackgroundColor(dominantColor)
                    Log.d("vkjndjknvd", "init: ${0}")
                } else {
                    Log.d("vkjndjknvd", "init: ${1}")
                }
            }
        } catch (e: Exception) {}

    }

    private fun onClick() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    fun checkPathOrLink(input: String): Boolean {
        if (input.length >= 4) {
            val firstFourCharacters = input.substring(0, 4)
            return firstFourCharacters == "http"
        }
        return false
    }

    private fun fixLink(link: String?): String? {
        return link?.let {
            when {
                it.startsWith("https://") -> it
                it.startsWith("http://") -> it.replaceFirst("http://", "https://")
                else -> it // Not a valid URL, return as-is
            }
        }
    }

}