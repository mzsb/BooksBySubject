package hu.mzsb.booksbysubject

import co.zsmb.rainbowcake.config.Loggers
import co.zsmb.rainbowcake.config.rainbowCake
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.timber.TIMBER
import hu.mzsb.booksbysubject.di.ApplicationModule
import timber.log.Timber

class TestBooksBySubjectApplication : RainbowCakeApplication() {

    override lateinit var injector: TestComponent

    override fun setupInjector() {
        injector = DaggerTestComponent
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