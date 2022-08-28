package com.example.notebook_example.ui.register.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.notebook_example.R
import com.example.notebook_example.model.CustomSpinnerModel


class CustomSpinnerAdapter(context: Context, private var data: List<CustomSpinnerModel>): BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0].spinnerText
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View
        val itemHolder: ItemHolder
        if (p1== null){
            view = inflater.inflate(R.layout.custom_spinner_item, p2, false)
            itemHolder = ItemHolder(view)
            view.tag = itemHolder
        }else{
            view = p1
            itemHolder = view.tag as ItemHolder
        }

        itemHolder.label.text = data[p0].spinnerText
        return view
    }

    private class ItemHolder(row: View?){
        val label: TextView

        init {
            label = row?.findViewById(R.id.spinner_text_view) as TextView
        }
    }


}