package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kerolosmagdy.imageproccessing.data.model.Result
import com.kerolosmagdy.imageproccessing.data.model.Thumbnail
import com.kerolosmagdy.imageproccessing.data.util.Common
import com.kerolosmagdy.imageproccessing.data.util.ConstantLinks
import com.kerolosmagdy.imageproccessing.databinding.ActivityListingImagesBinding
import com.kerolosmagdy.imageproccessing.databinding.LayoutSheetAddImageBinding
import com.kerolosmagdy.imageproccessing.presentation.base.BaseActivity
import com.kerolosmagdy.imageproccessing.presentation.ui.imageDetails.ImageDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class ListingImagesActivity : BaseActivity() {
    private lateinit var binding: ActivityListingImagesBinding
    private lateinit var adapter: ImagesAdapter
    private var imageList: List<Result> = ArrayList()
    private var newImg: String = ""

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
        binding.layoutDownload.setOnClickListener {
            if (adapter.getSelectedItems().isNotEmpty()) {
                saveImagesToInternalStorage(
                    binding.progressLoading,
                    getStringsImageList(adapter.getSelectedItems())
                )
            }
        }
        binding.btnAdd.setOnClickListener {
            chooseIdPhoto()
        }

    }

    private fun chooseIdPhoto() {
        ImagePicker.with(this)
            .crop()
            .compress(300)
            .maxResultSize(
                1080,
                1080
            )
            .start()
    }

    private fun getCharacters(offset: String) {
        binding.progressLoading.visibility = View.VISIBLE
        charactersViewModel.getCharacters(offset).observe(this) {
            if (it.code == 200) {
                imageList = it.data!!.results
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
                if (adapter.getSelectedItems().isNotEmpty()) {
                    binding.layoutDownload.visibility = View.VISIBLE
                } else {
                    binding.layoutDownload.visibility = View.GONE
                }
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
                val _list: List<Result> = ArrayList()
                for (item in imageList) {
                    if (item.description.lowercase(Locale.getDefault()).trim()
                            .contains(txt ?: "".lowercase(Locale.getDefault()).trim())
                    ) {
                        (_list as ArrayList).add(item)
                    }
                }
                setupRecycler(_list)
                return true
            }
        })
    }

    private fun getStringsImageList(list: List<Result>): List<String> {
        val imageList = ArrayList<String>()
        for (item in list) {
            val imgLink = "${item.thumbnail.path}.${item.thumbnail.extension}"
            imageList.add(fixLink(imgLink)!!)
        }
        return imageList
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


    private fun getOffsetByPage(page: Int): String {
        return (if (page == 1) 0 else (page - 1) * (ConstantLinks.ITEMS_PER_PAGE)).toString()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }

        val contentURI = data?.let { it.data }

        if (resultCode == RESULT_OK) {
            if (data != null && contentURI != null) {
                openAddLessonBottomSheet(contentURI)
            }
        } else {
            showToastSnack("something went wrong, Add Image Again", true)
        }
    }

    private fun openAddLessonBottomSheet(imgUri: Uri) {
        val dialog = BottomSheetDialog(this@ListingImagesActivity)
        val sheetBinding = LayoutSheetAddImageBinding.inflate(layoutInflater)
        dialog.setCancelable(true)

        sheetBinding.img.setImageURI(imgUri)
        dialog.setContentView(sheetBinding.root)
        dialog.show()

        sheetBinding.btnAdd.setOnClickListener {
            (imageList as ArrayList).add(
                0,
                Result(
                    description = sheetBinding.txtName.editText!!.text.toString(),
                    id = 100,
                    modified = "",
                    name = "",
                    thumbnail = Thumbnail(localPath = imgUri.toString())
                )
            )
            setupRecycler(imageList)

            dialog.dismiss()
        }

    }

}