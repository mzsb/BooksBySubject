package hu.mzsb.booksbysubject.data.local

import hu.mzsb.booksbysubject.data.local.models.RoomBook
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails

fun RoomBook.toDomainBook(): DomainBook {
    return DomainBook(
        id = id,
        subject = subject,
        title = title,
        authorName = authorName,
        isRead = isRead
    )
}

fun RoomBook.toDomainBookDetails(): DomainBookDetails {
    return DomainBookDetails(
        title = title,
        description = description,
        authorName = authorName,
        authorBio = authorBio,
        covers = covers,
        isRead = isRead
    )
}