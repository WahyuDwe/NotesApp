package com.wahyudwi.notesapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahyudwi.notesapp.R
import com.wahyudwi.notesapp.databinding.FragmentHomeBinding
import com.wahyudwi.notesapp.utils.SortUtils

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var mHomeViewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

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
        mHomeViewModel.getAllNote(SortUtils.LATEST).observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var filter = ""
        when (item.itemId) {
            R.id.action_latest -> filter = SortUtils.LATEST
            R.id.action_oldest -> filter = SortUtils.OLDEST
            R.id.action_random -> filter = SortUtils.RANDOM
        }
        mHomeViewModel.getAllNote(filter).observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }
}