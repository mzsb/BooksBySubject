package hu.mzsb.booksbysubject.domain.models

data class DomainBookDetails(
        val title: String,
        val description: String,
        val authorName: String,
        val authorBio: String,
        val covers: String,
        var isRead: Boolean
)