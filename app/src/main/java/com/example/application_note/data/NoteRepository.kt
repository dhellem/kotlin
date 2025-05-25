package com.example.application_note.data

// cette class permet d'accéder entre la source de donnée noteDao et le reste de l'app (accéder aux notes et en meme temps supprimer/insérer des données.
class NoteRepository(private val dao: NoteDao) {
    val notes = dao.getAllNotes()
    suspend fun insert(note: Note) = dao.insertNote(note)
    suspend fun delete(note: Note) = dao.deleteNote(note)
    suspend fun update(note: Note) = dao.update(note)
}

