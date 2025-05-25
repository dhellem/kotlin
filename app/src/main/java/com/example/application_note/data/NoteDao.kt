package com.example.application_note.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // cette requete récupère toute les notes grace aux ID, le Flow<List<Note>> permet de voir les changements en temps réel
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    // cela permet d'insérer une nouvelle note dans la base de donnée
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    // supprimer une note
    @Delete
    suspend fun deleteNote(note: Note)

    // modifier une note
    @Update
    suspend fun update(note: Note)
}