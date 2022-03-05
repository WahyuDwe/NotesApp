package com.wahyudwi.notesapp.ui.content

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahyudwi.notesapp.R
import com.wahyudwi.notesapp.data.entity.NoteEntity
import com.wahyudwi.notesapp.databinding.FragmentContentBinding


class ContentFragment : Fragment() {
    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding
    private val args by navArgs<ContentFragmentArgs>()
    private lateinit var mContentViewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContentViewModel = ViewModelProvider(this)[ContentViewModel::class.java]
        binding?.apply {
            etTitle.setText(args.currentNote.title)
            etContent.setText(args.currentNote.content)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.content_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_save -> saveNote()

            R.id.menu_delete -> deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveNote() {
        binding?.apply {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            if (args.currentNote.title.isEmpty() && args.currentNote.content.isEmpty()) {
                val note = NoteEntity(0, title, content)
                mContentViewModel.addNote(note)
            } else {
                val newNote = NoteEntity(args.currentNote.id, title, content)
                mContentViewModel.updateNote(newNote)
                Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_contentFragment_to_homeFragment)
            }
        }
    }

    private fun deleteNote() {
        TODO("Not yet implemented")
    }
}