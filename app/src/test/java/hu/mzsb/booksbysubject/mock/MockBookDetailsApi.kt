package hu.mzsb.booksbysubject.mock

import hu.mzsb.booksbysubject.data.network.BookDetailsApi
import hu.mzsb.booksbysubject.data.network.model.*
import kotlin.random.Random

class MockBookDetailsApi : BookDetailsApi {
    override suspend fun updateBookDetails(id: String, body: BookDetailsRequest) { }

    override suspend fun getBookDetailsById(id: String): BookDetailsResult =
        BookDetailsResult(
            title = "Lorem Ipsum",
            description = Description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra nec augue sed maximus. Pellentesque ornare egestas neque, a finibus orci tempor eget. Nulla lorem diam, porta a lobortis sed, luctus sit amet purus. Donec laoreet, nisl eget porttitor porttitor, tellus orci euismod arcu, nec porta ligula diam eget dui. Vivamus viverra sollicitudin convallis. In id egestas est. Morbi rhoncus consectetur porta."),
            covers = listOf(434916),
            authors = listOf(AuthorHolder(Author("OL00000A", "Margaret"))),
            isRead = Random.nextBoolean(),
            location = if(Random.nextBoolean()) "OL0000000M" else null)
}