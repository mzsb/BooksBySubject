package hu.mzsb.booksbysubject.ui.books

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.PresenterTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import hu.mzsb.booksbysubject.domain.interactors.BooksInteractor
import hu.mzsb.booksbysubject.domain.models.DomainBook
import hu.mzsb.booksbysubject.ui.books.models.UiBook
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BooksPresenterTest : PresenterTest() {

    companion object {
        private const val MOCK_IS_READ = true
        private const val MOCK_IS_NOT_READ = false
        private const val MOCK_SUBJECT = "mockSubject"

        private val MOCK_BOOKS = listOf(
            DomainBook("mockId1", "mockTitle1", "mockTitle1","mockAuthorName1", MOCK_IS_NOT_READ),
            DomainBook("mockId2", "mockTitle2", "mockTitle2", "mockAuthorName2", MOCK_IS_NOT_READ),
            DomainBook("mockId3", "mockTitle3", "mockTitle3", "mockAuthorName3", MOCK_IS_READ),
            DomainBook("mockId4", "mockTitle4", "mockTitle4", "mockAuthorName4", MOCK_IS_NOT_READ)
        )
    }

    @Test
    fun `Read books are loaded correctly from interactor by subject`() = runBlockingTest {
        val booksInteractor: BooksInteractor = mock()
        whenever(booksInteractor.getBooksBySubjectAndRead(MOCK_SUBJECT, MOCK_IS_READ)) doReturn MOCK_BOOKS.filter { it.isRead }

        val pres = BooksPresenter(booksInteractor)

        assertEquals(pres.getBooksBySubjectAndRead(MOCK_SUBJECT, MOCK_IS_READ), MOCK_BOOKS.filter { it.isRead }.map { it.toUiBook() });
    }

    @Test
    fun `Not read books are loaded correctly from interactor by subject`() = runBlockingTest {
        val booksInteractor: BooksInteractor = mock()
        whenever(booksInteractor.getBooksBySubjectAndRead(MOCK_SUBJECT, MOCK_IS_NOT_READ)) doReturn MOCK_BOOKS

        val pres = BooksPresenter(booksInteractor)

        assertEquals(pres.getBooksBySubjectAndRead(MOCK_SUBJECT, MOCK_IS_NOT_READ), MOCK_BOOKS.map { it.toUiBook() });
    }
}