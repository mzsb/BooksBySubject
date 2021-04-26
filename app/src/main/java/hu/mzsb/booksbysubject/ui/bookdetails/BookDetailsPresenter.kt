package hu.mzsb.booksbysubject.ui.bookdetails

import co.zsmb.rainbowcake.withIOContext
import hu.mzsb.booksbysubject.domain.interactors.BookDetailsInteractor
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import hu.mzsb.booksbysubject.ui.bookdetails.models.UiBookDetails
import javax.inject.Inject

class BookDetailsPresenter @Inject constructor(
    private val bookDetailsInteractor: BookDetailsInteractor
) {
    suspend fun setBookRead(bookId: String, isRead: Boolean) =
        bookDetailsInteractor.setBookRead(bookId, isRead)

    suspend fun getBookDetailsByBookId(bookId: String): UiBookDetails =
        bookDetailsInteractor.getBookDetailsByBookId(bookId).toUiBookDetails()
}

private fun DomainBookDetails.toUiBookDetails(): UiBookDetails {
    return UiBookDetails(
        title = title,
        description = description,
        authorName = authorName,
        authorBio = authorBio,
        imageUrl = imageUrl,
        isRead = isRead
    )
}