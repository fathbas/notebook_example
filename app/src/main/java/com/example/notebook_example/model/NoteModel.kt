package com.example.notebook_example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    val noteHeader: String,
    val noteContent: String,
    val date: String
): Parcelable
