package hu.mzsb.booksbysubject.data.network.model

data class BookDetailsResult(val title: String,
                             val description: Description?,
                             val covers: List<Int>?,
                             val authors: List<AuthorHolder>?,
                             val isRead: Boolean?)

data class AuthorHolder(val author: Author)