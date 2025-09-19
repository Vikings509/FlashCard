package com.example.flashcard

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_question)

        val buttonClose = findViewById<ImageView>(R.id.button_close)
        val buttonSave = findViewById<ImageView>(R.id.button_save)
        val editTextQuestion = findViewById<EditText>(R.id.editText_question)
        val editTextAnswer = findViewById<EditText>(R.id.editText_answer)

        // Fermer sans sauvegarder
        buttonClose.setOnClickListener {
            finish()
        }

        // Sauvegarder et renvoyer les données
        buttonSave.setOnClickListener {
            val question = editTextQuestion.text.toString().trim()
            val answer = editTextAnswer.text.toString().trim()

            val data = Intent().apply {
                putExtra("editTextField_question", question)
                putExtra("editTextField_answer", answer)
            }

            setResult(RESULT_OK, data)
            finish()
        }

        // Insets automatiques
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}