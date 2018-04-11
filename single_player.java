package com.example.user.tictactoe;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class single_player extends AppCompatActivity implements View.OnClickListener {

    private TextView txtPlayer1;
    private TextView txtPlayer2;
    private int player1Score;
    private int player2Score;
    private boolean player1Turn = true;
    private int count;

    private Button[][] buttons = new Button[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPlayer1 = findViewById(R.id.player1);
        txtPlayer2 = findViewById(R.id.player2);
        Button btnReset = findViewById(R.id.btn_reset);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);

                buttons[i][j].setOnClickListener(this);

            }
        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) view).setText("X");
            compMove();
            player1Turn=!player1Turn;

        }

        count++;


        if (checkForWin()) {

            if (player1Turn) {
                player1Wins();
            } else player2Wins();
        } else if (count == 9) {
            draw();
        } else player1Turn = !player1Turn;

    }

    private void compMove(){
        Random rand= new Random();

        int i=rand.nextInt(2);
        int j=rand.nextInt(2);
        if (buttons[i][j].getText().toString().equals(""))
            buttons[i][j].setText("0");
        else compMove();

    }



    private boolean checkForWin() {
        String field[][] = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }


        for (int i = 0; i < 3; i++) {
            //comparing the values of the buttons in a row if they are not blank
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            //comparing the values of the buttons in a column if they are not blank
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }
        //comparing the values of the buttons diagonally
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1Wins() {
        player1Score++;
        Toast.makeText(this, "Player 1 won", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void player2Wins() {
        player2Score++;
        Toast.makeText(this, "Player 2 won", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();


    }

    private void draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints() {
        txtPlayer1.setText("Player 1 : " + player1Score);
        txtPlayer2.setText("Player 2 : " + player2Score);

    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        count = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Score = 0;
        player2Score = 0;
        updatePoints();
        resetBoard();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
        outState.putInt("player1Score", player1Score);
        outState.putInt("player2Score", player2Score);
        outState.putBoolean("player1Turn", player1Turn);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
        player1Score = savedInstanceState.getInt("player1Score");
        player2Score = savedInstanceState.getInt("player2score");
        player1Turn = savedInstanceState.getBoolean("player1turn");
    }
}







