package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.model.Result
import com.kerolosmagdy.imageproccessing.databinding.LayoutImagesBinding

class ImagesAdapter(
    private val onClick: (image: Result, position: Int) -> Unit,
    private val onLongClick: (image: Result, position: Int) -> Unit,
) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private var imagesList = ArrayList<Result>()
    private var selectedItems = HashSet<Result>()

    fun setData(image: List<Result>) {
        imagesList = image as ArrayList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: LayoutImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result, position: Int) {
            val imgLink = "${item.thumbnail.path}.${item.thumbnail.extension}"
            if(checkPathOrLink(imgLink)) {
                binding.img.load(fixLink(imgLink)) {
                    crossfade(true)
                    placeholder(R.drawable.ic_logo)
                }
            } else {
                binding.img.setImageURI(item.thumbnail.localPath!!.toUri())
            }

            if (checkNoCaption(item.description)) {
                binding.txtTitle.text = truncateCaption(item.description)
                binding.txtTitle.setBackgroundResource(R.drawable.have_caption_style)
            } else {
                item.description = "No Caption"
                binding.txtTitle.text = "No Caption"
                binding.txtTitle.setBackgroundResource(R.drawable.no_caption_style)
            }

            if (selectedItems.contains(item)) {
                binding.root.setBackgroundResource(R.drawable.selected_item_background)
            } else {
                binding.root.setBackgroundResource(0) // Set your default background here
            }


            itemView.setOnClickListener {
                if (selectedItems.isNotEmpty())
                    toggleSelection(item)
                else
                    onClick(item, position)
            }

            itemView.setOnLongClickListener {
                toggleSelection(item)
                onLongClick(item, position)
                return@setOnLongClickListener true
            }

        }

        private fun checkNoImage(img: String): Boolean {
            return !(img == "https://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available"
                    || img == "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available")
        }

        private fun checkNoCaption(txt: String): Boolean {
            return !(txt == "" || txt == null || txt == "No Caption")
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

        private fun truncateCaption(caption: String): String {
            val maxLength = 80

            return if (caption.length <= maxLength) {
                caption
            } else {
                caption.substring(0, maxLength - 1) + "…"
            }
        }

    }

    private fun toggleSelection(item: Result) {
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
        notifyItemChanged(imagesList.indexOf(item))
    }

    fun getSelectedItems(): List<Result> {
        return ArrayList(selectedItems)
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