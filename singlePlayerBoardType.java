package com.example.user.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class singlePlayerBoardType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players_board_type);
    }
    public void threeByThree(View view){
        Intent intent=new Intent(getBaseContext(),single_player.class);
        startActivity(intent);

    }
    public void fiveByFive(View view){
        Intent intent=new Intent(getBaseContext(),singleFiveByFive.class);
        startActivity(intent);

    }

}