package com.example.notebook_example.ui.note

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.notebook_example.R
import com.example.notebook_example.databinding.FragmentNewNoteBinding
import com.example.notebook_example.model.NoteModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.util.*

class NewNoteFragment : Fragment() {

    private lateinit var newNoteBinding:  FragmentNewNoteBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newNoteBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_new_note,container,false)
        initDb()

        Log.d("noteData", arguments?.getString("userUid").toString())
        Log.d("noteData", arguments?.getParcelableArrayList<NoteModel>("userNoteList").toString())
        val uid = arguments?.getString("userUid")
        newNoteBinding.saveNote.setOnClickListener {
            val noteHeader = newNoteBinding.newNoteHeader.text.toString()
            val noteContent = newNoteBinding.newNoteContent.text.toString()
            val noteDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Date.from(Instant.now()).toString()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val newNote = NoteModel(noteHeader,noteContent,noteDate)
            db.collection(uid.toString()).add(newNote).addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    "Update is failure. Please try again later",
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnSuccessListener { _->
                Toast.makeText(requireContext(), "Update is success.", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it).popBackStack(R.id.noteListFragment,false,
                    saveState = true
                )
            }
        }
        // Inflate the layout for this fragment
        return newNoteBinding.root
    }

    private fun initDb(){
        db = Firebase.firestore
    }
}