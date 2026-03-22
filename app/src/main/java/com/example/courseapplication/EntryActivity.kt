package com.example.courseapplication

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courseapplication.ui.theme.CourseApplicationTheme
import com.example.interaction.*;
import kotlin.math.log

class EntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnterForm();
        }

    }
}
@Preview(showBackground = true)
@Composable
fun EnterForm(){
    val contx : Context = LocalContext.current;
    var inter = InteractionClass();
    val email = remember { mutableStateOf("") };
    val password = remember { mutableStateOf("") };
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Вход\n", fontSize = 28.sp);
        Text("Email", fontSize = 16.sp);
        TextField(
            value = email.value,
            onValueChange = {newValue -> email.value = newValue},
            placeholder = {},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        );
        Text("\nПароль");
        TextField(
            value = password.value,
            onValueChange = {newValue -> password.value = newValue },
            placeholder = {"Введите"},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        );
        Button(
            onClick = {
                inter.openActivity(contx,MainActivity::class.java);
            }
        ) {
            Text("Вход")
        }

        Row() {
            Text("Нет аккаунта?", fontSize = 12.sp);
            Text("Регистрация", fontSize = 12.sp)
        }
        Text("Забыл пароль", fontSize = 12.sp)

        Row() {
            Button(onClick = { inter.openURL("https://vk.com/", contx)
                }) {
                Text("VK")
            }
            Button(onClick = { inter.openURL("https://ok.ru/", contx)
                }) {
                Text("OK")
            }
        }
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CourseApplicationTheme {
        Greeting("Android")
    }
}
*/