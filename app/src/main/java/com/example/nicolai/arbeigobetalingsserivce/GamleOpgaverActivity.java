package com.example.nicolai.arbeigobetalingsserivce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GamleOpgaverActivity extends AppCompatActivity {

    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamle_opgaver);

        productList = new ArrayList<>();

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recylcerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        productList.add(
                new Product(
                        1,
                        "Rense tagrender",
                        "Færdig d. 30-02-2018",
                        1,
                        445,
                        R.drawable.tagrende));

        productList.add(
                new Product(
                        2,
                        "Rense tagrender",
                        "Færdig d. 30-02-2018",
                        3,
                        468,
                        R.drawable.tagrende));

        productList.add(
                new Product(
                        3,
                        "Rense tagrender",
                        "Færdig d. 30-02-2018",
                        2,
                        487,
                        R.drawable.tagrende));

        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    public void onClickIntent4(View view) {
        Intent intent = new Intent(GamleOpgaverActivity.this, opgaveInfoActivity.class);
        startActivity(intent);
    }
}
