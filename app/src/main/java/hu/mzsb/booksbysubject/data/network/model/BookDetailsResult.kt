package hu.mzsb.booksbysubject.data.network.model

data class BookDetailsResult(val title: String,
                             val description: Any?,
                             val covers: List<Int>?,
                             val authors: List<AuthorHolder>,
                             val isRead: Boolean,
                             val location: String?)

data class AuthorHolder(val author: Author)