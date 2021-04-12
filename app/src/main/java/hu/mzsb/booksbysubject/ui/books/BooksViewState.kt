package hu.mzsb.booksbysubject.ui.books


sealed class BooksViewState

object Initial : BooksViewState()

object Loading : BooksViewState()
