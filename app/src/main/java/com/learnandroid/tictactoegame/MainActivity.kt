package com.learnandroid.tictactoegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        letsplayBtn.setOnClickListener(){
            var i= Intent(this,StartGame::class.java)
            startActivity(i)
        }
    }


}