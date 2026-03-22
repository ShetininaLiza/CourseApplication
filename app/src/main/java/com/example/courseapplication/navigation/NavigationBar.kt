package com.example.courseapplication.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.courseapplication.MainActivity
import com.example.courseapplication.R
import com.example.data.models.Course

class NavigationBar {
    @Composable
    fun NavHostContainer(navController: NavHostController, padding: PaddingValues) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues = padding),
            builder = {
                composable("home") {
                    MainActivity()
                }
            })
    }
    @Composable
    fun createBottomNavigationBar(){
        val navController = rememberNavController();
        // Сначала получаем Painter в @Composable-контексте
        val painter = painterResource(R.drawable.flag)
        // Затем сохраняем его через remember
        var stateButton by remember { mutableStateOf(painter) }
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController = navController)
            },
            content = { paddingValues ->
                NavHostContainer(navController = navController, padding = paddingValues)
            })
    }
    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        NavigationBar(containerColor = Color.Green) {
            val navBackStruckEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStruckEntry?.destination?.route
            NavigationContainer.navigationItems.forEach { navItem ->
                NavigationBarItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route)
                    },
                    icon = {
                        Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                    },
                    label = { Text(navItem.label) },
                    alwaysShowLabel = true,
                    )
            }
        }
    }
    @Composable
    fun FavoriteButton(item: Course) {
        // Храним логическое состояние (избегает сравнения объектов-рисунков)
        var isFavorite by remember { mutableStateOf(item.getLike()) }

        IconButton(onClick = {
            // Переключаем локальное состояние
            isFavorite = !isFavorite
            item.setLike(isFavorite)
            // Обновляем состояние элемента
            //changeStateCourse(item)
        }) {
            Icon(
                painter = painterResource(
                    if (isFavorite) R.drawable.favorite else R.drawable.flag
                ),
                contentDescription = null,
                modifier = Modifier.size(
                    24.dp
                )
            )
        }
    }
}