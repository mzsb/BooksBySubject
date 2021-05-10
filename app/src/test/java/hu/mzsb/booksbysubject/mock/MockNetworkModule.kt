package hu.mzsb.booksbysubject.mock

import dagger.Module
import dagger.Provides
import hu.mzsb.booksbysubject.data.network.AuthorDetailsApi
import hu.mzsb.booksbysubject.data.network.BookDetailsApi
import hu.mzsb.booksbysubject.data.network.BooksApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MockNetworkModule {
    @Provides
    @Singleton
    fun provideBookDetailsApi(): BookDetailsApi = MockBookDetailsApi()

    @Provides
    @Singleton
    fun provideBooksApi(): BooksApi = MockBooksApi()

    @Provides
    @Singleton
    fun provideAuthorDetailsApi(): AuthorDetailsApi = MockAuthorDetailsApi()
}