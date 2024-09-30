package com.medlosoft.sqlitetest

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.medlosoft.sqlitetest.ui.theme.SQLiteTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "testdb1"
        )
        .allowMainThreadQueries() // TODO: Temporary. Remove this and implement coroutines
        .build()

        val newStudent = Student(
            name = "Roberto Medina",
            age = 22,
            career = "Software Engineering",
            active = true
        )

        db.studentDao().insert(newStudent)

        val allActiveStudents = db.studentDao().getAllActive()

        Log.i("tests", allActiveStudents.joinToString { it.name })

        setContent {
            SQLiteTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StudentsList(
                        students = allActiveStudents.map { it.name },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StudentsList(students: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(students) {
            BasicText(text = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentsPreview() {
    SQLiteTestTheme {
        StudentsList(listOf("Roberto Medina", "William Cáceres"))
    }
}