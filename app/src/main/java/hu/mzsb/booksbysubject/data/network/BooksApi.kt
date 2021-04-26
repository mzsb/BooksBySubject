package hu.mzsb.booksbysubject.data.network

import hu.mzsb.booksbysubject.data.network.model.BooksResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {
    /**
     * Get book by subject
     *
     * @param subject
     * @return Call<Subject>
    </Subject> */
    @GET("subjects/{subject}.json")
    suspend fun getBooksBySubject(@Path("subject") subject: String): BooksResult
}