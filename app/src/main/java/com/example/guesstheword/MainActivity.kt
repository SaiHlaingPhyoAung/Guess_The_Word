package com.example.guesstheword

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.Arrays
import java.util.Collections
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //Decalring Array of String

    internal var Days = arrayOf(
        "Sunday" , "Monday" , "Tuesday" , "Wednesday" , "Thursday" , "Friday" , "Saturday"
    )

    //Declaring all other varibale
    lateinit var day : String
    lateinit var random : Random

    lateinit var txtRightAnswer : TextView
    lateinit var txtQuestionContainer : TextView
    lateinit var txtCorrectAnswer : TextView
    lateinit var etUserInput : EditText
    lateinit var btnShow : Button
    lateinit var btnCheck : Button
    lateinit var btnNext : Button
    lateinit var cvCorrectAnswer : CardView
    lateinit var cvRightAnswer : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtRightAnswer = findViewById(R.id.txtRightAnswer)
        txtCorrectAnswer = findViewById(R.id.txtCorrectAnswer)
        txtQuestionContainer = findViewById(R.id.txtQuestionContainer)

        etUserInput = findViewById(R.id.etUserInput)

        btnShow = findViewById(R.id.btnShow)
        btnCheck = findViewById(R.id.btnCheck)
        btnNext = findViewById(R.id.btnNext)

        //initilize the random variable
        random = Random

        //Actual logic
        day = Days[random.nextInt(Days.size)]
        txtQuestionContainer.text = mixWord(day)

        btnCheck.setOnClickListener {
            if (etUserInput.text.toString().equals(day,ignoreCase = true)){

                val dialog = Dialog(this@MainActivity)
                dialog.setContentView(R.layout.correct_dialog)
                val btnContinue = dialog.findViewById<Button>(R.id.btnContinue)
                dialog.show()

                btnContinue.setOnClickListener {
                    dialog.dismiss()
                    day = Days[random.nextInt(Days.size)]
                    txtQuestionContainer.text = mixWord(day)

                    etUserInput.setText("")
                    txtRightAnswer.visibility = View.INVISIBLE
                    txtCorrectAnswer.visibility = View.INVISIBLE
                }

            }
            else{
                Toast.makeText(this@MainActivity , "Incorrect" , Toast.LENGTH_SHORT).show()
            }
        }

        btnNext.setOnClickListener {
            day = Days[random.nextInt(Days.size)]
            txtQuestionContainer.text = mixWord(day)

            etUserInput.setText("")
            txtRightAnswer.visibility = View.INVISIBLE
            txtCorrectAnswer.visibility = View.INVISIBLE
        }

        btnShow.setOnClickListener {
            d("btnShow" , "btnShow Click" )
            txtRightAnswer.visibility = View.VISIBLE
            txtCorrectAnswer.visibility = View.VISIBLE

            txtRightAnswer.text = day
        }
    }

    fun mixWord(words : String) : String{
        val word = Arrays.asList<String>(*words.split("".toRegex()).dropLastWhile ({ it.isEmpty() }).toTypedArray())

        Collections.shuffle(word)
        var mixed = ""

        for (i in word){
            mixed += i
        }
        return mixed
    }
}