package com.example.application_note

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application_note.ui.NoteViewModel
import com.example.application_note.ui.screens.AddEditNoteScreen
import com.example.application_note.ui.screens.NoteListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: NoteViewModel = viewModel()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate("add") },
                onEditClick = { note ->
                    navController.navigate("edit/${note.id}")
                }
            )
        }

        composable("add") {
            AddEditNoteScreen(
                onSave = { note ->
                    viewModel.addNote(note.title, note.content)
                    navController.popBackStack()
                }
            )
        }

        composable(
            "edit/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId")
            val note = viewModel.getNoteById(noteId)

            if (note != null) {
                AddEditNoteScreen(
                    note = note,
                    onSave = { updatedNote ->
                        viewModel.updateNote(updatedNote)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
