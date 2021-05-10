package hu.mzsb.booksbysubject.domain.interactors

import android.content.Context
import hu.mzsb.booksbysubject.data.local.LocalDataSource
import hu.mzsb.booksbysubject.data.network.NetworkDataSource
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import hu.mzsb.booksbysubject.util.network.isInternetAvailable
import javax.inject.Inject

class BookDetailsInteractor @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
    private val context: Context
) {

    suspend fun setBookRead(bookId: String, isRead: Boolean) {
        localDataSource.setBookRead(bookId, isRead)
        if(context.isInternetAvailable) {
            networkDataSource.setBookRead(bookId, isRead)
        }
    }

    suspend fun getBookDetailsByBookId(bookId: String) =
            if(context.isInternetAvailable) {
                networkDataSource.getBookDetailsByBookId(bookId).also { it.isRead = localDataSource.getBookRead(bookId) ?: false }
            }
            else{
                localDataSource.getBookDetailsByBookId(bookId)
            }
}