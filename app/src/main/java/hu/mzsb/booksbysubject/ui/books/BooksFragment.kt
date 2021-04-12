package hu.mzsb.booksbysubject.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import hu.mzsb.booksbysubject.databinding.FragmentBooksBinding
import hu.mzsb.booksbysubject.ui.books.models.UiBook
import kotlinx.android.synthetic.main.fragment_books.*
import timber.log.Timber

class BooksFragment : RainbowCakeFragment<BooksViewState, BooksViewModel>(), BooksAdapter.Listener {

    private lateinit var adapter: BooksAdapter

    override fun provideViewModel() = getViewModelFromFactory()

    private var _binding: FragmentBooksBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)

        adapter = BooksAdapter()
        adapter.listener = this
        binding.listBooks.adapter = adapter

        binding.spSubjects.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.loadBooks("localDummy1",false)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(viewState: BooksViewState) {
        when (viewState) {
            is Loading -> Timber.d("Loading")
            is BooksReady -> adapter.submitList(viewState.books)
        }
    }

    override fun onBookClicked(book: UiBook) {
        findNavController().navigate(
            BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(book.id)
        )
    }
}