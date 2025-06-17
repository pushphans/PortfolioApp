package com.example.portfolio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.portfolio.MainActivity
import com.example.portfolio.R
import com.example.portfolio.databinding.FragmentEducationBinding


class EducationFragment : Fragment(R.layout.fragment_education) {

    private var _binding : FragmentEducationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEducationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().popBackStack(R.id.homeFragment, false)
        }

    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).toolbarTitle.text = "Education"

    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}