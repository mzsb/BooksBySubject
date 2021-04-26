package hu.mzsb.booksbysubject.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject


val Context.isInternetAvailable: Boolean
    get() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }