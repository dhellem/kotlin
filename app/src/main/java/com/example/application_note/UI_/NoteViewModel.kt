package com.example.application_note.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.application_note.data.Note
import com.example.application_note.data.NoteDatabase
import com.example.application_note.data.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository //permet d'interagir avec room(add,delet,update)
    val notes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()//recupere le note dans la db
        repository = NoteRepository(dao)
        notes = repository.notes.asLiveData()//transforme la liste de note pour que Compose les observe automatiquement
    }

    fun addNote(title: String, content: String) = viewModelScope.launch {
        repository.insert(Note(title = title, content = content))
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun getNoteById(id: Int?): Note? {
        return notes.value?.find { it.id == id }
    }

}
