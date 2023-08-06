package com.example.notebook2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.notebook2.Management.Companion.system
import com.example.notebook2.databinding.FragmentNoteClickBinding

class NoteClick : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentNoteClickBinding.inflate(layoutInflater)

        binding.titleMainTxt.text = system!!.titles[mParam1]
        binding.noteMainTxt.text =
            system!!.titlesNotesMap[system!!.titles[mParam1]]
        return binding.root
    }

    companion object {
        private var mParam1 = 0
        private fun setMParam1(Param1: Int) {
            mParam1 = Param1
        }

        @JvmStatic
        fun newInstance(param1: Int) {
            setMParam1(param1)
        }
    }
}