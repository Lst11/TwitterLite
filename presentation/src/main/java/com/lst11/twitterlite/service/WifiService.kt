package com.lst11.twitterlite.service

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class WifiService : Service() {

    private var mBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): WifiService {
            return WifiService()
        }
    }

    val localBroadcastManager = LocalBroadcastManager.getInstance(this)

    private val myBroadcastReceicer = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.getIntExtra(
                    WifiManager.EXTRA_WIFI_STATE,
                    0
                ) == WifiManager.WIFI_STATE_ENABLED
            ) {
                val localIntent = Intent("NETWORK_AVAILABLE_YES")
                localBroadcastManager.sendBroadcast(localIntent)
                LocalBroadcastManager.getInstance(baseContext).sendBroadcast(intent)
                Log.d("AAA", "NETWORK_AVAILABLE : YES")
            } else {
                val localIntent = Intent("NETWORK_AVAILABLE_NO")
                localBroadcastManager.sendBroadcast(localIntent)
                LocalBroadcastManager.getInstance(baseContext).sendBroadcast(intent)
                Log.d("AAA", "NETWORK_AVAILABLE : NO")
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("AAA", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.e("AAA", "onBind")
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(myBroadcastReceicer, intentFilter)
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e("AAA", "onUnbind")
        unregisterReceiver(myBroadcastReceicer)
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.e("AAA", "onDestroy")
        super.onDestroy()
    }

    fun changeWifiStatus(wifiManager: WifiManager) {
        wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
    }
}
