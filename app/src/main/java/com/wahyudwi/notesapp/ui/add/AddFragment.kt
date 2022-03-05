package com.wahyudwi.notesapp.ui.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.wahyudwi.notesapp.R
import com.wahyudwi.notesapp.data.entity.NoteEntity
import com.wahyudwi.notesapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding
    private lateinit var mAddViewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAddViewModel = ViewModelProvider(this)[AddViewModel::class.java]
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add -> addNote()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addNote() {
        binding?.apply {
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            when {
                title.isEmpty() -> etTitle.error = getString(R.string.empty)

                content.isEmpty() -> etContent.error = getString(R.string.empty)

                else -> {
                    val note = NoteEntity(0, title, content)

                    mAddViewModel.addNote(note)
                    Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT)
                        .show()

                    findNavController().navigate(R.id.action_addFragment_to_homeFragment)
                }

            }
        }
    }
}