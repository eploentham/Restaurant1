package com.counterplus.ekapop.restaurant1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;

public class ResAddActivity extends AppCompatActivity {

    public RestaurantControl rs;
    Restaurant res = new Restaurant();
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrR;
    String ab;
    int textSize=20,textSize1=18, row;
    DatabaseSQLi daS;

    TextView lbRaCode, lbRaName, lbRaRemark, lbRaDefaultRes, lbRaRH1, lbRaRH2, lbRaRF1, lbRaRF2, lbRaTaxID, lbRaBillCode, lbRaSort1, lbRaActive;
    EditText txtRaCode, txtRaName, txtRaRemark, txtRaRH1, txtRaRH2, txtRaRF1, txtRaRF2, txtRaTaxID, txtRaBillCode, txtRaSort1;
    CheckBox chkRaDefaultRes;
    Switch chkRaActive;
    Button btnRaVoid, btnRaSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_add);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        res = new Restaurant();

        lbRaCode = findViewById(R.id.lbRaCode);
        lbRaName = findViewById(R.id.lbRaName);
        lbRaRemark = findViewById(R.id.lbRaRemark);
        lbRaDefaultRes = findViewById(R.id.lbRaDefaultRes);
        lbRaRH1 = findViewById(R.id.lbRaRH1);
        lbRaRH2 = findViewById(R.id.lbRaRH2);
        lbRaRF1 = findViewById(R.id.lbRaRF1);
        lbRaRF2 = findViewById(R.id.lbRaRF2);
        lbRaTaxID = findViewById(R.id.lbRaTaxID);
        lbRaBillCode = findViewById(R.id.lbRaBillCode);
        lbRaSort1 = findViewById(R.id.lbRaSort1);
        lbRaActive = findViewById(R.id.lbRaActive);

        txtRaCode = findViewById(R.id.txtRaCode);
        txtRaName = findViewById(R.id.txtRaName);
        txtRaRemark = findViewById(R.id.txtRaRemark);
        txtRaRH1 = findViewById(R.id.txtRaRH1);
        txtRaRH2 = findViewById(R.id.txtRaRH2);
        txtRaRF1 = findViewById(R.id.txtRaRF1);
        txtRaRF2 = findViewById(R.id.txtRaRF2);
        txtRaTaxID = findViewById(R.id.txtRaTaxID);
        txtRaBillCode = findViewById(R.id.txtRaBillCode);
        txtRaSort1 = findViewById(R.id.txtRaSort1);

        chkRaDefaultRes = findViewById(R.id.chkRaDefaultRes);
        chkRaActive = findViewById(R.id.chkRaActive);
        btnRaVoid = findViewById(R.id.btnRaVoid);
        btnRaSave = findViewById(R.id.btnRaSave);

        lbRaCode.setText(R.string.code);
        lbRaName.setText(R.string.name);
        lbRaRemark.setText(R.string.remark);
        lbRaActive.setText(R.string.active);
        lbRaSort1.setText(R.string.sort);
        lbRaDefaultRes.setText(R.string.RaDefaultRes);
        lbRaRH1.setText(R.string.RaRH1);
        lbRaRH2.setText(R.string.RaRH2);
        lbRaRF2.setText(R.string.RaRF1);
        lbRaRF1.setText(R.string.RaRF2);
        lbRaTaxID.setText(R.string.RaTaxID);
        lbRaBillCode.setText(R.string.RaBillCode);

        chkRaDefaultRes.setText("เป็นร้านคิดเงิน");
        btnRaVoid.setText(R.string.void1);
        btnRaVoid.setVisibility(View.INVISIBLE);
        txtRaPasswordVoid.setVisibility(View.INVISIBLE);
        txtRaBillCode.setFocusable(false);

        btnRaSave.setText(R.string.save);
        txtRaCode.setEnabled(false);
        chkRaActive.setText(R.string.activeon);
        chkRaActive.setChecked(true);
        txtRaCode.setEnabled(false);
    }
}
