package com.example.notebook2

class Note {
    private var title:String=""
    private var note:String=""


    fun getNote(): String{
        return note
    }

    fun getTitle(): String{
        return title
    }

    fun setNote(note: String){
        this.note=note
    }

    fun setTitle(title:String){
        this.title=title
    }

}