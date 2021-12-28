package com.learnandroid.tictactoegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_start_game.*

class StartGame : AppCompatActivity(), View.OnClickListener {

    var PLAYER=true;
    var TURN_COUNT=0;

    lateinit var board:Array<Array<Button>>

    var boardStatus =Array(3){IntArray(3)};

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        board= arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board){
            for(btn in i){
                btn.setOnClickListener(this)
            }
        }

        initializeButtonStatus()

        ResetBtn.setOnClickListener{
           TURN_COUNT=0;
            initializeButtonStatus()
            PLAYER=true;
            if(PLAYER){
                updateDisplay("PLAYER X TURN");
            }
            else{
                updateDisplay("PLAYER O TURN");
            }
        }
    }

    private fun initializeButtonStatus() {

        for (i in 0..2){
            for (j in 0..2){
                boardStatus[i][j] =-1
                board[i][j].isEnabled=true;
                board[i][j].text=""
            }
        }
    }


    override fun onClick(v: View){

        when(v.id){
            R.id.button1 ->{
               updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2 ->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3 ->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4 ->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5 ->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6 ->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7 ->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8 ->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9 ->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT++;
        PLAYER=!PLAYER;
        if(PLAYER){
           updateDisplay("PLAYER X TURN");
        }
        else{
            updateDisplay("PLAYER O TURN");
        }

        checkWinner()
        if(TURN_COUNT==9){
            updateDisplay("Game Draw")
        }


    }

    private fun checkWinner() {
        //Horizontal rows
           for(i in 0..2){
               for(j in 0..2){
                   if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]) {
                       if (boardStatus[i][0] == 1) {
                           updateDisplay("Congrats!! PLAYER X WON")
                           break;
                       } else if(boardStatus[i][0] == 0){
                           updateDisplay("Congrats!! PLAYER O WON")
                           break;
                       }
                   }
               }
           }

        //Vertical cols
        for(i in 0..2){
            for(j in 0..2){
                if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]) {
                    if (boardStatus[0][i] == 1) {
                        updateDisplay("Congrats!! PLAYER X WON")
                        break;
                    } else if(boardStatus[0][i] == 0){
                        updateDisplay("Congrats!! PLAYER O WON")
                        break;
                    }
                }

            }
        }


        //Diagonals
        //D1
        for(i in 0..2){
            for(j in 0..2) {
                if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
                    if (boardStatus[0][0] == 1) {
                        updateDisplay("Congrats!! PLAYER X WON")
                        break;
                    } else if(boardStatus[0][0] == 0){
                        updateDisplay("Congrats!! PLAYER O WON")
                        break;
                    }
                }

            }
        }

        //D2
        for(i in 0..2){
            for(j in 0..2){
                if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0]) {
                    if (boardStatus[1][1] == 1) {
                        updateDisplay("Congrats!! PLAYER X WON")
                        break;
                    } else if(boardStatus[1][1] == 0){
                        updateDisplay("Congrats!! PLAYER O WON")
                        break;
                    }
                }
            }
        }


    }

    private fun updateDisplay(t: String) {
        displayTV.text = t
        if(t.contains("WON")){
            disablebutton()
        }
    }

    private fun disablebutton() {
        for(i in board){
            for(btn in i){
                btn.isEnabled= false;
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
         var text = if(player) "X" else "O"
         var Value= if(player) 1 else 0

        board[row][col].apply {
            isEnabled=false
            setText(text)
        }

        boardStatus[row][col]=Value
    }

}