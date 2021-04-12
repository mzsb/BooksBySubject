package hu.mzsb.booksbysubject.ui.books

import hu.mzsb.booksbysubject.ui.books.models.UiBook


sealed class BooksViewState

object Initial : BooksViewState()

object Loading : BooksViewState()

data class BooksReady(val books: List<UiBook>) : BooksViewState()