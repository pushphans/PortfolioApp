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
import com.example.portfolio.databinding.FragmentExperienceBinding


class ExperienceFragment : Fragment(R.layout.fragment_experience) {

    private var _binding : FragmentExperienceBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExperienceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigate(R.id.homeFragment)
        }

        binding.cvCodicesWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.codicestech.com/")
            }
            val chooser = Intent.createChooser(intent, "Open with")
            startActivity(chooser)
        }

        binding.cvInternshipCertificate.setOnClickListener {
            findNavController().navigate(R.id.certificateFragment)
        }

        binding.cvMasterWebsite.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://mastergrouponline.com/")
            }
            val chooser = Intent.createChooser(intent, "Open with")
            startActivity(chooser)
        }

        binding.cvMasterGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://github.com/pushphans/MasterHealthcareApp")
            }
            val chooser = Intent.createChooser(intent, "Open with")
            startActivity(chooser)
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).toolbarTitle.text = "Experience"
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}