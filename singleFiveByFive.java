package com.example.user.tictactoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class singleFiveByFive extends AppCompatActivity implements View.OnClickListener {

    private TextView txtPlayer1;
    private TextView txtPlayer2;
    private int player1Score;
    private int player2Score;
    private boolean player1Turn=true;
    private int counter;

    private Button[][] buttons=new Button[5][5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_by_five);
        txtPlayer1 =  findViewById(R.id.player1);
        txtPlayer2 =  findViewById(R.id.player2);
        Button btnReset =  findViewById(R.id.btn_reset);



//looping through the buttons and setting onClick listeners to them
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                String buttonID = "btn_"+i+j;
                int resID=getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }
//setting onClick listener on the reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

    }

    @Override
    //implementation after a button click is made
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        if(player1Turn){
            ((Button)view).setText("X");
            compMove();
        }

        counter++;

        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }
            else player2Wins();
        }
        else if(counter==25){
            draw();
        }
        else player1Turn=!player1Turn;
    }

    private void compMove(){
        Random rand= new Random();

        int i=rand.nextInt(4);
        int j=rand.nextInt(4);
        if (buttons[i][j].getText().toString().equals(""))
            buttons[i][j].setText("0");
        else compMove();

    }

    //function checking for a win
    private boolean checkForWin(){
        String field[][]=new String [5][5];
        for( int i=0; i<5; i++) {
            for( int j=0; j<5 ;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        //comparing the values of the buttons in a row if they are not blank
        for(int i=0; i<5; i++){
            if ((field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])&& field[i][0].equals(field[i][3]) && !field[i][0].equals(""))
                    || (field[i][4].equals(field[i][3]) && field[i][4].equals(field[i][2])&&field[i][4].equals(field[i][1]) && !field[i][4].equals("") ))
            {
                return true;
            }
        }
        //comparing the values of the buttons in a column if they are not blank
        for(int i=0; i<5; i++){
            if ((field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])&& field[0][i].equals(field[3][i]) && !field[0][i].equals(""))
                    || (field[4][i].equals(field[3][i]) && field[4][i].equals(field[2][i])&&field[4][i].equals(field[1][i]) && !field[4][i].equals("") ))
            {
                return true;
            }
        }
        //comparing the values of the buttons diagonally
        if ((field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && field[0][0].equals(field[3][3]) && !field[0][0].equals(""))||
                field[4][4].equals(field[3][3]) && field[4][4].equals(field[2][2]) && field[4][4].equals(field[1][1]) && !field[4][4].equals("")||
                field[1][0].equals(field[2][1]) && field[1][0].equals(field[3][2]) && field[1][0].equals(field[4][3]) && !field[1][0].equals("")||
                field[0][1].equals(field[1][2]) && field[0][1].equals(field[2][3]) && field[0][1].equals(field[3][4]) && !field[0][1].equals(""))
        {
            return true;
        }
        if ((field[0][4].equals(field[1][3]) && field[0][4].equals(field[2][2])&& field[0][4].equals(field[3][1]) && !field[0][4].equals("")) ||
                field[4][0].equals(field[3][1]) && field[4][0].equals(field[2][2]) && field[4][0].equals(field[1][3])&& !field[4][0].equals("")||
                field[1][4].equals(field[2][3]) && field[1][4].equals(field[3][2]) && field[1][4].equals(field[4][1]) && !field[1][4].equals("")||
                field[0][3].equals(field[1][2]) && field[0][3].equals(field[2][1]) && field[0][3].equals(field[3][0]) && !field[0][3].equals(""))
        {
            return true;
        }
        return false;
    }
    //after player one wins
    private void player1Wins(){
        player1Score++;
        Toast.makeText(this, "Player 1 won", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    //after player two wins
    private void player2Wins(){
        player2Score++;
        Toast.makeText(this, "Player 2 won", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();


    }
    //incase of a draw
    private void draw(){
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    //updating the points after a win
    private void updatePoints(){
        txtPlayer1.setText("Player 1 : "+player1Score);
        txtPlayer2.setText("Player 2 : "+player2Score);

    }
    //resetting the board
    private  void resetBoard(){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                buttons[i][j].setText("");
            }
        }
        counter=0;
        player1Turn=true;
    }
    //resetting the entire game...after reset button is clicked
    private void resetGame(){
        player1Score=0;
        player2Score=0;
        updatePoints();
        resetBoard();
    }

    //Handling screen rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);
        outState.putInt("player1Score",player1Score);
        outState.putInt("player2Score",player2Score);
        outState.putBoolean("player1Turn",player1Turn);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter=savedInstanceState.getInt("counter");
        player1Score=savedInstanceState.getInt("player1Score");
        player2Score=savedInstanceState.getInt("player2score");
        player1Turn=savedInstanceState.getBoolean("player1turn");
    }

}




















