package hu.mzsb.booksbysubject.domain.interactors

import hu.mzsb.booksbysubject.data.local.LocalDataSource
import hu.mzsb.booksbysubject.data.network.NetworkDataSource
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import javax.inject.Inject

class BookDetailsInteractor @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) {

    fun setBookRead(bookId: String, isRead: Boolean) {
        //TODO set book read by book id in database or on network
        localDataSource.getBooksBySubjectAndRead(bookId, isRead)
    }

    fun getBookDetailsByBookId(bookId: String): DomainBookDetails {
        //TODO get book details by book id from database or network
        return localDataSource.getBookDetailsByBookId(bookId)
    }
}