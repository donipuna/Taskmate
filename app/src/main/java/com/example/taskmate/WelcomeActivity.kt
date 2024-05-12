package com.example.taskmate

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

        // Get the ConstraintLayout
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constarintWelcome)

        // Get screen height
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels

        fun adjustScreen(marginTopLogo:Int, marginTopH1:Int, marginTopH12:Int, marginTopBtnStart:Int,marginTopH2:Int) {
            // Set margins for views
            val logo = findViewById<ImageView>(R.id.logo)
            val h1 = findViewById<TextView>(R.id.h1)
            val h12 = findViewById<TextView>(R.id.h1_2)
            val btnnStart = findViewById<Button>(R.id.btn_start)
            val h2 = findViewById<TextView>(R.id.h2)

            val logoParams = logo.layoutParams as ConstraintLayout.LayoutParams
            logoParams.topMargin = marginTopLogo
            logo.layoutParams = logoParams

            val h1Params = h1.layoutParams as ConstraintLayout.LayoutParams
            h1Params.topMargin = marginTopH1
            h1.layoutParams = h1Params

            val h12Params = h12.layoutParams as ConstraintLayout.LayoutParams
            h12Params.topMargin = marginTopH12
            h12.layoutParams = h12Params

            val btnStartParams = btnnStart.layoutParams as ConstraintLayout.LayoutParams
            btnStartParams.topMargin = marginTopBtnStart
            btnnStart.layoutParams = btnStartParams

            val h2Params = h2.layoutParams as ConstraintLayout.LayoutParams
            h2Params.topMargin = marginTopH2
            h2.layoutParams = h2Params
            val btnStart: Button = findViewById(R.id.btn_start)


            btnStart.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

        if(screenHeight>1100) {
            val marginTopLogo = (screenHeight * 0.2).toInt()
            val marginTopH1 = (screenHeight * 0.08).toInt()
            val marginTopH12 = (screenHeight * 0.03).toInt()
            val marginTopBtnStart = (screenHeight * 0.08).toInt()
            val marginTopH2 = (screenHeight * 0.1).toInt()

            adjustScreen(marginTopLogo, marginTopH1, marginTopH12, marginTopBtnStart,marginTopH2)
        }
        else {
            // Calculate margins dynamically (adjust these percentages as needed)
            val marginTopLogo = (screenHeight * 0.08).toInt()
            val marginTopH1 = (screenHeight * 0.05).toInt()
            val marginTopH12 = (screenHeight * 0.03).toInt()
            val marginTopBtnStart = (screenHeight * 0.05).toInt()
            val marginTopH2 = (screenHeight * 0.05).toInt()

            adjustScreen(marginTopLogo, marginTopH1, marginTopH12, marginTopBtnStart,marginTopH2)
        }

    }
}