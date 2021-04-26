package hu.mzsb.booksbysubject.domain.interactors

import hu.mzsb.booksbysubject.data.local.LocalDataSource
import hu.mzsb.booksbysubject.data.network.NetworkDataSource
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import javax.inject.Inject

class BooksInteractor @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<DomainBook> {
        //TODO list books by subject and read from database or network
        return networkDataSource.getBooksBySubject(subject)
    }
}