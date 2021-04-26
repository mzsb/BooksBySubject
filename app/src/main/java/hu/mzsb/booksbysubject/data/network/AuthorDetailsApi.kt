package hu.mzsb.booksbysubject.data.network
import hu.mzsb.booksbysubject.data.network.model.AuthorDetailsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthorDetailsApi {
    /**
     * Get author details by author id
     *
     * @param id
     * @return Call<AuthorDetails>
    </AuthorDetails> */
    @GET("authors/{id}.json")
    suspend fun getAuthorDetailsById(@Path("id") id: String): AuthorDetailsResult
}