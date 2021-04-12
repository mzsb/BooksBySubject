package hu.mzsb.booksbysubject.ui.bookdetails

import hu.mzsb.booksbysubject.ui.bookdetails.models.UiBookDetails
import hu.mzsb.booksbysubject.ui.books.models.UiBook

sealed class BookDetailsViewState

object Initial : BookDetailsViewState()

object Loading : BookDetailsViewState()

data class BookDetailsReady(val bookDetails: UiBookDetails) : BookDetailsViewState()