package com.counterplus.ekapop.restaurant1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class AreaViewActivity extends AppCompatActivity {

    private RestaurantControl rs;

    Button btnAvAdd;
    ListView lvAvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_view);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        btnAvAdd = findViewById(R.id.btnAvAdd);
        lvAvView = findViewById(R.id.lvAvView);

        btnAvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), AreaAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
    }
}
