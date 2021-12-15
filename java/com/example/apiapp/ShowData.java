package com.example.apiapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowData extends AppCompatActivity {


    ListView listView;
    MyAdapter myAdapter;
    DataClass dataClass;
    String url = "https://apiapp1.000webhostapp.com/get.php";
    String deleteurl = "https://apiapp1.000webhostapp.com/delete.php";

    public static ArrayList<DataClass> dataClassArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listView = findViewById(R.id.listview);
        myAdapter = new MyAdapter(this,dataClassArrayList);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String age = dataClassArrayList.get(i).getAge();
                String gender = dataClassArrayList.get(i).getGender();
                String name = dataClassArrayList.get(i).getName();
                String id = dataClassArrayList.get(i).getId();

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowData.this);
                CharSequence[] items = {"Edit data","Delete Data"};
                builder.setTitle(dataClassArrayList.get(i).getName());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){
                            case 0:
                                Intent intent = new Intent(ShowData.this,EditActivity.class);
                                intent.putExtra("n",name);
                                intent.putExtra("a",age);
                                intent.putExtra("g",gender);
                                intent.putExtra("i",id);
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(dataClassArrayList.get(i).getId());
                                break;
                        }

                    }
                });

                builder.create().show();

            }
        });

        getData();

    }

    private void deleteData(String id) {

        StringRequest request = new StringRequest(Request.Method.POST, deleteurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("Data deleted")){
                    Toast.makeText(ShowData.this, "succesfully deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ShowData.this, "failed", Toast.LENGTH_SHORT).show();
                }


             //   Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ShowData.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param = new HashMap<String, String>();

                param.put("id",id);

                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ShowData.this);
        requestQueue.add(request);


    }


    private void getData() {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                dataClassArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")){

                        for (int i = 0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String name = object.getString("name");
                            String  age= object.getString("age");
                            String gender = object.getString("gender");

                            dataClass = new DataClass(id,name,age,gender);
                            dataClassArrayList.add(dataClass);
                            myAdapter.notifyDataSetChanged();
                        }
                    }

                }catch (Exception e){


                }

              //  Toast.makeText(ShowData.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ShowData.this,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(ShowData.this);
        requestQueue.add(request);

    }
}