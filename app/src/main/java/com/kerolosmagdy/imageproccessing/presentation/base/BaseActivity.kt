package com.kerolosmagdy.imageproccessing.presentation.base

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.kerolosmagdy.imageproccessing.R
import com.kerolosmagdy.imageproccessing.data.db.pref.SharedPreferenceHelper
import java.io.IOException
import java.net.InetSocketAddress
import java.util.Locale
import javax.net.SocketFactory


open class BaseActivity : AppCompatActivity() {
    var dp = 0f

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR)
        super.onCreate(savedInstanceState, persistentState)
        dp = resources.displayMetrics.density

        setAppLocale(SharedPreferenceHelper.language ?: "en")

    }

    override fun onResume() {
        super.onResume()
    }

    fun showToastSnack(word: String?, flag: Boolean) {
        try {
            val layout = LayoutInflater.from(this).inflate(R.layout.snack_bar_layout, null, false)
            layout.setBackgroundColor(
                if (flag) this.resources.getColor(R.color.snack_red) else this.resources.getColor(
                    R.color.snack_green
                )
            )
            val image = layout.findViewById<ImageView>(R.id.image)
            image.setImageResource(if (flag) R.drawable.ic_error else R.drawable.ic_success)
            val text = layout.findViewById<TextView>(R.id.text)
            text.text = word
            text.setTextColor(this.resources.getColor(R.color.white))
            val parentLayout = findViewById<View>(android.R.id.content)
            val snackbar = Snackbar.make(parentLayout, "", BaseTransientBottomBar.LENGTH_SHORT)
            (snackbar.view as ViewGroup).removeAllViews()
            (snackbar.view as ViewGroup).addView(layout)
            val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            snackbar.view.setBackgroundColor(
                if (flag) this.resources.getColor(R.color.snack_red)
                else this.resources.getColor(R.color.snack_green)
            )
            snackbar.setBackgroundTint(
                if (flag) this.resources.getColor(R.color.snack_red)
                else this.resources.getColor(R.color.snack_green)
            )

            snackbar.view.layoutParams = params
            snackbar.view.setPadding(
                (16 * dp).toInt(),
                (10 * dp).toInt(),
                (16 * dp).toInt(),
                (10 * dp).toInt()
            )
            snackbar.show()
        } catch (e: Exception) {

        }
    }

    fun showToast(str: String, flag: Boolean) {
        val toast = Toast(baseContext)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflater.inflate(R.layout.toast_layout, null)

        val text: TextView = layout.findViewById(R.id.toast_text)
        text.text = str

        toast.view = layout
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.TOP, 0, 100)

        // Set custom background color
        var backgroundColor: Int = if (flag)
            resources.getColor(R.color.snack_red)
        else {
            resources.getColor(R.color.snack_green)
        }
        val background = layout.background
        background.setTint(backgroundColor)

        toast.show()

    }

    fun showDialog(title: String, message: String, isCancelable: Boolean): AlertDialog.Builder {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@BaseActivity)

        builder.setTitle(title)
        builder.setMessage(message)
        builder.setCancelable(isCancelable)
        return builder
    }


    fun validateIncreaseQuantity(qty: Int, max: Int): Boolean {
        return (qty < max)
    }

    fun validateDecreaseQuantity(qty: Int): Boolean {
        return (qty > 1)
    }

//    fun onTokenExpired(message: String) {
//        showDialog("Warning!", message, true)
//            .setPositiveButton("Ok") { dialog: DialogInterface, i: Int ->
//                SharedPreferenceHelper.let { sharedPref ->
//                    SharedPreferenceHelper.isLogged = false
//                    SharedPreferenceHelper.isWelcomeShowed = false
//                    SharedPreferenceHelper.userObj = null
//                    SharedPreferenceHelper.membership = 0
//                    SharedPreferenceHelper.userToken = ""
//                }
//                startActivity(
//                    Intent(
//                        baseContext,
//                        AuthActivity::class.java
//                    )
//                )
//                finish()
//                dialog.dismiss()
//            }.create().show()
//
//    }

    fun showProgressDialog(view: View) {
        view.visibility = View.VISIBLE
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    fun hideProgressDialog(view: View) {
        view.visibility = View.GONE
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun setAppLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        SharedPreferenceHelper.language = lang

        resources.updateConfiguration(config, resources.displayMetrics)
    }

    fun checkConnection(): Boolean {
        // Make sure to execute this on a background thread.
        return try {
            val socket = SocketFactory.getDefault().createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }

}