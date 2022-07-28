package com.example.notebook_example.model

data class UserModel(
    val nameAndSurname: String,
    val email: String,
    val password: String,
    val securityQuestion: String,
    val questionAnswer: String,
    val noteList: List<NoteModel>
)
