package hu.mzsb.booksbysubject.ui.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import hu.mzsb.booksbysubject.databinding.FragmentBookDetailsBinding
import timber.log.Timber

class BookDetailsFragment : RainbowCakeFragment<BookDetailsViewState, BookDetailsViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

    private var _binding: FragmentBookDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val bookId = requireArguments().getString("bookId")!!
        viewModel.loadBookDetails(bookId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(viewState: BookDetailsViewState) {
        when (viewState) {
            is Loading -> Timber.d("Loading")
            is BookDetailsReady -> binding.tvTitle.text = viewState.bookDetails.title
        }
    }

}