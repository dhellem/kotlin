package com.example.application_note.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application_note.data.Note
import com.example.application_note.ui.theme.Black
import com.example.application_note.ui.theme.DarkGray
import com.example.application_note.ui.theme.LightMediumGray
import com.example.application_note.ui.theme.MediumDarkGrey
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
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .background(MediumDarkGrey, shape = RoundedCornerShape(16.dp))
                    .border(width = 2.dp, color = LightMediumGray, shape = RoundedCornerShape(16.dp))
                    .padding(1.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = {
                        Text(
                            "Titre",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Black,
                            fontWeight = FontWeight.Bold // contenu en gras
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Black,
                        unfocusedTextColor = Black,
                        focusedContainerColor = MediumDarkGrey,
                        unfocusedContainerColor = MediumDarkGrey,
                        focusedLabelColor = Black,
                        unfocusedLabelColor = Black,
                        cursorColor = Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(16.dp),
                    textStyle = TextStyle(
                        color = Black,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MediumDarkGrey, shape = RoundedCornerShape(16.dp))
                    .border(width = 2.dp, color = LightMediumGray, shape = RoundedCornerShape(16.dp))
                    .padding(1.dp)
            ) {
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = {
                        Text(
                            "Contenu",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Black,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Black,
                        unfocusedTextColor = Black,
                        focusedContainerColor = MediumDarkGrey,
                        unfocusedContainerColor = MediumDarkGrey,
                        focusedLabelColor = Black,
                        unfocusedLabelColor = Black,
                        cursorColor = Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(16.dp),
                    textStyle = TextStyle(
                        color = Black,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bouton Enregistrer
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
                ),
                shape = RoundedCornerShape(50),
            ) {
                Text(
                    text = "Enregistrer",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
