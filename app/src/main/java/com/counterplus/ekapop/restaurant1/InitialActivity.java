package com.counterplus.ekapop.restaurant1;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.epson.epos2.printer.Printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InitialActivity extends AppCompatActivity {

    public RestaurantControl rs;
    TextView lbIaHost, lbIaPrint, lbIaPosID, lbIaTaxID, lbIaWebDirectory, lbIaPortID, lbIaUserDB, lbIaPasswordDB;
    EditText txtIaHost, txtIaPrint, txtIaPosID, txtIaTaxID, txtIaWebDirectory, txtIaPortID, txtIaUserDB, txtIaPasswordDB;
    Button btnIaSave, btnIaPrint, btnFristRes,btnIaInit1;

    private Printer mPrinter = null;
    private Context mContext = null;
    private ArrayList<String> sCboPrinter = new ArrayList<String>();
    private ArrayList<String> sCboTextSize = new ArrayList<String>();
    LocalActivityManager mLocalActivityManager;

    InputStream is;
    String ab="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mContext = this;
//        Intent intent = getIntent();
        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        lbIaHost = findViewById(R.id.lbIaHost);
        lbIaPrint = findViewById(R.id.lbIaPrint);
        txtIaHost = findViewById(R.id.txtIaHost);
        txtIaPrint = findViewById(R.id.txtIaPrint);
        lbIaPosID = findViewById(R.id.lbIaPosID);
        txtIaPosID = findViewById(R.id.txtIaPosID);
        lbIaTaxID = findViewById(R.id.lbIaTaxID);
        txtIaTaxID = findViewById(R.id.txtIaTaxID);
        lbIaWebDirectory = findViewById(R.id.lbIaWebDirectory);
        txtIaWebDirectory = findViewById(R.id.txtIaWebDirectory);
        lbIaPortID = findViewById(R.id.lbIaPortID);
        txtIaPortID = findViewById(R.id.txtIaPortID);
        txtIaUserDB = findViewById(R.id.txtIaUserDB);
        txtIaPasswordDB = findViewById(R.id.txtIaPasswordDB);

        btnIaSave = findViewById(R.id.btnIaSave);
        btnIaPrint = findViewById(R.id.btnIaPrint);
        btnFristRes = findViewById(R.id.btnFristRes);
        btnIaInit1 = findViewById(R.id.btnIaInit1);

        lbIaUserDB = findViewById(R.id.lbIaUserDB);
        lbIaPasswordDB = findViewById(R.id.lbIaPasswordDB);

        btnIaPrint.setText("Test Print");
        lbIaHost.setText(R.string.hostIP);
        lbIaPrint.setText("Printer IP");
        lbIaPosID.setText("POS ID");
        lbIaWebDirectory.setText("Web Directory");
        lbIaTaxID.setText("Tax ID");
        lbIaPortID.setText("Port Number");

        lbIaUserDB.setText(R.string.user);
        lbIaPasswordDB.setText(R.string.password);

        btnIaSave.setText(R.string.save);
        btnFristRes.setText(R.string.btnFristRes);
        btnIaInit1.setText(R.string.btnIaInit1);

        btnIaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText();
            }
        });
        btnIaPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnFristRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), FirstResActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnIaInit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), FirstResActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
    }
    private void saveText(){
        FileOutputStream outputStream;
        String textSize="",printOrder="",printBill="",printCloseDay="";
//        textSize = chkPrintOrder.isChecked()?"ON":"Off";
//        printOrder = chkPrintOrder.isChecked()?"ON":"Off";
//        printBill = chkPrintBill.isChecked()?"ON":"Off";
//        printCloseDay = chkPrintCloseDay.isChecked()?"ON":"Off";
//        if(cboIaTextSize.getSelectedItem().toString().contains("16")){
//            textSize="16";
//        }else if(cboIaTextSize.getSelectedItem().toString().contains("18")){
//            textSize="18";
//        }else if(cboIaTextSize.getSelectedItem().toString().contains("20")){
//            textSize="20";
//        }else if(cboIaTextSize.getSelectedItem().toString().contains("21")){
//            textSize="21";
//        }
        String string = "host="+txtIaHost.getText().toString().trim()+";\n"
                +"printer="+txtIaPrint.getText().toString().trim()+";\n"
                +"PosID="+txtIaPosID.getText().toString().trim()+";\n"
                +"TaxID="+txtIaTaxID.getText().toString().trim()+";\n"
                +"PortNumber="+txtIaPortID.getText().toString().trim()+";\n"
                +"WebDirectory="+txtIaWebDirectory.getText().toString().trim()+";\n"
                +"UserDB="+txtIaUserDB.getText().toString().trim()+";\n"
                +"PasswordDB="+txtIaPasswordDB.getText().toString().trim()+";\n"
                +"TextSize="+textSize+";\n"
                +"PrintOrder="+printOrder+";\n"
                +"PrintBill="+printBill+";\n"
                +"PrintCloseDay="+printCloseDay+";\n";
        try {
            File file =getFileStreamPath("initial.cnf");
            outputStream = openFileOutput("initial.cnf", Context.MODE_PRIVATE);
//            outputStream = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            rs.pageLoad=true;
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
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
                String textSize="",printOrder="",printBill="",printCloseDay="";
                txtIaHost.setText(p[0].replace("host=",""));
                txtIaPrint.setText(p[1].replace("printer=","").replace("\n",""));
                txtIaPosID.setText(p[2].replace("PosID=","").replace("\n",""));
                txtIaTaxID.setText(p[3].replace("TaxID=","").replace("\n",""));
                txtIaPortID.setText(p[4].replace("PortNumber=","").replace("\n",""));
                txtIaWebDirectory.setText(p[5].replace("WebDirectory=","").replace("\n",""));
                txtIaUserDB.setText(p[6].replace("UserDB=","").replace("\n",""));
                txtIaPasswordDB.setText(p[7].replace("PasswordDB=","").replace("\n",""));
//                txtIaPasswordDB.setText(p[7].replace("PasswordDB=","").replace("\n",""));

                textSize = p[8].length()>0 ? p[8].replace("TextSize=","").replace("\n",""):"";
                printOrder = p[9].length()>0 ? p[9].replace("PrintOrder=","").replace("\n",""):"";
                printBill = p[10].length()>0 ? p[10].replace("PrintBill=","").replace("\n",""):"";
                printCloseDay = p[11].length()>0 ? p[11].replace("PrintCloseDay=","").replace("\n",""):"";
//                if(textSize.contains("16")){
//                    cboIaTextSize.setSelection(0);
//                }else if(textSize.contains("18")){
//                    cboIaTextSize.setSelection(1);
//                }else if(textSize.contains("20")){
//                    cboIaTextSize.setSelection(2);
//                }else if(textSize.contains("21")){
//                    cboIaTextSize.setSelection(3);
//                }
//                chkPrintOrder.setChecked(printOrder.equals("ON"));
//                chkPrintBill.setChecked(printBill.equals("ON"));
//                chkPrintCloseDay.setChecked(printCloseDay.equals("ON"));
                rs.PrnO = printOrder;
                rs.PrnB = printBill;
                rs.PrnC = printCloseDay;

                rs.hostIP = txtIaHost.getText().toString();
            }
            fileIn.close();
            rs.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
