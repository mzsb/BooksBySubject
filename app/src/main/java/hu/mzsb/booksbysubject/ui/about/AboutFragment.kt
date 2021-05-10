package hu.mzsb.booksbysubject.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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

        requireActivity()
                .onBackPressedDispatcher
                .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() = navigateToBooksFragment()
                })

        binding.btBackToBooksFragment.setOnClickListener {
            findNavController().navigate(
                    AboutFragmentDirections.actionAboutFragmentToBooksFragment()
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToBooksFragment() =
            findNavController().navigate(
                AboutFragmentDirections.actionAboutFragmentToBooksFragment()
            )
}