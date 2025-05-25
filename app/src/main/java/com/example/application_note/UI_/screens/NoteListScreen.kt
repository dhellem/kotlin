package com.example.application_note.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application_note.ui.NoteViewModel
import com.example.application_note.data.Note
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.Edit
import com.example.application_note.ui.theme.BlueDark
import com.example.application_note.ui.theme.White
import com.example.application_note.ui.theme.YellowLight
import com.example.application_note.ui.theme.BackgroundLight
import androidx.compose.foundation.background




@Composable
fun NoteListScreen(
    viewModel: NoteViewModel = viewModel(),
    onAddClick: () -> Unit,
    onEditClick: (Note) -> Unit
) {
    val notes by viewModel.notes.observeAsState(emptyList())
    var noteToDelete by remember { mutableStateOf<Note?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = BlueDark,
                contentColor = White
            ) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        },
        containerColor = BackgroundLight // fond général clair
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BlueDark)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.headlineLarge,
                    color = White
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(notes.size) { i ->
                    val note = notes[i]
                    NoteItem(
                        note = note,
                        onDeleteClick = { noteToDelete = note },
                        onEditClick = { onEditClick(note) }
                    )
                }
            }
        }
    }

    if (noteToDelete != null) {
        AlertDialog(
            onDismissRequest = { noteToDelete = null },
            title = { Text("Supprimer la note") },
            text = { Text("Voulez-vous vraiment supprimer la note \"${noteToDelete?.title}\" ?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        noteToDelete?.let { viewModel.deleteNote(it) }
                        noteToDelete = null
                    }
                ) {
                    Text("Oui")
                }
            },
            dismissButton = {
                TextButton(onClick = { noteToDelete = null }) {
                    Text("Non")
                }
            }
        )
    }
}



@Composable
fun NoteItem(
    note: Note,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(note.title, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(note.content, style = MaterialTheme.typography.bodyMedium)
            }

            Row {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit, // Icône édition
                        contentDescription = "Modifier la note"
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Supprimer la note"
                    )
                }
            }
        }
    }
}
