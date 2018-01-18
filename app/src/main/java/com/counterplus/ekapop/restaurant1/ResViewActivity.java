package com.counterplus.ekapop.restaurant1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ResViewActivity extends AppCompatActivity {

    private RestaurantControl rs;

    Button btnRvAdd;
    ListView lvRvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_view);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        btnRvAdd = findViewById(R.id.btnRvAdd);
        lvRvView = findViewById(R.id.lvRvView);

        btnRvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), ResAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });

    }
}
