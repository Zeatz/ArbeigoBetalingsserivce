package com.example.nicolai.arbeigobetalingsserivce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AktiveOpgaverActivity extends AppCompatActivity {

    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktive_opgaver);

        productList = new ArrayList<>();

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        productList.add(
                new Product(
                        1,
                        "Vindueskift",
                        "Førdig d. 30-02-2018",
                        4,
                        445,
                        R.drawable.vindueskift));

        productList.add(
                new Product(
                        2,
                        "Vindueskift",
                        "Førdig d. 30-02-2018",
                        5,
                        468,
                        R.drawable.vindueskift));

        productList.add(
                new Product(
                        3,
                        "Vindueskift",
                        "Førdig d. 30-02-2018",
                        3,
                        487,
                        R.drawable.vindueskift));

        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    public void onClickIntent3(View view) {
        Intent intent = new Intent(AktiveOpgaverActivity.this, opretOpgaveActivity.class);
        startActivity(intent);
    }

    public void onClickIntent4(View view) {
        Intent intent = new Intent(AktiveOpgaverActivity.this, opgaveInfoActivity.class);
        startActivity(intent);
    }
}
