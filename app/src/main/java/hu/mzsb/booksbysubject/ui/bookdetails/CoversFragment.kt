package hu.mzsb.booksbysubject.ui.bookdetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import hu.mzsb.booksbysubject.R
import hu.mzsb.booksbysubject.databinding.FragmentCoversBinding
import hu.mzsb.booksbysubject.util.network.isInternetAvailable

class CoversFragment(covers: String) : DialogFragment()  {

    private var _binding: FragmentCoversBinding? = null

    private val binding get() = _binding!!

    private var index = 0
    private val coverIds = covers.split("|")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoversBinding.inflate(inflater, container, false)

        binding.imCover.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
        binding.imCover.settings.builtInZoomControls = true
        binding.imCover.loadUrl(getString(R.string.coverURL, coverIds[index]))

        if(coverIds.size > 1){
            binding.llPanel.visibility = View.VISIBLE
        }

        binding.btRight.setOnClickListener {
            if(requireContext().isInternetAvailable){
                index = if(index == coverIds.size - 1) 0 else index + 1
                binding.imCover.loadUrl(getString(R.string.coverURL, coverIds[index]))
            }
            else {
                Toast.makeText(requireContext(), getString(R.string.internet_not_available), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btLeft.setOnClickListener {
            if(requireContext().isInternetAvailable){
                index = if(index == 0) coverIds.size - 1 else index - 1
                binding.imCover.loadUrl(getString(R.string.coverURL, coverIds[index]))
            }
            else{
                Toast.makeText(requireContext(), getString(R.string.internet_not_available), Toast.LENGTH_SHORT).show()
            }
        }

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }
}