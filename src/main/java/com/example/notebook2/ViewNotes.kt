package com.example.notebook2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook2.Management.Companion.system
import com.example.notebook2.NoteClick.Companion.newInstance
import com.example.notebook2.databinding.FragmentViewNotesBinding

class ViewNotes : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentViewNotesBinding.inflate(layoutInflater)

        if (system!!.titles.isNotEmpty()) {
            binding.noNotesTxt.text = ""
            val adapter = NotesAdapter(requireContext())
            val notesAndTitles = HashMap<String, String?>()
            for (i in 0 until system!!.titlesNotesMap.size) {
                notesAndTitles[system!!.titles[i]] = system!!.titlesNotesMap[system!!.titles[i]]
            }
            adapter.setHashMap(notesAndTitles)
            adapter.onItemClickListener =
                object : OnItemClickListener {
                    override fun onClick(o: Any?, position: Int) {
                        newInstance(position)
                    }
                }
            binding.notesRV.adapter = adapter
            binding.notesRV.layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }
}