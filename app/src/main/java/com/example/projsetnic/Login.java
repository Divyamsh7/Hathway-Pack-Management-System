package com.example.projsetnic;
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

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button buttonLogin;
    EditText editTextUserName,editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLoginn);
        buttonLogin.setOnClickListener(this);
    }
    private void login(){
        final ProgressDialog loading = ProgressDialog.show(this,"Signing in","Please wait");
        final String uname = editTextUserName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String uname1 = "prabhakar";
        final String password1 = "hologram";
        if(uname.equals(uname1)&&password.equals(password1))
        {
            Intent intent = new Intent(getApplicationContext(),mypage.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Incorrect Username or Password", Toast.LENGTH_SHORT).show();
        }
        loading.dismiss();

    }
    @Override
    public void onClick(View v) {


        if(v==buttonLogin){
            login();

            //Define what to do when button is clicked
        }
    }
}
