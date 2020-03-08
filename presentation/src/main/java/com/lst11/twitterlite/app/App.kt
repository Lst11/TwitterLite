package com.lst11.twitterlite.app

import android.app.Application
import com.lst11.twitterlite.dagger.AppComponent
import com.lst11.twitterlite.dagger.DaggerAppComponent
import com.lst11.twitterlite.dagger.PresenterModule


class App : Application() {

    companion object {
        lateinit var instance: App
        @JvmStatic
        lateinit var appComponent: AppComponent
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
}
