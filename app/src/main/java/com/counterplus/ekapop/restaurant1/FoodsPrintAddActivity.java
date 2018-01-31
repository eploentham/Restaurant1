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

public class FoodsPrintAddActivity extends AppCompatActivity {

    TextView lbFpaCode, lbFpaName, lbFpaRemark, lbFpaSort1, lbFpaActive, lbFpaIP, lbFpaBrand, lbFpaModel;
    EditText txtFpaCode, txtFpaName, txtFpaRemark, txtFpaSort1, txtFpaPasswordVoid, txtFpaIP;
    Switch chkFpaActive;
    Button btnFpaSave, btnFpaVoid;
    Spinner cboFpaBrand,cboFpaModel;

    FoodsPrint fp = new FoodsPrint();

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

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        fp = new FoodsPrint();

        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);

        lbFpaCode = findViewById(R.id.lbFpaCode);
        lbFpaName = findViewById(R.id.lbFpaName);
        lbFpaRemark = findViewById(R.id.lbFpaRemark);
        lbFpaActive = findViewById(R.id.lbFpaActive);
        lbFpaSort1 = findViewById(R.id.lbFpaSort1);
        lbFpaIP = findViewById(R.id.lbFpaIP);
        txtFpaCode = findViewById(R.id.txtFpaCode);
        txtFpaName = findViewById(R.id.txtFpaName);
        txtFpaRemark = findViewById(R.id.txtFpaRemark);
        txtFpaSort1 = findViewById(R.id.txtFpaSort1);
        txtFpaPasswordVoid = findViewById(R.id.txtFpaPasswordVoid);
        btnFpaSave = findViewById(R.id.btnFpaSave);
        btnFpaVoid = findViewById(R.id.btnFpaVoid);
        chkFpaActive = findViewById(R.id.chkFpaActive);
        txtFpaIP = findViewById(R.id.txtFpaIP);
        lbFpaBrand = findViewById(R.id.lbFpaBrand);
        lbFpaModel = findViewById(R.id.lbFpaModel);
        cboFpaBrand = findViewById(R.id.cboFpaBrand);
        cboFpaModel = findViewById(R.id.cboFpaModel);

        lbFpaCode.setText(R.string.code);
        lbFpaName.setText(R.string.name);
        lbFpaRemark.setText(R.string.remark);
        lbFpaActive.setText(R.string.active);
        lbFpaSort1.setText(R.string.sort);
        lbFpaIP.setText(R.string.lbFpaIP);
        btnFpaSave.setText(R.string.save);
        btnFpaVoid.setText(R.string.void1);
        lbFpaModel.setText(R.string.lbFpaModel);
        lbFpaBrand.setText(R.string.lbFpaBrand);
        btnFpaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rs.AccessMode.equals("Standalone")) {
                    getFoodsPrint();
                    jarr = daS.FoodsPrintInsert(fp.ID, fp.Code, fp.Name, fp.Remark, fp.Sort1, fp.IP);
                    getFoodsPrintInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    getFoodsPrint();
                    new insertFoodsPrint().execute();
                }else{
                    getFoodsPrint();
                    new insertFoodsPrint().execute();
                }

            }
        });
        btnFpaVoid.setVisibility(View.INVISIBLE);
        txtFpaPasswordVoid.setVisibility(View.INVISIBLE);
        chkFpaActive.setText(R.string.activeon);
        chkFpaActive.setChecked(true);
        txtFpaCode.setEnabled(false);
        chkFpaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkFpaActive.setText(R.string.activeon);
                    btnFpaVoid.setVisibility(View.INVISIBLE);
                }else{
                    chkFpaActive.setText(R.string.activeoff);
                    btnFpaVoid.setVisibility(View.VISIBLE);
                }
            }
        });
        if(rs.AccessMode.equals("Standalone")) {
            jarrF = daS.FoodsPrintSelectById(rs.fpID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveFoodsPrint().execute();
        }else{
            new retrieveFoodsPrint().execute();
        }
        ArrayAdapter<String> adaBrand = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboBrand);
        ArrayAdapter<String> adaModel = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboModel);
        cboFpaBrand.setAdapter(adaBrand);
        cboFpaModel.setAdapter(adaModel);
        btnFpaVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnFpaVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                    if(txtFpaPasswordVoid.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsPrintAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtFpaPasswordVoid.setSelection(0, txtFpaPasswordVoid.getText().length());
                                txtFpaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtFpaPasswordVoid.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"genid");
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.FoodsCategoryVoid(rs.chkUserByPassword(txtFpaPasswordVoid.getText().toString()), fp.ID);
                                getFoodsPrintVoid();
                            }else if(rs.AccessMode.equals("Internet")){
                                new FoodsPrintVoid().execute(rs.chkUserByPassword(txtFpaPasswordVoid.getText().toString()), fp.ID);
                            }else{
                                new FoodsPrintVoid().execute(rs.chkUserByPassword(txtFpaPasswordVoid.getText().toString()), fp.ID);
                            }

                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsPrintAddActivity.this);
                            builder1.setMessage("Password ไม่ถูกต้อง");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtFpaPasswordVoid.setSelection(0, txtFpaPasswordVoid.getText().length());
                                    txtFpaPasswordVoid.setFocusable(true);
                                }
                            }).create().show();
                        }
                    }


                    if(rs.AccessMode.equals("Standalone")) {

                    }else if(rs.AccessMode.equals("Internet")){

                    }else{

                    }
                }else{
                    if(!chkFpaActive.isChecked()){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsPrintAddActivity.this);
                        builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+" รหัส "+ txtFpaCode.getText().toString()+" "+ txtFpaName.getText().toString()+"\n");
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
                                txtFpaPasswordVoid.setVisibility(View.VISIBLE);
                                btnFpaVoid.setText(R.string.void1confrim);
                                txtFpaPasswordVoid.setSelection(0, txtFpaPasswordVoid.getText().length());
                                txtFpaPasswordVoid.setFocusable(true);

                            }
                        }).create().show();
                    }
                }
            }
        });
