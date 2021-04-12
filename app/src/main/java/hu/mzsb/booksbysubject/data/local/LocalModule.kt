package hu.mzsb.booksbysubject.data.local

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSource = LocalDataSource()
}