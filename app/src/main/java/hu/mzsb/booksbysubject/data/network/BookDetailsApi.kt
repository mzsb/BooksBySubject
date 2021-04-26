package hu.mzsb.booksbysubject.data.network

import hu.mzsb.booksbysubject.data.network.model.BookDetailsRequest
import hu.mzsb.booksbysubject.data.network.model.BookDetailsResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BookDetailsApi {
    /**
     * Update book details
     *
     * @param id
     * @param body
     * @return Call<Void>
    </Void> */
    @PUT("books/{id}")
    suspend fun updateBookDetails(@Path("id") id: String, @Body body: BookDetailsRequest)

    /**
     * Get book details by book id
     *
     * @param id
     * @return Call<BookDetails>
    </BookDetails> */
    @GET("books/{id}.json")
    suspend fun getBookDetailsById(@Path("id") id: String): BookDetailsResult
}