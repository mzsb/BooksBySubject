package hu.mzsb.booksbysubject.ui.books

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import hu.mzsb.booksbysubject.ui.books.models.UiBook

import javax.inject.Inject

class BooksViewModel @Inject constructor(
        private val booksPresenter: BooksPresenter
) : RainbowCakeViewModel<BooksViewState>(Initial) {

        fun loadBooksByRead(subject: String, isRead: Boolean) = execute {
                viewState = Loading
                viewState = BooksReady(booksPresenter.getBooksBySubjectAndRead(subject, isRead))
        }
}
