package com.counterplus.ekapop.restaurant1;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ekapop on 8/16/2016.
 */
public class RestaurantControl  extends Application implements Serializable {
    //JsonParser jsonparser = new JsonParser();
    public ArrayList<String> sCboTable = new ArrayList<String>();
    public ArrayList<String> sCboArea = new ArrayList<String>();
    public ArrayList<String> sCboRes = new ArrayList<String>();
    public ArrayList<String> sCboPrinter = new ArrayList<String>();
    public ArrayList<String> sCboTextSize = new ArrayList<String>();
    public ArrayList<String> sCboFoods = new ArrayList<String>();
    public ArrayList<String> sCboFoodsType = new ArrayList<String>();
    public ArrayList<String> sCboFoodsCategory = new ArrayList<String>();
    public ArrayList<String> sCboFoodsSpecific = new ArrayList<String>();
    public ArrayList<String> sCboFoodsPrint = new ArrayList<String>();
    public ArrayList<String> sCboUser = new ArrayList<String>();
    public ArrayList<String> sCboPrivilege = new ArrayList<String>();
    public ArrayList<String> sCboLanguage = new ArrayList<String>();
    public ArrayList<String> sCboMonth = new ArrayList<String>();
    public ArrayList<String> sCboYear = new ArrayList<String>();
    public ArrayList<String> sCboBrand = new ArrayList<String>();
    public ArrayList<String> sCboModel = new ArrayList<String>();

    public ArrayList<String> sTable = new ArrayList<String>();
    public ArrayList<String> sArea = new ArrayList<String>();
    public ArrayList<String> sRes = new ArrayList<String>();
    public ArrayList<String> sFoods = new ArrayList<String>();
    public ArrayList<String> sFoodsSpec = new ArrayList<String>();
    public ArrayList<String> sFoodsPrint = new ArrayList<String>();
    //public ArrayList<String> sCboPrinter = new ArrayList<String>();
    public ArrayList<String> sFoodsType = new ArrayList<String>();
    public ArrayList<String> sFoodsCategory = new ArrayList<String>();
    public ArrayList<String> sUser = new ArrayList<String>();
    public ArrayList<String> sBrand = new ArrayList<String>();
    public ArrayList<String> sModel = new ArrayList<String>();

    public String ResName="", ReceiptH1="", ReceiptH2="", ReceiptF1="", ReceiptF2="", imei="";

    public String hostIP="", hostWebDirectory ="", hostPORT="80", UserDB="", PasswordDB ="",TextSize="",PrnO="",PrnB="",PrnC="";
    public String fooID="", ordID="", ordLotID="", arID="", taID ="",resID="", ftID="", fcID="", fpID="", fsID="", usID ="", AccessMode="", HostID="", Language="",AccessMethod="";
    public String hostSaveOrder="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"saveTOrder.php";

    public String hostDailyGroupByFoods="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"DailyGroupByFoods.php";

    public String hostGetArea="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getArea.php";
    public String hostGetTable="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getTable.php";
    public String hostGetRes="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getRes.php";
    public String hostGetFoodsType ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getFoodsType.php";
    public String hostGetFoodsCat ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getFoodsCat.php";
    public String hostGetPrinterName ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getPrinterName.php";
    public String hostGetFoods ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getFoods.php";
    public String hostGetUser ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getUser.php";

    public String hostOrderByTableCode ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"OrderByTableCode.php";
    public String hostBillByTableId ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillByTableId.php";
    public String hostBillByBillCode ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillByBillCode.php";
    public String hostBillDetailByBillId ="http://"+hostIP+":80/"+ hostWebDirectory +"BillDetailByBillId.php";
    public String hostBillDetailByBillCode ="http://"+hostIP+":80/"+ hostWebDirectory +"BillDetailByBillCode.php";
    public String hostBillCloseDay ="http://"+hostIP+":80/"+ hostWebDirectory +"BillByCloseDay.php";


    public String hostBillInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillInsert.php";
    public String hostBillUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillUpdate.php";
    public String hostBillDetailInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillDetailInsert.php";
    public String hostBillDetailUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillDetailUpdate.php";
    public String hostBillVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillVoid.php";

    public String hostCloseDayInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ClosedayInsert.php";
    public String hostCloseDayVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ClosedayVoid.php";

