package hu.mzsb.booksbysubject.ui.bookdetails

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class BookDetailsViewModel @Inject constructor(
    private val bookDetailsPresenter: BookDetailsPresenter
) : RainbowCakeViewModel<BookDetailsViewState>(Initial) {

    fun loadBookDetails(bookId: String) = execute {
        viewState = Loading
        viewState = BookDetailsReady(bookDetailsPresenter.getBookDetailsByBookId(bookId))
    }

    fun setBookRead(bookId: String, isRead: Boolean) = execute {
        viewState = Loading
        bookDetailsPresenter.setBookRead(bookId, isRead)
        viewState = BookDetailsReady(bookDetailsPresenter.getBookDetailsByBookId(bookId))
    }
}