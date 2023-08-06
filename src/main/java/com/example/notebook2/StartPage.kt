package com.example.notebook2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notebook2.databinding.FragmentStartPageBinding

class StartPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentStartPageBinding.inflate(layoutInflater)

        binding.addButton.setOnClickListener {
            val fm = requireActivity().supportFragmentManager
            fm.beginTransaction().replace(R.id.fragmentContainerView, AddNote::class.java, null)
                .setReorderingAllowed(true).addToBackStack(null).commit()
        }
        binding.viewButton.setOnClickListener {
            val fm = requireActivity().supportFragmentManager
            fm.beginTransaction().replace(R.id.fragmentContainerView, ViewNotes::class.java, null)
                .setReorderingAllowed(true).addToBackStack(null).commit()
        }
        return binding.root
    }
}