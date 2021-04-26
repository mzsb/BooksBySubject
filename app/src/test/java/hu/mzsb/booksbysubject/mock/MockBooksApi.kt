package hu.mzsb.booksbysubject.mock

import hu.mzsb.booksbysubject.data.network.BooksApi
import hu.mzsb.booksbysubject.data.network.model.Author
import hu.mzsb.booksbysubject.data.network.model.Book
import hu.mzsb.booksbysubject.data.network.model.BooksResult

class MockBooksApi : BooksApi {
    override suspend fun getBooksBySubject(subject: String): BooksResult {
        val books = ArrayList<Book>()

        for (i in 1..100) {
            books.add(
                Book("OL0000000M",
                    "Lorem Ipsum",
                    listOf(
                        Author("OL00000A",
                            "Lorem Ipsum"))))
        }

        return BooksResult(
            "subjectName",
            1000,
            books
        )
    }
}