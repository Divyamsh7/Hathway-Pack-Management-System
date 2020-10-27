package com.example.projsetnic;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonActivation;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  buttonAddItem = (Button)findViewById(R.id.btn_add_item);
        // buttonAddItem.setOnClickListener(this);
        //   buttonList = (Button)findViewById(R.id.listRec);
        // buttonList.setOnClickListener(this);
        buttonActivation = (Button)findViewById(R.id.activation);
        buttonActivation.setOnClickListener(this);


        buttonLogin = (Button)findViewById(R.id.login);
        buttonLogin.setOnClickListener(this);


    }
   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
            System.exit(0);

    }*/
    @Override
    public void onClick(View v) {

        if(v==buttonActivation)
        {
            Intent intent1 = new Intent(getApplicationContext(),renewal.class);
            startActivity(intent1);
        }
        else if(v==buttonLogin){
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }
       /* else if(v==buttonList){
            Intent intent = new Intent(getApplicationContext(),list.class);
            startActivity(intent);
        }*/

      /* else if(v==buttonAddItem){

            Intent intent = new Intent(getApplicationContext(),AddItem.class);
            startActivity(intent);*/
    }




}