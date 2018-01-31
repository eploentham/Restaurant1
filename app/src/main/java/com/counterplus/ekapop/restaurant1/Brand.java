package com.counterplus.ekapop.restaurant1;

/**
 * Created by ekapop on 1/31/2018.
 */

public class Brand extends Database {
    public String ID="", Code="", Name="", Remark="", Active="", Sort1="";
    public String dbID="brand_id", dbCode="brand_code", dbName="brand_name", dbRemark="remark", dbActive="active", dbSort1="sort1";

    public String cBrandSQLi=creaT+" "+tbNameBrand+" "
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

            +"); ";
    public String cDropBrand="DROP TABLE IF EXISTS "+tbNameBrand+" ;";
}
