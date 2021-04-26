package hu.mzsb.booksbysubject.di

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import dagger.Component
import hu.mzsb.booksbysubject.data.local.LocalModule
import hu.mzsb.booksbysubject.data.network.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            RainbowCakeModule::class,
            ViewModelModule::class,
            NetworkModule::class,
            LocalModule::class,
            ApplicationModule::class
        ]
)
interface AppComponent : RainbowCakeComponent