package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.os.Bundle
import android.content.Intent
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.model.ImagesModel
import com.kerolosmagdy.imageproccessing.databinding.ActivityListingImagesBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity
import com.kerolosmagdy.imageproccessing.presentation.ui.imageDetails.ImageDetailsActivity
import java.util.Locale

class ListingImagesActivity : BaseActivity() {
    private lateinit var binding: ActivityListingImagesBinding
    private lateinit var adapter: ImagesAdapter
    private var imageList: List<ImagesModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init() {
        search()

        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.im_colors,
                "Logo colors "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.im_cat,
                "Logo Logo Logo Logo Logo Logo Logo Logo cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat"
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo cat cat "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )
        (imageList as ArrayList).add(
            ImagesModel(
                R.drawable.ic_logo_jpg,
                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
            )
        )

        setupProductsRecycler(imageList)
    }

    private fun onClick() {

    }


    private fun setupProductsRecycler(list: List<ImagesModel>) {
        val layoutManager = GridLayoutManager(baseContext, 2)
        adapter = ImagesAdapter(
            onClick = { image, position ->
                val intent = Intent(baseContext, ImageDetailsActivity::class.java)
                intent.putExtra("image", image)
                startActivity(intent)
            }
        )
        adapter.setData(list)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
    }

    private fun search() {
        binding.txtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(txt: String?): Boolean {
                val list: List<ImagesModel> = ArrayList()
                for (product in imageList) {
                    if (product.txt.lowercase(Locale.getDefault()).trim()
                            .contains(txt ?: "".lowercase(Locale.getDefault()).trim())
                    ) {
                        (list as ArrayList).add(product)
                    }
                }
                setupProductsRecycler(list)
                return true
            }
        })
    }
}