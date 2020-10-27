package com.example.projsetnic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class multipledates extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFromDate, editTextToDate;
    TextView textViewAmount,textView8;
    Button buttonSubmit;
    ProgressDialog loading;
    String FromDate,ToDate,Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amountmultiple);
        editTextFromDate = (EditText) findViewById(R.id.editTextFromDate);
        editTextToDate = (EditText) findViewById(R.id.editTextToDate);
        textView8 = (TextView) findViewById(R.id.textView8);
        textViewAmount = (TextView) findViewById(R.id.textViewAmount);
        textView8.setVisibility(View.GONE);
        textViewAmount.setVisibility(View.GONE);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(getApplicationContext(), mypage.class);
        startActivity(intent1);

    }

    private void onSubmitClick() {

        FromDate = editTextFromDate.getText().toString().trim();
        ToDate = editTextToDate.getText().toString().trim();
        loading = ProgressDialog.show(this, "Loading", "please wait", false, true);
//SEARCH
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxXLZ_YtsY1nNesqJ0T-De2KqGTLSjegnjWP-KzjI_mKrDPBvs/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        parseItems(response);
                       // Toast.makeText(multipledates.this, response, Toast.LENGTH_LONG).show();
                        //Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        //startActivity(intent1);
                        //parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                //here we pass params
                parmas.put("action", "onAmountClickMultiple");
                parmas.put("FromDate",FromDate);
                parmas.put("ToDate",ToDate);
                return parmas;

            }

        };

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void parseItems(String jsonResposnce) {

        final ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("rec");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                // String row = jo.getString("row");
                Amount = jo.getString("Amount");
                textView8.setVisibility(View.VISIBLE);
                textViewAmount.setVisibility(View.VISIBLE);
                textViewAmount.setText(Amount);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        if (v == buttonSubmit) {
            onSubmitClick();
        }

    }
}

