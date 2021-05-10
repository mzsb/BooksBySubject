package hu.mzsb.booksbysubject.data.network

import co.zsmb.rainbowcake.withIOContext
import com.google.gson.internal.LinkedTreeMap
import hu.mzsb.booksbysubject.R
import hu.mzsb.booksbysubject.data.network.model.BookDetailsRequest
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import retrofit2.HttpException
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
            result.works.map { book ->
                DomainBook(
                id = book.key.split("/").last(),
                title = book.title,
                authorName = if(book.authors.isNotEmpty()) book.authors.map { author -> author.name ?: "" }.first() else "",
                subject = result.name,
                isRead = false)
            }
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
            var bookDetailsResult = bookDetailsApi.getBookDetailsById(bookId)
            if (bookDetailsResult.location != null) {
                bookDetailsResult = bookDetailsApi.getBookDetailsById(bookDetailsResult.location!!.split("/").last())
            }

            var id = bookDetailsResult.authors.map { author -> author.author }.first().key.split("/").last()
            val authorDetailsResult = authorDetailsApi.getAuthorDetailsById(id)

            val tmp = bookDetailsResult.covers?.filter { it > 0 } ?: ArrayList()
            val covers = if(tmp.isNotEmpty()) {
                tmp.joinToString("|") { i -> i.toString() }
            } else {
                ""
            }

            DomainBookDetails(
                title = bookDetailsResult.title,
                description = getDescription(bookDetailsResult.description ?:""),
                authorName = authorDetailsResult.name ?: "",
                authorBio = getDescription(authorDetailsResult.bio ?:""),
                covers = covers,
                isRead = false)
        } catch (e: IOException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
            DomainBookDetails("","","","","",false)
        } catch (e: HttpException) {
            Timber.d("Network fetch failed")
            Timber.d(e)
            DomainBookDetails("","","","","",false)
        } catch(e: Exception){
            Timber.d(e.message)
            DomainBookDetails("","","","","",false)
        }
    }

    private fun getDescription(json: Any) : String {
        val result = if(json is LinkedTreeMap<*, *>) (json)["value"] else json
        return if(result != null){
            result as String
        }
        else{
            ""
        }
    }
}