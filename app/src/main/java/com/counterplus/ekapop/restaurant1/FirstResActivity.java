package com.counterplus.ekapop.restaurant1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class FirstResActivity extends AppCompatActivity {

    Button btnFrSaveText, btnFrBackupDatabase, btnFrCreateDatabase;
    TextView lbFrResName, lbFrHosId, lbFrLang, lbFrUserDB,lbFrHosIP, lbFrPasswordDB;
    Spinner cboFrLang;
    RadioButton chkFrStandalone, chkFrLan, chkFrInternet;
    EditText txtFrResName, txtFrHostID, txtFrUserDB,txtFrHosIP, txtFrPasswordDB;

    private RestaurantControl rs;
    JSONArray jarr;
    JsonParser jsonparser = new JsonParser();
    JSONArray jarrF;
    String ab;
    int textSize=20,textSize1=18, row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_res);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");

        btnFrBackupDatabase = findViewById(R.id.btnFrBackupDatabase);
        btnFrSaveText = findViewById(R.id.btnFrSaveText);
        btnFrCreateDatabase = findViewById(R.id.btnFrCreateDatabase);

        lbFrResName = findViewById(R.id.lbFrResName);
        lbFrHosId = findViewById(R.id.lbFrHosId);
        lbFrLang = findViewById(R.id.lbFrLang);
        lbFrUserDB = findViewById(R.id.lbFrUserName);
        lbFrHosIP = findViewById(R.id.lbFrHosIP);
        lbFrPasswordDB = findViewById(R.id.lbFrPassword);

        txtFrResName = findViewById(R.id.txtFrResName);
        txtFrHostID = findViewById(R.id.txtFrHosId);
        txtFrUserDB = findViewById(R.id.txtFruserName);
        txtFrPasswordDB = findViewById(R.id.txtFrPassword);
        txtFrHosIP = findViewById(R.id.txtFrHosIP);

        cboFrLang = findViewById(R.id.cboFrLang);

        chkFrStandalone = findViewById(R.id.chkFrFirstRes);
        chkFrLan = findViewById(R.id.chkFrLan);
        chkFrInternet = findViewById(R.id.chkFrInternet);

        lbFrHosIP.setText(R.string.hostIP);
        lbFrResName.setText(getResources().getString(R.string.name)+getResources().getString(R.string.restaurant));
        lbFrLang.setText(R.string.lbFrLanguage);
        lbFrUserDB.setText(R.string.user);
        lbFrHosId.setText(R.string.lbFrHostID);
        lbFrPasswordDB.setText(R.string.password);
        chkFrStandalone.setText(R.string.chkFrStandalone);
        chkFrLan.setText(R.string.chkFrHaveServer);
        chkFrInternet.setText(R.string.chkFrInternet);

        btnFrBackupDatabase.setText(R.string.btnFrBackUp);
        btnFrSaveText.setText(R.string.btnFrSaveText);
        btnFrCreateDatabase.setText(R.string.btnFrCreate);

        cboFrLang.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboLanguage));

        chkFrLan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkFrServer();
            }
        });
        chkFrInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkFrInternet();
            }
        });
        chkFrStandalone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChkFrStandalone();
            }
        });
        btnFrCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                saveText();
                String hostID = UUID.randomUUID().toString();
                txtFrHostID.setText(hostID);
                if(chkFrStandalone.isChecked()){
                    SQLiteDatabase mDb;
                    DatabaseSQLi das = new DatabaseSQLi(FirstResActivity.this, hostID);
                    mDb = das.getWritableDatabase();
                    das.onUpgrade(mDb,1,1);

//                    setTable();
                }else if (chkFrInternet.isChecked()){

                }else{

                }
                saveText();
                btnFrCreateDatabase.setEnabled(false);
                System.exit(0);
            }
        });
        btnFrBackupDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText();
            }
        });
        getText();
    }
    private void setChkFrStandalone(){
        SetControlHide();
    }
    private void setChkFrServer(){
        SetControlShow();
    }
    private void setChkFrInternet(){
        SetControlHide();
    }
    private void SetControlShow() {
        txtFrHosIP.setVisibility(View.VISIBLE);
//        txtFrWebDirectory.setVisibility(View.VISIBLE);
        txtFrPasswordDB.setVisibility(View.VISIBLE);
        txtFrUserDB.setVisibility(View.VISIBLE);
        lbFrHosIP.setVisibility(View.VISIBLE);
        lbFrUserDB.setVisibility(View.VISIBLE);
        lbFrPasswordDB.setVisibility(View.VISIBLE);
//        lbFrWebDirectory.setVisibility(View.VISIBLE);
    }
    private void SetControlHide() {
        txtFrHosIP.setVisibility(View.INVISIBLE);
//        txtFrWebDirectory.setVisibility(View.INVISIBLE);
        txtFrPasswordDB.setVisibility(View.INVISIBLE);
        txtFrUserDB.setVisibility(View.INVISIBLE);
        lbFrHosIP.setVisibility(View.INVISIBLE);
        lbFrUserDB.setVisibility(View.INVISIBLE);
        lbFrPasswordDB.setVisibility(View.INVISIBLE);
//        lbFrWebDirectory.setVisibility(View.INVISIBLE);
    }
    private void saveText(){
        FileOutputStream outputStream;
        String userDB="",passwordDB="",webDirectory="",printCloseDay="", port="", posID="",taxID="", printer="",AccessMethod="", host="";
        String string="",language="";
        if(chkFrStandalone.isChecked()) {
            userDB = "";
            passwordDB = "";
            webDirectory = "";
            AccessMethod="Standalone";
            host = txtFrHostID.getText().toString();
        }else if (chkFrInternet.isChecked()){
            userDB = "";
            passwordDB = "";
            webDirectory = "";
            AccessMethod="Internet";
            host = "ec2-35-166-120-55.us-west-2.compute.amazonaws.com";
            userDB = "root";
            passwordDB = "Ekartc2c5";
            webDirectory = "restaurant/";
        }else{
            userDB=txtFrUserDB.getText().toString().trim();
            passwordDB=txtFrPasswordDB.getText().toString().trim();
//            webDirectory=txtFrWebDirectory.getText().toString().trim();
            webDirectory="counterplus_restaurant";
            AccessMethod="Server";
            host=txtFrHosIP.getText().toString().trim();
        }
        string = "host="+host+";\n"
                +"printer="+printer+";\n"
                +"PosID="+posID+";\n"
                +"TaxID="+taxID+";\n"
                +"PortNumber="+port+";\n"
                +"WebDirectory="+webDirectory+";\n"
                +"UserDB="+userDB+";\n"
                +"PasswordDB="+passwordDB+";\n"
                +"TextSize=16;\n"
                +"PrintOrder=Off;\n"
                +"PrintBill=Off;\n"
                +"PrintCloseDay=Off;\n"
                +"HostID="+txtFrHostID.getText().toString().trim()+";\n"
                +"AccessMethod="+AccessMethod+";\n"
                +"Language="+cboFrLang.getSelectedItem().toString()+";\n";
        try {
            Log.d("saveText() string ",string);
            File file =getFileStreamPath("initial.cnf");
            outputStream = openFileOutput("initial.cnf", Context.MODE_PRIVATE);
//            outputStream = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
//            rs.pageLoad=true;
//            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("saveText() string ",e.getMessage());
        }
    }
    private void getText(){
        try {
            File file =getFileStreamPath("initial.cnf");
            final int READ_BLOCK_SIZE = 100;
            FileInputStream fileIn=openFileInput("initial.cnf");
//            FileInputStream fileIn=openFileInput(file.getPath());
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            String[] p = s.split(";");
            if(p.length>0){
                Log.d("getText() length ",String.valueOf(p.length));

//                btnFrCreate.setEnabled(false);

                String hostID = p[12].length()>0 ? p[12].replace("HostID=","").replace("\n",""):"";
                String AccessMethod = p[13].length()>0 ? p[13].replace("AccessMethod=","").replace("\n",""):"";
                String language = p[14].length()>0 ? p[14].replace("Language=","").replace("\n",""):"";
                txtFrHostID.setText(hostID);
                Log.d("getText() language ",language);
                if(language.equals("Thai")){
                    cboFrLang.setSelection(0);
                }else if(language.equals("English")){
                    cboFrLang.setSelection(1);
                }else{
                    cboFrLang.setSelection(0);
                }

                if(AccessMethod.equals("Standalone")){
                    chkFrStandalone.setChecked(true);
                    setChkFrStandalone();
                }else if(AccessMethod.equals("Internet")){
                    chkFrInternet.setChecked(true);
                    setChkFrInternet();
                }else if(AccessMethod.equals("Server")){
                    chkFrLan.setChecked(true);
                    setChkFrServer();
                }
            }
            fileIn.close();
            rs.refresh();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("getText() ",e.getMessage());
        }
    }
}
