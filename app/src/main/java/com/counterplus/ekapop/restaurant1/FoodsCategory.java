package com.counterplus.ekapop.restaurant1;

/**
 * Created by ekapop on 1/26/2018.
 */

public class FoodsCategory extends Database {
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="", VoidDate="", VoidUser="";
    public String dbID="foods_cat_id", dbCode="foods_cat_code", dbName="foods_cat_name", dbRemark="remark", dbActive="active", dbSort1="sort1", dbVoidDate="void_date", dbVoidUser="void_user";

    public String cFoodsCatMySQL=creaT+" '"+dbNameD+"'.'"+tbNameFoodsCat+"' "
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
    public String cFoodsCatSQLi=creaT+" "+tbNameFoodsCat+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
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
    public String cDropCatType="DROP TABLE IF EXISTS "+tbNameFoodsCat+" ;";
}
