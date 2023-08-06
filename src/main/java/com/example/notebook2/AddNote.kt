package com.example.notebook2

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.notebook2.databinding.FragmentAddNoteBinding

@Suppress("DEPRECATION")
class AddNote : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddNoteBinding = FragmentAddNoteBinding.inflate(layoutInflater)

        binding.saveButton.setOnClickListener { view: View ->
            binding.titleWarning.text = ""
            binding.noteWarning.text = ""
            if (binding.titleEdtTxt.text.toString().isEmpty()) {
                binding.titleWarning.text = "*Please Enter the Title."
            }
            if (binding.noteEdtTxt.text.toString().isEmpty()) {
                binding.noteWarning.text = "*Please Enter Your Note Text."
            }
            if (binding.titleEdtTxt.text.toString().isNotEmpty() && binding.noteEdtTxt.text.toString().isNotEmpty()) {
                if (!Management.system!!.titles.contains(binding.titleEdtTxt.text.toString())) {
                    Management.system!!
                        .addNote(binding.titleEdtTxt.text.toString(), binding.noteEdtTxt.text.toString())
                    Management.system!!.addTitle(binding.titleEdtTxt.text.toString())
                    val toastInflater = LayoutInflater.from(context)
                    val toastLayout = toastInflater.inflate(
                        R.layout.toast_layout_positive,
                        view.findViewById(R.id.custom_toast_container)
                    )
                    val text = toastLayout.findViewById<TextView>(R.id.custom_toast_text)
                    text.text = "Note saved."
                    val toast = Toast(context)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.view = toastLayout
                    toast.show()
                } else {
                    val builder = AlertDialog.Builder(
                        ContextThemeWrapper(
                            context, R.style.AlertDialogTheme
                        )
                    )
                    builder.setMessage("A Note with this title already exists. Do you want to replace it? ")
                    builder.setPositiveButton("YES") { _: DialogInterface?, _: Int ->
                        Management.system!!
                            .addNote(binding.titleEdtTxt.text.toString(), binding.noteEdtTxt.text.toString())
                        val toastInflater = LayoutInflater.from(context)
                        val toastLayout = toastInflater.inflate(
                            R.layout.toast_layout_positive,
                            view.findViewById(R.id.custom_toast_container)
                        )
                        val text = toastLayout.findViewById<TextView>(R.id.custom_toast_text)
                        text.text = "Note Replaced."
                        val toast = Toast(context)
                        toast.duration = Toast.LENGTH_SHORT
                        toast.view = toastLayout
                        toast.show()
                    }
                    builder.setNegativeButton("NO") { _: DialogInterface?, _: Int -> }
                    builder.create().show()
                }
                val fm = requireActivity().supportFragmentManager
                fm.beginTransaction()
                    .replace(R.id.fragmentContainerView, StartPage::class.java, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit()
            }
        }
        return binding.root
    }
}