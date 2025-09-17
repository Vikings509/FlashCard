package com.example.flashcard

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_question)
        val button_close = findViewById<ImageView>(R.id.button_close)
        // Set title to FlashCard App
        //supportActionBar?.title = "Add Question"
        button_close.setOnClickListener {
            finish()
        }
      /*  val button_add = findViewById<ImageView>(R.id.button_close)
        button_add.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val data = Intent() // create a new Intent, this is where we will put our data

        data.putExtra(
            "string1",
            "some string"
        ) // puts one string into the Intent, with the key as 'string1'

        data.putExtra(
            "string2",
            "another string"
        ) // puts another string into the Intent, with the key as 'string2

        setResult(RESULT_OK, data) // set result code and bundle data for response

        finish() // closes this activity and pass data to the original activity that launched this activity*/

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}