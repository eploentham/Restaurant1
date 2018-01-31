package com.counterplus.ekapop.restaurant1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrA, jarrT, jarrR, jarrF,jarrU, jarrP, jarrFt, jarrFc, jarrFs, jarrFp, jarrBrand, jarrModel;
    //Button btnMInt;
    ImageButton btnMBill, btnMOrderV,btnCookV,btnOrderA, btnMCloseDay, btnMInt,btnMReport;
    ImageView imageRes, imageArea,imageTable,imageFoods,imageFoodsType, imageUser;
    TextView lbMMessage;

    public RestaurantControl rs;
    ProgressDialog pd;
    Boolean fileExit=false;
    User us = new User();
    Table ta = new Table();
    FoodsType ft = new FoodsType();
    FoodsCategory fc = new FoodsCategory();
    FoodsPrint fp = new FoodsPrint();
    FoodsSpecific fs = new FoodsSpecific();
    Area ar = new Area();
    Foods foo = new Foods();
    Brand brand = new Brand();
    Model model = new Model();

    Restaurant res = new Restaurant();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCookV = findViewById(R.id.btnCookView);
        btnOrderA = findViewById(R.id.btnOrderAdd);

        lbMMessage = findViewById(R.id.lbMMessage);
        btnMBill = findViewById(R.id.btnMBill);
        btnMCloseDay = findViewById(R.id.btnMCloseDay);

        btnMInt = findViewById(R.id.btnMInt);
//        btnMReport = findViewById(R.id.btnMReport);
        imageRes = findViewById(R.id.imageRes);
        imageArea = findViewById(R.id.imageArea);
        imageTable = findViewById(R.id.imageTable);
        imageFoods = findViewById(R.id.imageFoods);
        imageFoodsType = findViewById(R.id.imageFoodsType);
        imageUser = findViewById(R.id.imageUser);

        lbMMessage.setVisibility(View.INVISIBLE);

        imageRes.setImageResource(R.drawable.red_big);
        imageArea.setImageResource(R.drawable.red_big);
        imageTable.setImageResource(R.drawable.red_big);
        imageFoods.setImageResource(R.drawable.red_big);
        imageFoodsType.setImageResource(R.drawable.red_big);
        imageUser.setImageResource(R.drawable.red_big);

        //btnMFoodsType.setText(Re);
        rs = new RestaurantControl();
        rs.pageLoad=false;
        btnMInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), InitialActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });

        try {
            final int READ_BLOCK_SIZE = 100;
            //File file = new File("initial.cnf");
            File file = getFileStreamPath("initial.cnf");
            if(file.exists()){
                FileInputStream fileIn = openFileInput("initial.cnf");
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
//                    txtIaHost.setText(s);
                String[] p = s.split(";");
                if(p.length>0){
                    rs.hostIP = p[0].replace("host=","");
//                        txtIaPrint.setText(p[1].replace("printer=",""));
//                        rs.hostIP = s;
//                txtIaHost.setText(p[0].replace("host=",""));
                    //txtIaPrint.setText(p[1].replace("printer=",""));
                    rs.hostPORT =  p[4].replace("PortNumber=","").replace("\n","");
                    rs.hostWebDirectory =p[5].replace("WebDirectory=","").replace("\n","");
                    rs.UserDB =p[6].replace("UserDB=","").replace("\n","");
                    rs.PasswordDB =p[7].replace("PasswordDB=","").replace("\n","");
                    rs.TextSize =p[8].replace("TextSize=","").replace("\n","");
                    rs.AccessMode =p[13].replace("AccessMethod=","").replace("\n","");
                    rs.HostID =p[12].replace("HostID=","").replace("\n","");
//                txtIaTaxID.setText(p[3].replace("TaxID=",""));
//                txtIaPortID.setText(p[4].replace("PortNumber=",""));
//                txtIaWebDirectory.setText(p[5].replace("WebDirectory=",""));
                }
                fileIn.close();
            }

            rs.refresh();
//            }
        }catch (Exception e) {
            e.printStackTrace();
//            fileErr=true;
        }
//        if(fileErr)
        //btnCookV.setText(R.string.kitchen);
        //btnOrderA.setText("");

        btnOrderA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent s1 = new Intent(view.getContext(), EsernOrderAddActivity.class);
                rs.refresh();
                startActivityForResult(new Intent(view.getContext(), OrderAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
        btnCookV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_delete_product="http://10.0.1.25:80/restaurant/testjson.php";
                //JSONObject json = jsonParser.makeHttpRequest(url_delete_product, "POST", params);

            }
        });

        btnMBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), BillAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }
        });
