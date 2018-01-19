package com.counterplus.ekapop.restaurant1;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TabHost;

public class OrderAddActivity extends AppCompatActivity {

    LocalActivityManager mLocalActivityManager;
    public RestaurantControl rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");

        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup(mLocalActivityManager);
        Intent s1 = new Intent(this, EsernOrderAdd.class);
        s1.putExtra("RestaurantControl",rs);

//        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab11")
//                .setIndicator(getResources().getString(R.string.esern))
//                .setContent(s1);

        Intent s2 = new Intent(this, MailarapOrderAdd.class);
        s2.putExtra("RestaurantControl",rs);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab22")
                .setIndicator(rs.ResName)
                .setContent(s2);

        Intent s3 = new Intent(this, WaterBarOrderAdd.class);
        s3.putExtra("RestaurantControl",rs);
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tab33")
                .setIndicator(getResources().getString(R.string.waterbar))
                .setContent(s3);

//        tabHost.addTab(tabSpec);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
