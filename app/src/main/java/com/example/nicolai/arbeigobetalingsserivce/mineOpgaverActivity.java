package com.example.nicolai.arbeigobetalingsserivce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mineOpgaverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_opgaver);
    }

    public void onClickIntent1(View view) {
        Intent intent = new Intent(mineOpgaverActivity.this, AktiveOpgaverActivity.class);
        startActivity(intent);
    }

    public void onClickIntent2(View view) {
        Intent intent = new Intent(mineOpgaverActivity.this, GamleOpgaverActivity.class);
        startActivity(intent);
    }
}
