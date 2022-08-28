package com.example.notebook_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.notebook_example.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var noteBinding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteBinding = DataBindingUtil.setContentView(this, R.layout.activity_note)
    }
}