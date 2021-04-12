package hu.mzsb.booksbysubject.domain.models

data class DomainBookDetails(
    val id: String,
    val title: String,
    val description: String,
    val authorName: String,
    val authorBio: String,
    val imageUrl: String,
    val isRead: Boolean
)