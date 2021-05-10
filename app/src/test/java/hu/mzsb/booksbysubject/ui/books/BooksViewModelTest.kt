package hu.mzsb.booksbysubject.ui.books

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import hu.mzsb.booksbysubject.ui.books.models.UiBook
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BooksViewModelTest : ViewModelTest() {

    companion object {
        private const val MOCK_IS_READ = true
        private const val MOCK_IS_NOT_READ = false
        private const val MOCK_SUBJECT = "mockSubject"

        private val MOCK_BOOKS = listOf(
            UiBook("mockId1", "mockTitle1", "mockAuthorName1", MOCK_IS_NOT_READ),
            UiBook("mockId2", "mockTitle2", "mockAuthorName2", MOCK_IS_NOT_READ),
            UiBook("mockId3", "mockTitle3", "mockAuthorName3", MOCK_IS_READ),
            UiBook("mockId4", "mockTitle4", "mockAuthorName4", MOCK_IS_NOT_READ))
    }

    @Test
    fun `Read books are loaded correctly from presenter by subject`() = runBlockingTest {
        val booksPresenter: BooksPresenter = mock()
        whenever(booksPresenter.getBooksBySubjectAndRead(
            MOCK_SUBJECT,
            MOCK_IS_READ
        )) doReturn MOCK_BOOKS.filter { it.isRead }

        val vm = BooksViewModel(booksPresenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.loadBooksByRead(
                MOCK_SUBJECT,
                MOCK_IS_READ
            )

            stateObserver.assertObserved(
                Initial,
                Loading,
                BooksReady(MOCK_BOOKS.filter { it.isRead })
            )
        }
    }

    @Test
    fun `Not read books are loaded correctly from presenter by subject`() = runBlockingTest {
        val booksPresenter: BooksPresenter = mock()
        whenever(booksPresenter.getBooksBySubjectAndRead(
            MOCK_SUBJECT,
            MOCK_IS_NOT_READ
        )) doReturn MOCK_BOOKS

        val vm = BooksViewModel(booksPresenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.loadBooksByRead(
                MOCK_SUBJECT,
                MOCK_IS_NOT_READ
            )

            stateObserver.assertObserved(
                Initial,
                Loading,
                BooksReady(MOCK_BOOKS)
            )
        }
    }
}