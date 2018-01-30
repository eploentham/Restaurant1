package com.counterplus.ekapop.restaurant1;

/**
 * Created by ekapop on 1/26/2018.
 */

public class FoodsSpecific extends Database {
    public String ID="", Code="", Name="", FoodsId="", FoodsCode="", Price="", Sort1="", Active="", VoidDate="", VoidUser="";
    public String dbID="foods_spec_id", dbCode="foods_spec_code", dbName="foods_spec_name", dbFoodsId="foods_id", dbFoodsCode="foods_code", dbPrice="price", dbSort1="sort1", dbActive="active", dbVoidDate="void_date", dbVoidUser="void_user";

    public String cFoodsSpecificMySQL=creaT+" '"+dbNameD+"'.'"+tbNameFoodsSpecific+"' "
            +"('"+dbID+"' "+varc+" NOT NULL "
            +", '"+dbCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbName+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbPrice+"' "+deci+"  NULL "+ defMySQL_v
            +", '"+dbActive+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbFoodsId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbFoodsCode+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbDateCreate+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbDateModi+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbHostId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbBranchId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbDeviceId+"' "+varc+"  NULL "+ defMySQL_v
            +", '"+dbSort1+"' "+varc+"  NULL "+ defMySQL_v
            +", PRIMARY KEY('"+dbID+"')) "
            +" ENGINE = MyISAM "
            +" CHARACTER SET utf8 COLLATE utf8_bim "
            +"COMMENT = ''";

    public String cFoodsSpecificSQLi=creaT+" "+tbNameFoodsSpecific+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbPrice+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbFoodsId+" "+tex+"  NULL "
            +", "+dbFoodsCode+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "
            +", "+dbSort1+" "+tex+"  NULL "
            +", "+dbVoidDate+" "+tex+"  NULL "
            +", "+dbVoidUser+" "+tex+"  NULL "
            +"); ";
    public String cDropFoodsSpecific="DROP TABLE IF EXISTS "+tbNameFoodsSpecific+" ;";
}
