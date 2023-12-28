package com.kerolosmagdy.imageproccessing.presentation.ui.imageDetails

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import androidx.palette.graphics.Palette
import coil.load
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.model.ImagesModel
import com.kerolosmagdy.imageproccessing.databinding.ActivityImageDetailsBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity
import kotlin.random.Random

class ImageDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityImageDetailsBinding
    private lateinit var imageModel: ImagesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init() {
        imageModel = intent.getSerializableExtra("image") as ImagesModel

        binding.img.load(imageModel.img) {
            crossfade(true)
            placeholder(R.drawable.ic_logo)
        }
        binding.txtTitle.text = imageModel.txt

        val bitmap = BitmapFactory.decodeResource(resources, imageModel.img)

        val palette = Palette.from(bitmap).generate()
        val dominantColor = palette.getDominantColor(getColor(R.color.gray))
        binding.root.setBackgroundColor(dominantColor)
    }

    private fun onClick() {

    }


}