package hu.mzsb.booksbysubject.ui.bookdetails.models

data class UiBookDetails(
    val id: String,
    val title: String,
    val description: String,
    val authorName: String,
    val authorBio: String,
    val imageUrl: String,
    val isRead: Boolean
)