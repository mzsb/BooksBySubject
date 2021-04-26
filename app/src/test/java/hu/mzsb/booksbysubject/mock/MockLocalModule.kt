package hu.mzsb.booksbysubject.mock

import dagger.Module
import dagger.Provides
import hu.mzsb.booksbysubject.data.local.dao.BookDao
import javax.inject.Singleton

@Module
class MockLocalModule {
    @Provides
    @Singleton
    fun provideBookDao(): BookDao = MockBookDao()
}