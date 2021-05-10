package hu.mzsb.booksbysubject.ui.bookdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import hu.mzsb.booksbysubject.R
import hu.mzsb.booksbysubject.databinding.FragmentBookDetailsBinding
import hu.mzsb.booksbysubject.util.network.isInternetAvailable
import timber.log.Timber


class BookDetailsFragment : RainbowCakeFragment<BookDetailsViewState, BookDetailsViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()

    private var _binding: FragmentBookDetailsBinding? = null

    private val binding get() = _binding!!

    private var coversFragment: CoversFragment? = null

    private lateinit var bookId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(viewState: BookDetailsViewState) {
        when (viewState) {
            is Initial -> {

                requireActivity()
                    .onBackPressedDispatcher
                    .addCallback(this, object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() = navigateToBooksFragment()
                    })

                binding.btBackToBooksFragment.setOnClickListener {
                    navigateToBooksFragment()
                }

                binding.btCovers.setOnClickListener {
                    coversFragment?.show(requireActivity().supportFragmentManager, "coversFragment")
                }

                binding.srlRefresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.primary))
                binding.srlRefresh.setOnRefreshListener {
                    viewModel.loadBookDetails(bookId)
                }

                binding.mcbIsRead.setOnCheckedChangeListener { _, _ -> viewModel.setBookRead(bookId, binding.mcbIsRead.isChecked) }

                bookId = requireArguments().getString("bookId")!!
                viewModel.loadBookDetails(bookId)
            }
            is Loading -> {
                binding.clPanel.visibility = View.INVISIBLE
                binding.btCovers.visibility = View.INVISIBLE
                binding.cpiBookDetails.visibility = View.VISIBLE
                Timber.d("Loading")
            }
            is BookDetailsReady -> {
                binding.srlRefresh.isRefreshing = false
                val bookDetails = viewState.bookDetails
                binding.mcbIsRead.isChecked = viewState.bookDetails.isRead
                binding.mcbIsRead.visibility = View.VISIBLE
                binding.tvTitle.text = bookDetails.title
                binding.tvDescription.text = if(bookDetails.description.isNotEmpty()) bookDetails.description else getString(R.string.not_available_description)
                binding.tvAuthorName.text = bookDetails.authorName
                binding.tvAuthorBio.text = if(bookDetails.authorBio.isNotEmpty()) bookDetails.authorBio else getString(R.string.not_available_bio)
                if(requireContext().isInternetAvailable) {
                    coversFragment = if (viewState.bookDetails.covers.isNotEmpty()) {
                        binding.btCovers.visibility = View.VISIBLE
                        CoversFragment(viewState.bookDetails.covers)
                    } else {
                        binding.btCovers.visibility = View.INVISIBLE
                        null
                    }
                }
                binding.cpiBookDetails.visibility = View.GONE
                binding.clPanel.visibility = View.VISIBLE
            }
        }
    }

    private fun navigateToBooksFragment() =
        findNavController().navigate(
                BookDetailsFragmentDirections.actionBookDetailsFragmentToBooksFragment()
        )
}