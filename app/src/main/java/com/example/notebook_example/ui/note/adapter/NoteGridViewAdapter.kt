package com.example.notebook_example.ui.note.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.notebook_example.R
import com.example.notebook_example.model.NoteModel

class NoteGridViewAdapter(context: Context, private var data: List<NoteModel>): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val itemHolder: ItemHolder

        if (p1 == null){
            view = inflater.inflate(R.layout.note_card_view,p2,false)
            itemHolder = ItemHolder(view)
            view.tag = itemHolder
        }else{
            view = p1
            itemHolder = view.tag as ItemHolder
        }


        Log.d("user",data.toString())

        itemHolder.noteHeader.text = data[p0].noteHeader
        itemHolder.noteContent.text = data[p0].noteContent
        itemHolder.noteTime.text = data[p0].date

        return view
    }

    private class ItemHolder(row:View?){
        val noteHeader: TextView
        val noteContent: TextView
        val noteTime: TextView

        init {
            noteHeader = row?.findViewById(R.id.note_header) as TextView
            noteContent = row.findViewById(R.id.note_content) as TextView
            noteTime = row.findViewById(R.id.note_time) as TextView
        }
    }
}