//        btnMOrderV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult(new Intent(view.getContext(), OrderViewActivity.class).putExtra("RestaurantControl",rs), 0);
//            }
//        });
        btnMCloseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), CloseDayAddActivity.class).putExtra("RestaurantControl",rs), 0);
            }

        });
//        btnMReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult(new Intent(view.getContext(), ReportActivity.class).putExtra("RestaurantControl",rs), 0);
//            }
//        });
        ArrayList<ItemData> list=new ArrayList<>();
        list.add(new ItemData("Khr",R.drawable.idel_blue));
        list.add(new ItemData("Usd",R.drawable.idel_red));
        list.add(new ItemData("Jpy",R.drawable.menu_icon));
        list.add(new ItemData("Aud",R.drawable.menu_icon));

//        String txt = rs.hostIP;
//        txt = txt.replace("http://","").trim();
        if(rs.AccessMode.equals("Standalone")) {
            DatabaseSQLi daS = new DatabaseSQLi(this,"");
            jarrT = daS.TableSelectAll();
            Log.d("jarrT length ",String.valueOf(jarrT.length()));
            setCboTable();

            jarrA = daS.AreaSelectAll();
            setCboArea();

            jarrR = daS.ResSelectAll();
            setRes();
            jarrU = daS.UserSelectAll();
            setUser();
            jarrF = daS.FoodsSelectAll();
            setFoods();
            jarrFt = daS.FoodsTypeSelectAll();
            setFoodsType();
            jarrFc = daS.FoodsCatSelectAll();
            setFoodsCategory();
            jarrFs = daS.FoodsSpecificSelectAll();
            setFoodsSpecific();
            jarrFp = daS.FoodsPrintSelectAll();
            setFoodsPrint();
            jarrBrand = daS.BrandSelectAll();
            setBrand();
            jarrModel = daS.ModelSelectAll();
            setModel();

        }else if(rs.AccessMode.equals("Internet")){
//            rs.hostIP = "";
            if(!rs.hostIP.equals("")) {
                new chkServerReachable().execute();
            }
        }else{
            if(!rs.hostIP.equals("")) {
                new chkServerReachable().execute();
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService( Context.TELEPHONY_SERVICE );

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }
    class retrieveArea extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrA = jsonparser.getJSONFromUrl(rs.hostGetArea,params);

//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String aaa = ab;
            setCboArea();
        }
        @Override
        protected void onPreExecute() {
            String aaa = ab;

        }
    }
    private void setCboArea(){
        if(jarrA!=null){
            rs.sCboArea.clear();
            rs.sArea.clear();
            //JSONArray categories = jobj.getJSONArray("area");
            //JSONArray json = new JSONArray(jobj);
            try {
                for (int i = 0; i < jarrA.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrA.get(i);
                    rs.sCboArea.add(catObj.getString(ar.dbName));
                    rs.sArea.add(catObj.getString(ar.dbID)+"@"+catObj.getString(ar.dbCode)+"@"+catObj.getString(ar.dbName));
                }
                imageArea.setImageResource(R.drawable.green1);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("setCboArea ",e.getMessage());
            }
        }
    }
    public class retrieveTable extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
//            try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
//                if(!rs.hostIP.equals("")) {
//
//                }
            jarrT = jsonparser.getJSONFromUrl(rs.hostGetTable,params);

