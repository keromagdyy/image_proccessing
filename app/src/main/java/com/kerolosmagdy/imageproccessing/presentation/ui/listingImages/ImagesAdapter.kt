package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.model.Result
import com.kerolosmagdy.imageproccessing.databinding.LayoutImagesBinding

class ImagesAdapter(
    private val onClick: (image: Result, position: Int) -> Unit,
) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private var imagesList = ArrayList<Result>()

    fun setData(image: List<Result>) {
        imagesList = image as ArrayList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result, position: Int) {

            binding.img.load(item.resourceURI) {
                crossfade(true)
                placeholder(R.drawable.ic_logo)
            }
            binding.txtTitle.text = truncateCaption(item.description)

            itemView.setOnClickListener {
                onClick(item, position)
            }

        }

        private fun truncateCaption(caption: String): String {
            val maxLength = 80

            return if (caption.length <= maxLength) {
                caption
            } else {
                caption.substring(0, maxLength - 1) + "…"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutImagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        imagesList?.get(position)?.let { holder.bind(it, position) }
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

}