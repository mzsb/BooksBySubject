package hu.mzsb.booksbysubject.data.network

import android.R
import co.zsmb.rainbowcake.withIOContext
import hu.mzsb.booksbysubject.data.network.model.BookDetailsRequest
import hu.mzsb.booksbysubject.data.network.model.BooksResult
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import io.swagger.client.api.BookDetailsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.*
import javax.inject.Inject


class NetworkDataSource @Inject constructor(
    private val booksApi: BooksApi,
    private val bookDetailsApi: BookDetailsApi,
    private val authorDetailsApi: AuthorDetailsApi
) {

    suspend fun getBooksBySubject(subject: String): List<DomainBook> = withIOContext {
        try {
            val result = booksApi.getBooksBySubject(subject.toLowerCase(Locale.ROOT))
            result.works?.map { book -> DomainBook(
                id = book.key!!.split("/").last(),
                title = book.title!!,
                authorName = book.authors?.map { author -> author.name }?.first() ?: "",
                subject = result.name!!,
                isRead = false)
            } ?: ArrayList<DomainBook>()
        } catch (e: IOException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
            ArrayList<DomainBook>()
        } catch (e: HttpException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
            ArrayList<DomainBook>()
        }
    }

    suspend fun setBookRead(bookId: String, isRead: Boolean) = withIOContext {
        try {
            bookDetailsApi.updateBookDetails(bookId, BookDetailsRequest(isRead))
        } catch (e: IOException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
        } catch (e: HttpException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
        }
    }

    suspend fun getBookDetailsByBookId(bookId: String): DomainBookDetails = withIOContext {
        try {
            val bookDetailsResult = bookDetailsApi.getBookDetailsById(bookId)
            val authorDetailsResult = authorDetailsApi.getAuthorDetailsById(bookDetailsResult.authors!!.map { author -> author.author }.first().key.split("/").last())
            DomainBookDetails(
                title = bookDetailsResult.title,
                description = bookDetailsResult.description?.value ?: "",
                authorName = authorDetailsResult.name,
                authorBio = authorDetailsResult.bio?.value ?: "",
                imageUrl = "http://covers.openlibrary.org/b/isbn/${bookDetailsResult.covers?.first()}-S.jpg",
                isRead = false)
        } catch (e: IOException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
            DomainBookDetails("","","","","",false)
        } catch (e: HttpException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
            DomainBookDetails("","","","","",false)
        }
    }
}