//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            setCboTable();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setCboTable(){
        String table="";
        try {
            if(jarrT!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboTable.clear();
                rs.sTable.clear();
                for (int i = 0; i < jarrT.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrT.get(i);
                    rs.sCboTable.add(catObj.getString(ta.dbName));
                    rs.sTable.add(catObj.getString(ta.dbID)+"@"+catObj.getString(ta.dbCode)+"@"+catObj.getString(ta.dbName)+"@"+catObj.getString(ta.dbStatusUse));
                    table += catObj.getString(ta.dbCode)+"="+catObj.getString(ta.dbStatusUse)+";\n";
                }
                imageTable.setImageResource(R.drawable.green1);
            }
            try {
                FileOutputStream outputStream;
//                    File file =getFileStreamPath("table.cnf");
                outputStream = openFileOutput("table.cnf", Context.MODE_PRIVATE);
//            outputStream = openFileOutput(file.getPath(), Context.MODE_PRIVATE);
                outputStream.write(table.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("outputStream ",e.getMessage());
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setCboTable ",e.getMessage());
        }
    }
    class retrieveRes extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            setRes();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setRes(){
        try {
            if(jarrR!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboRes.clear();
                rs.sRes.clear();
                for (int i = 0; i < jarrR.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrR.get(i);
                    rs.sCboRes.add(catObj.getString(res.dbName));
                    rs.sRes.add(catObj.getString(res.dbID)+"@"+catObj.getString(res.dbCode)+"@"+catObj.getString(res.dbName));
                    if(catObj.getString(res.dbDefaultRes).equals("1")){
                        rs.ResName = catObj.getString(res.dbName);
                        rs.ReceiptH1 = catObj.getString(res.dbRH1);
                        rs.ReceiptH2 = catObj.getString(res.dbRH2);
                        rs.ReceiptF1 = catObj.getString(res.dbRF1);
                        rs.ReceiptF2 = catObj.getString(res.dbRF2);
                    }
                }
                imageRes.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setRes ",e.getMessage());
        }
    }

    /**
     * ส่งobject ไปทาง RestaurantControl แล้วError
     * เลยเปลี่ยนเป็นส่งไปทาง String แทน
     */
    public class retrieveFoods extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrF = jsonparser.getJSONFromUrl(rs.hostGetFoods,params);
            rs.jarrF = jarrF.toString();
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);

            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setFoods();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setFoods(){
        try {
            if(jarrF!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboFoods.clear();
                rs.sFoods.clear();
                for (int i = 0; i < jarrF.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrF.get(i);
                    rs.sCboFoods.add(catObj.getString(foo.dbName));
                    rs.sFoods.add(catObj.getString(foo.dbID)+"@"+catObj.getString(foo.dbCode)+"@"+catObj.getString(foo.dbName));
                }
                imageFoods.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setFoodsType ",e.getMessage());
        }

    }
    class retrievePrinterName extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("userdb",rs.UserDB));
                params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
                jarrP = jsonparser.getJSONFromUrl(rs.hostGetPrinterName,params);
                if(jarrP!=null){
                    //JSONArray categories = jobj.getJSONArray("area");
                    //JSONArray json = new JSONArray(jobj);
                    rs.sCboPrinter.clear();
                    for (int i = 0; i < jarrP.length(); i++) {
                        JSONObject catObj = (JSONObject) jarrP.get(i);
                        rs.sCboPrinter.add(catObj.getString("printer_name"));
                    }
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("retrievePrinterName ",e.getMessage());
            }
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){

        }
        @Override
        protected void onPreExecute() {

        }
    }
    class retrieveFoodsType extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrFt = jsonparser.getJSONFromUrl(rs.hostGetFoodsType,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            setFoodsType();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setFoodsType(){
        try {
            if(jarrFt!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboFoodsType.clear();
                rs.sFoodsType.clear();
                for (int i = 0; i < jarrFt.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrFt.get(i);
                    rs.sCboFoodsType.add(catObj.getString(ft.dbName));
                    rs.sFoodsType.add(catObj.getString(ft.dbID)+"@"+catObj.getString(ft.dbCode)+"@"+catObj.getString(ft.dbName));
                }
                imageFoodsType.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setFoodsType ",e.getMessage());
        }
    }
    private void setFoodsCategory(){
        try {
            if(jarrFc!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboFoodsCategory.clear();
                rs.sFoodsCategory.clear();
                for (int i = 0; i < jarrFc.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrFc.get(i);
                    rs.sCboFoodsCategory.add(catObj.getString(fc.dbName));
                    rs.sFoodsCategory.add(catObj.getString(fc.dbID)+"@"+catObj.getString(fc.dbCode)+"@"+catObj.getString(fc.dbName));
                }
//                imageFoodsType.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setFoodsType ",e.getMessage());
        }
    }
    private void setFoodsSpecific(){
        try {
            if(jarrFs!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboFoodsSpecific.clear();
                rs.sFoodsSpec.clear();
                for (int i = 0; i < jarrFs.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrFs.get(i);
                    rs.sCboFoodsSpecific.add(catObj.getString(fs.dbName));
                    rs.sFoodsSpec.add(catObj.getString(fs.dbID)+"@"+catObj.getString(fs.dbCode)+"@"+catObj.getString(fs.dbName));
                }
//                imageFoodsType.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setFoodsType ",e.getMessage());
        }
    }
    private void setFoodsPrint(){
        try {
            if(jarrFs!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboFoodsPrint.clear();
                rs.sFoodsPrint.clear();
                for (int i = 0; i < jarrFp.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrFp.get(i);
                    rs.sCboFoodsPrint.add(catObj.getString(fp.dbName));
                    rs.sFoodsPrint.add(catObj.getString(fp.dbID)+"@"+catObj.getString(fp.dbCode)+"@"+catObj.getString(fp.dbName));
                }
//                imageFoodsType.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setFoodsType ",e.getMessage());
        }
    }
    private void setBrand(){
        try {
            if(jarrBrand!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboBrand.clear();
                rs.sBrand.clear();
                for (int i = 0; i < jarrBrand.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrBrand.get(i);
                    rs.sCboBrand.add(catObj.getString(brand.dbName));
                    rs.sBrand.add(catObj.getString(brand.dbID)+"@"+catObj.getString(brand.dbCode)+"@"+catObj.getString(brand.dbName));
                }
//                imageFoodsType.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setFoodsType ",e.getMessage());
        }
    }
    private void setModel(){
        try {
            if(jarrModel!=null){
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                rs.sCboModel.clear();
                rs.sModel.clear();
                for (int i = 0; i < jarrModel.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrModel.get(i);
                    rs.sCboModel.add(catObj.getString(brand.dbName));
                    rs.sModel.add(catObj.getString(model.dbID)+"@"+catObj.getString(model.dbCode)+"@"+catObj.getString(model.dbName));
                }
//                imageFoodsType.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setFoodsType ",e.getMessage());
        }
    }

    class retrieveUser extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrU = jsonparser.getJSONFromUrl(rs.hostGetUser,params);
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            setUser();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {

        }
    }
    private void setUser(){
        try {
            if(jarrU!=null){
                rs.sCboUser.clear();
                rs.sUser.clear();
                for (int i = 0; i < jarrU.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrU.get(i);
                    rs.sCboUser.add(catObj.getString(us.dbName));
                    rs.sUser.add(catObj.getString(us.dbID)+"@"+catObj.getString(us.dbLogin)+"@"+catObj.getString(us.dbName)+"@"+
                            catObj.getString(us.dbPassword1)+"@"+catObj.getString(us.dbPrivilege)+"@"+catObj.getString(us.dbRemark));
                }
                imageUser.setImageResource(R.drawable.green1);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("setUser ",e.getMessage());
        }
    }
    class chkServerReachable extends AsyncTask<String,String,String> {
        Boolean chk = false;

        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            Socket socket;
//            final String host = "10.0.1.51";
            final int port = 80;
            final int timeout = 20000;   // 10 seconds

            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(rs.hostIP, port), timeout);
                chk = true;
            } catch (UnknownHostException uhe) {
                chk = false;
                Log.e("ServerSock", "I couldn't resolve the host you've provided!");
                pd.dismiss();
                ab = "ServerSock I couldn't resolve the host you've provided!";
            } catch (SocketTimeoutException ste) {
                chk = false;
                Log.e("ServerSock", "After a reasonable amount of time, I'm not able to connect, Server is probably down!");
                pd.dismiss();
                ab = "ServerSock After a reasonable amount of time, I'm not able to connect, Server is probably down!";
            } catch (IOException ioe) {
                chk = false;
                Log.e("ServerSock", "Hmmm... Sudden disconnection, probably you should start again!");
                pd.dismiss();
                ab = "ServerSock Hmmm... Sudden disconnection, probably you should start again!";
            }
            return ab + rs.hostIP;
        }

        @Override
        protected void onPostExecute(String ab) {
//            chk=true;
            if (chk) {
                new retrieveArea().execute();
                new retrieveTable().execute();
                new retrieveRes().execute();
                new retrieveFoods().execute();
                new retrievePrinterName().execute();
                new retrieveFoodsType().execute();
                new retrieveUser().execute();
            } else {
                lbMMessage.setVisibility(View.VISIBLE);
                lbMMessage.setText(ab);
            }
            pd.dismiss();

        }

        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading information...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();

        }
    }
}
