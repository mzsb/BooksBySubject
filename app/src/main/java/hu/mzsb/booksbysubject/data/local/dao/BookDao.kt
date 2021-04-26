package hu.mzsb.booksbysubject.data.local.dao

import androidx.room.*
import hu.mzsb.booksbysubject.data.local.models.RoomBook

@Dao
interface BookDao {
    @Query("SELECT * FROM books WHERE subject = :subject AND isRead = :isRead")
    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<RoomBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: RoomBook) : Void

    @Query("UPDATE books SET isRead=:isRead WHERE id = :bookId")
    suspend fun updateBookRead(bookId: String, isRead: Boolean)
}