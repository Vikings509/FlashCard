package com.example.flashcard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import androidx.core.graphics.drawable.DrawableCompat

class MainActivity : AppCompatActivity() {

    private var isOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // --- Toolbar ---
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "FlashCard App"

        // --- Question / Réponse ---
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question1)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer1)

        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
            applyFadeAnimation(flashcardAnswer)
        }

        flashcardAnswer.setOnClickListener {
            flashcardAnswer.visibility = View.INVISIBLE
            flashcardQuestion.visibility = View.VISIBLE
            applyFadeAnimation(flashcardQuestion)
        }

        // --- Choix ---
        val isShowingAnswers = findViewById<ImageView>(R.id.toggle_choices_visibility)
        val flashcardChoice1 = findViewById<TextView>(R.id.flashcard_q1_choice1)
        val flashcardChoice2 = findViewById<TextView>(R.id.flashcard_q1_choice2)
        val flashcardChoice3 = findViewById<TextView>(R.id.flashcard_q1_choice3)

        flashcardChoice1.setOnClickListener {
            resetChoices(flashcardChoice1, flashcardChoice2, flashcardChoice3)
            setChoiceBackground(flashcardChoice1, R.color.red)
        }

        flashcardChoice2.setOnClickListener {
            resetChoices(flashcardChoice1, flashcardChoice2, flashcardChoice3)
            setChoiceBackground(flashcardChoice2, R.color.red)
        }

        flashcardChoice3.setOnClickListener {
            resetChoices(flashcardChoice1, flashcardChoice2, flashcardChoice3)
            setChoiceBackground(flashcardChoice3, R.color.green)
        }

        // --- Toggle affichage choix ---
        isShowingAnswers.setOnClickListener {
            isOn = !isOn
            val rotate = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            rotate.duration = 300
            isShowingAnswers.startAnimation(rotate)

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
            }
        }

        // --- Ajouter une question ---
        val buttonAdd = findViewById<ImageView>(R.id.plus_button)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            if (data != null) {
                val string1 = data.getStringExtra("editTextField_question") ?: "Question?"
                val string2 = data.getStringExtra("editTextField_answer") ?: "Réponse"

                Log.i("MainActivity", "editTextField_question: $string1")
                Log.i("MainActivity", "editTextField_answer: $string2")

                flashcardQuestion.text = string1
                flashcardAnswer.text = string2
            } else {
                Log.i("MainActivity", "Returned null data from AddQuestion")
            }
        }

        buttonAdd.setOnClickListener {
            val intent = Intent(this, AddQuestion::class.java)
            resultLauncher.launch(intent)
        }

        // --- Insets ---
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Animation fondu
    private fun applyFadeAnimation(view: View) {
        val fade = AlphaAnimation(0f, 1f)
        fade.duration = 300
        view.startAnimation(fade)
    }

    // Reset choix (fond par défaut bg_choice.xml)
    private fun resetChoices(c1: TextView, c2: TextView, c3: TextView) {
        c1.background = ContextCompat.getDrawable(this, R.drawable.bg_choice)
        c2.background = ContextCompat.getDrawable(this, R.drawable.bg_choice)
        c3.background = ContextCompat.getDrawable(this, R.drawable.bg_choice)
    }

    // Appliquer une couleur de fond en gardant arrondi + bordure
    private fun setChoiceBackground(choice: TextView, colorRes: Int) {
        val drawable = ContextCompat.getDrawable(this, R.drawable.bg_choice)?.mutate()
        drawable?.let {
            DrawableCompat.setTint(it, ContextCompat.getColor(this, colorRes))
            choice.background = it
        }
    }
}
