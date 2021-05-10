package hu.mzsb.booksbysubject.data.local.dao

import androidx.room.*
import hu.mzsb.booksbysubject.data.local.models.RoomBook

@Dao
interface BookDao {
    @Query("SELECT * FROM books WHERE subject = :subject AND isRead = :isRead")
    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<RoomBook>

    @Query("SELECT * FROM books WHERE subject = :subject")
    suspend fun getBooksBySubject(subject: String): List<RoomBook>

    @Query("SELECT * FROM books WHERE id = :bookId")
    suspend fun getBookById(bookId: String): RoomBook

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<RoomBook>)

    @Query("UPDATE books SET isRead=:isRead WHERE id = :bookId")
    suspend fun updateBookRead(bookId: String, isRead: Boolean)

    @Query("SELECT isRead FROM books WHERE id = :bookId")
    suspend fun getBookRead(bookId: String): Boolean?
}