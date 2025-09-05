package com.example.flashcard

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question1)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer1)
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE

        }
        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
            // find the toggle button
            val isShowingAnswers =
                findViewById<ImageView>(R.id.toggle_choices_visibility)
            val flashcardChoice1 = findViewById<TextView>(R.id.flashcard_q1_choice1)
            val flashcardChoice2 = findViewById<TextView>(R.id.flashcard_q1_choice2)
            val flashcardChoice3 = findViewById<TextView>(R.id.flashcard_q1_choice3)
            // set initial icon
            isShowingAnswers.setImageResource(R.drawable.eye_lined_off)
            isShowingAnswers.setOnClickListener {
                flashcardChoice1.visibility = View.INVISIBLE
                flashcardChoice2.visibility = View.INVISIBLE
                flashcardChoice3.visibility = View.INVISIBLE
            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}