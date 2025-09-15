package com.example.flashcard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private var isOn = false
    private var rp1 = false
    private var rp2 = false
    private var rp3 = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Set title to FlashCard App
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        supportActionBar?.title = "FlashCard App"

        // Get the question and answer text views
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question1)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer1)
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE

            // Animation rotation
            val rotate = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            flashcardQuestion.startAnimation(rotate)
            AlphaAnimation(0f, 1f)

            rotate.duration = 150
            flashcardQuestion.startAnimation(rotate)
            AlphaAnimation(0f, 1f)

        }
        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
            // Animation rotation
            val rotate = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            flashcardAnswer.startAnimation(rotate)
            AlphaAnimation(0f, 1f)
            rotate.duration = 150
            flashcardAnswer.startAnimation(rotate)
            AlphaAnimation(0f, 1f)

        }
        // Set values
        val isShowingAnswers = findViewById<ImageView>(R.id.toggle_choices_visibility)
        val flashcardChoice1 = findViewById<TextView>(R.id.flashcard_q1_choice1)
        val flashcardChoice2 = findViewById<TextView>(R.id.flashcard_q1_choice2)
        val flashcardChoice3 = findViewById<TextView>(R.id.flashcard_q1_choice3)

        // Validate the responses
        flashcardChoice1.setOnClickListener {
            if (rp1) {
                flashcardChoice1.setBackgroundColor(Color.RED)
            } else {
                flashcardChoice1.setBackgroundColor(Color.RED)
                flashcardChoice2.setBackgroundColor(Color.YELLOW)
                flashcardChoice3.setBackgroundColor(Color.GREEN)

            }
            // Active
            rp1 = !rp1
        }
        flashcardChoice2.setOnClickListener {
            if (rp2) {
                flashcardChoice2.setBackgroundColor(Color.RED)
                // Retour par defaut
            } else {
                flashcardChoice1.setBackgroundColor(Color.YELLOW)
                flashcardChoice2.setBackgroundColor(Color.RED)
                flashcardChoice3.setBackgroundColor(Color.GREEN)
            }
            // Active
            rp2 = !rp2
        }
        flashcardChoice3.setOnClickListener {
            if (rp3) {
                flashcardChoice3.setBackgroundColor(Color.GREEN)
                // Retour par defaut
            } else {
                flashcardChoice3.setBackgroundColor(Color.GREEN)
                flashcardChoice1.setBackgroundColor(Color.RED)
                flashcardChoice2.setBackgroundColor(Color.RED)
            }
            // Active
            rp3 = !rp3
        }

        // set initial icon
        isShowingAnswers.setOnClickListener {
            isOn = !isOn

            // Animation rotation
            val rotate = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            rotate.duration = 300
            isShowingAnswers.startAnimation(rotate)
            AlphaAnimation(0f, 1f)

            // Changer l'icone
            if (isOn) {
                isShowingAnswers.setImageResource(R.drawable.eye_lined)
                flashcardChoice1.visibility = View.INVISIBLE
                flashcardChoice2.visibility = View.INVISIBLE
                flashcardChoice3.visibility = View.INVISIBLE
            } else {
                isShowingAnswers.setImageResource(R.drawable.eye_lined_off)
                flashcardChoice1.visibility = View.VISIBLE
                flashcardChoice2.visibility = View.VISIBLE
                flashcardChoice3.visibility = View.VISIBLE

                //Set value
                val button_add = findViewById<ImageView>(R.id.plus_button)
                button_add.setOnClickListener {
                    val intent = Intent(this, AddQuestion::class.java)
                    startActivity(intent)
                }


                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(
                        systemBars.left,
                        systemBars.top,
                        systemBars.right,
                        systemBars.bottom
                    )
                    insets
                }
            }
        }
    }

}