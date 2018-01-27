package com.counterplus.ekapop.restaurant1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class TableAddActivity extends AppCompatActivity {

    TextView lbTaCode, lbTaName, lbTaArea, lbTaRemark, lbTaActive,lbTaSort1;
    EditText txtTaCode, txtTaName, txtTaRemark, txtTaSort1, txtTaPasswordVoid;
    Spinner cboTaArea;
    Button btnTaSave, btnTaVoid;
    Switch chkTaActive;

    Table ta;
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
        setContentView(R.layout.activity_table_add);

        Intent intent = getIntent();
        rs = (RestaurantControl) intent.getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");
        ta = new Table();

        textSize = rs.TextSize.equals("")?16:Integer.parseInt(rs.TextSize);
        lbTaCode = findViewById(R.id.lbTaCode);
        lbTaName = findViewById(R.id.lbTaName);
        lbTaArea = findViewById(R.id.lbTaArea);
        lbTaRemark = findViewById(R.id.lbTaRemark);
        lbTaActive = findViewById(R.id.lbTaActive);
        lbTaSort1 = findViewById(R.id.lbTaSort1);
        txtTaCode = findViewById(R.id.txtTaCode);
        txtTaName = findViewById(R.id.txtTaName);
        txtTaRemark = findViewById(R.id.txtTaRemark);
        txtTaSort1 = findViewById(R.id.txtTaSort1);
        txtTaPasswordVoid = findViewById(R.id.txtTaPasswordVoid);
        btnTaSave = findViewById(R.id.btnTaSave);
        btnTaVoid = findViewById(R.id.btnTaVoid);
        cboTaArea = findViewById(R.id.cboTaArea);
        chkTaActive = findViewById(R.id.chkTaActive);

        lbTaCode.setText(R.string.code);
        lbTaName.setText(R.string.name);
        lbTaArea.setText(R.string.area);
        lbTaRemark.setText(R.string.remark);
        lbTaActive.setText(R.string.active);
        lbTaSort1.setText(R.string.sort);
        btnTaSave.setText(R.string.save);
        btnTaVoid.setText(R.string.void1);
        btnTaVoid.setVisibility(View.INVISIBLE);
        txtTaPasswordVoid.setVisibility(View.INVISIBLE);

        btnTaSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rs.AccessMode.equals("Standalone")) {
                    getTable();
                    jarr = daS.TableInsert(ta.ID, ta.Code, ta.Name,ta.AreaID, ta.Remark, ta.Sort1, ta.StatusUse,ta.StatusToGo,ta.DateUse);
                    getTableInsert();
                }else if(rs.AccessMode.equals("Internet")){
                    getTable();
                    new insertTable().execute();
                }else{
                    getTable();
                    new insertTable().execute();
                }
            }
        });
        chkTaActive.setText(R.string.activeon);
        chkTaActive.setChecked(true);
        txtTaCode.setEnabled(false);
        chkTaActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkTaActive.setText(R.string.activeon);
                    btnTaVoid.setVisibility(View.INVISIBLE);
                }else{
                    chkTaActive.setText(R.string.activeoff);
                    btnTaVoid.setVisibility(View.VISIBLE);
                }
            }
        });
        ArrayAdapter<String> adaArea = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,rs.sCboArea);
        cboTaArea.setAdapter(adaArea);
        if(rs.AccessMode.equals("Standalone")) {
            jarrF = daS.TableSelectById(rs.taID);
            setControl();
        }else if(rs.AccessMode.equals("Internet")){
            new retrieveTable().execute();
        }else{
            new retrieveTable().execute();
        }
//        new retrieveTable().execute();
        btnTaVoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnTaVoid.getText().toString().equals(getResources().getString(R.string.void1confrim))){
                    if(txtTaPasswordVoid.getText().toString().equals("")){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(TableAddActivity.this);
                        builder1.setMessage("Password ไม่ได้ป้อน");
                        builder1.setCancelable(true);
                        builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtTaPasswordVoid.setSelection(0,txtTaPasswordVoid.getText().length());
                                txtTaPasswordVoid.setFocusable(true);
                            }
                        }).create().show();
                    }else{
                        if(rs.chkPasswordVoid(txtTaPasswordVoid.getText().toString())){
//                            String tableid = rs.getTable(cboBvTable.getSelectedItem().toString(),"genid");
                            if(rs.AccessMode.equals("Standalone")) {
                                jarr = daS.TableVoid(rs.chkUserByPassword(txtTaPasswordVoid.getText().toString()), ta.ID);
                                getTableVoid();
                            }else if(rs.AccessMode.equals("Internet")){
                                new TableVoid().execute(rs.chkUserByPassword(txtTaPasswordVoid.getText().toString()), ta.ID);
                            }else{
                                new TableVoid().execute(rs.chkUserByPassword(txtTaPasswordVoid.getText().toString()), ta.ID);
                            }

                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(TableAddActivity.this);
                            builder1.setMessage("Password ไม่ถูกต้อง");
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    txtTaPasswordVoid.setSelection(0,txtTaPasswordVoid.getText().length());
                                    txtTaPasswordVoid.setFocusable(true);
                                }
                            }).create().show();
                        }
                    }
                    if(rs.AccessMode.equals("Standalone")) {

                    }else if(rs.AccessMode.equals("Internet")){

                    }else{

                    }
                }else{
                    if(!chkTaActive.isChecked()){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(TableAddActivity.this);
                        builder1.setMessage("ต้องการเยกเลิกรายการนี้.\n\nลำดับ "+" รหัส "+txtTaCode.getText().toString()+" "+ txtTaName.getText().toString()+"\n");
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
                                txtTaPasswordVoid.setVisibility(View.VISIBLE);
                                btnTaVoid.setText(R.string.void1confrim);
                                txtTaPasswordVoid.setSelection(0,txtTaPasswordVoid.getText().length());
                                txtTaPasswordVoid.setFocusable(true);

                            }
                        }).create().show();

                    }
                }
            }
        });
