package com.example.application_note

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.application_note.ui.screens.AddEditNoteScreen
import com.example.application_note.ui.screens.NoteListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("list") {
            NoteListScreen(onAddClick = { navController.navigate("add") })
        }
        composable("add") {
            AddEditNoteScreen(onSave = { navController.popBackStack() })
        }
    }
}
