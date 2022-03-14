package com.wahyudwi.notesapp.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahyudwi.notesapp.data.entity.NoteEntity
import com.wahyudwi.notesapp.databinding.ItemNoteBinding
import com.wahyudwi.notesapp.utils.DiffUtils


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.NoteViewHolder>() {
    private var oldList = emptyList<NoteEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemBinding =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = oldList[position]
        holder.bind(note)
        holder.cvColor(position)
    }

    override fun getItemCount(): Int = oldList.size

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            binding.apply {
                tvTitle.text = note.title
                tvContent.text = note.content
                tvDate.text = note.date
                itemView.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(note)
                    itemView.findNavController().navigate(action)
                }

            }
        }

        fun cvColor(position: Int) {
            binding.apply {
                when {
                    position % 3 == 0 -> {
                        cvNote.setCardBackgroundColor(Color.parseColor("#FFF89A"))
                    }
                    position % 2 == 0 -> {
                        cvNote.setCardBackgroundColor(Color.parseColor("#FFB2A6"))
                    }
                    else -> {
                        cvNote.setCardBackgroundColor(Color.parseColor("#FF8AAE"))
                    }
                }
            }
        }

    }

    fun setData(newList: List<NoteEntity>) {
        val diffUtils = DiffUtils(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtils)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}