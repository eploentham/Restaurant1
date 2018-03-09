package com.counterplus.ekapop.restaurant1;

/**
 * Created by ekapop on 1/29/2018.
 */

public class FoodsPrint extends Database {
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="", VoidDate="", VoidUser="",FlagPrinterConnect="";
    public String dbID="foods_print_id", dbCode="foods_print_code", dbName="foods_print_name", dbRemark="remark", dbActive="active", dbSort1="sort1", dbVoidDate="void_date", dbVoidUser="void_user";

    public String IP="", PrintBrand="", PrintModel="";
    public String dbIP="ip", dbPrintBrand="brand", dbPrintModel="model", dbFlagPrinterConnect="flag_printer_connect";

    public String cFoodsPrintMySQL=creaT+" '"+dbNameD+"'.'"+tbNameFoodsPrint+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbRemark+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbSort1+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";
    public String cFoodsPrintSQLi=creaT+" "+tbNameFoodsPrint+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbIP+" "+tex+"  NULL "
            +", "+dbPrintBrand+" "+tex+"  NULL "
            +", "+dbPrintModel+" "+tex+"  NULL "
            +", "+dbFlagPrinterConnect+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbSort1+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +", "+dbVoidDate+" "+tex+"  NULL "
            +", "+dbVoidUser+" "+tex+"  NULL "
            +"); ";
    public String cDropFoodsPrint="DROP TABLE IF EXISTS "+tbNameFoodsPrint+" ;";
}
