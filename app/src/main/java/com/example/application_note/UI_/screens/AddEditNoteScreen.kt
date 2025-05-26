package com.example.application_note.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.application_note.data.Note
import com.example.application_note.ui.theme.Black
import com.example.application_note.ui.theme.DarkGray
import com.example.application_note.ui.theme.MediumDarkGray
import com.example.application_note.ui.theme.Yellow

@Composable
fun AddEditNoteScreen(
    note: Note? = null,
    onSave: (Note) -> Unit
) {
    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = DarkGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(DarkGray)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = {
                    Text(
                        "Titre",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Black
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Black,
                    unfocusedTextColor = Black,
                    focusedContainerColor = MediumDarkGray,
                    unfocusedContainerColor = MediumDarkGray,
                    focusedLabelColor = Black,
                    unfocusedLabelColor = Black,
                    cursorColor = Black,
                    focusedBorderColor = Black,
                    unfocusedBorderColor = Yellow
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .background(MediumDarkGray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = {
                    Text(
                        "Contenu",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Black
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Black,
                    unfocusedTextColor = Black,
                    focusedContainerColor = MediumDarkGray,
                    unfocusedContainerColor = MediumDarkGray,
                    focusedLabelColor = Black,
                    unfocusedLabelColor = Black,
                    cursorColor = Black,
                    focusedBorderColor = Black,
                    unfocusedBorderColor = Yellow
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(MediumDarkGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        val newNote = note?.copy(title = title, content = content)
                            ?: Note(title = title, content = content)
                        onSave(newNote)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Black,
                )
            ) {
                Text(
                    text = "Enregistrer",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