    public String hostFoodsInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsInsert.php";
    public String hostFoodsUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsUpdate.php";
    public String hostFoodsSearch ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsSearch.php";
    public String hostFoodsVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsVoid.php";

    public String hostAreaInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaInsert.php";
    public String hostAreaUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaUpdate.php";
    public String hostAreaVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaVoid.php";
    public String hostAreaSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaSelectByID.php";

    public String hostTableInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableInsert.php";
    public String hostTableUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableUpdate.php";
    public String hostTableVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableVoid.php";
    public String hostTableSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableSelectByID.php";
    public String hostTableChange ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableChange.php";

    public String hostResInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResInsert.php";
    public String hostResUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResUpdate.php";
    public String hostResVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResVoid.php";
    public String hostResSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResSelectByID.php";

    public String hostFoodsTypeInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsTypeInsert.php";
    public String hostFoodsTypeUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsTypeUpdate.php";
    public String hostFoodsTypeVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsPrintVoid.php";
    public String hostFoodsTypeSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsTypeSelectByID.php";

    public String hostFoodsCatInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatInsert.php";
    public String hostFoodsCatUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatUpdate.php";
    public String hostFoodsCatVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatVoid.php";
    public String hostFoodsCatSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatSelectByID.php";

    public String hostSelectFoods="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"selectFoods.php";
    public String hostSelectFoodsByID="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"selectFoodsByID.php";
    public String hostSelectFoodsByCode="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"selectFoodsByCode.php";

    public String hostUserSelectAll ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserSelectAll.php";
    public String hostUserSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserSelectByID.php";
    public String hostUserInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserInsert.php";
    public String hostUserUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserUpdate.php";
    public String hostUserVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserVoid.php";

    public String discount="0.0", SC="0.0", vat="0.0";

