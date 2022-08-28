package com.example.notebook_example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val uid: String,
    val nameAndSurname: String,
    val email: String,
    val password: String,
    val securityQuestion: String,
    val questionAnswer: String
): Parcelable

