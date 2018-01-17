package com.counterplus.ekapop.restaurant1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Initial1Activity extends AppCompatActivity {
    Button btnIa1FoodsTypeView,btnIa1FoodsView, btnIa1BillVoid, btnIa1TableChange, btnIa1TableMerge,btnIa1User;
    Button btnIa1TableView,btnIa1AreaView, btnIa1ResView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial1);

        btnIa1FoodsTypeView = findViewById(R.id.btnIa1FoodsTypeView);
        btnIa1FoodsView = findViewById(R.id.btnIa1FoodsView);
        btnIa1BillVoid = findViewById(R.id.btnIa1BillVoid);
        btnIa1TableChange = findViewById(R.id.btnIa1TableChange);
        btnIa1TableMerge = findViewById(R.id.btnIa1TableMerge);
        btnIa1User = findViewById(R.id.btnIa1User);
        btnIa1TableView = findViewById(R.id.btnIa1TableView);
        btnIa1AreaView = findViewById(R.id.btnIa1AreaView);
        btnIa1ResView = findViewById(R.id.btnIa1ResView);

        btnIa1FoodsTypeView.setText(getResources().getString(R.string.add)+getResources().getString(R.string.type)+getResources().getString(R.string.foods));
        btnIa1FoodsView.setText(R.string.foodsadd);
        btnIa1BillVoid.setText(R.string.btnMBillVoid);
        btnIa1TableChange.setText(R.string.btnMTChange);
        btnIa1ResView.setText(R.string.btnFristRes);
        btnIa1TableMerge.setText(R.string.btnMTMerge);
        btnIa1User.setText(R.string.user);
        btnIa1TableView.setText(R.string.btnIa1TableView);
        btnIa1AreaView.setText(R.string.btnIa1AreaView);


    }
}
