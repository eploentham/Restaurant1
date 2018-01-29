package com.counterplus.ekapop.restaurant1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;

public class FoodsPrintAddActivity extends AppCompatActivity {

    TextView lbFcaCode, lbFcaName, lbFcaRemark, lbFcaSort1, lbFcaActive;
    EditText txtFcaCode, txtFcaName, txtFcaRemark, txtFcaSort1, txtFcaPasswordVoid;
    Switch chkFcaActive;
    Button btnFcaSave, btnFcaVoid;

    FoodsCategory fc = new FoodsCategory();

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=14,textSize1=18, row;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_print_add);
    }
}
