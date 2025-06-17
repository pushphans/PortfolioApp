package com.example.portfolio.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.portfolio.MainActivity
import com.example.portfolio.R
import com.example.portfolio.databinding.ActivityMainBinding
import com.example.portfolio.databinding.FragmentWebviewBinding

class WebViewFragment : Fragment(R.layout.fragment_webview) {
    private var _binding : FragmentWebviewBinding? =  null
    private val binding get() = _binding!!

    private val args : WebViewFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val link = args.link
        webView(link)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().popBackStack(R.id.homeFragment, false)
        }


    }



    @SuppressLint("SetJavaScriptEnabled")
    private fun webView(link : String){
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(link)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).toolbar.visibility = View.GONE

    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}