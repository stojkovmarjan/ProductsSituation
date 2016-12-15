package com.example.maryan.productssituation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean add=false;
        setContentView(R.layout.activity_main);

    }

    public void Button_Click(View v){
        Button btn=(Button)v;
        String btn_text=btn.getText().toString().toLowerCase();

        if (btn_text.equals("units")){
            Intent intent=new Intent(this, EnterUnit.class);
            startActivity(intent);

        }

        if (btn_text.equals("categories")){
            Intent intent=new Intent(this, EnterCategory.class);
            startActivity(intent);
        }

        if (btn_text.equals("products")){
            Intent intent=new Intent(this, EnterProduct.class);
            startActivity(intent);
        }
    }
}
