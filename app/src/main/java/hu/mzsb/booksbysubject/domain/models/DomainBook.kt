package hu.mzsb.booksbysubject.domain.models

data class DomainBook(
    val id: String,
    val title: String,
    val authorName: String,
    val isRead: Boolean
)