package hu.mzsb.booksbysubject.domain.interactors

import android.content.Context
import co.zsmb.rainbowcake.withIOContext
import hu.mzsb.booksbysubject.data.local.LocalDataSource
import hu.mzsb.booksbysubject.data.network.NetworkDataSource
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.util.network.isInternetAvailable
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import javax.inject.Inject
import kotlin.concurrent.thread

class BooksInteractor @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
    private val context: Context
) {

    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean) =
        if(context.isInternetAvailable) {
            val result = networkDataSource.getBooksBySubject(subject)
            GlobalScope.launch { saveBooks(result) }
            result
        }
        else {
            localDataSource.getBooksBySubjectAndRead(subject, isRead)
        }

    private suspend fun saveBooks(books: List<DomainBook>) = books.forEach { book ->
        val details = networkDataSource.getBookDetailsByBookId(book.id)
        localDataSource.insertBook(book, details)
    }
}