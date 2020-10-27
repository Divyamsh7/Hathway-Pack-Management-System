package com.example.projsetnic;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class amountwithdrawn extends AppCompatActivity implements View.OnClickListener {
    Button buttonWithdraw;
    EditText amount, editTextPassword;
    ProgressDialog loading;
    RadioButton buttonHway, buttonSiti;
    RadioButton radioButton;
    RadioGroup radioGroup;
    String HwayorSiti,option1,option2,amountstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amountwithdrawn);
        amount = (EditText) findViewById(R.id.editTextAmountWithdrawn);
        buttonHway =  findViewById(R.id.radioButtonHway);
        buttonSiti = findViewById(R.id.radioButtonSiti);
        radioGroup = findViewById(R.id.radioGroup);
        buttonWithdraw = (Button) findViewById(R.id.buttonWithdraw);
        buttonWithdraw.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(getApplicationContext(), mypage.class);
        startActivity(intent1);

    }
    private void withdraw() {

        amountstr = amount.getText().toString().trim();
        // Toast.makeText(getApplicationContext(),"box number"+box,Toast.LENGTH_SHORT).show();
        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxXLZ_YtsY1nNesqJ0T-De2KqGTLSjegnjWP-KzjI_mKrDPBvs/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                         Toast.makeText(amountwithdrawn.this, response, Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(), amountwithdrawn.class);
                        startActivity(intent1);
                      //  parseItems(response);
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
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                // Toast.makeText(this,"selected "+radioButton.getText(), Toast.LENGTH_SHORT).show();
                HwayorSiti = radioButton.getText().toString();
                option1 = "Hathway";
                option2 = "Siti Cable";
                parmas.put("action", "amountwithdrawn");
                if(HwayorSiti.equals(option1))
                {
                    parmas.put("HwayorSiti", option1);
                    parmas.put("amount", amountstr);
                }
                else if(HwayorSiti.equals(option2))
                {
                    parmas.put("HwayorSiti", option2);
                    parmas.put("amount", amountstr);
                }
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


        if (v == buttonWithdraw) {
            withdraw();
            //Define what to do when button is clicked
        }

    }
}
