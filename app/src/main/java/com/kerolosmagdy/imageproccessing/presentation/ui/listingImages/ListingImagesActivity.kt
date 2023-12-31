package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.model.Result
import com.kerolosmagdy.imageproccessing.data.util.Common
import com.kerolosmagdy.imageproccessing.data.util.ConstantLinks
import com.kerolosmagdy.imageproccessing.databinding.ActivityListingImagesBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity
import com.kerolosmagdy.imageproccessing.presentation.ui.imageDetails.ImageDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class ListingImagesActivity : BaseActivity() {
    private lateinit var binding: ActivityListingImagesBinding
    private lateinit var adapter: ImagesAdapter
    private var imageList: List<Result> = ArrayList()
    @Inject
    lateinit var charactersFactory: CharactersViewModelFactory
    private lateinit var charactersViewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClick()
    }

    private fun init() {
        search()
        charactersViewModel = ViewModelProvider(this, charactersFactory)[CharactersViewModel::class.java]
        getBrand(getOffsetByPage(0))

//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.im_colors,
//                "Logo colors "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.im_cat,
//                "Logo Logo Logo Logo Logo Logo Logo Logo cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat cat"
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo cat cat "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )
//        (imageList as ArrayList).add(
//            ImagesModel(
//                R.drawable.ic_logo_jpg,
//                "Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo Logo "
//            )
//        )

    }

    private fun onClick() {

    }


    private fun getBrand(offset: String){

        charactersViewModel.getCharacters(offset).observe(this) {
//            if (it.data!!.results.isNotEmpty()) {
//                setupRecycler(it.data!!.results)
//            }
        }

    }

    private fun setupRecycler(list: List<Result>) {
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
                val list: List<Result> = ArrayList()
                for (product in imageList) {
                    if (product.description.lowercase(Locale.getDefault()).trim()
                            .contains(txt ?: "".lowercase(Locale.getDefault()).trim())
                    ) {
                        (list as ArrayList).add(product)
                    }
                }
                setupRecycler(list)
                return true
            }
        })
    }

    private fun getOffsetByPage(page: Int): String{
        return (if(page == 1) 0 else (page-1) * (ConstantLinks.ITEMS_PER_PAGE)).toString()
    }
}