<?php
//include "connectdb.inc.php";

//$conn = new ConnectDB();
$resultArray = array();
//$resultArray["area"] = array();
$objConnect = mysql_connect("localhost",$_POST['userdb'],$_POST['passworddb']);
$objDB = mysql_select_db("restaurant");
mysql_query("SET NAMES UTF8");

    $obj = mysql_query("update b_user Set active = '3', status_void = '1', void_user = '".$_POST['void_user']."', void_date = now() Where user_id = '".$_POST['user_id']."' ");
    $ok="";
    $err="";
    if(!$objQuery){
        $ok="0";
        $err= mysql_error();
    }else{
        $ok="1";
    }
    //$sql = "Update t_order Set status_bill ='2', bill_id = ".$_POST['bill_id']." Where order_id ='".$_POST['order_id']."'";
    //$objQuery = mysql_query("Update t_order Set status_bill ='1' Where bill_id ='".$_POST['bill_id']."'");
    $tmp = array();
    $tmp["status"] = "1";
    $tmp["message"] = "ยกเลิกเรียบร้อย";
    $tmp["sql"] = "update b_user Set active = '3', status_void = '1', void_user = '".$_POST['void_user']."', void_date = now() Where user_id = '".$_POST['user_id']."' ";
    $tmp["error"] = $err;

array_push($resultArray,$tmp);
header('Content-Type: application/json');
echo json_encode($resultArray);

mysql_close($objConnect);
	//$pid = $_POST['order_id'];
	//$name = $_POST['foods_code'];
	//$price = $_POST['price'];
	//$description = $_POST['description'];
	//$conn->getArea();

?>