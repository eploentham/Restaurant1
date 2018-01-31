package com.counterplus.ekapop.restaurant1;

/**
 * Created by ekapop on 1/31/2018.
 */

public class Model extends Database {
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="", BrandId="";
    public String dbID="model_id", dbCode="model_code", dbName="model_name", dbRemark="remark", dbActive="active", dbSort1="sort1", dbBrandId="brand_id";

    public String cModelSQLi =creaT+" "+tbNameModel+" "
            +"( "+dbID+" "+tex+" PRIMARY KEY "
            +", "+dbCode+" "+tex+"  NULL "
            +", "+dbName+" "+tex+"  NULL "
            +", "+dbRemark+" "+tex+"  NULL "
            +", "+dbBrandId+" "+tex+"  NULL "
            +", "+dbActive+" "+tex+"  NULL "
            +", "+dbSort1+" "+tex+"  NULL "
            +", "+dbDateCreate+" "+tex+"  NULL "
            +", "+dbDateModi+" "+tex+"  NULL "
            +", "+dbHostId+" "+tex+"  NULL "
            +", "+dbBranchId+" "+tex+"  NULL "
            +", "+dbDeviceId+" "+tex+"  NULL "

            +"); ";
    public String cDropModel ="DROP TABLE IF EXISTS "+tbNameModel+" ;";
}
