package space.abdilazov.youtubeapi

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Build.VERSION_CODES.N
import androidx.lifecycle.LiveData
import android.net.NetworkInfo as NetworkInfo

@Suppress("DEPRECATION")
class CheckInternet(private val context: Context): LiveData<Boolean>() {
    private var connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    private lateinit var checkCallback: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        update()
        when{
            Build.VERSION.SDK_INT >= N ->{

                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback())
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ->{
                networkRequest()
            }
            else -> {

                context.registerReceiver(
                    networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun networkRequest() {
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(
            requestBuilder.build(),
            connectivityManagerCallback())
    }

    private fun connectivityManagerCallback(): ConnectivityManager.NetworkCallback {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return object : ConnectivityManager.NetworkCallback() {

                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }
            }

        }else {
            throw IllegalAccessError("Error!")
        }
        }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            update()
        }
    }

                private fun update() {
                    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                    postValue(activeNetwork?.isConnected == true)
                }
            }
        