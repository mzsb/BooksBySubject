package hu.mzsb.booksbysubject.data.local

import co.zsmb.rainbowcake.withIOContext
import hu.mzsb.booksbysubject.data.local.dao.BookDao
import hu.mzsb.booksbysubject.data.local.models.RoomBook
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import java.util.*
import javax.inject.Inject

//TODO inject Dao
class LocalDataSource @Inject constructor(
    private val bookDao: BookDao
) {
    suspend fun insertBook(book: DomainBook, bookDetails: DomainBookDetails) = withIOContext {
        bookDao.insertBook(
            RoomBook(
                id = book.id,
                title = book.title,
                subject = book.subject,
                authorName = book.authorName,
                authorBio = bookDetails.authorBio,
                imageUrl = bookDetails.imageUrl,
                description = bookDetails.description,
                isRead = bookDetails.isRead)
        )
    }

    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<DomainBook> = withIOContext {
        bookDao.getBooksBySubjectAndRead(subject.toLowerCase(Locale.ROOT), isRead).map(RoomBook::toDomainBook)
    }

    suspend fun setBookRead(bookId: String, isRead: Boolean) = withIOContext {
        bookDao.updateBookRead(bookId, isRead)
    }

    suspend fun getBookDetailsByBookId(bookId: String): DomainBookDetails = withIOContext {
        bookDao.getBookById(bookId).toDomainBookDetails()
    }
}