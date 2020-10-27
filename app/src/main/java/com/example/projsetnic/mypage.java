package com.example.projsetnic;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
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

public class mypage extends AppCompatActivity implements View.OnClickListener {
    Button buttonSingleDay,buttonMultipleDays,buttonRenewal,buttonWithdraw;
    EditText editTextUserName,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);
        buttonSingleDay = (Button)findViewById(R.id.buttonSingleDay);
        buttonSingleDay.setOnClickListener(this);
        buttonMultipleDays = (Button)findViewById(R.id.buttonMultipleDays);
        buttonMultipleDays.setOnClickListener(this);
        buttonWithdraw = (Button)findViewById(R.id.buttonWithdraw);
        buttonWithdraw.setOnClickListener(this);
        buttonRenewal = (Button)findViewById(R.id.buttonRenewal);
        buttonRenewal.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);

    }
    private void SingleDay(){

            Intent intent = new Intent(getApplicationContext(),singledate.class);
            startActivity(intent);


    }
    private void MultipleDays(){

        Intent intent = new Intent(getApplicationContext(),multipledates.class);
        startActivity(intent);


    }
    private void Withdraw(){

        Intent intent = new Intent(getApplicationContext(),amountwithdrawn.class);
        startActivity(intent);


    }
    private void Renewal(){

        Intent intent = new Intent(getApplicationContext(),renewalLogin.class);
        startActivity(intent);


    }
    @Override
    public void onClick(View v) {


        if(v==buttonSingleDay){
            SingleDay();

            //Define what to do when button is clicked
        }
        if(v==buttonMultipleDays){
            MultipleDays();

            //Define what to do when button is clicked
        }
        if(v==buttonWithdraw){
            Withdraw();

            //Define what to do when button is clicked
        }
        if(v==buttonRenewal){
            Renewal();

            //Define what to do when button is clicked
        }
    }
}

