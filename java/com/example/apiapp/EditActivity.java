package com.example.apiapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    EditText nameEt,ageEt,idEt,genderEt;
    ProgressBar progressBar;
    Button button;
    String name,age,gender,id;
    String url = "https://apiapp1.000webhostapp.com/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameEt = findViewById(R.id.nameEt);
        ageEt = findViewById(R.id.ageEt);
        idEt = findViewById(R.id.idEt);
        genderEt = findViewById(R.id.genderEt);
        button = findViewById(R.id.btn_update);
        progressBar = findViewById(R.id.pb);


        Bundle bundle =  getIntent().getExtras();

        if (bundle != null){

            name = bundle.getString("n");
            id = bundle.getString("i");
            age = bundle.getString("a");
            gender = bundle.getString("g");

            ageEt.setText(age);
            nameEt.setText(name);
            idEt.setText(id);
            genderEt.setText(gender);

        }else {
            Toast.makeText(this, "not received", Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                String nameup = nameEt.getText().toString();
                String ageup = ageEt.getText().toString();
                String genderup = genderEt.getText().toString();


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(EditActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(EditActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }){

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> param = new HashMap<String, String>();

                        param.put("id",idEt.getText().toString());
                        param.put("name",nameup);
                        param.put("age",ageup);
                        param.put("gender",genderup);

                        return param;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(EditActivity.this);
                requestQueue.add(request);



            }
        });


    }
}