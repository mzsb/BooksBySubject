package hu.mzsb.booksbysubject.di

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            RainbowCakeModule::class,
            ViewModelModule::class,
            PresenterModule::class,
            ApplicationModule::class
        ]
)
interface AppComponent : RainbowCakeComponent