package hu.mzsb.booksbysubject.ui.books

import co.zsmb.rainbowcake.withIOContext
import hu.mzsb.booksbysubject.domain.interactors.BookDetailsInteractor
import hu.mzsb.booksbysubject.domain.interactors.BooksInteractor
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import hu.mzsb.booksbysubject.ui.bookdetails.models.UiBookDetails
import hu.mzsb.booksbysubject.ui.books.models.UiBook
import javax.inject.Inject

class BooksPresenter @Inject constructor(
    private val booksInteractor: BooksInteractor
) {
    suspend fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<UiBook> =
            booksInteractor.getBooksBySubjectAndRead(subject, isRead).map { it.toUiBook() }
}

fun DomainBook.toUiBook(): UiBook {
    return UiBook(
        id = id,
        title = title,
        authorName = authorName,
        isRead = isRead
    )
}