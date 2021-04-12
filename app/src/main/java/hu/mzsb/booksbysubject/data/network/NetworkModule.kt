package hu.mzsb.booksbysubject.data.network

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideNetworkDataSource(): NetworkDataSource = NetworkDataSource()
}