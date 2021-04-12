package hu.mzsb.booksbysubject.data.local

import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails

//TODO inject Dao
class LocalDataSource {

    fun getBooksBySubjectAndRead(subject: String, isRead: Boolean): List<DomainBook> {
        //TODO list books by subject and read from database
        return listOf(
            DomainBook("localDummy1","localDummy1","localDummy1","localDummy1",false),
            DomainBook("localDummy2","localDummy2","localDummy2","localDummy2",true))
    }

    fun setBookRead(bookId: String, isRead: Boolean) {
        //TODO set book read by book id in database
    }

    fun getBookDetailsByBookId(bookId: String): DomainBookDetails {
        //TODO get book details by book id from database
        return DomainBookDetails("localDummy3","localDummy3","localDummy3","localDummy3", "localDummy3", "localDummy3",true)
    }
}