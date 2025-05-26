package com.example.application_note.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Déclaration d'une data class qui comporte les id, un titre et un content
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String
)
