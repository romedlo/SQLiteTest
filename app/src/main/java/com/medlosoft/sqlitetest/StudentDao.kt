package com.medlosoft.sqlitetest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student)

    @Query("SELECT * FROM students WHERE id = :id")
    fun getById(id: String) : Student

    @Query("SELECT * FROM students WHERE active = 1")
    fun getAllActive(): List<Student>

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)
}
