package com.kerolosmagdy.imageproccessing.shared

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
       fun isNetworkAvailable():Boolean{
            if (baseContext == null) return false
            val connectivityManager = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            return true
                        }
                    }
                }
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            }
            return false

        }

        if (!isNetworkAvailable()){
//            MaterialAlertDialogBuilder(baseContext)
//                .setView(R.layout.no_internet_dialog)
//                .setBackground(resources.getDrawable(R.drawable.dialog_corner))
//                .setNeutralButton("الغاء") { dialog, which ->
//                    dialog.dismiss()
//
//                }
//                .setPositiveButton("أعادة المحاولة") { dialog, which ->
//                    isNetworkAvailable()
//                    dialog.dismiss()
//
//                }
//                .setCancelable(false)
//                .show()
        }
    }
}