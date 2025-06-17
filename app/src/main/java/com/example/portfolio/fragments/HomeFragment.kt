package com.example.portfolio.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.portfolio.MainActivity
import com.example.portfolio.R
import com.example.portfolio.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://github.com/pushphans")
            }
            val chooser = Intent.createChooser(intent, "Open with")
            startActivity(chooser)
        }

        binding.cvLinkedIn.setOnClickListener {
            val uri = Uri.parse("https://www.linkedin.com/in/pushphans1502/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            val chooser = Intent.createChooser(intent, "Open LinkedIn Profile")
            startActivity(chooser)
        }

        binding.cvResume.setOnClickListener {
            val uri = Uri.parse("https://drive.google.com/file/d/1J2zHatv5cx5oNdQNqWaUeJpecdNPx5Kg/view?usp=sharing")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            val chooser = Intent.createChooser(intent, "Open Resume")
            startActivity(chooser)
        }
    }


    override fun onResume() {
        super.onResume()

        (activity as MainActivity).toolbarTitle.text = "SUMMARY"
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}