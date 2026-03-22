package com.example.interaction.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dbModels.CourseModel

//Объект доступа к данным курсов
@Dao
interface CourseDAO {
    @Query("SELECT * FROM courses")
    fun getCourses(): LiveData<List<CourseModel>>

    @Insert
    fun addCourse(course: CourseModel)

    @Query("DELETE FROM courses WHERE title = :title")
    fun deleteCourse(title: String)
}
