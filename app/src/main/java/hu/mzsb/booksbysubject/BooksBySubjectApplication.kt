package hu.mzsb.booksbysubject

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.timber.TIMBER
import hu.mzsb.booksbysubject.di.AppComponent
import hu.mzsb.booksbysubject.di.ApplicationModule
import hu.mzsb.booksbysubject.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class BooksBySubjectApplication : RainbowCakeApplication() {

    override lateinit var injector: AppComponent

    override fun setupInjector() {
        injector = DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        rainbowCake {
            logger = Loggers.TIMBER
            isDebug = BuildConfig.DEBUG
        }

        Timber.plant(Timber.DebugTree())
    }
}