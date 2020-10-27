package com.example.projsetnic;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class renewal extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    SimpleAdapter adapter;
    ProgressDialog loading;
    TextView textViewName, textViewAddress, textViewAmount, textViewName1, textViewAddress1, textViewAmount1;
    EditText editTextBoxNumber;
    Button buttonSearch, buttonRenew,buttonShowPlans;
    String box,lowbox,totNumberstr,finalbox;
    int totNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.renewal);
        listView = (ListView) findViewById(R.id.listPlans);

        editTextBoxNumber = findViewById(R.id.editTextBoxNumber);

        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName1 = (TextView) findViewById(R.id.textViewName1);


        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewAddress1 = (TextView) findViewById(R.id.textViewAddress1);


        textViewAmount = (TextView) findViewById(R.id.textViewAmount);
        textViewAmount1 = (TextView) findViewById(R.id.textViewAmount1);

        buttonShowPlans = (Button) findViewById(R.id.buttonShowPlans);
        buttonShowPlans.setOnClickListener(this);

        buttonRenew = (Button) findViewById(R.id.buttonRenewal);
        buttonRenew.setOnClickListener(this);


        textViewName.setVisibility(View.GONE);
        textViewName1.setVisibility(View.GONE);

        textViewAddress.setVisibility(View.GONE);
        textViewAddress1.setVisibility(View.GONE);

        textViewAmount.setVisibility(View.GONE);
        textViewAmount1.setVisibility(View.GONE);

        buttonShowPlans.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        buttonRenew.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);

    }

    private void getName() {
        lowbox = editTextBoxNumber.getText().toString().trim();
       // Toast.makeText(getApplicationContext(),"Box Number"+ box.toUpperCase(),Toast.LENGTH_SHORT).show();
        box=lowbox.toUpperCase();
        finalbox = boxnumber(box);
        //boxnumber(box);
        //Toast.makeText(getApplicationContext(),"box number"+finalbox,Toast.LENGTH_SHORT).show();
        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxXLZ_YtsY1nNesqJ0T-De2KqGTLSjegnjWP-KzjI_mKrDPBvs/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        // Toast.makeText(renewal.this, response, Toast.LENGTH_LONG).show();
                        //Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        //startActivity(intent1);
                        parseItems(response);
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
                parmas.put("action", "getName");
                parmas.put("Boxnum", finalbox);
                return parmas;
            }
        };

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
    private String boxnumber(String box){

        //Toast.makeText(getApplicationContext(),"Invalid Box Number",Toast.LENGTH_SHORT).show();

        if(box.length()!=12)
        {
            Toast.makeText(getApplicationContext(),"Invalid Box Number",Toast.LENGTH_SHORT).show();
        }

         else if(box.charAt(0)=='1'||box.charAt(0)=='2'||box.charAt(0)=='3'||box.charAt(0)=='0'&&box.charAt(1)=='0'&&box.charAt(2)=='1') {
            //Toast.makeText(getApplicationContext(),"In else condition",Toast.LENGTH_SHORT).show();
            char[] temp = new char[17];
            int ind = 0, pos = 1;

             for(int i = 0;i<12;i++)
             {
                 //temp[i]=box.charAt(i);
                 if(pos==1)
                 temp[ind]=box.charAt(i);
                 else if(pos==2)
                 {
                     temp[ind]=box.charAt(i);
                     ind++;
                     if(i==11)
                         break;
                     temp[ind]=':';
                     pos=0;
                 }
                 ind++;
                 pos++;

             }
             String finbox= String.copyValueOf(temp);
            // Toast.makeText(getApplicationContext(),"box number"+finbox,Toast.LENGTH_SHORT).show();
             return finbox;
         }

        return box;
    }
    private void onRenewClick() {

        totNumberstr = String.valueOf(totNumber);
        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);
//SEARCH
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxXLZ_YtsY1nNesqJ0T-De2KqGTLSjegnjWP-KzjI_mKrDPBvs/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(renewal.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),renewal.class);
                        startActivity(intent);
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
                    parmas.put("action","onRenewClick1");
                    parmas.put("Boxnum", finalbox);
                return parmas;

                }

        };

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }
    @Override
    public void onClick(View v) {
        if (v == buttonSearch) {
           // box = editTextBoxNumber.getText().toString().trim();
            // Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),"box number"+box,Toast.LENGTH_SHORT).show();
            getName();


            //Define what to do when button is clicked
        }
        else if (v == buttonShowPlans) {

            listView.setVisibility(View.VISIBLE);

        }
        else if (v == buttonRenew) {

            onRenewClick();
        }

    }

    private void parseItems(String jsonResposnce) {


        String Name, Address;
        final ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("rec");

          //  Toast.makeText(getApplicationContext(),"I'm here",Toast.LENGTH_SHORT).show();
            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                Name = jo.getString("Name");
                Address = jo.getString("Address");

               // Toast.makeText(getApplicationContext(),"Name"+Name,Toast.LENGTH_SHORT).show();

                    textViewName.setVisibility(View.VISIBLE);
                    textViewName1.setVisibility(View.VISIBLE);

                    textViewAddress.setVisibility(View.VISIBLE);
                    textViewAddress1.setVisibility(View.VISIBLE);

                   // textViewAmount.setVisibility(View.VISIBLE);
                   // textViewAmount1.setVisibility(View.VISIBLE);

                   // buttonShowPlans.setVisibility(View.VISIBLE);
                    buttonRenew.setVisibility(View.VISIBLE);

                    textViewName1.setText(Name);
                    textViewAddress1.setText(Address);
                  //  textViewAmount1.setText(totAmountstr);

            }


        }

        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}


