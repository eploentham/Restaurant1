package com.counterplus.ekapop.restaurant1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodsCatViewActivity extends AppCompatActivity {

    JsonParser jsonparser = new JsonParser();
    String ab;
    JSONObject jobj = null;
    JSONArray jarrFc;
    private RestaurantControl rs;
    ListView lvFcView;
    Button btnFcAdd;
    Boolean pageLoad=false;
    public ArrayList<FoodsCategory> lRes = new ArrayList<FoodsCategory>();
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    ProgressDialog pd;
    DatabaseSQLi daS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_cat_view);

        pageLoad = true;
        lvFcView = findViewById(R.id.lvFcView);
        lvFcView.setBackgroundColor(getResources().getColor(R.color.BackScreenMailarap));

        rs = (RestaurantControl) getIntent().getSerializableExtra("RestaurantControl");
        daS = new DatabaseSQLi(this,"");

        btnFcAdd = findViewById(R.id.btnFcAdd);
        btnFcAdd.setText(R.string.btnIa1FoodsCatView);
        btnFcAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rs.ftID ="";
                Intent s1 = new Intent(view.getContext(), FoodsTypeAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });
        lvFcView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    JSONObject catObj = (JSONObject) jarrFc.get(i);
                    //String ID = catObj.getString("foods_id");
                    rs.ftID = catObj.getString("foods_type_id");
                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Intent s1 = new Intent(view.getContext(), FoodsTypeAddActivity.class);
                s1.putExtra("RestaurantControl",rs);
                startActivityForResult(s1, 0);
            }
        });

        pageLoad=false;
    }
    @Override
    protected void onResume() {
        if(rs.AccessMode.equals("Standalone")) {
            if(!pageLoad) {
                super.onResume();
                jarrFc = daS.FoodsCatSelectAll();
                setLvFoodsCat();
            }
        }else if(rs.AccessMode.equals("Internet")){
            if(!pageLoad){
                super.onResume();
                new retrieveFoodsCat().execute();
                setLvFoodsCat();
            }
        }else{
            if(!pageLoad){
                super.onResume();
                new retrieveFoodsCat().execute();
                setLvFoodsCat();
            }
        }
    }
    class retrieveFoodsCat extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... arg0) {
            //Log.d("Login attempt", jobj.toString());
            //try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("userdb",rs.UserDB));
            params.add(new BasicNameValuePair("passworddb",rs.PasswordDB));
            jarrFc = jsonparser.getJSONFromUrl(rs.hostGetFoodsType,params);
            //rs.jarrR = jarrR.toString();
            //} catch (JSONException e) {
            // TODO Auto-generated catch block
            //    e.printStackTrace();
            //}
            return ab;
        }
        @Override
        protected void onPostExecute(String ab){
            String a = ab;
            setLvFoodsCat();
            pd.dismiss();
        }
        @Override
        protected void onPreExecute() {
            pd = new ProgressDialog(FoodsCatViewActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setTitle("Loading...");
            pd.setMessage("Loading Bill ...");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgress(0);
            pd.show();
        }
    }
    private void setLvFoodsCat(){
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            //jarrR = jsonparser.getJSONFromUrl(rs.hostSelectFoods,params);
            //jarrR = jsonparser.getJSONFromUrl(rs.hostGetRes,params);
            if(jarrFc !=null){
                //jarrR =  new JSONArray(rs.jarrR);
                arrayList = new ArrayList<String>();
                //JSONArray categories = jobj.getJSONArray("area");
                //JSONArray json = new JSONArray(jobj);
                lRes.clear();
                for (int i = 0; i < jarrFc.length(); i++) {
                    JSONObject catObj = (JSONObject) jarrFc.get(i);
                    FoodsCategory a = new FoodsCategory();
                    a.ID = catObj.getString(a.dbID);
                    a.Code = catObj.getString(a.dbCode);
                    a.Name = catObj.getString(a.dbName);
                    a.Remark = catObj.getString(a.dbRemark);
                    a.Active = catObj.getString(a.dbActive);
                    //a.AreaID = catObj.getString("area_id");
                    a.Sort1 = catObj.getString(a.dbSort1);
                    lRes.add(a);
                    //arrayList.add(f.Code+" "+f.Name+" "+f.Price+" "+f.Remark+" ร้าน "+rs.getResToName(f.ResId,"genid")+" ประเภท "+rs.getFoodsTypeToName(f.TypeId,"genid")+" สถานะ "+f.StatusFoods+" เครื่องพิมพ์ "+f.PrinterName);
                    arrayList.add(a.Code+" "+a.Name+" "+a.Remark);
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        // Get the current item from ListView
                        View view = super.getView(position,convertView,parent);

                        // Get the Layout Parameters for ListView Current Item View
                        ViewGroup.LayoutParams params = view.getLayoutParams();

                        // Set the height of the Item View
                        params.height = 60;
                        view.setLayoutParams(params);

                        return view;
                    }
                };
                //lvAvView = (ListView)findViewById(R.genid.lvFoods);
                lvFcView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
