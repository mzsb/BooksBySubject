package hu.mzsb.booksbysubject.data.network.model

data class BooksResult(val name: String?,
                       val work_count: Int?,
                       val works: List<Book>?)

data class Book(val key: String?,
                val title: String?,
                val authors: List<Author>?)