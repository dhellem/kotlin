package com.example.application_note.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application_note.data.Note

@Composable
fun AddEditNoteScreen(
    note: Note? = null,// si il n'y a pas de note on est en mode "ajout" sinon en mode "modifier"
    onSave: (Note) -> Unit//callback
) {
    //declare deux etats locaux avec des valeur initial ou non en fonction du mode
    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//paragraphe pour le titre
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
//paragraphe pour le contenue
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Contenu") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            //verifie si les deux paragraphe ne sont pas vide
            if (title.isNotBlank() && content.isNotBlank()) {
                val newNote = note?.copy(title = title, content = content) ?: Note(title = title, content = content)
                onSave(newNote)
            }
        }) {
            Text("Enregistrer")
        }
    }
}
