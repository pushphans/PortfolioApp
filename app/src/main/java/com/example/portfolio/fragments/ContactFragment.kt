package com.example.portfolio.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.portfolio.MainActivity
import com.example.portfolio.R
import com.example.portfolio.databinding.FragmentContactBinding


class ContactFragment : Fragment(R.layout.fragment_contact) {

    private var _binding : FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().popBackStack(R.id.homeFragment, false)
        }

        binding.cvNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:6006581286")
            }
            startActivity(intent)
        }

        binding.cvEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:pushp.hans1502@gmail.com")
            }
            startActivity(intent)
        }

        binding.email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:pushp.hans1502@gmail.com")
            }
            startActivity(intent)
        }

        binding.number.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:6006581286")
            }
            startActivity(intent)
        }

    }


    override fun onResume() {
        super.onResume()

        (activity as MainActivity).toolbarTitle.text = "Contact Me"
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}