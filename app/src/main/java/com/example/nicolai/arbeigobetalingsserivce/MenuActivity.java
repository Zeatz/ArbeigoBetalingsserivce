package com.example.nicolai.arbeigobetalingsserivce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class  MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_pay);
    }

    public void onClickMineOpgaver(View view){
        Intent intent = new Intent(MenuActivity.this, mineOpgaverActivity.class);
        startActivity(intent);
    }
}
