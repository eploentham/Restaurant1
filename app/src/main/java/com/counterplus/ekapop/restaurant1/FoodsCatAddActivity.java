package com.counterplus.ekapop.restaurant1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodsCatAddActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_foods_cat_add);

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        fc = new FoodsCategory();

        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        lbFcaCode = findViewById(R.id.lbFcaCode);
        lbFcaName = findViewById(R.id.lbFcaName);
        lbFcaRemark = findViewById(R.id.lbFcaRemark);
        lbFcaActive = findViewById(R.id.lbFcaActive);
        lbFcaSort1 = findViewById(R.id.lbFcaSort1);
        txtFcaCode = findViewById(R.id.txtFcaCode);
        txtFcaName = findViewById(R.id.txtFcaName);
        txtFcaRemark = findViewById(R.id.txtFcaRemark);
        txtFcaSort1 = findViewById(R.id.txtFcaSort1);
        txtFcaPasswordVoid = findViewById(R.id.txtFcaPasswordVoid);
        btnFcaSave = findViewById(R.id.btnFcaSave);
        btnFcaVoid = findViewById(R.id.btnFcaVoid);
        chkFcaActive = findViewById(R.id.chkFcaActive);

        lbFcaCode.setText(R.string.code);
        lbFcaName.setText(R.string.name);
        lbFcaRemark.setText(R.string.remark);
        lbFcaActive.setText(R.string.active);
        lbFcaSort1.setText(R.string.sort);
        btnFcaSave.setText(R.string.save);
        btnFcaVoid.setText(R.string.void1);
        btnFcaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rs.AccessMode.equals("Standalone")) {
                    getFoodsCategory();
                    jarr = daS.FoodsCategoryInsert(fc.ID, fc.Code, fc.Name, fc.Remark, fc.Sort1);
                    getFoodsCategoryInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    getFoodsCategory();
                    new insertFoodsCategory().execute();
                }else{
                    getFoodsCategory();
                    new insertFoodsCategory().execute();
                }

            }
        });
        btnFcaVoid.setVisibility(View.INVISIBLE);
        txtFcaPasswordVoid.setVisibility(View.INVISIBLE);
        chkFcaActive.setText(R.string.activeon);
        chkFcaActive.setChecked(true);
        txtFcaCode.setEnabled(false);
        chkFcaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkFcaActive.setText(R.string.activeon);
                    btnFcaVoid.setVisibility(View.INVISIBLE);
                }else{
                    chkFcaActive.setText(R.string.activeoff);
                    btnFcaVoid.setVisibility(View.VISIBLE);
                }
            }
        });
        if(rs.AccessMode.equals("Standalone")) {
            jarrF = daS.FoodsTypeSelectById(rs.ftID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveFoodsCategory().execute();
        }else{
            new retrieveFoodsCategory().execute();
        }
        btnFcaVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(btnFcaVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                if(txtFcaPasswordVoid.getText().toString().equals("")){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsCatAddActivity.this);
                    builder1.setMessage("Password ไม่ได้ป้อน");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            txtFcaPasswordVoid.setSelection(0, txtFcaPasswordVoid.getText().length());
                            txtFcaPasswordVoid.setFocusable(true);
                        }
                    }).create().show();
                }else{
                    if(rs.chkPasswordVoid(txtFcaPasswordVoid.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"genid");
                        if(rs.AccessMode.equals("Standalone")) {
                            jarr = daS.FoodsCategoryVoid(rs.chkUserByPassword(txtFcaPasswordVoid.getText().toString()), fc.ID);
                            getFoodsCategoryVoid();
                        }else if(rs.AccessMode.equals("Internet")){
                            new FoodsCategoryVoid().execute(rs.chkUserByPassword(txtFcaPasswordVoid.getText().toString()), fc.ID);
                        }else{
                            new FoodsCategoryVoid().execute(rs.chkUserByPassword(txtFcaPasswordVoid.getText().toString()), fc.ID);
                        }

                    }else{
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsCatAddActivity.this);
                        builder1.setMessage("Password ไม่ถูกต้อง");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtFcaPasswordVoid.setSelection(0, txtFcaPasswordVoid.getText().length());
                                txtFcaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }
                }


                if(rs.AccessMode.equals("Standalone")) {

                }else if(rs.AccessMode.equals("Internet")){

                }else{

                }
            }else{
                if(!chkFcaActive.isChecked()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsCatAddActivity.this);
                    builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+" รหัส "+ txtFcaCode.getText().toString()+" "+ txtFcaName.getText().toString()+"\n");
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
                            txtFcaPasswordVoid.setVisibility(View.VISIBLE);
                            btnFcaVoid.setText(R.string.void1confrim);
                            txtFcaPasswordVoid.setSelection(0, txtFcaPasswordVoid.getText().length());
                            txtFcaPasswordVoid.setFocusable(true);

                        }
                    }).create().show();
                }
            }
            }
        });