    public ArrayList<Foods> lFoo = new ArrayList<Foods>();
    public String jarrA, jarrT, jarrR, jarrF, flagReport;
    public Boolean pageLoad=false;
    //JSONArray jarrF1;
    //public List<Ta> lFoo = new ArrayList<Foods>();
    public RestaurantControl(){
        setCboTable();
//        getText();
//        setCboMOnth();
        sCboPrivilege.add("All");
        sCboPrivilege.add("Order");
        sCboPrivilege.add("Order Bill");
        sCboPrivilege.add("Order Bill Closeday");
        sCboLanguage.add("Thai");
        sCboLanguage.add("English");


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String currentDateandTime = sdf.format(new Date());
        sCboYear.add(currentDateandTime);
        sCboYear.add(String.valueOf(Integer.parseInt(currentDateandTime)-1));
        sCboYear.add(String.valueOf(Integer.parseInt(currentDateandTime)-2));
        sCboYear.add(String.valueOf(Integer.parseInt(currentDateandTime)-3));
        sCboYear.add(String.valueOf(Integer.parseInt(currentDateandTime)-4));

        refresh();
    }
    public void setCboMOnth(Context context){
        getText(context);
        sCboMonth.clear();
        if(Language.equals("Thai")){
            sCboMonth.add("มกราคม");
            sCboMonth.add("กุมภาพันธ์");
            sCboMonth.add("มีนาคม");
            sCboMonth.add("เมษายน");
            sCboMonth.add("พฤษภาคม");
            sCboMonth.add("มิถุนายน");
            sCboMonth.add("กรกฎาคม");
            sCboMonth.add("สิงหาคม");
            sCboMonth.add("กันยายน");
            sCboMonth.add("ตุลาคม");
            sCboMonth.add("พฤศจิกายน");
            sCboMonth.add("ธันวาคม");
        }else if(Language.equals("English")){
            sCboMonth.add("January");
            sCboMonth.add("February");
            sCboMonth.add("March");
            sCboMonth.add("April");
            sCboMonth.add("May");
            sCboMonth.add("June");
            sCboMonth.add("July");
            sCboMonth.add("August");
            sCboMonth.add("September");
            sCboMonth.add("October");
            sCboMonth.add("November");
            sCboMonth.add("December");
        }

    }
    public void refresh(){
        hostDailyGroupByFoods="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"DailyGroupByFoods.php";

        hostSaveOrder="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"saveTOrder.php";

        hostGetArea="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getArea.php";
        hostGetTable="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getTable.php";
        hostGetRes="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getRes.php";
        hostGetFoodsType ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getFoodsType.php";
        hostGetFoodsCat ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getFoodsCat.php";
        hostGetPrinterName ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getPrinterName.php";
        hostGetFoods ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getFoods.php";
        hostGetUser ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"getUser.php";

        hostOrderByTableCode ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"OrderByTableCode.php";
        hostBillByTableId ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillByTableId.php";
        hostBillByBillCode ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillByBillCode.php";
        hostBillDetailByBillId ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillDetailByBillId.php";
        hostBillDetailByBillCode ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillDetailByBillCode.php";

        hostBillInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillInsert.php";
        hostBillUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillUpdate.php";
        hostBillDetailInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillDetailInsert.php";
        hostBillDetailUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillDetailUpdate.php";
        hostBillVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillVoid.php";
        hostBillCloseDay ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"BillByCloseDay.php";

        hostCloseDayInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ClosedayInsert.php";
        hostCloseDayVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ClosedayVoid.php";

        hostFoodsInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsInsert.php";
        hostFoodsUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsUpdate.php";
        hostFoodsSearch ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsSearch.php";
        hostFoodsVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsVoid.php";

        hostAreaInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaInsert.php";
        hostAreaUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaUpdate.php";
        hostAreaVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaVoid.php";
        hostAreaSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"AreaSelectByID.php";

        hostTableInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableInsert.php";
        hostTableUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableUpdate.php";
        hostTableVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableVoid.php";
        hostTableSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableSelectByID.php";
        hostTableChange ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"TableChange.php";

        hostResInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResInsert.php";
        hostResUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResUpdate.php";
        hostResVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResVoid.php";
        hostResSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"ResSelectByID.php";

        hostFoodsTypeInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsTypeInsert.php";
        hostFoodsTypeUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsTypeUpdate.php";
        hostFoodsTypeVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsPrintVoid.php";
        hostFoodsTypeSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsTypeSelectByID.php";

        hostFoodsCatInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatInsert.php";
        hostFoodsCatUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatUpdate.php";
        hostFoodsCatVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatVoid.php";
        hostFoodsCatSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"FoodsCatSelectByID.php";

        hostSelectFoods="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"selectFoods.php";
        hostSelectFoodsByID="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"selectFoodsByID.php";
        hostSelectFoodsByCode="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"selectFoodsByCode.php";

        hostUserSelectAll ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserSelectAll.php";
        hostUserSelectByID ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserSelectByID.php";
        hostUserInsert ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserInsert.php";
        hostUserUpdate ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserUpdate.php";
        hostUserVoid ="http://"+hostIP+":"+hostPORT+"/"+ hostWebDirectory +"UserVoid.php";
    }
    private void setCboTable(){
        //sCboTable.add("โต๊ะ 1");
        //sCboTable.add("โต๊ะ 2");
        //sCboTable.add("โต๊ะ 3");
        //sCboTable.add("โต๊ะ 4");
        //sCboTable.add("โต๊ะ 5");
        //sCboTable.add("โต๊ะ 6");
        //sCboTable.add("โต๊ะ 7");
        //sCboTable.add("โต๊ะ 8");
        //sCboTable.add("โต๊ะ 9");
        //sCboTable.add("โต๊ะ 10");
        //sCboTable.add("โต๊ะ 11");

        //sCboArea.add("ในร้าน");
        //sCboArea.add("ฟุตบาท");
        //sCboArea.add("ในสวน");
    }
    public String getFoodsSpecific(String fsName, String flag){
        String ab="";
        for(int i=0;i<sArea.size();i++){
            String[] aa = sArea.get(i).split("@");
            if(fsName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getFoods(String foodsName, String flag){
        String ab="";
        for(int i=0;i<sFoods.size();i++){
            String[] aa = sFoods.get(i).split("@");
            if(foodsName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getFoodsToName(String foods, String flag){
        String ab="";
        for(int i=0;i<sFoods.size();i++){
            String[] aa = sFoods.get(i).split("@");
            if(flag.equals("code")) {
                if (foods.equals(aa[1])) {
                    ab = aa[2];
                    break;
                }
            }else if(flag.equals("idtocode")){
                if (foods.equals(aa[0])) {
                    ab = aa[1];
                    break;
                }
            }else{
                if(foods.equals(aa[0])){
                    ab = aa[2];
                    break;
                }
            }
        }
        return ab;
    }
    public String getArea(String areaName, String flag){
        String ab="";
        for(int i=0;i<sArea.size();i++){
            String[] aa = sArea.get(i).split("@");
            if(areaName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getAreaToName(String area, String flag){
        String ab="";
        for(int i=0;i<sArea.size();i++){
            String[] aa = sArea.get(i).split("@");
            if(flag.equals("code")){
                if(area.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(area.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getTable(String tableName, String flag){
        String ab="";
        for(int i=0;i<sTable.size();i++){
            String[] aa = sTable.get(i).split("@");
            if(tableName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getTableToName(String table, String flag){
        String ab="";
        for(int i=0;i<sTable.size();i++){
            String[] aa = sTable.get(i).split("@");
            if(flag.equals("code")) {
                if (table.equals(aa[1])) {
                    ab = aa[2];
                }
            }else if(flag.equals("idtocode")){
                if (table.equals(aa[0])) {
                    ab = aa[1];
                }
            }else{
                if(table.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getFoodsCategory(String foodsCatName, String flag){
        String ab="";
        for(int i=0;i<sFoodsCategory.size();i++){
            String[] aa = sFoodsCategory.get(i).split("@");
            if(foodsCatName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getFoodsCategoryToName(String foodsCat, String flag){
        String ab="";
        for(int i=0;i<sFoodsCategory.size();i++){
            String[] aa = sFoodsCategory.get(i).split("@");
            if(flag.equals("code")){
                if(foodsCat.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(foodsCat.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getFoodsType(String foodsTypeName, String flag){
        String ab="";
        for(int i=0;i<sFoodsType.size();i++){
            String[] aa = sFoodsType.get(i).split("@");
            if(foodsTypeName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getFoodsTypeToName(String foodsType, String flag){
        String ab="";
        for(int i=0;i<sFoodsType.size();i++){
            String[] aa = sFoodsType.get(i).split("@");
            if(flag.equals("code")){
                if(foodsType.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(foodsType.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getRes(String resName, String flag){
        String ab="";
        if(resName==null) return"";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(resName.equals(aa[2])){
                if(flag.equals("code")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getResToName(String res, String flag){
        String ab="";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(flag.equals("code")){
                if(res.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(res.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
    public String getPrinterName(String printerName, String flag){
        String ab="";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(printerName.equals(aa[2])){
                if(flag.equals("ip")){
                    ab = aa[1];
                }else{
                    ab = aa[0];
                }
            }
        }
        return ab;
    }
    public String getPrinterNameToName(String printerName, String flag){
        String ab="";
        for(int i=0;i<sRes.size();i++){
            String[] aa = sRes.get(i).split("@");
            if(flag.equals("ip")){
                if(printerName.equals(aa[1])){
                    ab = aa[2];
                }
            }else{
                if(printerName.equals(aa[0])){
                    ab = aa[2];
                }
            }
        }
        return ab;
    }
//    public String getResNameDefault(){
//        String ab="";
//        for(int i=0;i<sRes.size();i++){
//            String[] aa = sRes.get(i).split("@");
//            aa
//        }
//        return ab;
//    }
    public void setInitial(FileOutputStream ot,String node, String data) {
        String xmlFile = "initial.xml";
        String userNAme = "username";
        String password = "password";
        try {
//            xmlFile = getResources().getAssets()+"initial.xml";
            FileOutputStream fos = new FileOutputStream("com.nakoyagarden.ekapop.restaurant.assets/initial.xml");
//            ot = getApplicationContext().openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "Restaurant");
            xmlSerializer.startTag(null, node);
            xmlSerializer.text(data);
            xmlSerializer.endTag(null, node);
            xmlSerializer.endTag(null, "Restaurant");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            ot.write(dataWrite.getBytes());
            ot.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String getInitial(InputStream is,String node){
//        public List<Employee> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        String aa="", text="";
        try {
//            InputStream is = getAssets().open("initial.xml");
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("employee")) {
                            // create a new instance of employee
//                                employee = new Employee();
                        }
                        break;

                    case XmlPullParser.TEXT:
                            text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(node)) {
                            aa = text;
                            // add employee object to list
//                                employees.add(employee);
                        } else if (tagname.equalsIgnoreCase("name")) {
//                                employee.setName(text);
                        } else if (tagname.equalsIgnoreCase("genid")) {
//                                employee.setId(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("department")) {
//                                employee.setDepartment(text);
                        } else if (tagname.equalsIgnoreCase("email")) {
//                                employee.setEmail(text);
                        } else if (tagname.equalsIgnoreCase("type")) {
//                                employee.setType(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aa;
    }
    public void setHostName(String hostname){
        String fileName = "initial.cnf";
        FileOutputStream outputStream;
//        String string = hostname;
        try {
            outputStream = openFileOutput("initial.cnf", Context.MODE_PRIVATE);
            outputStream.write(hostname.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static String getHostName (String filename) throws Exception {
//        try {
//            InputStream is = getAssets().open(filename);
//            int size = is.available();
//            byte buffer[] = new byte[size];
//            is.read(buffer);
//            is.close();
//            return new String(buffer);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "" ;
//        }
//    }
    public String chkNumber(String txt){
        if(txt.equals("")){
            return "0.0";
        }else{
            return txt.trim();
        }
    }
    public String StringNull(String txt){
        if(txt.equals("null")){
            return "";
        }else{
            return txt.trim();
        }
    }
    public String chkUserByPassword(String password){
        String ab="";
        for(int i=0;i<sUser.size();i++){
            String[] aa = sUser.get(i).split("@");
            if(password.equals(aa[3])){
                ab = aa[0];
            }
        }
        return ab;
    }
    public String chkUserByPassword1(String password){
        String ab="";
        for(int i=0;i<sUser.size();i++){
            String[] aa = sUser.get(i).split("@");
            if(password.equals(aa[3])){
                ab = aa[2];
            }
        }
        return ab;
    }
    public Boolean chkPassword(String password){
        String ab="";
        Boolean chk=false;
        for(int i=0;i<sUser.size();i++){
            String[] aa = sUser.get(i).split("@");
            if(password.equals(aa[3])){
                chk=true;
            }
        }
        return chk;
    }
    public Boolean chkPasswordOrder(String password){
        String ab="";
        Boolean chk=false;
        for(int i=0;i<sUser.size();i++){
            String[] aa = sUser.get(i).split("@");
            if(password.equals(aa[3])){
                if(aa[4].equals("1")){
                    chk=true;
                }else if (aa[4].equals("2")){
                    chk = true;
                }
            }
        }
        return chk;
    }
    public Boolean chkPasswordBill(String password){
        String ab="";
        Boolean chk=false;
        for(int i=0;i<sUser.size();i++){
            String[] aa = sUser.get(i).split("@");
            if(password.equals(aa[3])){
                if(aa[4].equals("1")){
                    chk=true;
                }else if (aa[4].equals("2")){
                    chk = true;
                }else if (aa[4].equals("3")){
                    chk = true;
                }
            }
        }
        return chk;
    }
    public Boolean chkPasswordCloseDay(String password){
        String ab="";
        Boolean chk=false;
        for(int i=0;i<sUser.size();i++){
            String[] aa = sUser.get(i).split("@");
            if(password.equals(aa[3])){
                if(aa[4].equals("1")){
                    chk=true;
                }else if (aa[4].equals("2")){
                    chk = true;
                }else if (aa[4].equals("3")){
                    chk = true;
                }else if (aa[4].equals("4")){
                    chk = true;
                }
            }
        }
        return chk;
    }
    public Boolean chkPasswordVoid(String password){
        String ab="";
        Boolean chk=false;
        for(int i=0;i<sUser.size();i++){
            String[] aa = sUser.get(i).split("@");
            if(password.equals(aa[3])){
                if(aa[4].equals("1")){
                    chk=true;
                }
            }
        }
        return chk;
    }
    private void getText(Context context){
        try {
            File file =context.getFileStreamPath("initial.cnf");
            final int READ_BLOCK_SIZE = 100;
            FileInputStream fileIn=context.openFileInput(file.getName());
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
                this.HostID=hostID;
                this.AccessMethod=AccessMethod;
                Log.d("getText() language ",language);
                if(language.equals("Thai")){
                    Language="Thai";
                }else if(language.equals("English")){
                    Language="English";
                }else{
                    Language="Thai";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("getText() ",e.getMessage());
        }
    }
}