//        new retrieveFoodsPrint().execute();
        if(rs.ftID.equals("")) chkFpaActive.setChecked(true);
        setTheme();
    }
    private void setTheme(){
        lbFpaCode.setTextSize(textSize);
        lbFpaName.setTextSize(textSize);
        lbFpaRemark.setTextSize(textSize);
        lbFpaSort1.setTextSize(textSize);
        lbFpaActive.setTextSize(textSize);
        txtFpaCode.setTextSize(textSize);
        txtFpaName.setTextSize(textSize);
        txtFpaRemark.setTextSize(textSize);
        txtFpaSort1.setTextSize(textSize);
    }
    private void setControl(){
        try {
            fp = new FoodsPrint();
            if((jarrF!=null) && (!jarrF.equals("[]")) & jarrF.length()>0){
                JSONObject catObj = (JSONObject) jarrF.get(0);
                fp.ID = catObj.getString(fp.dbID);
                fp.Code = catObj.getString(fp.dbCode);
                fp.Name = rs.StringNull(catObj.getString(fp.dbName));
                fp.Remark = rs.StringNull(catObj.getString(fp.dbRemark));
                fp.Sort1 = catObj.getString(fp.dbSort1);
                fp.Active = catObj.getString(fp.dbActive);
                fp.IP = catObj.getString(fp.dbIP);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setControl ",e.getMessage());
        }
        if(fp !=null){
            txtFpaCode.setText(fp.Code);
            txtFpaName.setText(fp.Name);
            txtFpaRemark.setText(fp.Remark);
            txtFpaSort1.setText(fp.Sort1);
            txtFpaIP.setText(fp.IP);
            if(fp.Active.equals("1")){
                chkFpaActive.setChecked(true);
            }else{
                chkFpaActive.setChecked(false);
            }
        }
    }
    private void getFoodsPrint(){
        fp = new FoodsPrint();
        fp.ID = rs.fpID;
        //res.Sort1=txtRaSort1.getText().toString();
        fp.Active="1";
        fp.Code= txtFpaCode.getText().toString();
        fp.Name= txtFpaName.getText().toString();
        fp.Remark= txtFpaRemark.getText().toString();
        fp.IP= txtFpaIP.getText().toString();
    }
    class insertFoodsPrint extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(fp.dbID, fp.ID));
            params.add(new BasicNameValuePair(fp.dbCode, fp.Code));
            params.add(new BasicNameValuePair(fp.dbName, fp.Name));
            params.add(new BasicNameValuePair(fp.dbRemark, fp.Remark));
            params.add(new BasicNameValuePair(fp.dbActive, fp.Active));
            params.add(new BasicNameValuePair(fp.dbSort1, fp.Sort1));
            params.add(new BasicNameValuePair(fp.dbIP, fp.IP));
            if(fp.ID.equals("")){
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
            getFoodsPrintInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getFoodsPrintInsert(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("success").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsPrintAddActivity.this);
                builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFpaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getFoodsPrintInsert ",e.getMessage());
        }
    }
    private void getFoodsPrintVoid(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("status").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(FoodsPrintAddActivity.this);
                builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnFpaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getFoodsPrintInsert ",e.getMessage());
        }
    }
    class retrieveFoodsPrint extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(fp.dbID, rs.ftID));
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
    class FoodsPrintVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(fp.dbID, arg0[1]));
            params.add(new BasicNameValuePair(fp.dbVoidUser, arg0[0]));
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));

            jarr = jsonparser.getJSONFromUrl(rs.hostFoodsTypeVoid,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getFoodsPrintVoid();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
