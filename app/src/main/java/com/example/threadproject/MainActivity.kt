package com.example.threadproject

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*


lateinit var progressBar: ProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textView: TextView = findViewById(R.id.textview)
        progressBar = findViewById<ProgressBar>(R.id.progresbarr)

        GlobalScope.launch(Dispatchers.Main) {
            Log.d("hello", "onCreate ${Thread.currentThread()}")
            var input = 0
            progressBar.visibility =View.GONE
            withContext(Dispatchers.Default) {
                delay(1000)
                for (i in input..100){
                    delay(500)
                    Log.d("hello", "interation ,$input")
                    input += 1

                    withContext(Dispatchers.Main) {
                        updateProgress(input)
                        textView.text = i.toString()
                    if (i == 100){
                        textView.text = "Finish"
                        delay(2000)
                        progressBar.visibility = View.INVISIBLE
                    }
                    }

                }
            }
        }

    }

    private fun updateProgress(progress: Int) {

        progressBar.progress = progress
        progressBar.visibility = View.VISIBLE
    }
}