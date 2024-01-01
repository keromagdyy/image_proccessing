package com.kerolosmagdy.imageproccessing.presentation.ui.imageDetails

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.palette.graphics.Palette
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.model.Result
import com.kerolosmagdy.imageproccessing.databinding.ActivityImageDetailsBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity
import java.net.URL

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
        binding.img.load(fixLink(imgLink)) {
            crossfade(true)
            placeholder(R.drawable.ic_logo)
        }
        binding.txtTitle.text = imageModel.description

        loadImageBitmap(imgLink) { bitmap ->
            if (bitmap != null) {
                val url = URL(imgLink)
                val inputStream = url.openStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)

                val palette = Palette.from(bitmap).generate()
                val dominantColor = palette.getDominantColor(getColor(R.color.white))
                binding.root.setBackgroundColor(dominantColor)

            } else {
                binding.root.setBackgroundColor(getColor(R.color.white))
            }
        }
        binding.root.setBackgroundColor(getColor(R.color.white))
    }

    private fun onClick() {

    }

    fun loadImageBitmap(imageUrl: String, callback: (Bitmap?) -> Unit) {
        Glide.with(baseContext)
            .asBitmap()
            .load(imageUrl)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    callback.invoke(resource)
                }
            })
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