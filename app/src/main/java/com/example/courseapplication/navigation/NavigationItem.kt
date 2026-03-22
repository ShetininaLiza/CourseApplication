package com.example.courseapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val label: String,
    val  icon : ImageVector,
    val route : String,
)

object NavigationContainer{
    val navigationItems=listOf(
        NavigationItem("Главная", Icons.Filled.Home, "home"),
        NavigationItem("Избранное", Icons.Filled.FavoriteBorder, "home"),
        NavigationItem("Аккаунт", Icons.Filled.Person, "home")
    )
}