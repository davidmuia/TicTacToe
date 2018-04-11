package com.example.user.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
    }
    public void twoPlayers(View view){
        Intent intent=new Intent(getBaseContext(),twoPlayersBoardType.class);
        startActivity(intent);
    }
    public void singlePlayer(View view){
        Intent intent=new Intent(getBaseContext(),singlePlayerBoardType.class);
        startActivity(intent);
    }

}
