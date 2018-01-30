package com.counterplus.ekapop.restaurant1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by ekapop on 12/29/2016.
 */

public class DatabaseSQLi extends SQLiteOpenHelper {
    private static final String DB_NAME = "restaurant";
    private static final int DB_VERSION = 1;
    private String creSQLi="";
    Database da = new Database();
    Restaurant res = new Restaurant();
    Area ar = new Area();
    Table ta = new Table();
    Foods foo = new Foods();
    FoodsType ft = new FoodsType();
    User us = new User();
    Order or = new Order();
    Bill bi = new Bill();
    BillDetail bid = new BillDetail();
    CloseDay cd = new CloseDay();
    TableChange tc = new TableChange();
    FoodsSpecific fs = new FoodsSpecific();
    FoodsCategory fc = new FoodsCategory();
    FoodsPrint fp = new FoodsPrint();

    Context c;
    SQLiteDatabase mDb;
    String hostID="";
    String gendate ="strftime('%Y-%m-%d %H:%M:%S','now')";
    String genid = "lower(hex(randomblob(16)))";

    public DatabaseSQLi(Context context, String hostID) {
        super(context, DB_NAME, null, DB_VERSION);
        c= context;
        this.hostID = hostID;
    }
    public void onCreate(SQLiteDatabase db) {
        creSQLi = "";
        createDatabase();
        String cAr="";
        String resID = UUID.randomUUID().toString();
        String typeID1 = UUID.randomUUID().toString();
        String typeID2 = UUID.randomUUID().toString();
        String arID = UUID.randomUUID().toString();
        String catID1 = UUID.randomUUID().toString();
        String catID2 = UUID.randomUUID().toString();

        cAr=da.creaT+" "+da.tbNameRes+" "
                +"( "+ar.dbID+"' "+da.tex+" PRIMARY KEY "
                +", "+ar.dbCode+"' "+da.tex+"  NULL "
                +", '"+ar.dbName+"' "+da.tex+"  NULL "
                +", '"+ar.dbRemark+"' "+da.tex+"  NULL "
                +", '"+ar.dbActive+"' "+da.tex+"  NULL "
                +", '"+ar.dbSort1+"' "+da.tex+"  NULL "
                +") ";
        try{
            Log.d("onCreate ",ar.cAreaSQLi);
            db.execSQL(ar.cDropArea);
            db.execSQL(ar.cAreaSQLi);
            db.execSQL("Insert Into "+da.tbNameArea+" ("+ar.dbID+","+ar.dbCode+", "+ar.dbName+", "+ar.dbActive+", "+ar.dbDateCreate+","+ar.dbHostId+") Values('"+arID+"','01', 'ในร้าน','1',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",ta.cTableSQLi);
            db.execSQL(ta.cDropTable);
            db.execSQL(ta.cTableSQLi);
            db.execSQL("Insert Into "+da.tbNameTable+" ("+ta.dbID+", "+ta.dbCode+", "+ta.dbName+", "+ta.dbActive+","
                    +ta.dbStatusUse+","+ta.dbAreaID+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'100', 'โต๊ะ 1','1','0','"+arID+"',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameTable+" ("+ta.dbID+", "+ta.dbCode+", "+ta.dbName+", "+ta.dbActive+","
                    +ta.dbStatusUse+","+ta.dbAreaID+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'101', 'โต๊ะ 2','1','0','"+arID+"',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",res.cResSQLi);
            db.execSQL(res.cDropRes);
            db.execSQL(res.cResSQLi);
            db.execSQL("Insert Into "+da.tbNameRes+" ("+res.dbID+", "+res.dbCode+", "+res.dbName+", "+res.dbActive+","
                    +res.dbDefaultRes+","+res.dbRH1+","+res.dbRH2+","+res.dbRF1+","
                    +res.dbRF2+","+res.dbBillCode+","+res.dbTaxID+", "+ar.dbDateCreate+","+ar.dbHostId+") Values('"+resID+"','01', 'ร้านอาหาร','1','1','','','','','','',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",us.cUserSQLi);
            db.execSQL(us.cDropUser);
            db.execSQL(us.cUserSQLi);
            db.execSQL("Insert Into "+da.tbNameUser+" ("+us.dbID+", "+us.dbLogin+", "+us.dbName+", "+us.dbActive+","
                    +us.dbPrivilege+","+us.dbVoidBill+","+us.dbVoidCloseday+","+us.dbPassword1+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'admin', 'admin','1','1','1','1','1618',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",foo.cFoodsSQLi);
            db.execSQL(foo.cDropFoods);
            db.execSQL(foo.cFoodsSQLi);
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0101', 'อาหาร1',45,'1','"+typeID1+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0102', 'อาหาร2',45,'1','"+typeID1+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0201', 'อาหาร3',50,'1','"+typeID2+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoods+" ("+foo.dbID+", "+foo.dbCode+", "+foo.dbName+", "+foo.dbPrice+", "
                    +foo.dbActive+", "+foo.dbTypeId+", "+foo.dbResId+", "+foo.dbResCode+", "
                    +foo.dbStatusFoods+", "+ar.dbDateCreate+","+ar.dbHostId+") Values(lower(hex(randomblob(16))),'0202', 'อาหาร4',50,'1','"+typeID2+"','"+resID+"','10','1',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",ft.cFoodsTypeSQLi);
            db.execSQL(ft.cDropFoodsType);
            db.execSQL(ft.cFoodsTypeSQLi);
            db.execSQL("Insert Into "+da.tbNameFoodsType+" ("+ft.dbID+", "+ft.dbCode+", "+ft.dbName+", "+ft.dbActive+", "+ft.dbDateCreate+","+ft.dbHostId+") Values('"+typeID1+"','01', 'ประเภทอาหาร1','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoodsType+" ("+ft.dbID+", "+ft.dbCode+", "+ft.dbName+", "+ft.dbActive+", "+ft.dbDateCreate+","+ft.dbHostId+") Values('"+typeID2+"','02', 'ประเภทอาหาร2','1',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",fc.cFoodsCatSQLi);
            db.execSQL(fc.cDropFoodsCat);
            db.execSQL(fc.cFoodsCatSQLi);
            db.execSQL("Insert Into "+da.tbNameFoodsCat+" ("+fc.dbID+", "+fc.dbCode+", "+fc.dbName+", "+fc.dbActive+", "+fc.dbDateCreate+","+fc.dbHostId+") Values('"+catID1+"','01', 'กลุ่มอาหาร1','1',"+ gendate +",'"+hostID+"');");
            db.execSQL("Insert Into "+da.tbNameFoodsCat+" ("+fc.dbID+", "+fc.dbCode+", "+fc.dbName+", "+fc.dbActive+", "+fc.dbDateCreate+","+fc.dbHostId+") Values('"+catID2+"','02', 'กลุ่มอาหาร2','1',"+ gendate +",'"+hostID+"');");

            Log.d("onCreate ",fp.cFoodsPrintSQLi);
            db.execSQL(fp.cDropFoodsPrint);
            db.execSQL(fp.cFoodsPrintSQLi);

            Log.d("onCreate ",fs.cFoodsSpecificSQLi);
            db.execSQL(fs.cDropFoodsSpecific);
            db.execSQL(fs.cFoodsSpecificSQLi);

            Log.d("onCreate ",bi.cBillSQLi);
            db.execSQL(bi.cDropBill);
            db.execSQL(bi.cBillSQLi);

            Log.d("onCreate ",bid.cBillDetailSQLi);
            db.execSQL(bid.cDropBillDetail);
            db.execSQL(bid.cBillDetailSQLi);

            Log.d("onCreate ",or.cOrderSQLi);
            db.execSQL(or.cDropOrder);
            db.execSQL(or.cOrderSQLi);

            Log.d("onCreate ",cd.cClosedaySQLi);
            db.execSQL(cd.cDropCloseday);
            db.execSQL(cd.cClosedaySQLi);

            Log.d("onCreate ",tc.cTableChangeSQLi);
            db.execSQL(tc.cDropTableChange);
            db.execSQL(tc.cTableChangeSQLi);
        }catch(Exception e){
            Log.e("onCreate ",e.getMessage());
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }
    public void createDatabase() {
        String url = "/data/data/" + c.getPackageName() + "/databases/"+da.dbNameD;
        File f = new File(url);
        Log.d("createDatabase ",url);
        if(!f.exists()) {
            try {
//                mHelper = new MyDbHelper(this);
                mDb = getWritableDatabase();
                mDb.close();
//                mHelper.close();
                InputStream in = c.getAssets().open(da.dbNameD);
                OutputStream out = new FileOutputStream(url);
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                out.write(buffer, 0, buffer.length);
                in.close();
                out.close();
                Log.d("createDatabase ","OK");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("createDatabase ",e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("createDatabase ",e.getMessage());
            }
        }
    }
    public JSONArray TableSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameTable+" Where "+ta.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
                jarr.put(getJsonObjectTable(c));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray TableSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameTable+" Where "+ta.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
                //Do something Here with values
                jarr.put(getJsonObjectTable(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private JSONObject getJsonObjectTable(Cursor c){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(ta.dbID, c.getString(c.getColumnIndex(ta.dbID)));
            jsonObj.put(ta.dbAreaID, chkNull(c.getString(c.getColumnIndex(ta.dbAreaID)))?"":c.getString(c.getColumnIndex(ta.dbAreaID)));
            jsonObj.put(ta.dbCode, chkNull(c.getString(c.getColumnIndex(ta.dbCode)))?"":c.getString(c.getColumnIndex(ta.dbCode)));
            jsonObj.put(ta.dbName, chkNull(c.getString(c.getColumnIndex(ta.dbName)))?"":c.getString(c.getColumnIndex(ta.dbName)));
            jsonObj.put(ta.dbActive, chkNull(c.getString(c.getColumnIndex(ta.dbActive)))?"":c.getString(c.getColumnIndex(ta.dbActive)));
            jsonObj.put(ta.dbRemark, chkNull(c.getString(c.getColumnIndex(ta.dbRemark)))?"":c.getString(c.getColumnIndex(ta.dbRemark)));
            jsonObj.put(ta.dbSort1, chkNull(c.getString(c.getColumnIndex(ta.dbSort1)))?"":c.getString(c.getColumnIndex(ta.dbSort1)));
            jsonObj.put(ta.dbDateCreate, chkNull(c.getString(c.getColumnIndex(ta.dbDateCreate)))?"":c.getString(c.getColumnIndex(ta.dbDateCreate)));
            jsonObj.put(ta.dbDateModi, chkNull(c.getString(c.getColumnIndex(ta.dbDateModi)))?"":c.getString(c.getColumnIndex(ta.dbDateModi)));
            jsonObj.put(ta.dbStatusUse, chkNull(c.getString(c.getColumnIndex(ta.dbStatusUse)))?"":c.getString(c.getColumnIndex(ta.dbStatusUse)));
            jsonObj.put(ta.dbDateUse, chkNull(c.getString(c.getColumnIndex(ta.dbDateUse)))?"":c.getString(c.getColumnIndex(ta.dbDateUse)));
            jsonObj.put(ta.dbHostId, chkNull(c.getString(c.getColumnIndex(ta.dbHostId)))?"":c.getString(c.getColumnIndex(ta.dbHostId)));
            jsonObj.put(ta.dbBranchId, chkNull(c.getString(c.getColumnIndex(ta.dbBranchId)))?"":c.getString(c.getColumnIndex(ta.dbBranchId)));
            jsonObj.put(ta.dbDeviceId, chkNull(c.getString(c.getColumnIndex(ta.dbDeviceId)))?"":c.getString(c.getColumnIndex(ta.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TableSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("TableSelectAll ",e.getMessage());
        }
        return jsonObj;
    }
    private JSONObject getJsonObjectTableChange(Cursor c){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(tc.dbID, c.getString(c.getColumnIndex(tc.dbID)));
            jsonObj.put(tc.dbTableIdFrom, chkNull(c.getString(c.getColumnIndex(tc.dbTableIdFrom)))?"":c.getString(c.getColumnIndex(tc.dbTableIdFrom)));
            jsonObj.put(tc.dbTableIdTo, chkNull(c.getString(c.getColumnIndex(tc.dbTableIdTo)))?"":c.getString(c.getColumnIndex(tc.dbTableIdTo)));
            jsonObj.put(tc.dbTableChangeDate, chkNull(c.getString(c.getColumnIndex(tc.dbTableChangeDate)))?"":c.getString(c.getColumnIndex(tc.dbTableChangeDate)));
            jsonObj.put(tc.dbTableChangeUser, chkNull(c.getString(c.getColumnIndex(tc.dbTableChangeUser)))?"":c.getString(c.getColumnIndex(tc.dbTableChangeUser)));
            jsonObj.put(tc.dbActive, chkNull(c.getString(c.getColumnIndex(tc.dbActive)))?"":c.getString(c.getColumnIndex(tc.dbActive)));
            jsonObj.put(tc.dbDateCreate, chkNull(c.getString(c.getColumnIndex(tc.dbDateCreate)))?"":c.getString(c.getColumnIndex(tc.dbDateCreate)));
            jsonObj.put(tc.dbDateModi, chkNull(c.getString(c.getColumnIndex(tc.dbDateModi)))?"":c.getString(c.getColumnIndex(tc.dbDateModi)));
            jsonObj.put(tc.dbHostId, chkNull(c.getString(c.getColumnIndex(tc.dbHostId)))?"":c.getString(c.getColumnIndex(tc.dbHostId)));
            jsonObj.put(tc.dbBranchId, chkNull(c.getString(c.getColumnIndex(tc.dbBranchId)))?"":c.getString(c.getColumnIndex(tc.dbBranchId)));
            jsonObj.put(tc.dbDeviceId, chkNull(c.getString(c.getColumnIndex(tc.dbDeviceId)))?"":c.getString(c.getColumnIndex(tc.dbDeviceId)));
            jsonObj.put(tc.dbVoidDate, chkNull(c.getString(c.getColumnIndex(tc.dbVoidDate)))?"":c.getString(c.getColumnIndex(tc.dbVoidDate)));
            jsonObj.put(tc.dbVoidUser, chkNull(c.getString(c.getColumnIndex(tc.dbVoidUser)))?"":c.getString(c.getColumnIndex(tc.dbVoidUser)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TableSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("TableSelectAll ",e.getMessage());
        }
        return jsonObj;
    }
    public JSONArray TableChangeById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameTableChange+" Where "+tc.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
                jarr.put(getJsonObjectTableChange(c));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }

    public JSONArray AreaSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameArea+" Where "+ar.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectArea(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectArea(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private JSONObject getJsonObjectArea(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(ar.dbID, c.getString(c.getColumnIndex(ar.dbID)));
            jsonObj.put(ar.dbCode, chkNull(c.getString(c.getColumnIndex(ar.dbCode)))?"":c.getString(c.getColumnIndex(ar.dbCode)));
            jsonObj.put(ar.dbName, chkNull(c.getString(c.getColumnIndex(ar.dbName)))?"":c.getString(c.getColumnIndex(ar.dbName)));
            jsonObj.put(ar.dbActive, chkNull(c.getString(c.getColumnIndex(ar.dbActive)))?"":c.getString(c.getColumnIndex(ar.dbActive)));
            jsonObj.put(ar.dbRemark, chkNull(c.getString(c.getColumnIndex(ar.dbRemark)))?"":c.getString(c.getColumnIndex(ar.dbRemark)));
            jsonObj.put(ar.dbSort1, chkNull(c.getString(c.getColumnIndex(ar.dbSort1)))?"":c.getString(c.getColumnIndex(ar.dbSort1)));
            jsonObj.put(ar.dbDateCreate, chkNull(c.getString(c.getColumnIndex(ar.dbDateCreate)))?"":c.getString(c.getColumnIndex(ar.dbDateCreate)));
            jsonObj.put(ar.dbDateModi, chkNull(c.getString(c.getColumnIndex(ar.dbDateModi)))?"":c.getString(c.getColumnIndex(ar.dbDateModi)));
            jsonObj.put(ar.dbHostId, chkNull(c.getString(c.getColumnIndex(ar.dbHostId)))?"":c.getString(c.getColumnIndex(ar.dbHostId)));
            jsonObj.put(ar.dbBranchId, chkNull(c.getString(c.getColumnIndex(ar.dbBranchId)))?"":c.getString(c.getColumnIndex(ar.dbBranchId)));
            jsonObj.put(ar.dbDeviceId, chkNull(c.getString(c.getColumnIndex(ar.dbDeviceId)))?"":c.getString(c.getColumnIndex(ar.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("AreaSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("AreaSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray AreaSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameArea+" Where "+ar.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = ;
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectArea(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray ResSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameRes+" Where "+res.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectRes(c);
                jarr.put(getJsonObjectRes(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }


    private JSONObject getJsonObjectRes(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(res.dbID, c.getString(c.getColumnIndex(res.dbID)));
            jsonObj.put(res.dbCode, chkNull(c.getString(c.getColumnIndex(res.dbCode)))?"":c.getString(c.getColumnIndex(res.dbCode)));
            jsonObj.put(res.dbName, chkNull(c.getString(c.getColumnIndex(res.dbName)))?"":c.getString(c.getColumnIndex(res.dbName)));
            jsonObj.put(res.dbActive, chkNull(c.getString(c.getColumnIndex(res.dbActive)))?"":c.getString(c.getColumnIndex(res.dbActive)));
            jsonObj.put(res.dbRemark, chkNull(c.getString(c.getColumnIndex(res.dbRemark)))?"":c.getString(c.getColumnIndex(res.dbRemark)));
            jsonObj.put(res.dbDefaultRes, chkNull(c.getString(c.getColumnIndex(res.dbDefaultRes)))?"":c.getString(c.getColumnIndex(res.dbDefaultRes)));
            jsonObj.put(res.dbRF1, chkNull(c.getString(c.getColumnIndex(res.dbRF1)))?"":c.getString(c.getColumnIndex(res.dbRF1)));
            jsonObj.put(res.dbRF2, chkNull(c.getString(c.getColumnIndex(res.dbRF2)))?"":c.getString(c.getColumnIndex(res.dbRF2)));
            jsonObj.put(res.dbRH1, chkNull(c.getString(c.getColumnIndex(res.dbRH1)))?"":c.getString(c.getColumnIndex(res.dbRH1)));
            jsonObj.put(res.dbRH2, chkNull(c.getString(c.getColumnIndex(res.dbRH2)))?"":c.getString(c.getColumnIndex(res.dbRH2)));
            jsonObj.put(res.dbBillCode, chkNull(c.getString(c.getColumnIndex(res.dbBillCode)))?"":c.getString(c.getColumnIndex(res.dbBillCode)));
            jsonObj.put(res.dbTaxID, chkNull(c.getString(c.getColumnIndex(res.dbTaxID)))?"":c.getString(c.getColumnIndex(res.dbTaxID)));
            jsonObj.put(res.dbPrinterOrder1, chkNull(c.getString(c.getColumnIndex(res.dbPrinterOrder1)))?"":c.getString(c.getColumnIndex(res.dbPrinterOrder1)));
            jsonObj.put(res.dbPrinterOrder2, chkNull(c.getString(c.getColumnIndex(res.dbPrinterOrder2)))?"":c.getString(c.getColumnIndex(res.dbPrinterOrder2)));
            jsonObj.put(res.dbPrinterOrder3, chkNull(c.getString(c.getColumnIndex(res.dbPrinterOrder3)))?"":c.getString(c.getColumnIndex(res.dbPrinterOrder3)));
            jsonObj.put(res.dbPrinterWaterBar1, chkNull(c.getString(c.getColumnIndex(res.dbPrinterWaterBar1)))?"":c.getString(c.getColumnIndex(res.dbPrinterWaterBar1)));
            jsonObj.put(res.dbPrinterWaterBar2, chkNull(c.getString(c.getColumnIndex(res.dbPrinterWaterBar2)))?"":c.getString(c.getColumnIndex(res.dbPrinterWaterBar2)));
            jsonObj.put(res.dbPrinterWaterBar3, chkNull(c.getString(c.getColumnIndex(res.dbPrinterWaterBar3)))?"":c.getString(c.getColumnIndex(res.dbPrinterWaterBar3)));
            jsonObj.put(res.dbSort1, chkNull(c.getString(c.getColumnIndex(res.dbSort1)))?"":c.getString(c.getColumnIndex(res.dbSort1)));
            jsonObj.put(res.dbDateCreate, chkNull(c.getString(c.getColumnIndex(res.dbDateCreate)))?"":c.getString(c.getColumnIndex(res.dbDateCreate)));
            jsonObj.put(res.dbDateModi, chkNull(c.getString(c.getColumnIndex(res.dbDateModi)))?"":c.getString(c.getColumnIndex(res.dbDateModi)));
            jsonObj.put(res.dbHostId, chkNull(c.getString(c.getColumnIndex(res.dbHostId)))?"":c.getString(c.getColumnIndex(res.dbHostId)));
            jsonObj.put(res.dbBranchId, chkNull(c.getString(c.getColumnIndex(res.dbBranchId)))?"":c.getString(c.getColumnIndex(res.dbBranchId)));
            jsonObj.put(res.dbDeviceId, chkNull(c.getString(c.getColumnIndex(res.dbDeviceId)))?"":c.getString(c.getColumnIndex(res.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ResSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("ResSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray ResSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameRes+" Where "+res.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectRes(c);
                jarr.put(getJsonObjectRes(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray UserSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameUser+" Where "+us.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectUser(c);
                jarr.put(getJsonObjectUser(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }


    private JSONObject getJsonObjectUser(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(us.dbID, c.getString(c.getColumnIndex(us.dbID)));
            jsonObj.put(us.dbLogin, chkNull(c.getString(c.getColumnIndex(us.dbLogin)))?"":c.getString(c.getColumnIndex(us.dbLogin)));
            jsonObj.put(us.dbName, chkNull(c.getString(c.getColumnIndex(us.dbName)))?"":c.getString(c.getColumnIndex(us.dbName)));
            jsonObj.put(us.dbActive, chkNull(c.getString(c.getColumnIndex(us.dbActive)))?"":c.getString(c.getColumnIndex(us.dbActive)));
            jsonObj.put(us.dbPassword1, chkNull(c.getString(c.getColumnIndex(us.dbPassword1)))?"":c.getString(c.getColumnIndex(us.dbPassword1)));
            jsonObj.put(us.dbPrivilege, chkNull(c.getString(c.getColumnIndex(us.dbPrivilege)))?"":c.getString(c.getColumnIndex(us.dbPrivilege)));
            jsonObj.put(us.dbRemark, chkNull(c.getString(c.getColumnIndex(us.dbRemark)))?"":c.getString(c.getColumnIndex(us.dbRemark)));
            jsonObj.put(us.dbSort1, chkNull(c.getString(c.getColumnIndex(us.dbSort1)))?"":c.getString(c.getColumnIndex(us.dbSort1)));
            jsonObj.put(us.dbVoidBill, chkNull(c.getString(c.getColumnIndex(us.dbVoidBill)))?"":c.getString(c.getColumnIndex(us.dbVoidBill)));
            jsonObj.put(us.dbVoidCloseday, chkNull(c.getString(c.getColumnIndex(us.dbVoidCloseday)))?"":c.getString(c.getColumnIndex(us.dbVoidCloseday)));
            jsonObj.put(us.dbDateCreate, chkNull(c.getString(c.getColumnIndex(us.dbDateCreate)))?"":c.getString(c.getColumnIndex(us.dbDateCreate)));
            jsonObj.put(us.dbDateModi, chkNull(c.getString(c.getColumnIndex(us.dbDateModi)))?"":c.getString(c.getColumnIndex(us.dbDateModi)));
            jsonObj.put(us.dbHostId, chkNull(c.getString(c.getColumnIndex(us.dbHostId)))?"":c.getString(c.getColumnIndex(us.dbHostId)));
            jsonObj.put(us.dbBranchId, chkNull(c.getString(c.getColumnIndex(us.dbBranchId)))?"":c.getString(c.getColumnIndex(us.dbBranchId)));
            jsonObj.put(us.dbDeviceId, chkNull(c.getString(c.getColumnIndex(us.dbDeviceId)))?"":c.getString(c.getColumnIndex(us.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ResSelectAll ",e.getMessage());
        }catch (Exception e){
            Log.e("ResSelectAll ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray UserSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameUser+" Where "+us.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectUser(c);
                jarr.put(getJsonObjectUser(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsSpecificSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsSpecific+" Where "+fs.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsSpecific(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsTypeSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsType+" Where "+ft.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsType(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsCatSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsCat+" Where "+fc.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsCat(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsPrintSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsPrint+" Where "+fp.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsPrint(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private JSONObject getJsonObjectFoodsSpecific(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(fs.dbID, c.getString(c.getColumnIndex(fs.dbID)));
            jsonObj.put(fs.dbCode, chkNull(c.getString(c.getColumnIndex(fs.dbCode)))?"":c.getString(c.getColumnIndex(fs.dbCode)));
            jsonObj.put(fs.dbName, chkNull(c.getString(c.getColumnIndex(fs.dbName)))?"":c.getString(c.getColumnIndex(fs.dbName)));
            jsonObj.put(fs.dbActive, chkNull(c.getString(c.getColumnIndex(fs.dbActive)))?"":c.getString(c.getColumnIndex(fs.dbActive)));
            jsonObj.put(fs.dbFoodsCode, chkNull(c.getString(c.getColumnIndex(fs.dbFoodsCode)))?"":c.getString(c.getColumnIndex(fs.dbFoodsCode)));
            jsonObj.put(fs.dbSort1, chkNull(c.getString(c.getColumnIndex(fs.dbSort1)))?"":c.getString(c.getColumnIndex(fs.dbSort1)));
            jsonObj.put(fs.dbDateCreate, chkNull(c.getString(c.getColumnIndex(fs.dbDateCreate)))?"":c.getString(c.getColumnIndex(fs.dbDateCreate)));
            jsonObj.put(fs.dbDateModi, chkNull(c.getString(c.getColumnIndex(fs.dbDateModi)))?"":c.getString(c.getColumnIndex(fs.dbDateModi)));
            jsonObj.put(fs.dbHostId, chkNull(c.getString(c.getColumnIndex(fs.dbHostId)))?"":c.getString(c.getColumnIndex(fs.dbHostId)));
            jsonObj.put(fs.dbBranchId, chkNull(c.getString(c.getColumnIndex(fs.dbBranchId)))?"":c.getString(c.getColumnIndex(fs.dbBranchId)));
            jsonObj.put(fs.dbDeviceId, chkNull(c.getString(c.getColumnIndex(fs.dbDeviceId)))?"":c.getString(c.getColumnIndex(fs.dbDeviceId)));
//            jsonObj.put(fs.dbIP, chkNull(c.getString(c.getColumnIndex(fp.dbIP)))?"":c.getString(c.getColumnIndex(fp.dbIP)));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectFoodsCat ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectFoodsCat ",e.getMessage());
        }
        return jsonObj;
    }
    private JSONObject getJsonObjectFoodsPrint(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(fp.dbID, c.getString(c.getColumnIndex(fp.dbID)));
            jsonObj.put(fp.dbCode, chkNull(c.getString(c.getColumnIndex(fp.dbCode)))?"":c.getString(c.getColumnIndex(fp.dbCode)));
            jsonObj.put(fp.dbName, chkNull(c.getString(c.getColumnIndex(fp.dbName)))?"":c.getString(c.getColumnIndex(fp.dbName)));
            jsonObj.put(fp.dbActive, chkNull(c.getString(c.getColumnIndex(fp.dbActive)))?"":c.getString(c.getColumnIndex(fp.dbActive)));
            jsonObj.put(fp.dbRemark, chkNull(c.getString(c.getColumnIndex(fp.dbRemark)))?"":c.getString(c.getColumnIndex(fp.dbRemark)));
            jsonObj.put(fp.dbSort1, chkNull(c.getString(c.getColumnIndex(fp.dbSort1)))?"":c.getString(c.getColumnIndex(fp.dbSort1)));
            jsonObj.put(fp.dbDateCreate, chkNull(c.getString(c.getColumnIndex(fp.dbDateCreate)))?"":c.getString(c.getColumnIndex(fp.dbDateCreate)));
            jsonObj.put(fp.dbDateModi, chkNull(c.getString(c.getColumnIndex(fp.dbDateModi)))?"":c.getString(c.getColumnIndex(fp.dbDateModi)));
            jsonObj.put(fp.dbHostId, chkNull(c.getString(c.getColumnIndex(fp.dbHostId)))?"":c.getString(c.getColumnIndex(fp.dbHostId)));
            jsonObj.put(fp.dbBranchId, chkNull(c.getString(c.getColumnIndex(fp.dbBranchId)))?"":c.getString(c.getColumnIndex(fp.dbBranchId)));
            jsonObj.put(fp.dbDeviceId, chkNull(c.getString(c.getColumnIndex(fp.dbDeviceId)))?"":c.getString(c.getColumnIndex(fp.dbDeviceId)));
            jsonObj.put(fp.dbIP, chkNull(c.getString(c.getColumnIndex(fp.dbIP)))?"":c.getString(c.getColumnIndex(fp.dbIP)));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectFoodsCat ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectFoodsCat ",e.getMessage());
        }
        return jsonObj;
    }
    private JSONObject getJsonObjectFoodsCat(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(fc.dbID, c.getString(c.getColumnIndex(fc.dbID)));
            jsonObj.put(fc.dbCode, chkNull(c.getString(c.getColumnIndex(fc.dbCode)))?"":c.getString(c.getColumnIndex(fc.dbCode)));
            jsonObj.put(fc.dbName, chkNull(c.getString(c.getColumnIndex(fc.dbName)))?"":c.getString(c.getColumnIndex(fc.dbName)));
            jsonObj.put(fc.dbActive, chkNull(c.getString(c.getColumnIndex(fc.dbActive)))?"":c.getString(c.getColumnIndex(fc.dbActive)));
            jsonObj.put(fc.dbRemark, chkNull(c.getString(c.getColumnIndex(fc.dbRemark)))?"":c.getString(c.getColumnIndex(fc.dbRemark)));
            jsonObj.put(fc.dbSort1, chkNull(c.getString(c.getColumnIndex(fc.dbSort1)))?"":c.getString(c.getColumnIndex(fc.dbSort1)));
            jsonObj.put(fc.dbDateCreate, chkNull(c.getString(c.getColumnIndex(fc.dbDateCreate)))?"":c.getString(c.getColumnIndex(fc.dbDateCreate)));
            jsonObj.put(fc.dbDateModi, chkNull(c.getString(c.getColumnIndex(fc.dbDateModi)))?"":c.getString(c.getColumnIndex(fc.dbDateModi)));
            jsonObj.put(fc.dbHostId, chkNull(c.getString(c.getColumnIndex(fc.dbHostId)))?"":c.getString(c.getColumnIndex(fc.dbHostId)));
            jsonObj.put(fc.dbBranchId, chkNull(c.getString(c.getColumnIndex(fc.dbBranchId)))?"":c.getString(c.getColumnIndex(fc.dbBranchId)));
            jsonObj.put(fc.dbDeviceId, chkNull(c.getString(c.getColumnIndex(fc.dbDeviceId)))?"":c.getString(c.getColumnIndex(fc.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectFoodsCat ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectFoodsCat ",e.getMessage());
        }
        return jsonObj;
    }

    private JSONObject getJsonObjectFoodsType(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(ft.dbID, c.getString(c.getColumnIndex(ft.dbID)));
            jsonObj.put(ft.dbCode, chkNull(c.getString(c.getColumnIndex(ft.dbCode)))?"":c.getString(c.getColumnIndex(ft.dbCode)));
            jsonObj.put(ft.dbName, chkNull(c.getString(c.getColumnIndex(ft.dbName)))?"":c.getString(c.getColumnIndex(ft.dbName)));
            jsonObj.put(ft.dbActive, chkNull(c.getString(c.getColumnIndex(ft.dbActive)))?"":c.getString(c.getColumnIndex(ft.dbActive)));
            jsonObj.put(ft.dbRemark, chkNull(c.getString(c.getColumnIndex(ft.dbRemark)))?"":c.getString(c.getColumnIndex(ft.dbRemark)));
            jsonObj.put(ft.dbSort1, chkNull(c.getString(c.getColumnIndex(ft.dbSort1)))?"":c.getString(c.getColumnIndex(ft.dbSort1)));
            jsonObj.put(ft.dbDateCreate, chkNull(c.getString(c.getColumnIndex(ft.dbDateCreate)))?"":c.getString(c.getColumnIndex(ft.dbDateCreate)));
            jsonObj.put(ft.dbDateModi, chkNull(c.getString(c.getColumnIndex(ft.dbDateModi)))?"":c.getString(c.getColumnIndex(ft.dbDateModi)));
            jsonObj.put(ft.dbHostId, chkNull(c.getString(c.getColumnIndex(ft.dbHostId)))?"":c.getString(c.getColumnIndex(ft.dbHostId)));
            jsonObj.put(ft.dbBranchId, chkNull(c.getString(c.getColumnIndex(ft.dbBranchId)))?"":c.getString(c.getColumnIndex(ft.dbBranchId)));
            jsonObj.put(ft.dbDeviceId, chkNull(c.getString(c.getColumnIndex(ft.dbDeviceId)))?"":c.getString(c.getColumnIndex(ft.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectFoodsType ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectFoodsType ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray FoodsPrintSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsPrint+" Where "+fp.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsPrint(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsCategorySelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsCat+" Where "+fc.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsCat(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsTypeSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoodsType+" Where "+ft.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoodsType(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoodsType(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    public JSONArray FoodsSelectAll(){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoods+" Where "+foo.dbActive+" = '1' ", null);
        if(c.moveToFirst()){
            do{
//                JSONObject jsonObj = getJsonObjectFoods(c);
                //assing values
//                String column1 = c.getString(0);
//                String column2 = c.getString(1);
//                String column3 = c.getString(2);
                //Do something Here with values
                jarr.put(getJsonObjectFoods(c));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }


    private JSONObject getJsonObjectFoods(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(foo.dbID, c.getString(c.getColumnIndex(foo.dbID)));
            jsonObj.put(foo.dbCode, chkNull(c.getString(c.getColumnIndex(foo.dbCode)))?"":c.getString(c.getColumnIndex(foo.dbCode)));
            jsonObj.put(foo.dbName, chkNull(c.getString(c.getColumnIndex(foo.dbName)))?"":c.getString(c.getColumnIndex(foo.dbName)));
            jsonObj.put(foo.dbPrice, c.getDouble(c.getColumnIndex(foo.dbPrice)));
            jsonObj.put(foo.dbActive, chkNull(c.getString(c.getColumnIndex(foo.dbActive)))?"":c.getString(c.getColumnIndex(foo.dbActive)));
            jsonObj.put(foo.dbTypeId, chkNull(c.getString(c.getColumnIndex(foo.dbTypeId)))?"":c.getString(c.getColumnIndex(foo.dbTypeId)));
            jsonObj.put(foo.dbRemark, chkNull(c.getString(c.getColumnIndex(foo.dbRemark)))?"":c.getString(c.getColumnIndex(foo.dbRemark)));
            jsonObj.put(foo.dbResId, chkNull(c.getString(c.getColumnIndex(foo.dbResId)))?"":c.getString(c.getColumnIndex(foo.dbResId)));
            jsonObj.put(foo.dbResCode, chkNull(c.getString(c.getColumnIndex(foo.dbResCode)))?"":c.getString(c.getColumnIndex(foo.dbResCode)));
            jsonObj.put(foo.dbStatusFoods, chkNull(c.getString(c.getColumnIndex(foo.dbStatusFoods)))?"":c.getString(c.getColumnIndex(foo.dbStatusFoods)));
            jsonObj.put(foo.dbPrinterName, chkNull(c.getString(c.getColumnIndex(foo.dbPrinterName)))?"":c.getString(c.getColumnIndex(foo.dbPrinterName)));
            jsonObj.put(foo.dbSort1, chkNull(c.getString(c.getColumnIndex(foo.dbSort1)))?"":c.getString(c.getColumnIndex(foo.dbSort1)));
            jsonObj.put(foo.dbDateCreate, chkNull(c.getString(c.getColumnIndex(foo.dbDateCreate)))?"":c.getString(c.getColumnIndex(foo.dbDateCreate)));
            jsonObj.put(foo.dbDateModi, chkNull(c.getString(c.getColumnIndex(foo.dbDateModi)))?"":c.getString(c.getColumnIndex(foo.dbDateModi)));
            jsonObj.put(foo.dbHostId, chkNull(c.getString(c.getColumnIndex(foo.dbHostId)))?"":c.getString(c.getColumnIndex(foo.dbHostId)));
            jsonObj.put(foo.dbBranchId, chkNull(c.getString(c.getColumnIndex(foo.dbBranchId)))?"":c.getString(c.getColumnIndex(foo.dbBranchId)));
            jsonObj.put(foo.dbDeviceId, chkNull(c.getString(c.getColumnIndex(foo.dbDeviceId)))?"":c.getString(c.getColumnIndex(foo.dbDeviceId)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectFoods ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectFoods ",e.getMessage());
        }
        return jsonObj;
    }

    public JSONArray FoodsSelectById(String id){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * From "+da.tbNameFoods+" Where "+foo.dbID+" = '"+id+"' ", null);
        if(c.moveToFirst()){
            do{
                jarr.put(getJsonObjectFoods(c));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private Boolean chkNull(String c){
        if(c==null){
            return true;
        }else{
            return false;
        }
    }
    public JSONArray OrderInsert(String row1,String lotID,String areacode,String tablecode,String Qty,String FoodsCode,
                        String FoodsName,String Remark,String ResCode,String Price,String PrinterName,
                         String FoodsId,String StatusToGo,String user,String tableid,String CntCust, String imei,String hostid){
        String sql="",err="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try {//strftime('%Y-%m-%d %H:%M:%S','now')
            sql = "Insert into "+da.tbNameOrder+"("+or.dbID+","+or.dbFoodsCode+","+or.dbOrderDate+","+or.dbPrice+","+or.dbQty+","+or.dbLotId+","+or.dbResCode+","+or.dbTableCode+","
                    +or.dbAreaCode+","+or.dbStatusFoods1+","+or.dbStatusFoods2+","+or.dbStatusFoods3+","+or.dbActive+","+or.dbStatusVoid+","+or.dbStatusBill+","+or.dbStatusCook+","+or.dbDateCreate+","
                    +or.dbPrinterName+","+or.dbRemark+","+or.dbrow1+","+or.dbFoodsId+","+or.dbFoodsName+","+or.dbStatusToGo+","
                    +or.dbOrderUser+","+or.dbStatusCloseday+","+or.dbClosedayId+","+or.dbCntCust+","+or.dbBillId+","+or.dbHostId+","+or.dbDeviceId+")"
                    +" Values("+genid+",'"+FoodsCode+"',"+gendate+",'"+Price+"','"+Qty+"','"+lotID+"','"+ResCode+"','"+tablecode+"','"
                    +areacode+"','','','','1','0','0','0',"+gendate+",'"
                    +PrinterName+"','"+Remark+"','"+row1+"','"+FoodsId+"','"+FoodsName+"','"+StatusToGo+"','"
                    +user+"','0','','"+CntCust+"','','"+hostid+"','"+imei+"')";
            db.execSQL(sql);
            sql = "Update "+da.tbNameTable+" Set "+ta.dbStatusUse+" ='1' Where "+ta.dbID+" ='"+tableid+"'";
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("OrderInsert ",e.getMessage());
            err = e.getMessage();
        }

        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Order success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("OrderInsert ",e.getMessage());
        }
        return  jarr;
    }
//    public JSONArray TableChangeInsert(String id, String from, String to, String tadate, String tauser, String hostid,String deviceid){
//        String sql="",err="", code1="";
//        JSONArray jarr = new JSONArray();
//        JSONObject jsonObj = new JSONObject();
//        SQLiteDatabase db = this.getReadableDatabase();
//        try{
//            int cnt=0;
//            Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameTableChange+" ", null);
//            if(c.moveToFirst()){
//                do{
//                    Log.d("AreaInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
//                    cnt = c.getInt(c.getColumnIndex("cnt"))+1;
//                    code1 = "00"+cnt;
//                    code1 = code1.substring(code1.length()-2);
//                    Log.d("AreaInsert code1 ",code1);
//                }while(c.moveToNext());
//            }
//            c.close();
////                db.close();
//            sql ="Insert Into "+da.tbNameTableChange+"("+tc.dbID+","+tc.dbTableIdFrom+","+tc.dbTableIdTo+","+tc.dbTableChangeDate+","
//                    +tc.dbTableChangeUser+","+tc.dbHostId+","+tc.dbDeviceId+","+tc.dbDateCreate+","+tc.dbActive+") "
//                    +"Values ("+this.genid +",'"+from+"','"+to+"','"+tadate+"','"
//                    +tauser+"',"+ hostid +",'"+deviceid+"','"+gendate+"','1')";
//            db.execSQL(sql);
//        }catch (Exception e){
//            Log.e("TableChangeInsert 1 ",e.getMessage());
//            err = e.getMessage();
//        }
//        db.close();
//        try{
//            jsonObj = new JSONObject();
//            jsonObj.put("success", "1");
//            jsonObj.put("message", "insert TableChange success");
//            jsonObj.put("sql", sql);
//            jsonObj.put("error", err);
//            jarr.put(jsonObj);
//        }catch (JSONException e) {
//            Log.e("TableChangeInsert 2 ",e.getMessage());
//        }
//        return  jarr;
//    }
    public JSONArray AreaInsert(String id, String code, String name, String remark, String sort1){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameArea+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("AreaInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("AreaInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameArea+"("+ar.dbID+","+ar.dbCode+","+ar.dbName+","+ar.dbRemark+","+ar.dbSort1+","+ar.dbDateCreate+","+ar.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameArea +" "
                +"Set "+ar.dbCode+"='"+code+"' "
                +","+ar.dbName+"='"+name+"' "
                +","+ar.dbRemark+"='"+remark+"' "
                +","+ar.dbSort1+"='"+sort1+"' "
                +" Where "+ar.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("AreaInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Area success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("AreaInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray FoodsInsert(String id, String code, String name, String price, String typeid, String remark
            , String resid, String rescode, String statusfoods, String printname, String sort1, String hostid, String branchid, String catid){
        String sql="",err="", code1="",foodsCode="", cnt="",id1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                sql = "Select bft.foods_type_code, count(1) as cnt From b_foods bf inner join b_foods_type bft on bf.foods_type_id = bft.foods_type_id Where bf.foods_type_id = '"+typeid+"'";
                Cursor c = db.rawQuery(sql, null);
                if(c.moveToFirst()){
                    do{
                        Log.d("FoodsInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        foodsCode = c.getString(c.getColumnIndex("foods_type_code"));
                        cnt = String.valueOf(c.getInt(c.getColumnIndex("cnt"))+1);
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("FoodsInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
                foodsCode=foodsCode+code1;
                sql = "Insert into "+foo.tbNameFoods+"("+foo.dbID+","+foo.dbCode+","+foo.dbName+","+foo.dbActive+","+foo.dbTypeId+","+foo.dbRemark+","+foo.dbResId+","+foo.dbStatusFoods+","
                    +foo.dbPrinterName+","+foo.dbResCode+","+foo.dbPrice+","+foo.dbCatId+","+foo.dbDateCreate+")"
                    +" Values ("+genid+",'"+code+"','"+foodsCode+"','1','"+typeid+"','"+remark+"','"
                    +resid+"','"+statusfoods+"','"+printname+"','"+rescode+"',"+price+"',"+catid+","+gendate+")";
            }else{
                foodsCode = code;
                sql="Update "+foo.tbNameFoods+" "
                +"Set "+foo.dbCode+" ='"+code+"'"
                +","+foo.dbName+"='"+name+"'"
                +","+foo.dbTypeId+"='"+typeid+"'"
                +","+foo.dbRemark+"='"+remark+"'"
                +","+foo.dbResId+"='"+resid+"'"
                +","+foo.dbStatusFoods+"='"+statusfoods+"'"
                +","+foo.dbPrinterName+"='"+printname+"'"
                +","+foo.dbResCode+"='"+rescode+"'"
                +","+foo.dbPrice+"="+price+" "
                +","+foo.dbCatId+"="+catid+" "
                +"Where "+foo.dbID+"='"+id+"'";
            }
            db.execSQL(sql);

            Cursor c = db.rawQuery("Select "+foo.dbID+" From "+da.tbNameFoods+" Where "+foo.dbCode+"='"+foodsCode+"'", null);
            if(c.moveToFirst()){
                do{
//                    Log.d("FoodsInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                    id1 = c.getString(c.getColumnIndex(foo.dbID));
//                    Log.d("FoodsInsert code1 ",code1);
                }while(c.moveToNext());
            }
            c.close();
            db.close();
            Log.d("FoodsInsert ","OK");
        }catch (Exception e){
            Log.e("FoodsInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Foods success");
            jsonObj.put("foods_id",id1);
            jsonObj.put("foods_code",foodsCode);
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray FoodsTypeInsert(String id, String code, String name, String remark, String sort1 ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameFoodsType+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("FTInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("FoodsTypeInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameFoodsType+"("+ft.dbID+","+ft.dbCode+","+ft.dbName+","+ft.dbRemark+","+ft.dbSort1+","+ft.dbDateCreate+","+ft.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameFoodsType +" "
                        +"Set "+ft.dbCode+"='"+code+"' "
                        +","+ft.dbName+"='"+name+"' "
                        +","+ft.dbRemark+"='"+remark+"' "
                        +","+ft.dbSort1+"='"+sort1+"' "
                        +" Where "+ft.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("FoodsTypeInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert FoodsType success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsTypeInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray FoodsCategoryInsert(String id, String code, String name, String remark, String sort1 ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameFoodsCat+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("FTInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("FoodsTypeInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameFoodsCat+"("+fc.dbID+","+fc.dbCode+","+fc.dbName+","+fc.dbRemark+","+fc.dbSort1+","+fc.dbDateCreate+","+fc.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameFoodsCat +" "
                        +"Set "+fc.dbCode+"='"+code+"' "
                        +","+fc.dbName+"='"+name+"' "
                        +","+fc.dbRemark+"='"+remark+"' "
                        +","+fc.dbSort1+"='"+sort1+"' "
                        +" Where "+fc.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("FoodsCategoryInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert FoodsCategory success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsCategoryInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray FoodsPrintInsert(String id, String code, String name, String remark, String sort1, String ip ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameFoodsPrint+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("FTInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("FoodsPrintInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameFoodsPrint+"("+fp.dbID+","+fp.dbCode+","+fp.dbName+","+fp.dbRemark+","+fp.dbSort1+","+fp.dbDateCreate+","+fp.dbActive+","+fp.dbIP+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1','"+ip+"')";
            }else{
                sql="Update "+da.tbNameFoodsPrint +" "
                        +"Set "+fp.dbCode+"='"+code+"' "
                        +","+fp.dbName+"='"+name+"' "
                        +","+fp.dbRemark+"='"+remark+"' "
                        +","+fp.dbSort1+"='"+sort1+"' "
                        +","+fp.dbIP+"='"+ip+"' "
                        +" Where "+fp.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("FoodsPrintInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert FoodsCategory success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsCategoryInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray FoodsSpecificInsert(String id, String code, String name, String foodscode, String sort1 ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameFoodsSpecific+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("FTInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("FoodsTypeInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameFoodsSpecific+"("+fs.dbID+","+fs.dbCode+","+fs.dbName+","+fs.dbFoodsCode+","+fs.dbSort1+","+fs.dbDateCreate+","+fs.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+foodscode+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameFoodsSpecific +" "
                        +"Set "+fs.dbCode+"='"+code+"' "
                        +","+fs.dbName+"='"+name+"' "
                        +","+fs.dbFoodsCode+"='"+foodscode+"' "
                        +","+fs.dbSort1+"='"+sort1+"' "
                        +" Where "+fs.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("FoodsSpecificInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert FoodsSpecific success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsSpecificInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray ResInsert(String id, String code, String name, String remark, String sort1 ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameRes+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("ResInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("ResInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameRes+"("+res.dbID+","+res.dbCode+","+res.dbName+","+res.dbRemark+","+res.dbSort1+","+res.dbDateCreate+","+res.dbActive+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"+sort1+"',"+ gendate +",'1')";
            }else{
                sql="Update "+da.tbNameRes +" "
                        +"Set "+res.dbCode+"='"+code+"' "
                        +","+res.dbName+"='"+name+"' "
                        +","+res.dbRemark+"='"+remark+"' "
                        +","+res.dbSort1+"='"+sort1+"' "
                        +" Where "+res.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("ResInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Res success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("ResInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray TableInsert(String id, String code, String name,String arid, String remark, String sort1, String statususe, String statustogo, String dateuse ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameTable+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("TableInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "00"+cnt;
                        code1 = code1.substring(code1.length()-2);
                        Log.d("TableInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameTable+"("+ta.dbID+","+ta.dbCode+","+ta.dbName+","+ta.dbRemark+","
                        +ta.dbSort1+","+ta.dbDateCreate+","+ta.dbActive+","
                        +ta.dbStatusToGo+","+ta.dbDateUse+","+ta.dbStatusUse+") "
                        +"Values ("+this.genid +",'"+code1+"','"+name+"','"+remark+"','"
                        +sort1+"',"+ gendate +",'1','"
                        +statustogo+"','','0')";
            }else{
                sql="Update "+da.tbNameTable +" "
                        +"Set "+ta.dbCode+"='"+code+"' "
                        +","+ta.dbName+"='"+name+"' "
                        +","+ta.dbRemark+"='"+remark+"' "
                        +","+ta.dbSort1+"='"+sort1+"' "
                        +","+ta.dbStatusUse+"='0' "
                        +","+ta.dbStatusToGo+"='"+statustogo+"' "
//                        +","+ta.dbDateUse+"='"+dateuse+"' "
                        +" Where "+ta.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("TableInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Table success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("TableInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray UserInsert(String id, String login, String name,String password, String privilege, String remark, String sort1, String voidbill, String voidcloseday ){
        String sql="",err="", code1="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(id.equals("")){
                int cnt=0;
                Cursor c = db.rawQuery("Select count(1) as cnt From "+da.tbNameUser+" ", null);
                if(c.moveToFirst()){
                    do{
                        Log.d("UserInsert rawQuery ",String.valueOf(c.getInt(c.getColumnIndex("cnt"))));
                        cnt = c.getInt(c.getColumnIndex("cnt"))+1;
                        code1 = "000"+cnt;
                        code1 = code1.substring(code1.length()-3);
                        Log.d("UserInsert code1 ",code1);
                    }while(c.moveToNext());
                }
                c.close();
//                db.close();
                sql ="Insert Into "+da.tbNameUser+"("+us.dbID+","+us.dbLogin+","+us.dbName+","+us.dbPassword1+","
                        +us.dbPrivilege+","+us.dbRemark+","+us.dbDateCreate+","+us.dbActive+","
                        +us.dbSort1+","+us.VoidBill+","+us.VoidCloseday+") "
                        +"Values ("+this.genid +",'"+login+"','"+name+"','"+password+"','"
                        +privilege+"',"+ remark+"',"+gendate+",'1','"
                        +sort1+"','"+voidbill+"','"+voidcloseday+"')";
            }else{
                sql="Update "+da.tbNameUser +" "
                        +"Set "+us.dbName+"='"+name+"' "
                        +","+us.dbPassword1+"='"+password+"' "
                        +","+us.dbPrivilege+"='"+privilege+"' "
                        +","+us.dbRemark+"='"+remark+"' "
                        +","+us.dbSort1+"='"+sort1+"' "
                        +","+us.VoidBill+"='"+voidbill+"' "
                        +","+us.VoidCloseday+"='"+voidcloseday+"' "
                        +" Where "+us.dbID+"='"+id+"'";
            }
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("UserInsert 1 ",e.getMessage());
            err = e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert User success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("UserInsert 2 ",e.getMessage());
        }
        return  jarr;
    }
    public JSONArray OrderByTableCode(String tablecode){
        JSONArray jarr = new JSONArray();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor c = db.rawQuery("Select * From "+da.tbNameOrder+" Where "+or.dbTableCode+" = '"+tablecode+"' and "+or.dbActive+"='1' and "+or.dbStatusBill+"<> '2' Order By "+or.dbLotId+", "+or.dbrow1, null);
            if(c.moveToFirst()){
                do{
                    jarr.put(getJsonObjectOrder(c));
                }while(c.moveToNext());
            }
            c.close();
        }catch (Exception e){
            Log.e("OrderByTableCode ",e.getMessage());
        }
        db.close();
//        jarr = new JSONArray(json);
        return  jarr;
    }
    private JSONObject getJsonObjectOrder(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(or.dbID, c.getString(c.getColumnIndex(or.dbID)));
            jsonObj.put(or.dbLotId, chkNull(c.getString(c.getColumnIndex(or.dbLotId)))?"":c.getString(c.getColumnIndex(or.dbLotId)));
            jsonObj.put(or.dbrow1, chkNull(c.getString(c.getColumnIndex(or.dbrow1)))?"":c.getString(c.getColumnIndex(or.dbrow1)));
            jsonObj.put(or.dbFoodsId, chkNull(c.getString(c.getColumnIndex(or.dbFoodsId)))?"":c.getString(c.getColumnIndex(or.dbFoodsId)));
            jsonObj.put(or.dbFoodsCode, chkNull(c.getString(c.getColumnIndex(or.dbFoodsCode)))?"":c.getString(c.getColumnIndex(or.dbFoodsCode)));
            jsonObj.put(or.dbOrderDate, chkNull(c.getString(c.getColumnIndex(or.dbOrderDate)))?"":c.getString(c.getColumnIndex(or.dbOrderDate)));
            jsonObj.put(or.dbPrice, chkNull(c.getString(c.getColumnIndex(or.dbPrice)))?"":c.getString(c.getColumnIndex(or.dbPrice)));
            jsonObj.put(or.dbQty, chkNull(c.getString(c.getColumnIndex(or.dbQty)))?"":c.getString(c.getColumnIndex(or.dbQty)));
            jsonObj.put(or.dbRemark, chkNull(c.getString(c.getColumnIndex(or.dbRemark)))?"":c.getString(c.getColumnIndex(or.dbRemark)));
            jsonObj.put(or.dbTableCode, chkNull(c.getString(c.getColumnIndex(or.dbTableCode)))?"":c.getString(c.getColumnIndex(or.dbTableCode)));
            jsonObj.put(or.dbResCode, chkNull(c.getString(c.getColumnIndex(or.dbResCode)))?"":c.getString(c.getColumnIndex(or.dbResCode)));
            jsonObj.put(or.dbAreaCode, chkNull(c.getString(c.getColumnIndex(or.dbAreaCode)))?"":c.getString(c.getColumnIndex(or.dbAreaCode)));
            jsonObj.put(or.dbStatusFoods1, chkNull(c.getString(c.getColumnIndex(or.dbStatusFoods1)))?"":c.getString(c.getColumnIndex(or.dbStatusFoods1)));
            jsonObj.put(or.dbStatusFoods2, chkNull(c.getString(c.getColumnIndex(or.dbStatusFoods2)))?"":c.getString(c.getColumnIndex(or.dbStatusFoods2)));
            jsonObj.put(or.dbStatusFoods3, chkNull(c.getString(c.getColumnIndex(or.dbStatusFoods3)))?"":c.getString(c.getColumnIndex(or.dbStatusFoods3)));
            jsonObj.put(or.dbStatusBill, chkNull(c.getString(c.getColumnIndex(or.dbStatusBill)))?"":c.getString(c.getColumnIndex(or.dbStatusBill)));
            jsonObj.put(or.dbBillCheckDate, chkNull(c.getString(c.getColumnIndex(or.dbBillCheckDate)))?"":c.getString(c.getColumnIndex(or.dbBillCheckDate)));
            jsonObj.put(or.dbStatusCook, chkNull(c.getString(c.getColumnIndex(or.dbStatusCook)))?"":c.getString(c.getColumnIndex(or.dbStatusCook)));
            jsonObj.put(or.dbCookReceiveDate, chkNull(c.getString(c.getColumnIndex(or.dbCookReceiveDate)))?"":c.getString(c.getColumnIndex(or.dbCookReceiveDate)));
            jsonObj.put(or.dbCookFinishDate, chkNull(c.getString(c.getColumnIndex(or.dbCookFinishDate)))?"":c.getString(c.getColumnIndex(or.dbCookFinishDate)));
            jsonObj.put(or.dbActive, chkNull(c.getString(c.getColumnIndex(or.dbActive)))?"":c.getString(c.getColumnIndex(or.dbActive)));
            jsonObj.put(or.dbVoidDate, chkNull(c.getString(c.getColumnIndex(or.dbVoidDate)))?"":c.getString(c.getColumnIndex(or.dbVoidDate)));
            jsonObj.put(or.dbStatusVoid, chkNull(c.getString(c.getColumnIndex(or.dbStatusVoid)))?"":c.getString(c.getColumnIndex(or.dbStatusVoid)));
            jsonObj.put(or.dbPrinterName, chkNull(c.getString(c.getColumnIndex(or.dbPrinterName)))?"":c.getString(c.getColumnIndex(or.dbPrinterName)));
            jsonObj.put(or.dbDateCreate, chkNull(c.getString(c.getColumnIndex(or.dbDateCreate)))?"":c.getString(c.getColumnIndex(or.dbDateCreate)));
            jsonObj.put(or.dbFoodsName, chkNull(c.getString(c.getColumnIndex(or.dbFoodsName)))?"":c.getString(c.getColumnIndex(or.dbFoodsName)));
            jsonObj.put(or.dbBillId, chkNull(c.getString(c.getColumnIndex(or.dbBillId)))?"":c.getString(c.getColumnIndex(or.dbBillId)));
            jsonObj.put(or.dbOrderUser, chkNull(c.getString(c.getColumnIndex(or.dbOrderUser)))?"":c.getString(c.getColumnIndex(or.dbOrderUser)));
            jsonObj.put(or.dbStatusCloseday, chkNull(c.getString(c.getColumnIndex(or.dbStatusCloseday)))?"":c.getString(c.getColumnIndex(or.dbStatusCloseday)));
            jsonObj.put(or.dbClosedayId, chkNull(c.getString(c.getColumnIndex(or.dbClosedayId)))?"":c.getString(c.getColumnIndex(or.dbClosedayId)));
            jsonObj.put(or.dbCntCust, chkNull(c.getString(c.getColumnIndex(or.dbCntCust)))?"":c.getString(c.getColumnIndex(or.dbCntCust)));
            jsonObj.put(or.dbStatusToGo, chkNull(c.getString(c.getColumnIndex(or.dbStatusToGo)))?"":c.getString(c.getColumnIndex(or.dbStatusToGo)));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectOrder ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectOrder ",e.getMessage());
        }
        return jsonObj;
    }
    private JSONObject getJsonObjectBill(Cursor c){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(bi.dbID, c.isNull(c.getColumnIndex(bi.dbID))?"":c.getString(c.getColumnIndex(bi.dbID)));
            jsonObj.put(bi.dbCode, c.isNull(c.getColumnIndex(bi.dbCode))?"":c.getString(c.getColumnIndex(bi.dbCode)));
            jsonObj.put(bi.dbBillDate, c.isNull(c.getColumnIndex(bi.dbBillDate))?"":c.getString(c.getColumnIndex(bi.dbBillDate)));
            jsonObj.put(bi.dbLotID, c.isNull(c.getColumnIndex(bi.dbLotID))?"":c.getString(c.getColumnIndex(bi.dbLotID)));
            jsonObj.put(bi.dbActive, c.isNull(c.getColumnIndex(bi.dbActive))?"":c.getString(c.getColumnIndex(bi.dbActive)));
            jsonObj.put(bi.dbRemark, c.isNull(c.getColumnIndex(bi.dbRemark))?"":c.getString(c.getColumnIndex(bi.dbRemark)));
            jsonObj.put(bi.dbStatusVoid, c.isNull(c.getColumnIndex(bi.dbStatusVoid))?"":c.getString(c.getColumnIndex(bi.dbStatusVoid)));
            jsonObj.put(bi.dbVoidDate, c.isNull(c.getColumnIndex(bi.dbVoidDate))?"":c.getString(c.getColumnIndex(bi.dbVoidDate)));
            jsonObj.put(bi.dbVoidUser, c.isNull(c.getColumnIndex(bi.dbVoidUser))?"":c.getString(c.getColumnIndex(bi.dbVoidUser)));
            jsonObj.put(bi.dbTableId, c.isNull(c.getColumnIndex(bi.dbTableId))?"":c.getString(c.getColumnIndex(bi.dbTableId)));
            jsonObj.put(bi.dbResId, c.isNull(c.getColumnIndex(bi.dbResId))?"":c.getString(c.getColumnIndex(bi.dbResId)));
            jsonObj.put(bi.dbAreaId, c.isNull(c.getColumnIndex(bi.dbAreaId))?"":c.getString(c.getColumnIndex(bi.dbAreaId)));
            jsonObj.put(bi.dbDeviceId, c.isNull(c.getColumnIndex(bi.dbDeviceId))?"":c.getString(c.getColumnIndex(bi.dbDeviceId)));
            jsonObj.put(bi.dbAmt, c.isNull(c.getColumnIndex(bi.dbAmt))?"":c.getString(c.getColumnIndex(bi.dbAmt)));
            jsonObj.put(bi.dbDiscount, c.isNull(c.getColumnIndex(bi.dbDiscount))?"":c.getString(c.getColumnIndex(bi.dbDiscount)));
            jsonObj.put(bi.dbSC, c.isNull(c.getColumnIndex(bi.dbSC))?"":c.getString(c.getColumnIndex(bi.dbSC)));
            jsonObj.put(bi.dbVat, c.isNull(c.getColumnIndex(bi.dbVat))?"":c.getString(c.getColumnIndex(bi.dbVat)));
            jsonObj.put(bi.dbTotal, c.isNull(c.getColumnIndex(bi.dbTotal))?"":c.getString(c.getColumnIndex(bi.dbTotal)));
            jsonObj.put(bi.dbNetTotal, c.isNull(c.getColumnIndex(bi.dbNetTotal))?"":c.getString(c.getColumnIndex(bi.dbNetTotal)));
            jsonObj.put(bi.dbCashReceive, c.isNull(c.getColumnIndex(bi.dbCashReceive))?"":c.getString(c.getColumnIndex(bi.dbCashReceive)));
            jsonObj.put(bi.dbCashTon, c.isNull(c.getColumnIndex(bi.dbCashTon))?"":c.getString(c.getColumnIndex(bi.dbCashTon)));
            jsonObj.put(bi.dbDateCreate, c.isNull(c.getColumnIndex(bi.dbDateCreate))?"":c.getString(c.getColumnIndex(bi.dbDateCreate)));
            jsonObj.put(bi.dbDateModi, c.isNull(c.getColumnIndex(bi.dbDateModi))?"":c.getString(c.getColumnIndex(bi.dbDateModi)));
            jsonObj.put(bi.dbBillUser, c.isNull(c.getColumnIndex(bi.dbBillUser))?"":c.getString(c.getColumnIndex(bi.dbBillUser)));
            jsonObj.put(bi.dbStatusCloseday, c.isNull(c.getColumnIndex(bi.dbStatusCloseday))?"":c.getString(c.getColumnIndex(bi.dbStatusCloseday)));
            jsonObj.put(bi.dbClosedayId, c.isNull(c.getColumnIndex(bi.dbClosedayId))?"":c.getString(c.getColumnIndex(bi.dbClosedayId)));
            jsonObj.put(bi.dbHostId, c.isNull(c.getColumnIndex(bi.dbHostId))?"":c.getString(c.getColumnIndex(bi.dbHostId)));
            jsonObj.put(bi.dbBranchId, c.isNull(c.getColumnIndex(bi.dbBranchId))?"":c.getString(c.getColumnIndex(bi.dbBranchId)));
//            jsonObj.put(bi.dbID, c.getString(c.getColumnIndex(bi.dbID)));
        }catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectOrder ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectOrder ",e.getMessage());
        }
        return jsonObj;
    }
    private JSONObject getJsonObjectBillDetail(Cursor c){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(bid.dbID, c.isNull(c.getColumnIndex(bid.dbID))?"":c.getString(c.getColumnIndex(bid.dbID)));
            jsonObj.put(bid.dbBillId, c.isNull(c.getColumnIndex(bid.dbBillId))?"":c.getString(c.getColumnIndex(bid.dbBillId)));
            jsonObj.put(bid.dbORderId, c.isNull(c.getColumnIndex(bid.dbORderId))?"":c.getString(c.getColumnIndex(bid.dbORderId)));
            jsonObj.put(bid.dbLotID, c.isNull(c.getColumnIndex(bid.dbLotID))?"":c.getString(c.getColumnIndex(bid.dbLotID)));
            jsonObj.put(bid.dbStatusVoid, c.isNull(c.getColumnIndex(bid.dbStatusVoid))?"":c.getString(c.getColumnIndex(bid.dbStatusVoid)));
            jsonObj.put(bid.dbRow1, c.isNull(c.getColumnIndex(bid.dbRow1))?"":c.getString(c.getColumnIndex(bid.dbRow1)));
            jsonObj.put(bid.dbFoodsId, c.isNull(c.getColumnIndex(bid.dbFoodsId))?"":c.getString(c.getColumnIndex(bid.dbFoodsId)));
            jsonObj.put(bid.dbFoodsCode, c.isNull(c.getColumnIndex(bid.dbFoodsCode))?"":c.getString(c.getColumnIndex(bid.dbFoodsCode)));
            jsonObj.put(bid.dbPrice, c.isNull(c.getColumnIndex(bid.dbPrice))?"":c.getString(c.getColumnIndex(bid.dbPrice)));
            jsonObj.put(bid.dbQty, c.isNull(c.getColumnIndex(bid.dbQty))?"":c.getString(c.getColumnIndex(bid.dbQty)));
            jsonObj.put(bid.dbAmt, c.isNull(c.getColumnIndex(bid.dbAmt))?"":c.getString(c.getColumnIndex(bid.dbAmt)));
            jsonObj.put(bid.dbDateCreate, c.isNull(c.getColumnIndex(bid.dbDateCreate))?"":c.getString(c.getColumnIndex(bid.dbDateCreate)));
            jsonObj.put(bid.dbDateModi, c.isNull(c.getColumnIndex(bid.dbDateModi))?"":c.getString(c.getColumnIndex(bid.dbDateModi)));
            jsonObj.put(bid.dbActive, c.isNull(c.getColumnIndex(bid.dbActive))?"":c.getString(c.getColumnIndex(bid.dbActive)));
            jsonObj.put(bid.dbRemark, c.isNull(c.getColumnIndex(bid.dbRemark))?"":c.getString(c.getColumnIndex(bid.dbRemark)));
            jsonObj.put(bid.dbHostId, c.isNull(c.getColumnIndex(bid.dbHostId))?"":c.getString(c.getColumnIndex(bid.dbHostId)));
            jsonObj.put(bid.dbBranchId, c.isNull(c.getColumnIndex(bid.dbBranchId))?"":c.getString(c.getColumnIndex(bid.dbBranchId)));
            jsonObj.put(bid.dbBillCode, c.isNull(c.getColumnIndex(bid.dbBillCode))?"":c.getString(c.getColumnIndex(bid.dbBillCode)));
            jsonObj.put(bid.dbTableId, c.isNull(c.getColumnIndex(bid.dbTableId))?"":c.getString(c.getColumnIndex(bid.dbTableId)));
            jsonObj.put(bid.dbFoodsName, c.isNull(c.getColumnIndex(bid.dbFoodsName))?"":c.getString(c.getColumnIndex(bid.dbFoodsName)));
        }catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectOrder ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectOrder ",e.getMessage());
        }
        return jsonObj;
    }
    public JSONArray BillInsert(String tableid,String areaid,String deviceid,String discount,String amt,String  sc,
            String vat,String total,String nettotal,String billID, String cashreceive,String cashton,String user, String remark, String hostid){
        String sql="",err="", code1="",resID="", code="",year="",month="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor c = db.rawQuery("Select ifnull("+res.dbBillCode+",'000000000') as "+res.dbBillCode+" From "+da.tbNameRes+" Where "+res.dbDefaultRes+" = '1' and "+res.dbBillMonth+" = strftime('%m','now') ", null);
            if(!c.moveToFirst()){
                db.execSQL("update "+da.tbNameRes+" set "+res.dbBillMonth+" = strftime('%m','now'), "+res.dbBillCode+" = NULL where "+res.dbDefaultRes+" = '1'");
            }
            c.close();
//            c = db.rawQuery("Select "+res.dbID+", strftime('%m','now') as month, strftime('%Y','now') as year, ifnull("+res.dbBillCode+",'000000000') as "+res.dbBillCode
//                    +" From "+da.tbNameRes+" Where "+res.dbDefaultRes+" = '1' and "+res.dbBillMonth+" = strftime('%m','now')",null);
            c = db.rawQuery("Select "+res.dbID+", strftime('%m','now') as month, strftime('%Y','now') as year, ifnull("+res.dbBillCode+",'000000000') as "+res.dbBillCode
                    +" From "+da.tbNameRes+" Where "+res.dbDefaultRes+" = '1' ",null);
            if(c.moveToFirst()){
                do{
                    resID = c.getString(c.getColumnIndex("res_id"));
                    code = "0000"+String.valueOf(Integer.parseInt(c.getString(c.getColumnIndex("bill_code")))+1);
                    code = code.substring(code.length()-5);
                    year = String.valueOf(c.getInt(c.getColumnIndex("year"))+543);
                    year = year.substring(year.length()-2);
                    month = c.getString(c.getColumnIndex("month"));
                    code1 = year+month+code;
                    Log.d("BillInsert code1 ",code1);
                }while(c.moveToNext());
            }
            c.close();
            db.execSQL("update "+da.tbNameRes+" set "+res.dbBillCode+" = '"+code+"' Where "+res.dbID+"  = '"+resID+"'");
            sql = "Insert into "+da.tbNameBill+"("+bi.dbID+","+bi.dbBillDate+","+bi.dbDateCreate+","+bi.dbRemark+","+bi.dbStatusVoid+","+bi.dbTableId+","+bi.dbResId+","
                    +bi.dbAreaId+","+bi.dbDeviceId+","+bi.dbAmt+","+bi.dbDiscount+","+bi.dbActive+","+bi.dbSC+","+bi.dbVat+","+bi.dbTotal+","+bi.dbNetTotal+","
                    +bi.dbCode+","+bi.dbBillUser+","+bi.dbCashReceive+","+bi.dbCashTon+","+bi.dbStatusCloseday+","+bi.dbClosedayId+","+bi.dbHostId+")"
                    +" Values ('"+billID+"',"+gendate+","+gendate+",'"+remark+"','0','"+tableid+"','"+resID+"','"
                    +areaid+"','"+deviceid+"',"+amt+","+discount+",'1',"+sc+","+vat+","+total+","+nettotal+",'"
                    +code1+"','"+user+"',"+cashreceive+","+cashton+",'0','','"+hostid+"')";
            db.execSQL(sql);
            db.execSQL("Update "+da.tbNameTable+" Set "+ta.dbStatusUse+" ='0' Where table_id ='"+tableid+"'");
        }catch (Exception e){
            Log.e("BillInsert 1 ",e.getMessage());
            err=e.getMessage();
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert Bill success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillInsert 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray BillDetailInsert(String billID,String LotId,String Qty,String FoodsCode,String FoodsName,
            String FoodsId,String Price,String Amt,String orderID,String row1,String FlagVoid){

        String sql="",err="", code1="",resID="", code="",year="",month="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            if(FlagVoid.equals("1")){
                sql = "Update "+da.tbNameOrder+" Set "+or.dbStatusVoid+" ='3', "+or.dbVoidDate+" = "+gendate+" Where "+or.dbID+" ='"+orderID+"'";
                db.execSQL(sql);
            }else{
                sql = "Insert into "+da.tbNameBillDetail+"("+bid.dbID+","+bid.dbDateCreate+","+bid.dbBillId+","+bid.dbORderId+","+bid.dbStatusVoid+","+bid.dbRow1+","+bid.dbFoodsId+","+bid.dbFoodsCode+","
                        +bid.dbPrice+","+bid.dbQty+","+bid.dbAmt+","+bid.dbActive+")"
                        +" Values ("+genid+","+gendate+",'"+billID+"','"+orderID+"','0','"+row1+"','"+FoodsId+"','"+FoodsCode+"',"
                        +Price+","+Qty+","+Amt+",'1')";
                db.execSQL(sql);
            }
        }catch (Exception e){
            Log.e("BillDetailInsert 1 ",e.getMessage());
            err=e.getMessage();
        }
        db.execSQL("Update "+da.tbNameOrder+" Set "+or.dbStatusBill+" ='2', "+or.dbBillId+" = '"+billID+"', "+or.dbBillCheckDate+" = "+gendate+" Where "+or.dbID+" ='"+orderID+"'");
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert BillDetail success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillDetailInsert 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray BillByCloseday(String resid, String closedaydate){
        String sql="",err="", date1="",date2="", wheredate="",year="",month="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c,c1,c2;
        try{
            Log.d("BillByCloseday ","ok");
            date1 = closedaydate+" 00:00:00";
            date2 = closedaydate+" 23:59:59";
            wheredate=" and "+cd.dbCloseDayDate+" >= '"+date1+"' and "+cd.dbCloseDayDate+" <= '"+date2+"'";
            sql = "Select * From t_closeday Where res_id = '"+resid+"' and active ='1'  "+wheredate;
//            sql = "Select sum(amount) amount amt, sum(discount) as discount, sum(total) as total, sum(service_charge) as service_charge, sum(vat) as vat, sum(nettotal) as nettotal, count(1) as cnt_bill "
//                +"From "+da.tbNameBill+" Where "+bi.dbStatusCloseday+" <> '1' "+wheredate+" and "+bi.dbActive+" = '1'";
            c = db.rawQuery(sql,null);
            if(!c.moveToFirst()){
                jsonObj = getJsonObjectCloseday(null);
                wheredate=" and bill_date >= '"+date1+"' and bill_date <= '"+date2+"'";
//                sql = "Select count(1) as cnt_order, sum(price*qty) as amt_order From t_order Where status_closeday <> '1' "+wheredate+" and active = '1'";
                sql = "Select sum(amount) as amount, sum(discount) as discount, sum(total) as total, sum(service_charge) as service_charge, sum(vat) as vat, sum(nettotal) as nettotal, count(1) as cnt_bill "
                    +"From "+da.tbNameBill+" Where "+bi.dbStatusCloseday+" <> '1' "+wheredate+" and "+bi.dbActive+" = '1'";
                c1 = db.rawQuery(sql,null);
                if(c1.moveToFirst()){
                    do {
                        jsonObj.put(cd.dbAmt, c1.getString(c1.getColumnIndex(cd.dbAmt)));
                        jsonObj.put(cd.dbDiscount, c1.getString(c1.getColumnIndex(cd.dbDiscount)));
                        jsonObj.put(cd.dbTotal, c1.getString(c1.getColumnIndex(cd.dbTotal)));
                        jsonObj.put(cd.dbSC, c1.getString(c1.getColumnIndex(cd.dbSC)));
                        jsonObj.put(cd.dbVat, c1.getString(c1.getColumnIndex(cd.dbVat)));
                        jsonObj.put(cd.dbNetTotal, c1.getString(c1.getColumnIndex(cd.dbNetTotal)));
                        jsonObj.put(cd.dbCntBill, c1.getString(c1.getColumnIndex(cd.dbCntBill)));
                    }while(c1.moveToNext());
                }
                c1.close();
                wheredate=" and order_date >= '"+date1+"' and order_date <= '"+date2+"'";
                sql = "Select count(1) as cnt_order, sum(price*qty) as amount_order From t_order Where status_closeday = '2" +
                        "' "+wheredate+"  and "+or.dbStatusBill+"='1'";
                c2 = db.rawQuery(sql,null);
                if(c2.moveToFirst()){
                    do {
                        jsonObj.put(cd.dbCntOrder, c2.getString(c2.getColumnIndex(cd.dbCntOrder)));
                        jsonObj.put(cd.dbAmtOrder, c2.getString(c2.getColumnIndex(cd.dbAmtOrder)));
                    }while(c2.moveToNext());
                }
                c2.close();
                jarr.put(jsonObj);
            }else{
                do {
//                    jsonObj = getJsonObjectCloseday(c);
                    jarr.put(getJsonObjectCloseday(c));
                }while(c.moveToNext());

            }
            c.close();
        }catch (JSONException e){
            Log.e("BillByCloseday 1 ",e.getMessage());
            err=e.getMessage();
        }catch (Exception e1) {
            Log.e("BillByCloseday 1 ",e1.getMessage());
            err=e1.getMessage();
        }
        db.close();
//        try{
//            jsonObj = new JSONObject();
//            jsonObj.put("success", "1");
//            jsonObj.put("message", "insert BillDetail success");
//            jsonObj.put("sql", sql);
//            jsonObj.put("error", err);
//            jarr.put(jsonObj);
//        }catch (JSONException e) {
//            Log.e("BillByCloseday 2 ",e.getMessage());
//            err=e.getMessage();
//        }
        return jarr;
    }
    private JSONObject getJsonObjectCloseday(Cursor c) {
        JSONObject jsonObj = new JSONObject();
        try{
            if(c==null){
                jsonObj.put(cd.dbID, "");
                jsonObj.put(cd.dbCloseDayDate, "");
                jsonObj.put(cd.dbResId, "");
                jsonObj.put(cd.dbAmt, "");
                jsonObj.put(cd.dbDiscount, "");
                jsonObj.put(cd.dbTotal, "");
                jsonObj.put(cd.dbSC, "");
                jsonObj.put(cd.dbVat, "");
                jsonObj.put(cd.dbNetTotal, "");
                jsonObj.put(cd.dbRemark, "");
                jsonObj.put(cd.dbActive, "");
                jsonObj.put(cd.dbStatusVoid, "");
                jsonObj.put(cd.dbVoidDate, "");
                jsonObj.put(cd.dbVoidUser, "");
                jsonObj.put(cd.dbCntBill, "");
                jsonObj.put(cd.dbAmtBill, "");
                jsonObj.put(cd.dbCntOrder, "");
                jsonObj.put(cd.dbAmtOrder, "");
                jsonObj.put(cd.dbCloseDayUser, "");
                jsonObj.put(cd.dbCashR1, "");
                jsonObj.put(cd.dbCashR2, "");
                jsonObj.put(cd.dbCashR3, "");
                jsonObj.put(cd.dbCashR1Remark, "");
                jsonObj.put(cd.dbCashR2Remark, "");
                jsonObj.put(cd.dbCashR3Remark, "");
                jsonObj.put(cd.dbCashD1, "");
                jsonObj.put(cd.dbCashD2, "");
                jsonObj.put(cd.dbCashD3, "");
                jsonObj.put(cd.dbCashD1Remark, "");
                jsonObj.put(cd.dbCashD2Remark, "");
                jsonObj.put(cd.dbCashD3Remark, "");
                jsonObj.put(cd.dbHostId, "");
                jsonObj.put(cd.dbBranchId, "");
                jsonObj.put(cd.dbDeviceId, "");
                jsonObj.put(cd.dbWeather, "");
            }else{
                jsonObj.put(cd.dbID, c.getString(c.getColumnIndex(cd.dbID)));
                jsonObj.put(cd.dbCloseDayDate, c.getString(c.getColumnIndex(cd.dbCloseDayDate)));
                jsonObj.put(cd.dbResId, c.getString(c.getColumnIndex(cd.dbResId)));
                jsonObj.put(cd.dbAmt, c.getString(c.getColumnIndex(cd.dbAmt)));
                jsonObj.put(cd.dbDiscount, c.getString(c.getColumnIndex(cd.dbDiscount)));
                jsonObj.put(cd.dbTotal, c.getString(c.getColumnIndex(cd.dbTotal)));
                jsonObj.put(cd.dbSC, c.getString(c.getColumnIndex(cd.dbSC)));
                jsonObj.put(cd.dbVat, c.getString(c.getColumnIndex(cd.dbVat)));
                jsonObj.put(cd.dbNetTotal, c.getString(c.getColumnIndex(cd.dbNetTotal)));
                jsonObj.put(cd.dbRemark, c.getString(c.getColumnIndex(cd.dbRemark)));
                jsonObj.put(cd.dbActive, c.getString(c.getColumnIndex(cd.dbActive)));
                jsonObj.put(cd.dbStatusVoid, c.getString(c.getColumnIndex(cd.dbStatusVoid)));
                jsonObj.put(cd.dbVoidDate, c.getString(c.getColumnIndex(cd.dbVoidDate)));
                jsonObj.put(cd.dbVoidUser, c.getString(c.getColumnIndex(cd.dbVoidUser)));
                jsonObj.put(cd.dbCntBill, c.getString(c.getColumnIndex(cd.dbCntBill)));
                jsonObj.put(cd.dbAmtBill, c.getString(c.getColumnIndex(cd.dbAmtBill)));
                jsonObj.put(cd.dbCntOrder, c.getString(c.getColumnIndex(cd.dbCntOrder)));
                jsonObj.put(cd.dbAmtOrder, c.getString(c.getColumnIndex(cd.dbAmtOrder)));
                jsonObj.put(cd.dbCloseDayUser, c.getString(c.getColumnIndex(cd.dbCloseDayUser)));
                jsonObj.put(cd.dbCashR1, c.getString(c.getColumnIndex(cd.dbCashR1)));
                jsonObj.put(cd.dbCashR2, c.getString(c.getColumnIndex(cd.dbCashR2)));
                jsonObj.put(cd.dbCashR3, c.getString(c.getColumnIndex(cd.dbCashR3)));
                jsonObj.put(cd.dbCashR1Remark, c.getString(c.getColumnIndex(cd.dbCashR1Remark)));
                jsonObj.put(cd.dbCashR2Remark, c.getString(c.getColumnIndex(cd.dbCashR2Remark)));
                jsonObj.put(cd.dbCashR3Remark, c.getString(c.getColumnIndex(cd.dbCashR3Remark)));
                jsonObj.put(cd.dbCashD1, c.getString(c.getColumnIndex(cd.dbCashD1)));
                jsonObj.put(cd.dbCashD2, c.getString(c.getColumnIndex(cd.dbCashD2)));
                jsonObj.put(cd.dbCashD3, c.getString(c.getColumnIndex(cd.dbCashD3)));
                jsonObj.put(cd.dbCashD1Remark, c.getString(c.getColumnIndex(cd.dbCashD1Remark)));
                jsonObj.put(cd.dbCashD2Remark, c.getString(c.getColumnIndex(cd.dbCashD2Remark)));
                jsonObj.put(cd.dbCashD3Remark, c.getString(c.getColumnIndex(cd.dbCashD3Remark)));
                jsonObj.put(cd.dbHostId, c.getString(c.getColumnIndex(cd.dbHostId)));
                jsonObj.put(cd.dbBranchId, c.getString(c.getColumnIndex(cd.dbBranchId)));
                jsonObj.put(cd.dbDeviceId, c.getString(c.getColumnIndex(cd.dbDeviceId)));
                jsonObj.put(cd.dbWeather, c.getString(c.getColumnIndex(cd.dbWeather)));
            }
        }catch (JSONException e) {
            e.printStackTrace();
            Log.e("getJsonObjectCloseday1 ",e.getMessage());
        }catch (Exception e){
            Log.e("getJsonObjectCloseday2 ",e.getMessage());
        }
        return jsonObj;
    }
    public JSONArray ClosedayInsert(String id,String closedate, String resid, String amt, String discount, String total, String sc, String vat
            ,String nettotal, String remark, String cntbill, String billamt, String cntorder, String amtorder, String closeuser, String cashr1, String cashr2
            ,String cashr3, String cashd1, String cashd2, String cashd3, String cashr1r, String cashr2r, String cashr3r, String cashd1r, String cashd2r, String cashd3r, String weather){
        String sql="",err="", code1="",resID="", code="",year="",month="",date1="",date2="", wheredate="";
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            date1 = closedate+" 00:00:00";
            date2 = closedate+" 23:59:59";
            sql = "Insert into "+da.tbNameCloseday+"("+cd.dbID+","+cd.dbCloseDayDate+","+cd.dbResId+","+cd.dbAmt+","+cd.dbDiscount+","+cd.dbTotal+","+cd.dbSC+","
                    +cd.dbVat+","+cd.dbNetTotal+","+cd.dbRemark+","+cd.dbActive+","+cd.dbStatusVoid+","+cd.dbVoidDate+","+cd.dbVoidUser+","+cd.dbCntBill+","+cd.dbAmtBill+","
                    +cd.dbCntOrder+","+cd.dbAmtOrder+","+cd.dbCloseDayUser+","+cd.dbCashR1+","+cd.dbCashR2+","+cd.dbCashR3+","+cd.dbCashD1+","+cd.dbCashD2+","+cd.dbCashD3+","
                    +cd.dbCashR1Remark+","+cd.dbCashR2Remark+","+cd.dbCashR3Remark+","+cd.dbCashD1Remark+","+cd.dbCashD2Remark+","+cd.dbCashD3Remark+","+cd.dbWeather+")"
                    +" Values ('"+id+"',"+gendate+",'"+resid+"',"+amt+","+discount+","+total+","+sc+","
                    +vat+","+nettotal+",'"+remark+"','1','0','','',"+cntbill+","+billamt+","
                    +cntorder+","+amtorder+",'"+closeuser+"',"+cashr1+","+cashr2+","+cashr3+","+cashd1+","+cashd2+","+cashd3+",'"
                    +cashr1r+"','"+cashr2r+"','"+cashr3r+"','"+cashd1r+"','"+cashd2r+"','"+cashd3r+"','"+weather+"')";
            db.execSQL(sql);
            wheredate=" and bill_date >= '"+date1+"' and bill_date <= '"+date2+"'";
            sql = "Update t_bill Set status_closeday ='1', closeday_id = '"+id+"' Where status_closeday ='0' "+wheredate ;
            db.execSQL(sql);
            wheredate=" and order_date >= '"+date1+"' and order_date <= '"+date2+"'";
            sql = "Update t_order Set status_closeday ='1', closeday_id = '"+id+"' Where status_closeday ='0' "+wheredate ;
            db.execSQL(sql);
        }catch (Exception e) {
            Log.e("ClosedayInsert ",e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "insert BillDetail success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillDetailInsert 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray BillByTableID(String tableid, String billdate){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        String sql="",date1="",date2="", wheredate="";
        date1 = billdate+" 00:00:00";
        date2 = billdate+" 23:59:59";
        sql="Select * From "+da.tbNameBill
                +" Where "+bi.dbActive+" = '1' and table_id = '"+tableid+"' and bill_date >= '"+date1+"' and bill_date <= '"+date2+"' and "+bi.dbStatusCloseday + "<>'2' "
                +" Order By bill_date";
//        sql="Select * From "+da.tbNameBill
////                +" Where "+bi.dbActive+" = '1' and table_id = '"+tableid+"' and bill_date >= '"+date1+"' and bill_date <= '"+date1+"' and "+bi.dbStatusCloseday + "<>'2' "
//                +" Where "+bi.dbActive+" = '1'  and table_id = '"+tableid+"' and "+bi.dbStatusCloseday + "<>'2' and bill_date >= '"+date1+"' and bill_date <= '"+date1+"' "
//                +" Order By bill_date";
        try {
            Log.d("BillByTableID ", "ok");
            c = db.rawQuery(sql,null);
            if(c.moveToFirst()){
                do{
                    jarr.put(getJsonObjectBill(c));
                }while(c.moveToNext());
            }
            c.close();
        }catch (Exception e) {
            Log.d("BillByTableID ", "ok");
        }
        db.close();
        return jarr;
    }
    public JSONArray BillDetailByBillCode(String billcode){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        String sql="Select "+da.tbNameBillDetail+".*, b_foods.foods_name, t_bill.bill_code, t_bill.table_id "
                +"From "+da.tbNameBillDetail+" left join "+da.tbNameFoods+" on "+da.tbNameBillDetail+".foods_id = b_foods.foods_id "
                +" left join "+da.tbNameBill+" on t_bill.bill_id = "+da.tbNameBillDetail+".bill_id "
                +"Where "+da.tbNameBillDetail+".active = '1' and t_bill.bill_code = '"+billcode+"' Order By t_bill_detail.row1";

        try {
            Log.d("BillDetailByBillCode ", "ok");
            c = db.rawQuery(sql,null);
            if(c.moveToFirst()){
                do{
                    jarr.put(getJsonObjectBillDetail(c));
                }while(c.moveToNext());
            }
            c.close();
        }catch (Exception e) {
            Log.d("BillDetailByBillCode ", "ok");
        }
        db.close();
        return jarr;
    }
    public JSONArray BillDetailByBillId(String billid){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c;
        String sql="Select "+da.tbNameBillDetail+".*, b_foods.foods_name, t_bill.bill_code, t_bill.table_id "
                +"From "+da.tbNameBillDetail+" left join "+da.tbNameFoods+" on "+da.tbNameBillDetail+".foods_id = b_foods.foods_id "
                +" left join "+da.tbNameBill+" on t_bill.bill_id = "+da.tbNameBillDetail+".bill_id "
                +"Where "+da.tbNameBillDetail+".active = '1' and t_bill.bill_id = '"+billid+"' Order By t_bill_detail.row1";

        try {
            Log.d("BillDetailByBillCode ", "ok");
            c = db.rawQuery(sql,null);
            if(c.moveToFirst()){
                do{
                    jarr.put(getJsonObjectBillDetail(c));
                }while(c.moveToNext());
            }
            c.close();
        }catch (Exception e) {
            Log.d("BillDetailByBillCode ", "ok");
        }
        db.close();
        return jarr;
    }
    public JSONArray BillVoid(String user,String billid){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameBill+" Set "+bi.dbActive+" = '3', "+bi.dbStatusVoid+" = '1', "+bi.dbVoidUser+" = '"+user+"', "+bi.dbVoidDate+" = "+gendate+" Where "+bi.dbID+" = '"+billid+"' ";
            db.execSQL(sql);
            sql="Update "+da.tbNameOrder+" Set "+or.dbStatusBill+" ='1' Where "+or.dbBillId+" ='"+billid+"'";
            db.execSQL(sql);

        }catch (Exception e) {
            Log.d("BillVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void BillVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray FoodsCategoryVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameFoodsType+" Set "+fc.dbActive+" = '3', "+fc.dbVoidUser+" = '"+user+"', "+fc.dbVoidDate+" = "+gendate+" Where "+fc.dbID+" = '"+id+"' ";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.d("FoodsPrintVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void FoodsPrintVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray FoodsTypeVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameFoodsType+" Set "+ft.dbActive+" = '3', "+ft.dbVoidUser+" = '"+user+"', "+ft.dbVoidDate+" = "+gendate+" Where "+ft.dbID+" = '"+id+"' ";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.d("FoodsPrintVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void FoodsPrintVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("BillVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray AreaVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameArea+" Set "+ar.dbActive+" = '3', "+ar.dbVoidUser+" = '"+user+"', "+ar.dbVoidDate+" = "+gendate+" Where "+ar.dbID+" = '"+id+"' ";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.d("AreaVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void AreaVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("AreaVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray FoodsVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameFoods+" Set "+foo.dbActive+" = '3', "+foo.dbVoidUser+" = '"+user+"', "+foo.dbVoidDate+" = "+gendate+" Where "+foo.dbID+" = '"+id+"' ";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.d("FoodsVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void FoodsVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("FoodsVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray ResVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameRes+" Set "+res.dbActive+" = '3', "+res.dbVoidUser+" = '"+user+"', "+res.dbVoidDate+" = "+gendate+" Where "+res.dbID+" = '"+id+"' ";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.d("ResVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void ResVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("ResVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray TableVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameTable+" Set "+ta.dbActive+" = '3', "+ta.dbVoidUser+" = '"+user+"', "+ta.dbVoidDate+" = "+gendate+" Where "+ta.dbID+" = '"+id+"' ";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.d("TableVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void TableVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("TableVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray UserVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameUser+" Set "+us.dbActive+" = '3', "+us.dbVoidUser+" = '"+user+"', "+us.dbVoidDate+" = "+gendate+" Where "+us.dbID+" = '"+id+"' ";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.d("UserVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void UserVoid success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("UserVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray TableChangeInsert(String id,String tableidFrom, String tableidTo, String user, String hostid, String deviceid, String tableCodeTo, String tableCodeFrom){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        Log.d("TableChangeInsert 0 ", "TableChangeInsert");
        try{
            sql = "Insert into "+da.tbNameTableChange+" ("+tc.dbID+","+tc.dbTableIdFrom+","+tc.dbTableIdTo+","+tc.dbTableChangeUser+","
                    +tc.dbHostId+","+tc.dbDeviceId+","+tc.dbDateCreate+","+tc.dbActive+") "
            +"Values ('"+id+"','"+tableidFrom+"','"+tableidTo+"','"+user+"','"
                    +hostid+"','"+deviceid+"',"+gendate+",'1')" + "";
            db.execSQL(sql);
            sql="Update "+da.tbNameOrder+" Set "+or.dbTableId+"='"+tableidTo+"' "
                    +","+or.dbTableCode+"='"+tableCodeTo+"' "
                    +","+or.dbTableChangeId +"='"+id+"' "
                    +" Where "+or.dbTableCode+" = '"+tableCodeFrom+"' and "+or.dbActive+"='1' and "+or.dbStatusBill+"= '0'";
            db.execSQL(sql);
            sql="Update "+da.tbNameTable+" Set "+ta.dbStatusUse+"='1' "
                +"Where "+ta.dbID+"='"+tableidTo+"'";
            db.execSQL(sql);
            sql="Update "+da.tbNameTable+" Set "+ta.dbStatusUse+"='0' "
                    +"Where "+ta.dbID+"='"+tableidFrom+"'";
            db.execSQL(sql);
        }catch (Exception e) {
            Log.e("TableChangeInsert 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void TableChangeInsert success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("TableChangeInsert 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
    public JSONArray ClosedayVoid(String user,String id){
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql="",err="";
        try{
            sql = "update "+da.tbNameCloseday+" Set "+cd.dbActive+" = '3', "+cd.dbVoidUser+" = '"+user+"', "+cd.dbVoidDate+" = "+gendate+" Where "+cd.dbID+" = '"+id+"' ";
            db.execSQL(sql);
            db.execSQL("Update t_bill Set status_closeday ='0', closeday_id = '' Where closeday_id = '"+id+"'");
            db.execSQL("Update t_order Set status_closeday ='0', closeday_id = '' Where closeday_id = '"+id+"'");
        }catch (Exception e) {
            Log.d("ClosedayVoid 1 ", e.getMessage());
        }
        db.close();
        try{
            jsonObj = new JSONObject();
            jsonObj.put("success", "1");
            jsonObj.put("message", "Void Closeday success");
            jsonObj.put("sql", sql);
            jsonObj.put("error", err);
            jsonObj.put("status", "1");
            jarr.put(jsonObj);
        }catch (JSONException e) {
            Log.e("ClosedayVoid 2 ",e.getMessage());
            err=e.getMessage();
        }
        return jarr;
    }
}