//        new retrieveFoodsCat().execute();
        if(rs.taID.equals("")) chkTaActive.setChecked(true);
        setTheme();
    }
    private void setTheme(){
        lbTaCode.setTextSize(textSize);
        lbTaName.setTextSize(textSize);
        lbTaRemark.setTextSize(textSize);
        lbTaSort1.setTextSize(textSize);
        lbTaActive.setTextSize(textSize);
        txtTaCode.setTextSize(textSize);
        txtTaName.setTextSize(textSize);
        txtTaRemark.setTextSize(textSize);
        txtTaSort1.setTextSize(textSize);
    }
    private void setControl(){
        try {
            ta = new Table();
            Log.d("table_id ",rs.taID);
            if((jarrF!=null) && (!jarrF.equals("[]")) && jarrF.length()>0){
                JSONObject catObj = (JSONObject) jarrF.get(0);
                ta.ID = catObj.getString(ta.dbID);
                ta.Code = catObj.getString(ta.dbCode);
                ta.Name = rs.StringNull(catObj.getString(ta.dbName)).trim();
                ta.Remark = rs.StringNull(catObj.getString(ta.dbRemark)).trim();
                ta.Sort1 = catObj.getString(ta.dbSort1);
                ta.Active = rs.StringNull(catObj.getString(ta.dbActive)).trim();
                ta.AreaID = rs.StringNull(catObj.getString(ta.dbAreaID)).trim();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(ta!=null){
            txtTaCode.setText(ta.Code);
            txtTaName.setText(ta.Name);
            txtTaRemark.setText(ta.Remark);
            txtTaSort1.setText(ta.Sort1);
            for(int i=0;i<cboTaArea.getCount();i++){
                if(cboTaArea.getItemAtPosition(i).equals(rs.getAreaToName(ta.AreaID,"genid"))){
                    cboTaArea.setSelection(i);
                }
            }
            if(ta.Active.equals("1")){
                chkTaActive.setChecked(true);
            }else{
                chkTaActive.setChecked(false);
            }
        }
    }
    private void getTable(){
        String areaId = rs.getArea(cboTaArea.getSelectedItem().toString(),"id");

        ta = new Table();
        ta.ID = rs.taID;
        ta.Sort1=txtTaSort1.getText().toString().trim();
        ta.Active="1";
        ta.Code=txtTaCode.getText().toString().trim();
        ta.Name=txtTaName.getText().toString().trim();
        ta.Remark=txtTaRemark.getText().toString().trim();
        ta.StatusUse="0";
        if(chkTaActive.isChecked()){
            ta.Active="1";
        }else{
            ta.Active="3";
        }
        ta.AreaID = areaId;
    }
    class insertTable extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(ta.dbID, ta.ID));
            params.add(new BasicNameValuePair(ta.dbCode, ta.Code));
            params.add(new BasicNameValuePair(ta.dbName, ta.Name));
            params.add(new BasicNameValuePair(ta.dbRemark, ta.Remark));
            params.add(new BasicNameValuePair(ta.dbActive, ta.Active));
            params.add(new BasicNameValuePair(ta.dbSort1, ta.Sort1));
            params.add(new BasicNameValuePair(ta.dbAreaID, ta.AreaID));
            if(ta.ID.equals("")){
                jarr = jsonparser.getJSONFromUrl(rs.hostTableInsert,params);
            }else{
                jarr = jsonparser.getJSONFromUrl(rs.hostTableUpdate,params);
            }

//                if(jarr!=null){
//                    JSONObject catObj = (JSONObject) jarr.get(0);
//                }
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getTableInsert();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
    private void getTableInsert(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("success").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(TableAddActivity.this);
                builder1.setMessage("บันทึกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnTaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {

        }
    }
    private void getTableVoid(){
        try {
            JSONObject catObj = (JSONObject) jarr.get(0);
            Log.d("sql",catObj.getString("sql"));
            if(catObj.getString("status").equals("1")){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(TableAddActivity.this);
                builder1.setMessage("ยกเลิกข้อมูล  เรียบร้อย");
                builder1.setCancelable(true);
                builder1.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnTaSave.setEnabled(false);
                    }
                }).create().show();
            }
        } catch (JSONException e) {
            Log.e("getTableVoid ",e.getMessage());
        }
    }
    class retrieveTable extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(ta.dbID, rs.taID));

            jarrF = jsonparser.getJSONFromUrl(rs.hostTableSelectByID,params);
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
    class TableVoid extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            params.add(new BasicNameValuePair(ta.dbID, arg0[1]));
            params.add(new BasicNameValuePair(ta.dbVoidUser, arg0[0]));

            jarr = jsonparser.getJSONFromUrl(rs.hostTableVoid,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            getTableVoid();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;
        }
    }
}