//        new retrieveFoodsPrint().execute();
        if(rs.ftID.equals("")) chkFcaActive.setChecked(true);
        setTheme();
    }
    private void setTheme(){
        lbFcaCode.setTextSize(textSize);
        lbFcaName.setTextSize(textSize);
        lbFcaRemark.setTextSize(textSize);
        lbFcaSort1.setTextSize(textSize);
        lbFcaActive.setTextSize(textSize);
        txtFcaCode.setTextSize(textSize);
        txtFcaName.setTextSize(textSize);
        txtFcaRemark.setTextSize(textSize);
        txtFcaSort1.setTextSize(textSize);
    }
    private void setControl(){
        try {
            fc = new FoodsCategory();
            if((jarrF!=null) && (!jarrF.equals("[]")) & jarrF.length()>0){
                JSONObject catObj = (JSONObject) jarrF.get(0);
                fc.ID = catObj.getString(fc.dbID);
                fc.Code = catObj.getString(fc.dbCode);
                fc.Name = rs.StringNull(catObj.getString(fc.dbName));
                fc.Remark = rs.StringNull(catObj.getString(fc.dbRemark));
                fc.Sort1 = catObj.getString(fc.dbSort1);
                fc.Active = catObj.getString(fc.dbActive);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setControl ",e.getMessage());
        }
        if(fc !=null){
            txtFcaCode.setText(fc.Code);
            txtFcaName.setText(fc.Name);
            txtFcaRemark.setText(fc.Remark);
            txtFcaSort1.setText(fc.Sort1);
            if(fc.Active.equals("1")){
                chkFcaActive.setChecked(true);
            }else{
                chkFcaActive.setChecked(false);
            }
        }
    }
    private void getFoodsCategory(){
        fc = new FoodsCategory();
        fc.ID = rs.ftID;
        //res.Sort1=txtRaSort1.getText().toString();
        fc.Active="1";
        fc.Code= txtFcaCode.getText().toString();
        fc.Name= txtFcaName.getText().toString();
        fc.Remark= txtFcaRemark.getText().toString();
    }
    class insertFoodsCategory extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(fc.dbID, fc.ID));
            params.add(new BasicNameValuePair(fc.dbCode, fc.Code));
            params.add(new BasicNameValuePair(fc.dbName, fc.Name));
            params.add(new BasicNameValuePair(fc.dbRemark, fc.Remark));
            params.add(new BasicNameValuePair(fc.dbActive, fc.Active));
            params.add(new BasicNameValuePair(fc.dbSort1, fc.Sort1));
            if(fc.ID.equals("")){
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
            getFoodsCategoryInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getFoodsCategoryInsert(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("success").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsCatAddActivity.this);
                builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFcaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getFoodsCategoryInsert ",e.getMessage());
        }
    }
    private void getFoodsCategoryVoid(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("status").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsCatAddActivity.this);
                builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFcaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getFoodsCategoryInsert ",e.getMessage());
        }
    }
    class retrieveFoodsCategory extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(fc.dbID, rs.ftID));
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
    class FoodsCategoryVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(fc.dbID, arg0[1]));
            params.add(new BasicNameValuePair(fc.dbVoidUser, arg0[0]));
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

            jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeVoid,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getFoodsCategoryVoid();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
