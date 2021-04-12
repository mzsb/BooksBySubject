package hu.mzsb.booksbysubject.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.mzsb.booksbysubject.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val fromFragmentName = requireArguments().getString("fromFragmentName")!!
        binding.tvToBooks.text = "navigate to Books, prev was ${fromFragmentName}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}