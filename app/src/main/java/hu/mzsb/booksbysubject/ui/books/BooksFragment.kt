package hu.mzsb.booksbysubject.ui.books

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import hu.mzsb.booksbysubject.R
import hu.mzsb.booksbysubject.databinding.FragmentBooksBinding
import hu.mzsb.booksbysubject.ui.books.models.UiBook
import hu.mzsb.booksbysubject.util.network.isInternetAvailable


class BooksFragment : RainbowCakeFragment<BooksViewState, BooksViewModel>(), BooksAdapter.Listener{

    private lateinit var adapter: BooksAdapter

    override fun provideViewModel() = getViewModelFromFactory()

    private var _binding: FragmentBooksBinding? = null

    private val binding get() = _binding!!

    private lateinit var subject: String
    private var isRead: Boolean = false

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun render(viewState: BooksViewState) {
        when (viewState) {
            is Initial -> {
                sharedPreferences = requireContext().getSharedPreferences("BooksFragment",0)

                val firstSubject = resources.getStringArray(R.array.subjects).first()
                val trySubject = sharedPreferences.getString("subject", firstSubject)
                subject = if(trySubject == null) {
                    sharedPreferences.edit().putString("subject", firstSubject).apply()
                    firstSubject
                }
                else {
                    trySubject
                }

                binding.btToAboutFragment.setOnClickListener {
                    findNavController().navigate(
                            BooksFragmentDirections.actionBooksFragmentToAboutFragment()
                    )
                }

                adapter = BooksAdapter()
                adapter.listener = this
                binding.listBooks.adapter = adapter

                binding.srlRefresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.primary))
                binding.srlRefresh.setOnRefreshListener {
                    getBooks()
                }

                isRead = sharedPreferences.getBoolean("isRead", false)
                binding.mcbIsRead.isChecked = isRead
                binding.mcbIsRead.setOnCheckedChangeListener { _, _ -> run{
                    isRead = binding.mcbIsRead.isChecked
                    sharedPreferences.edit().putBoolean("isRead", isRead).apply()
                    getBooks()
                }}

                ArrayAdapter.createFromResource(
                        requireContext(),
                        R.array.subjects,
                        R.layout.subjects_spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(R.layout.subject_spinner_dropdown_item)
                    binding.spSubjects.adapter = adapter
                }

                binding.spSubjects.setSelection(resources.getStringArray(R.array.subjects).indexOf(subject))
                binding.spSubjects.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                        subject = parent.getItemAtPosition(position).toString()
                        sharedPreferences.edit().putString("subject", subject).apply()
                        getBooks()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }
                }
            }
            is Loading -> {
                binding.listBooks.visibility = View.INVISIBLE
                binding.cpiListBooks.visibility = View.VISIBLE
            }
            is BooksReady -> {
                binding.srlRefresh.isRefreshing = false
                if(!requireContext().isInternetAvailable && viewState.books.isEmpty() && !isRead)
                {
                    Toast.makeText(requireContext(), getString(R.string.try_refresh), Toast.LENGTH_LONG).show()
                    binding.cpiListBooks.visibility = View.INVISIBLE
                }
                else {
                    adapter.submitList(viewState.books)
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.cpiListBooks.visibility = View.INVISIBLE
                        binding.listBooks.visibility = View.VISIBLE
                    }, 1000)
                }
            }
        }
    }

    private fun getBooks() =
        viewModel.loadBooksByRead(subject, isRead)

    override fun onBookClicked(book: UiBook) {
        findNavController().navigate(
            BooksFragmentDirections.actionBooksFragmentToBookDetailsFragment(book.id)
        )
    }
}