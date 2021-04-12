package hu.mzsb.booksbysubject.ui.books.models

data class UiBook(
    val id: String,
    val title: String,
    val authorName: String,
    val isRead: Boolean
)