package com.wahyudwi.notesapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyudwi.notesapp.R
import com.wahyudwi.notesapp.data.entity.NoteEntity
import com.wahyudwi.notesapp.databinding.FragmentHomeBinding
import com.wahyudwi.notesapp.utils.SortUtils

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val listNote: ArrayList<NoteEntity> = arrayListOf()
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    private val noteObserver = Observer<List<NoteEntity>> { noteList ->
        if (noteList != null) {
            adapter.setData(noteList)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeAdapter()

        binding?.apply {
            fabAdd.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addFragment)
            }
            rvNote.adapter = adapter
            rvNote.setHasFixedSize(true)
            rvNote.layoutManager = LinearLayoutManager(requireContext())
        }

        mHomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        mHomeViewModel.getAllNote(SortUtils.LATEST).observe(viewLifecycleOwner, noteObserver)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.maxWidth = Int.MAX_VALUE
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean = true

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchNote(query)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var filter = ""
        when (item.itemId) {
            R.id.action_latest -> filter = SortUtils.LATEST
            R.id.action_oldest -> filter = SortUtils.OLDEST
            R.id.action_random -> filter = SortUtils.RANDOM
        }
        mHomeViewModel.getAllNote(filter).observe(viewLifecycleOwner, noteObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private fun searchNote(query: String) {
        val searchQuery = "%$query%"
        mHomeViewModel.getSearchNote(searchQuery).observe(this) { listNote ->
            adapter.setData(listNote)
        }
    }
}