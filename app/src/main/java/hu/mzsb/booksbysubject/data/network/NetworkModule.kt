package hu.mzsb.booksbysubject.data.network

import dagger.Module
import dagger.Provides
import hu.mzsb.booksbysubject.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBookDetailsApi(retrofit: Retrofit): BookDetailsApi {
        return retrofit.create(BookDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBooksApi(retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthorDetailsApi(retrofit: Retrofit): AuthorDetailsApi {
        return retrofit.create(AuthorDetailsApi::class.java)
    }
}