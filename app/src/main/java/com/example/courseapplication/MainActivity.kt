package com.example.courseapplication

import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Layout
import android.util.Log
import android.util.Size
import android.view.Menu
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.courseapplication.navigation.NavigationContainer
import com.example.courseapplication.ui.theme.CourseApplicationTheme
import com.example.data.models.Course
import com.example.interaction.DataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.sp
import androidx.room.Room
import androidx.room.util.TableInfo
import com.example.interaction.database.CourseDatabase
import com.example.courseapplication.navigation.NavigationBar
class MainActivity : ComponentActivity() {
    //lateinit
    val dataClass =  DataClass(this@MainActivity);
    var navigationBar : NavigationBar = NavigationBar();

    var courses: List<Course> = arrayListOf()

    fun changeStateCourse(course : Course) {
        if(course.getLike())
            dataClass.saveInDatabase()
        else
            dataClass.removeFromDatabase()
    }
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dataClass = DataClass(applicationContext)
//                var db = Room.databaseBuilder(applicationContext, CourseDatabase::class.java, "courses_db.db")
//            .build();
        //enableEdgeToEdge()
        getCoursesData() // Передаем контекст в suspend-функцию
        setContent {
            coursesPage(courses)
        }
    }

    fun getCoursesData() {
        scope.launch {
            // Ждём завершения запроса
            val result = dataClass.sendRequest()
            //когда все законсчилось
            when (result) {
                is DataClass.Result.Success -> {
                    courses = result.data.toList()
                    /*
                    val toast = Toast.makeText(
                        this@MainActivity,
                        "Size: ${courses.size}",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                    */
                    //надо отобразить результаты
                    setContent {
                        coursesPage(courses)
                    }
                }

                is DataClass.Result.Failure -> {
                    showError("Ошибка загрузки: ${result.exception.message}")
                }
            }
        }
    }

    private fun showError(message: String) {
        println("Ошибка: $message")
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
    @Composable
    fun coursesPage(courses: List<Course>) {
        navigationBar.createBottomNavigationBar();

        //val context = LocalContext.current // Получаем контекст внутри Composable
        var isEmpty = courses.isEmpty()
        when (isEmpty) {
            true -> {
                Log.w("form", "list is empty")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            else -> {
                Log.w("form", "!!!!!! list is NOT empty")
                LazyColumn(Modifier.padding(16.dp, 16.dp, 16.dp)) {
                    items(courses){ course->
                      PersonView(course = course)
                    }
                }
            }
        }
    }

    @Composable
    fun PersonView(course: Course) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(){
                //в первой строке отобожаем рейтинг и дату начала
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(course.getRateToStr(), modifier =Modifier.align(Alignment.TopStart))
                    Text(course.getStartData("dd MMMM yyyy"), modifier =Modifier.align(Alignment.TopEnd))
                }

                //друг за другом отображаем:
                //название курса
                //текст (максимум 2 строки)
                //затем в строке цену и кнопку подробнее
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(course.getTitle(), modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text(
                        text = course.getText(),
                        maxLines = 2
                    )
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(course.getPrice(), modifier =Modifier.align(Alignment.TopStart))
                        Button(onClick = {}, modifier =Modifier.align(Alignment.TopEnd)) {
                            Text("Подробнее")
                        }
                    }
                }
            }
        }
    }
}
