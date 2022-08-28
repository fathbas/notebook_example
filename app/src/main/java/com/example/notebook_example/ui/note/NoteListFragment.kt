package com.example.notebook_example.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.notebook_example.R
import com.example.notebook_example.databinding.FragmentNoteListBinding
import com.example.notebook_example.model.NoteModel
import com.example.notebook_example.model.UserModel
import com.example.notebook_example.ui.note.adapter.NoteGridViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteListFragment : Fragment() {

    private lateinit var noteListBinding: FragmentNoteListBinding
    private lateinit var userModel: UserModel
    private lateinit var userNoteList: List<NoteModel>

    private val noteListFragmentViewModel: NoteListFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noteListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_note_list, container, false
        )
        val args = activity?.intent?.extras
        if (args != null) {
            userModel = args.getParcelable<UserModel>("userData") as UserModel
        }

        userNoteList = emptyList()
        observers()
        noteListFragmentViewModel.getNoteList(userModel.uid)

        noteListBinding.noteListGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            Toast.makeText(
                requireContext(),
                i.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }


        noteListBinding.addNote.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("userUid", userModel.uid)
            it.findNavController().navigate(R.id.action_noteListFragment_to_newNoteFragment, bundle)
        }


        // Inflate the layout for this fragment
        return noteListBinding.root
    }


    private fun observers() {
        lifecycleScope.launch(Dispatchers.Main) {
            forLifeCycle()
        }
    }

    private fun forLifeCycle() {
        noteListFragmentViewModel.noteListResponse.observe(viewLifecycleOwner) { event ->
            when (event) {
                is NoteListFragmentViewModel.NoteListEvent.Success -> {

                   userNoteList = event.noteListResponse
                    val noteGridViewAdapter = NoteGridViewAdapter(requireContext(), userNoteList)

                    noteListBinding.noteListGrid.adapter = noteGridViewAdapter
                }
                is NoteListFragmentViewModel.NoteListEvent.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        event.errorResponseModel.errorMessage,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                is NoteListFragmentViewModel.NoteListEvent.Loading -> {
                    // no-op
                }

                is NoteListFragmentViewModel.NoteListEvent.Empty -> {
                    // no-op
                }
                else -> {
                    // no-op
                }
            }
        }
    }

}