package hu.mzsb.booksbysubject.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import hu.mzsb.booksbysubject.data.local.dao.BookDao
import javax.inject.Singleton

@Module
class LocalModule {
    companion object {
        private const val DB_NAME = "books.db"
    }

    @Provides
    @Singleton
    fun provideBookDao(db: AppDatabase): BookDao = db.bookDao()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}