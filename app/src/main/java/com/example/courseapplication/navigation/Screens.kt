package com.example.courseapplication.navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import  androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text

class Screens {
    @Preview
    @Composable
    fun HomeScreen(){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.Home,
                contentDescription = "home",
                tint = Color.Magenta
                )
            Text(text="Главная", color=Color.Blue)
        }
    }

    @Composable
    fun FavoriteScreen(){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "favorite",
                tint = Color.Magenta
            )
            Text(text="Избранное", color=Color.Blue)
        }
    }

    @Composable
    fun ProfileScreen(){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Default.Person,
                contentDescription = "profile",
                tint = Color.Magenta
            )
            Text(text="Аккаунт", color=Color.Blue)
        }
    }
}