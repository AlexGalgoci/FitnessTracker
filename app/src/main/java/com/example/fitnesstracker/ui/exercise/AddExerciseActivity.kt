package com.example.fitnesstracker.ui.exercise

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnesstracker.R

class AddExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

        val addButton: Button = findViewById(R.id.addButton)
        val exerciseName: EditText = findViewById(R.id.exerciseName)
        val exerciseDuration: EditText = findViewById(R.id.exerciseDuration)

        addButton.setOnClickListener {
            val name = exerciseName.text.toString()
            val duration = exerciseDuration.text.toString().toInt()
            val exercise = Exercise(name, duration)

            val resultIntent = intent
            resultIntent.putExtra("exercise", exercise)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
