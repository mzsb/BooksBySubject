package hu.mzsb.booksbysubject.domain.models

import javax.security.auth.Subject

data class DomainBook(
    val id: String,
    val subject: String,
    val title: String,
    val authorName: String,
    val isRead: Boolean
)