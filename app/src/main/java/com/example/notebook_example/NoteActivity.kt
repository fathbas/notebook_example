package com.example.notebook_example.ui.note

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.notebook_example.R
import com.example.notebook_example.model.UserModel

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        val user = intent.getSerializableExtra("userModel") as UserModel
        val textv: TextView = findViewById(R.id.deneme)
        textv.text = user.nameAndSurname
    }
}