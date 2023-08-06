package com.example.notebook2

class Management {
    val titlesNotesMap = HashMap<String, String>()
    val titles = ArrayList<String>()
    fun addNote(title: String, note: String) {
        titlesNotesMap[title] = note
    }

    fun addTitle(title: String) {
        titles.add(title)
    }

    fun removeNote(title: String) {
        titles.remove(title)
        titlesNotesMap.remove(title)
    }

    companion object {
        @JvmStatic
        var system: Management? = null
            get() {
                if (field == null) {
                    field = Management()
                }
                return field
            }
            private set
    }
}