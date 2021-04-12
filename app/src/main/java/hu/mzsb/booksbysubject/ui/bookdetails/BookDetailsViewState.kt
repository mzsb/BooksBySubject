package hu.mzsb.booksbysubject.ui.bookdetails

sealed class BookDetailsViewState

object Initial : BookDetailsViewState()

object Loading : BookDetailsViewState()