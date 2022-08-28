package com.example.notebook_example.ui.note


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook_example.model.ErrorResponseModel
import com.example.notebook_example.model.NoteModel
import com.example.notebook_example.util.SingleEvent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteListFragmentViewModel : ViewModel() {

    val noteListResponse: SingleEvent<NoteListEvent> by lazy { SingleEvent() }
    private var mFirestore: FirebaseFirestore? = null


    init {
        mFirestore = FirebaseFirestore.getInstance()
    }

    fun getNoteList(uid: String) {
        val notes: ArrayList<NoteModel> = arrayListOf()

        viewModelScope.launch(Dispatchers.Main) {
            noteListResponse.value = NoteListEvent.Loading
            mFirestore!!.collection(uid).addSnapshotListener { value, _ ->
                    for (doc in value!!) {
                        val note =
                            NoteModel(
                                doc.data["noteHeader"].toString(),
                                doc.data["noteContent"].toString(),
                                doc.data["date"].toString()
                            )
                        notes.add(note)
                    }

                noteListResponse.value = NoteListEvent.Success(noteListResponse = notes)
                }



        }

    }
    sealed class NoteListEvent {
        class Success(val noteListResponse: List<NoteModel>) : NoteListEvent()
        class Failure(val errorResponseModel: ErrorResponseModel) : NoteListEvent()
        object Loading : NoteListEvent()
        object Empty : NoteListEvent()
    }
}



