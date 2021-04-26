package hu.mzsb.booksbysubject.mock

import hu.mzsb.booksbysubject.data.local.dao.BookDao
import hu.mzsb.booksbysubject.data.local.models.RoomBook
import hu.mzsb.booksbysubject.data.network.model.Author
import kotlin.random.Random

class MockBookDao : BookDao {
    override suspend fun getBooksBySubjectAndRead(
        subject: String,
        isRead: Boolean
    ): List<RoomBook> {
        TODO("Not yet implemented")
    }

    override suspend fun getBookById(bookId: String) =
        RoomBook(
            id = "OL0000000M",
            title = "Lorem Ipsum",
            subject = "subject",
            authorName = "Margaret",
            authorBio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra nec augue sed maximus. Pellentesque ornare egestas neque, a finibus orci tempor eget. Nulla lorem diam, porta a lobortis sed, luctus sit amet purus. Donec laoreet, nisl eget porttitor porttitor, tellus orci euismod arcu, nec porta ligula diam eget dui. Vivamus viverra sollicitudin convallis. In id egestas est. Morbi rhoncus consectetur porta.",
            imageUrl = "Lorem ipsum dolor sit amet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra nec augue sed maximus. Pellentesque ornare egestas neque, a finibus orci tempor eget. Nulla lorem diam, porta a lobortis sed, luctus sit amet purus. Donec laoreet, nisl eget porttitor porttitor, tellus orci euismod arcu, nec porta ligula diam eget dui. Vivamus viverra sollicitudin convallis. In id egestas est. Morbi rhoncus consectetur porta.",
            isRead = Random.nextBoolean()
        )

    override suspend fun insertBook(book: RoomBook) { }

    override suspend fun updateBookRead(bookId: String, isRead: Boolean) { }
}