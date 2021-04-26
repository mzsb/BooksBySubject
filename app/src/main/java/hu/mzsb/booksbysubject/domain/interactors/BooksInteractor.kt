package hu.mzsb.booksbysubject.domain.interactors

import android.content.Context
import hu.mzsb.booksbysubject.data.local.LocalDataSource
import hu.mzsb.booksbysubject.data.network.NetworkDataSource
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import hu.mzsb.booksbysubject.util.network.isInternetAvailable
import javax.inject.Inject

class BooksInteractor @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
    private val context: Context
) {

    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<DomainBook> {

        var i = context.isInternetAvailable
        return networkDataSource.getBooksBySubject(subject)
    }
}