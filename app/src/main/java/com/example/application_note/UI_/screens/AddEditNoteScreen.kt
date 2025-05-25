package com.example.application_note.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application_note.ui.NoteViewModel

@Composable
fun AddEditNoteScreen(
    viewModel: NoteViewModel = viewModel(),
    onSave: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Contenu") },
            modifier = Modifier.fillMaxWidth().height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (title.isNotBlank() && content.isNotBlank()) {
                viewModel.addNote(title, content)
                onSave()
            }
        }) {
            Text("Enregistrer")
        }
    }
}
