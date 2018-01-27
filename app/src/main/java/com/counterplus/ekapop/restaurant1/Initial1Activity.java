package com.counterplus.ekapop.restaurant1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Initial1Activity extends AppCompatActivity {
    Button btnIa1FoodsTypeView,btnIa1FoodsView, btnIa1BillVoid, btnIa1TableChange, btnIa1TableMerge,btnIa1User;
    Button btnIa1TableView,btnIa1AreaView, btnIa1ResView, btnIa1FoodsCatView, btnIa1FoodsSpecView, btnIa1PrinterView;

    public RestaurantControl rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial1);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");

        btnIa1FoodsTypeView = findViewById(R.id.btnIa1FoodsTypeView);
        btnIa1FoodsView = findViewById(R.id.btnIa1FoodsView);
        btnIa1BillVoid = findViewById(R.id.btnIa1BillVoid);
        btnIa1TableChange = findViewById(R.id.btnIa1TableChange);
        btnIa1TableMerge = findViewById(R.id.btnIa1TableMerge);
        btnIa1User = findViewById(R.id.btnIa1User);
        btnIa1TableView = findViewById(R.id.btnIa1TableView);
        btnIa1AreaView = findViewById(R.id.btnIa1AreaView);
        btnIa1ResView = findViewById(R.id.btnIa1ResView);
        btnIa1FoodsCatView = findViewById(R.id.btnIa1FoodsCatView);
        btnIa1FoodsSpecView = findViewById(R.id.btnIa1FoodsSpecView);
        btnIa1PrinterView = findViewById(R.id.btnIa1PrinterView);

        btnIa1FoodsTypeView.setText(getResources().getString(R.string.add)+getResources().getString(R.string.type)+getResources().getString(R.string.foods));
        btnIa1FoodsView.setText(R.string.foodsadd);
        btnIa1BillVoid.setText(R.string.btnMBillVoid);
        btnIa1TableChange.setText(R.string.btnMTChange);
        btnIa1ResView.setText(R.string.resadd);
        btnIa1TableMerge.setText(R.string.btnMTMerge);
        btnIa1User.setText(R.string.user);
        btnIa1TableView.setText(R.string.btnIa1TableView);
        btnIa1AreaView.setText(R.string.btnIa1AreaView);
        btnIa1FoodsCatView.setText(R.string.btnIa1FoodsCatView);
        btnIa1FoodsSpecView.setText(R.string.btnIa1FoodsSpecView);
        btnIa1PrinterView.setText(R.string.btnIa1PrinterView);

        btnIa1ResView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), ResViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1AreaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), AreaViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1TableView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), TableViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1FoodsTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), FoodsTypeViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1FoodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), FoodsViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1BillVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), BillVoidActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), UserViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1TableChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), TableChangeActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIa1FoodsCatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), FoodsCatViewActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
    }
}
