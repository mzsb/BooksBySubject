package hu.mzsb.booksbysubject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.mzsb.booksbysubject.data.local.dao.BookDao
import hu.mzsb.booksbysubject.data.local.models.*

@Database(
    entities = [RoomBook::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

}