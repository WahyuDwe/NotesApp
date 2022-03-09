package com.wahyudwi.notesapp.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val LATEST = "latest"
    const val OLDEST = "oldest"
    const val RANDOM = "random"

    fun sortedQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM note_table ")
        when (filter) {
            LATEST -> simpleQuery.append("ORDER BY id DESC")

            OLDEST -> simpleQuery.append("ORDER BY id ASC")

            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}