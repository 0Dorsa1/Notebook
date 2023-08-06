package com.example.notebook2

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook2.Management.Companion.system

@Suppress("DEPRECATION")
class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    @JvmField
    var onItemClickListener: OnItemClickListener? = null
    private var titlesNotes = HashMap<String, String?>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var parentCardViewLayout: CardView
        var note: TextView
        var title: TextView
        var delete: TextView

        init {
            parentCardViewLayout = itemView.findViewById(R.id.parentCardViewLayout)
            note = itemView.findViewById(R.id.noteTxt)
            title = itemView.findViewById(R.id.titleTxt)
            delete = itemView.findViewById(R.id.delete)
            parentCardViewLayout.setOnClickListener {
                val fm = (context as MainActivity).supportFragmentManager
                fm.beginTransaction()
                    .replace(R.id.fragmentContainerView, NoteClick::class.java, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit()
                val position = layoutPosition
                onItemClickListener!!.onClick(null, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_preview, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.title.text = system!!.titles[position]
        holder.note.text = system!!.titlesNotesMap[system!!.titles[position]]
        holder.delete.setOnClickListener { view: View ->
            val builder = AlertDialog.Builder(
                ContextThemeWrapper(
                    context, R.style.AlertDialogTheme
                )
            )
            builder.setMessage("Delete " + system!!.titles[position] + "?")
            builder.setPositiveButton("YES") { _: DialogInterface?, _: Int ->
                system!!.removeNote(
                    system!!.titles[position]
                )
                val toastInflater = LayoutInflater.from(context)
                val toastLayout = toastInflater.inflate(
                    R.layout.toast_layout_positive,
                    view.findViewById(R.id.custom_toast_container)
                )
                val text = toastLayout.findViewById<TextView>(R.id.custom_toast_text)
                text.text = "Note deleted."
                val fm = (context as MainActivity).supportFragmentManager
                fm.beginTransaction()
                    .replace(R.id.fragmentContainerView, StartPage::class.java, null)
                    .setReorderingAllowed(true).addToBackStack(null).commit()
                val toast = Toast(context)
                toast.duration = Toast.LENGTH_SHORT
                toast.view = toastLayout
                toast.show()
            }
            builder.setNegativeButton("NO") { _: DialogInterface?, _: Int -> }
            builder.create().show()
        }
    }

    override fun getItemCount(): Int {
        return titlesNotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHashMap(titlesNotesMap: HashMap<String, String?>) {
        this.titlesNotes = titlesNotesMap
        notifyDataSetChanged()
    }
}