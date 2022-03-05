package com.wahyudwi.notesapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.wahyudwi.notesapp.data.entity.NoteEntity

class DiffUtils(
    private val oldList: List<NoteEntity>,
    private val newList: List<NoteEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false

            oldList[oldItemPosition].title != newList[newItemPosition].title -> false

            oldList[oldItemPosition].content != newList[newItemPosition].content -> false

            else -> true
        }
    }
}