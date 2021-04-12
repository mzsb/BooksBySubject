package hu.mzsb.booksbysubject.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import hu.mzsb.booksbysubject.databinding.FragmentBooksBinding
import timber.log.Timber

class BooksFragment : RainbowCakeFragment<BooksViewState, BooksViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

    private var _binding: FragmentBooksBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)

        binding.tvToBookDetails.setOnClickListener {
            findNavController().navigate(
                BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment("BooksFragment")
            )
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val fromFragmentName = requireArguments().getString("fromFragmentName") ?: "Start"
        binding.tvToBookDetails.text = "navigate to Book details, prev was ${fromFragmentName}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(viewState: BooksViewState) {
        when (viewState) {
            is Loading -> Timber.d("Loading")
            is Initial -> Timber.d("Init")
        }
    }
}