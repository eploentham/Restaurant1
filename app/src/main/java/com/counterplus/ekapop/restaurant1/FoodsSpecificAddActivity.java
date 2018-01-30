package com.counterplus.ekapop.restaurant1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodsSpecificAddActivity extends AppCompatActivity {

    TextView lbFsaCode, lbFsaName, lbFsaRemark, lbFsaSort1, lbFsaActive, lbFsaFoodsCode;
    EditText txtFsaCode, txtFsaName, txtFsaRemark, txtFsaSort1, txtFsaPasswordVoid;
    Switch chkFsaActive;
    Button btnFsaSave, btnFsaVoid;
    Spinner cboFsaFoodsCode;

    FoodsSpecific fs = new FoodsSpecific();

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
        setContentView(R.layout.activity_foods_specific_add);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        fs = new FoodsSpecific();

        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        lbFsaFoodsCode = findViewById(R.id.lbFsaFoodsCode);
        lbFsaCode = findViewById(R.id.lbFsaCode);
        lbFsaName = findViewById(R.id.lbFsaName);

        lbFsaActive = findViewById(R.id.lbFsaActive);
        lbFsaSort1 = findViewById(R.id.lbFsaSort1);
        cboFsaFoodsCode = findViewById(R.id.cboFsaFoodsCode);
        txtFsaCode = findViewById(R.id.txtFsaCode);
        txtFsaName = findViewById(R.id.txtFsaName);

        txtFsaSort1 = findViewById(R.id.txtFsaSort1);
        txtFsaPasswordVoid = findViewById(R.id.txtFsaPasswordVoid);
        btnFsaSave = findViewById(R.id.btnFsaSave);
        btnFsaVoid = findViewById(R.id.btnFsaVoid);
        chkFsaActive = findViewById(R.id.chkFsaActive);

        lbFsaCode.setText(R.string.code);
        lbFsaName.setText(R.string.name);

        lbFsaFoodsCode.setText(R.string.desc);
        lbFsaActive.setText(R.string.active);
        lbFsaSort1.setText(R.string.sort);
        btnFsaSave.setText(R.string.save);
        btnFsaVoid.setText(R.string.void1);
        btnFsaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rs.AccessMode.equals("Standalone")) {
                    getFoodsSpecific();
                    jarr = daS.FoodsSpecificInsert(fs.ID, fs.Code, fs.Name, fs.FoodsCode, fs.Sort1);
                    getFoodsSpecificInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    getFoodsSpecific();
                    new insertFoodsSpecific().execute();
                }else{
                    getFoodsSpecific();
                    new insertFoodsSpecific().execute();
                }

            }
        });
        btnFsaVoid.setVisibility(View.INVISIBLE);
        txtFsaPasswordVoid.setVisibility(View.INVISIBLE);
        chkFsaActive.setText(R.string.activeon);
        chkFsaActive.setChecked(true);
        txtFsaCode.setEnabled(false);
        chkFsaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkFsaActive.setText(R.string.activeon);
                    btnFsaVoid.setVisibility(View.INVISIBLE);
                }else{
                    chkFsaActive.setText(R.string.activeoff);
                    btnFsaVoid.setVisibility(View.VISIBLE);
                }
            }
        });
        if(rs.AccessMode.equals("Standalone")) {
            jarrF = daS.FoodsCategorySelectById(rs.fcID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveFoodsSpecific().execute();
        }else{
            new retrieveFoodsSpecific().execute();
        }
        btnFsaVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnFsaVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                    if(txtFsaPasswordVoid.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsSpecificAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtFsaPasswordVoid.setSelection(0, txtFsaPasswordVoid.getText().length());
                                txtFsaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtFsaPasswordVoid.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"genid");
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.FoodsCategoryVoid(rs.chkUserByPassword(txtFsaPasswordVoid.getText().toString()), fs.ID);
                                getFoodsSpecificVoid();
                            }else if(rs.AccessMode.equals("Internet")){
                                new FoodsSpecificVoid().execute(rs.chkUserByPassword(txtFsaPasswordVoid.getText().toString()), fs.ID);
                            }else{
                                new FoodsSpecificVoid().execute(rs.chkUserByPassword(txtFsaPasswordVoid.getText().toString()), fs.ID);
                            }

                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsSpecificAddActivity.this);
                            builder1.setMessage("Password ไม่ถูกต้อง");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtFsaPasswordVoid.setSelection(0, txtFsaPasswordVoid.getText().length());
                                    txtFsaPasswordVoid.setFocusable(true);
                                }
                            }).create().show();
                        }
                    }


                    if(rs.AccessMode.equals("Standalone")) {

                    }else if(rs.AccessMode.equals("Internet")){

                    }else{

                    }
                }else{
                    if(!chkFsaActive.isChecked()){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsSpecificAddActivity.this);
                        builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+" รหัส "+ txtFsaCode.getText().toString()+" "+ txtFsaName.getText().toString()+"\n");
                        builder1.setCancelable(true);
                        builder1.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                finish();
