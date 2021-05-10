package hu.mzsb.booksbysubject.ui.bookdetails

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.PresenterTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import hu.mzsb.booksbysubject.domain.interactors.BookDetailsInteractor
import hu.mzsb.booksbysubject.domain.models.DomainBookDetails
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookDetailsPresenterTest : PresenterTest() {

    companion object {
        private const val MOCK_IS_READ = true
        private const val MOCK_IS_NOT_READ = false
        private const val MOCK_BOOK_ID = "mockId"

        private val MOCK_NOT_READ_BOOK_DETAILS = DomainBookDetails(MOCK_BOOK_ID, "mockDescription", "mockAuthorName", "mockAuthorBio", "", MOCK_IS_NOT_READ)
        private val MOCK_READ_BOOK_DETAILS = DomainBookDetails(MOCK_BOOK_ID, "mockDescription", "mockAuthorName", "mockAuthorBio", "", MOCK_IS_READ)
    }

    @Test
    fun `Details of not read book is loaded correctly from interactor by book id`() = runBlockingTest {
        val bookDetailsInteractor: BookDetailsInteractor = mock()
        whenever(bookDetailsInteractor.getBookDetailsByBookId(MOCK_BOOK_ID)) doReturn MOCK_NOT_READ_BOOK_DETAILS

        val pres = BookDetailsPresenter(bookDetailsInteractor)

        assertEquals(pres.getBookDetailsByBookId(MOCK_BOOK_ID), MOCK_NOT_READ_BOOK_DETAILS.toUiBookDetails())
    }

    @Test
    fun `Details of read book is loaded correctly from interactor by book id`() = runBlockingTest {
        val bookDetailsInteractor: BookDetailsInteractor = mock()
        whenever(bookDetailsInteractor.getBookDetailsByBookId(MOCK_BOOK_ID)) doReturn MOCK_READ_BOOK_DETAILS

        val pres = BookDetailsPresenter(bookDetailsInteractor)

        assertEquals(pres.getBookDetailsByBookId(MOCK_BOOK_ID), MOCK_READ_BOOK_DETAILS.toUiBookDetails())
    }
}