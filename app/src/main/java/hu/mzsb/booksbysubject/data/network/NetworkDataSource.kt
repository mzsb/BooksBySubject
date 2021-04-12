package hu.mzsb.booksbysubject.data.network

import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails

//TODO inject api
class NetworkDataSource {

    fun getBooksBySubject(subject: String): List<DomainBook> {
        //TODO list books by subject from network
        return listOf(
            DomainBook("networkDummy1","networkDummy1","networkDummy1","networkDummy1",false),
            DomainBook("networkDummy2","localDummy2","networkDummy2","networkDummy2",true)
        )
    }

    fun setBookRead(bookId: String, isRead: Boolean) {
        //TODO set book read by book id on network
    }

    fun getBookDetailsByBookId(bookId: String): DomainBookDetails {
        //TODO get book details by book id from network
        return DomainBookDetails("networkDummy3","networkDummy3","networkDummy3","networkDummy3", "localDummy3","localDummy3", true)
    }
}