package com.example.interaction.database

import android.annotation.SuppressLint
import androidx.room.Room
import dbModels.CourseModel
import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.Course
import java.time.LocalDate
import com.example.interaction.database.CourseDAO
@Database(entities = [(CourseModel::class)], version = 1)
abstract class CourseDatabase : RoomDatabase() {
   abstract fun courseDAO() : CourseDAO;

   // реализуем синглтон
   companion object {
      private var INSTANCE: CourseDatabase? = null
      fun getInstance(context: Context): CourseDatabase {

         synchronized(this) {
            var instance = INSTANCE
            if (instance == null) {
               instance = Room.databaseBuilder(
                  context.applicationContext,
                  CourseDatabase::class.java,
                  "courses_db"

               ).build()
               INSTANCE = instance
            }
            return instance
         }
      }
   }
}