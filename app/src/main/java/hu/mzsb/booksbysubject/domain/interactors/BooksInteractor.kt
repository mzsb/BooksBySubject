package hu.mzsb.booksbysubject.domain.interactors

import android.content.Context
import co.zsmb.rainbowcake.withIOContext
import hu.mzsb.booksbysubject.data.local.LocalDataSource
import hu.mzsb.booksbysubject.data.network.NetworkDataSource
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import hu.mzsb.booksbysubject.ui.books.models.UiBook
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
            if(context.isInternetAvailable){
                var result = networkDataSource.getBooksBySubject(subject)
                result.forEach { book -> book.isRead = localDataSource.getBookRead(book.id) ?: false }
                if(isRead) {
                    result = result.filter { it.isRead }
                }
                GlobalScope.launch { saveBooks(result) }
                result
            }
            else {
                if(isRead) {
                    localDataSource.getBooksBySubjectAndRead(subject, isRead)
                }
                else {
                    localDataSource.getBooksBySubject(subject)
                }
            }

    private suspend fun saveBooks(books: List<DomainBook>) {
        val booksMap = HashMap<DomainBook, DomainBookDetails>()
        books.forEach { book ->
            book.isRead = localDataSource.getBookRead(book.id) ?: false
            val details = networkDataSource.getBookDetailsByBookId(book.id)
            booksMap[book] = details
        }
        localDataSource.insertBooks(booksMap)
    }
}