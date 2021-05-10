package hu.mzsb.booksbysubject.ui.bookdetails.models

data class UiBookDetails(
        val title: String,
        val description: String,
        val authorName: String,
        val authorBio: String,
        val covers: String,
        val isRead: Boolean
)