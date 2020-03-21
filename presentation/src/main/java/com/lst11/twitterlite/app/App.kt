package com.lst11.twitterlite.app

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.lst11.twitterlite.dagger.AppComponent
import com.lst11.twitterlite.dagger.DaggerAppComponent
import com.lst11.twitterlite.dagger.PresenterModule


class App : MultiDexApplication() {

    companion object {
        lateinit var instance: App
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .presenterModule(PresenterModule(this))
            .build()
    }

    fun getInstance(): App {
        return instance
    }
}
