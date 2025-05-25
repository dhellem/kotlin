package com.example.application_note.data

class NoteRepository(private val dao: NoteDao) {
    val notes = dao.getAllNotes()
    suspend fun insert(note: Note) = dao.insertNote(note)
    suspend fun delete(note: Note) = dao.deleteNote(note)
}