//                                flagDel=false;
//                                Toast.makeText(com.counterplus.ekapop.restaurant1.Area.MailarapOrderAdd.this,"You clicked no button",Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        });
                        builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Whatever...
                                txtFsaPasswordVoid.setVisibility(View.VISIBLE);
                                btnFsaVoid.setText(R.string.void1confrim);
                                txtFsaPasswordVoid.setSelection(0, txtFsaPasswordVoid.getText().length());
                                txtFsaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }
                }
            }
        });
//        new retrieveFoodsPrint().execute();
        if(rs.fsID.equals("")) chkFsaActive.setChecked(true);
        ArrayAdapter<String> adaFoods = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboFoods);
        cboFsaFoodsCode.setAdapter(adaFoods);
        setTheme();
    }
    private void setTheme(){
        lbFsaCode.setTextSize(textSize);
        lbFsaName.setTextSize(textSize);
        //lbFsaRemark.setTextSize(textSize);
        lbFsaSort1.setTextSize(textSize);
        lbFsaActive.setTextSize(textSize);
        txtFsaCode.setTextSize(textSize);
        txtFsaName.setTextSize(textSize);
//        txtFsaRemark.setTextSize(textSize);
        txtFsaSort1.setTextSize(textSize);
    }
    private void setControl(){
        try {
            fs = new FoodsSpecific();
            if((jarrF!=null) && (!jarrF.equals("[]")) & jarrF.length()>0){
                JSONObject catObj = (JSONObject) jarrF.get(0);
                fs.ID = catObj.getString(fs.dbID);
                fs.Code = catObj.getString(fs.dbCode);
                fs.Name = rs.StringNull(catObj.getString(fs.dbName));
                fs.Sort1 = catObj.getString(fs.dbSort1);
                fs.Active = catObj.getString(fs.dbActive);
                fs.FoodsCode = catObj.getString(fs.FoodsCode);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setControl ",e.getMessage());
        }
        if(fs !=null){
            txtFsaCode.setText(fs.Code);
            txtFsaName.setText(fs.Name);
            txtFsaSort1.setText(fs.Sort1);
            if(fs.Active.equals("1")){
                chkFsaActive.setChecked(true);
            }else{
                chkFsaActive.setChecked(false);
            }
        }
    }
    private void getFoodsSpecific(){
        String foocode = rs.getFoods(cboFsaFoodsCode.getSelectedItem().toString(),"code");
        String fooid = rs.getFoods(cboFsaFoodsCode.getSelectedItem().toString(),"genid");

        fs = new FoodsSpecific();
        fs.ID = rs.fsID;
        //res.Sort1=txtRaSort1.getText().toString();
        fs.Active="1";
        fs.Code = txtFsaCode.getText().toString();
        fs.Name = txtFsaName.getText().toString();
        fs.FoodsCode = foocode;
    }
    class insertFoodsSpecific extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(fs.dbID, fs.ID));
            params.add(new BasicNameValuePair(fs.dbCode, fs.Code));
            params.add(new BasicNameValuePair(fs.dbName, fs.Name));

            params.add(new BasicNameValuePair(fs.dbActive, fs.Active));
            params.add(new BasicNameValuePair(fs.dbSort1, fs.Sort1));
            if(fs.ID.equals("")){
                jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeInsert,params);
            }else{
                jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeUpdate,params);
            }
//            if(jarr!=null){
//                JSONObject catObj = (JSONObject) jarr.get(0);
//            }
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getFoodsSpecificInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getFoodsSpecificInsert(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("success").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsSpecificAddActivity.this);
                builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFsaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getFoodsSpecificInsert ",e.getMessage());
        }
    }
    private void getFoodsSpecificVoid(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("status").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsSpecificAddActivity.this);
                builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFsaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getFoodsSpecificInsert ",e.getMessage());
        }
    }
    class retrieveFoodsSpecific extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(fs.dbID, rs.ftID));
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

            jarrF = jsonparser.getJSONFromUrl(rs.hostFoodsTypeSelectByID,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            setControl();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    class FoodsSpecificVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(fs.dbID, arg0[1]));
            params.add(new BasicNameValuePair(fs.dbVoidUser, arg0[0]));
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

            jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeVoid,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getFoodsSpecificVoid();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
