package hu.mzsb.booksbysubject

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import dagger.Component
import hu.mzsb.booksbysubject.di.ApplicationModule
import hu.mzsb.booksbysubject.di.ViewModelModule
import hu.mzsb.booksbysubject.mock.MockLocalModule
import hu.mzsb.booksbysubject.mock.MockNetworkModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        RainbowCakeModule::class,
        ViewModelModule::class,
        MockNetworkModule::class,
        MockLocalModule::class,
        ApplicationModule::class
    ]
)
interface TestComponent : RainbowCakeComponent