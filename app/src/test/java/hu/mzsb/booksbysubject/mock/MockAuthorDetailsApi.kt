package hu.mzsb.booksbysubject.mock

import hu.mzsb.booksbysubject.data.network.AuthorDetailsApi
import hu.mzsb.booksbysubject.data.network.model.AuthorDetailsResult
import hu.mzsb.booksbysubject.data.network.model.Description

class MockAuthorDetailsApi : AuthorDetailsApi {
    override suspend fun getAuthorDetailsById(id: String): AuthorDetailsResult =
        AuthorDetailsResult("Bob",
                            Description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis eget libero feugiat, maximus quam eu, aliquet magna. Aenean id purus at lectus faucibus finibus sit amet sit amet ante. Pellentesque eget magna odio. Integer porttitor mollis libero, in euismod diam imperdiet eget. Quisque ipsum ligula, placerat vitae vulputate sed, porta eu libero. Fusce a bibendum ante. Sed a feugiat purus. Etiam posuere malesuada purus in ullamcorper. Integer fringilla, nulla eu elementum ornare, arcu diam ultrices purus, sit amet elementum est risus id orci."))
}