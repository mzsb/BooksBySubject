package hu.mzsb.booksbysubject.data.local

import co.zsmb.rainbowcake.withIOContext
import hu.mzsb.booksbysubject.data.local.dao.BookDao
import hu.mzsb.booksbysubject.data.local.models.RoomBook
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

//TODO inject Dao
class LocalDataSource @Inject constructor(
    private val bookDao: BookDao
) {
    suspend fun insertBooks(books: HashMap<DomainBook, DomainBookDetails>) = withIOContext {
        bookDao.insertBooks(books.map { pair ->
            RoomBook(
                    id = pair.key.id,
                    title = pair.key.title,
                    subject = pair.key.subject,
                    authorName = pair.key.authorName,
                    authorBio = pair.value.authorBio,
                    covers = pair.value.covers,
                    description = pair.value.description,
                    isRead = pair.key.isRead)
        })
    }

    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<DomainBook> = withIOContext {
        bookDao.getBooksBySubjectAndRead(subject.toLowerCase(Locale.ROOT), isRead).map(RoomBook::toDomainBook)
    }

    suspend fun getBooksBySubject(subject: String): List<DomainBook> = withIOContext {
        bookDao.getBooksBySubject(subject.toLowerCase(Locale.ROOT)).map(RoomBook::toDomainBook)
    }

    suspend fun setBookRead(bookId: String, isRead: Boolean) = withIOContext {
        bookDao.updateBookRead(bookId, isRead)
    }

    suspend fun getBookDetailsByBookId(bookId: String): DomainBookDetails = withIOContext {
        bookDao.getBookById(bookId).toDomainBookDetails()
    }

    suspend fun getBookRead(id: String) =
            bookDao.getBookRead(id)
}