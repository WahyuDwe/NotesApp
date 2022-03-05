package com.wahyudwi.notesapp.ui.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wahyudwi.notesapp.R
import com.wahyudwi.notesapp.data.entity.NoteEntity
import com.wahyudwi.notesapp.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUpdateViewModel: UpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUpdateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
        binding?.apply {
            etTitle.setText(args.currentNote.title)
            etContent.setText(args.currentNote.content)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
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

            when {
                title.isEmpty() -> {
                    etTitle.error = getString(R.string.empty)
                }

                content.isEmpty() -> {
                    etContent.error = getString(R.string.empty)
                }

                else -> {
                    val newNote = NoteEntity(args.currentNote.id, title, content, "")
                    mUpdateViewModel.updateNote(newNote)
                    Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
                }
            }

        }
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setPositiveButton("Yes") { _, _ ->
                mUpdateViewModel.deleteNote(args.currentNote)
                Toast.makeText(requireContext(), "Successfully removed", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }

            setNegativeButton("No") { _, _ ->

            }

            setTitle("Delete")
            setMessage("Are you sure want to delete this?")
            create().show()
        }
    }
}