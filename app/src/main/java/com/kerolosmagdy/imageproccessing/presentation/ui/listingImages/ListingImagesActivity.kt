package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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
        charactersViewModel =
            ViewModelProvider(this, charactersFactory)[CharactersViewModel::class.java]
        getCharacters("0")

    }

    private fun onClick() {
        binding.swipeRefresh.setOnRefreshListener {
            getCharacters("0")
        }
    }


    private fun getCharacters(offset: String) {
        binding.progressLoading.visibility = View.VISIBLE
        charactersViewModel.getCharacters(offset).observe(this) {
            if (it.code == 200) {
                setupRecycler(it.data!!.results)
            }
            binding.swipeRefresh.isRefreshing = false
            binding.progressLoading.visibility = View.GONE
        }

    }

    private fun setupRecycler(list: List<Result>) {
        val layoutManager = GridLayoutManager(baseContext, 2)
        adapter = ImagesAdapter(
            onClick = { image, position ->
                val intent = Intent(baseContext, ImageDetailsActivity::class.java)
                intent.putExtra("image", image)
                startActivity(intent)
            },
            onLongClick = { image, position ->

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

    private fun getOffsetByPage(page: Int): String {
        return (if (page == 1) 0 else (page - 1) * (ConstantLinks.ITEMS_PER_PAGE)).toString()
    }
}