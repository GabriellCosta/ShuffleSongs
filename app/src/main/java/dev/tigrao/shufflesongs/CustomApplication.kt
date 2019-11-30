package dev.tigrao.shufflesongs

import android.app.Application

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinModules().start()
    }
}
