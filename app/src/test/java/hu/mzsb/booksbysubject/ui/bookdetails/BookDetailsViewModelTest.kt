package hu.mzsb.booksbysubject.ui.bookdetails

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.nhaarman.mockitokotlin2.*
import hu.mzsb.booksbysubject.ui.bookdetails.models.UiBookDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookDetailsViewModelTest : ViewModelTest() {

    companion object {
        private const val MOCK_IS_READ = true
        private const val MOCK_IS_NOT_READ = false
        private const val MOCK_BOOK_ID = "mockId"

        private val MOCK_NOT_READ_BOOK_DETAILS = UiBookDetails(MOCK_BOOK_ID, "mockDescription", "mockAuthorName", "mockAuthorBio", "", MOCK_IS_NOT_READ)
        private val MOCK_READ_BOOK_DETAILS = UiBookDetails(MOCK_BOOK_ID, "mockDescription", "mockAuthorName", "mockAuthorBio", "", MOCK_IS_READ)
    }

    @Test
    fun `Details of not read book is loaded correctly from presenter by book id`() = runBlockingTest {
        val bookDetailsPresenter: BookDetailsPresenter = mock()
        whenever(bookDetailsPresenter.getBookDetailsByBookId(MOCK_BOOK_ID)) doReturn MOCK_NOT_READ_BOOK_DETAILS

        val vm = BookDetailsViewModel(bookDetailsPresenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.loadBookDetails(MOCK_BOOK_ID)

            stateObserver.assertObserved(
                Initial,
                Loading,
                BookDetailsReady(MOCK_NOT_READ_BOOK_DETAILS)
            )
        }
    }

    @Test
    fun `Details of read book is loaded correctly from presenter by book id`() = runBlockingTest {
        val bookDetailsPresenter: BookDetailsPresenter = mock()
        whenever(bookDetailsPresenter.getBookDetailsByBookId(MOCK_BOOK_ID)) doReturn MOCK_READ_BOOK_DETAILS

        val vm = BookDetailsViewModel(bookDetailsPresenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.loadBookDetails(MOCK_BOOK_ID)

            stateObserver.assertObserved(
                Initial,
                Loading,
                BookDetailsReady(MOCK_READ_BOOK_DETAILS)
            )
        }
    }

    @Test
    fun `Setting read of book details is correct with presenter by book id`() = runBlockingTest {
        val bookDetailsPresenter: BookDetailsPresenter = mock()
        whenever(bookDetailsPresenter.getBookDetailsByBookId(MOCK_BOOK_ID)) doReturn MOCK_READ_BOOK_DETAILS

        val vm = BookDetailsViewModel(bookDetailsPresenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.setBookRead(MOCK_BOOK_ID, MOCK_IS_READ)

            stateObserver.assertObserved(
                Initial,
                Loading,
                BookDetailsReady(MOCK_READ_BOOK_DETAILS)
            )
        }
    }

    @Test
    fun `Setting not read of book details is correct with presenter by book id`() = runBlockingTest {
        val bookDetailsPresenter: BookDetailsPresenter = mock()
        whenever(bookDetailsPresenter.getBookDetailsByBookId(MOCK_BOOK_ID)) doReturn MOCK_NOT_READ_BOOK_DETAILS

        val vm = BookDetailsViewModel(bookDetailsPresenter)

        vm.observeStateAndEvents { stateObserver, _ ->
            vm.setBookRead(MOCK_BOOK_ID, MOCK_IS_NOT_READ)

            stateObserver.assertObserved(
                Initial,
                Loading,
                BookDetailsReady(MOCK_NOT_READ_BOOK_DETAILS)
            )
        }
    }
}
