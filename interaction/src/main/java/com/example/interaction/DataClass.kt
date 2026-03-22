package com.example.interaction

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.data.models.Course
import com.example.interaction.adapter.LocalDateTypeAdapter
import com.example.interaction.database.CourseDatabase
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate


//класс для работы с данными
class DataClass {
    sealed class Result<out T> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Failure(val exception: Exception) : Result<Nothing>()
    }
    val context : Context
    //lateinit var db : CourseDatabase;
    constructor(context : Context){
        this.context = context;
        Log.w("DataClass", "Data Class constructor");
//        var db = Room.databaseBuilder(context, CourseDatabase::class.java, "courses_db.db")
//            .build();
        /*
        val instance = Room.databaseBuilder(
                context.applicationContext,
                CourseDatabase::class.java,
                "course_db"
            ).fallbackToDestructiveMigration(false)
            .build()
         */
        Log.w("DataClass", "1");
    }

    suspend fun sendRequest(): Result<List<Course>> = withContext(Dispatchers.IO) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://drive.usercontent.google.com/u/0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val courseApi = retrofit.create(ApiService::class.java)
            val response = courseApi.getCour().execute() // Блокирующий вызов

            if (response.isSuccessful) {
                val body = response.body()?.string() ?: ""
                val coursesData = body.substringAfter("courses")
                    .let { it.substring(3, it.lastIndex - 1) }

                Log.w("get Data", "getData")
                val courseList = splitData(coursesData)
                Result.Success(courseList)
            } else {
                Result.Failure(Exception("HTTP error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    @SuppressLint("NewApi")
    fun splitData(data : String) : List<Course>{
        var result : List<Course> = arrayListOf()
        if(!data.isNullOrEmpty()) {
            Log.w("read data", "SPLIT DATA: ${data}")
            var gson = GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
                .create();

            var type = object : TypeToken<Array<Course>>() {}.getType();
            result = gson.fromJson<Array<Course>>(data, type).toList();

            Log.w("read data", "LENGTH: ${result.size}")
            result.forEach { t -> Log.w("read data", t.getData()) }
        }
        return result;
    }

    fun saveInDatabase(){
        val toast = Toast.makeText(
            context,
            "SAVE In DATABASE",
            Toast.LENGTH_LONG
        )
        toast.show()
    }
    fun removeFromDatabase(){
        val toast = Toast.makeText(
            context,
            "REMOVE FROM DATABASE",
            Toast.LENGTH_LONG
        )
        toast.show()
    }
    fun loadFromDatabase(){

    }
}