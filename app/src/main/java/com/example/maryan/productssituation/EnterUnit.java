package com.example.maryan.productssituation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterUnit extends AppCompatActivity {

    private EditText txtUnit;
    private EditText txtSymbol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_unit);

        this.setTitle("Enter new measure unit");

        txtUnit=(EditText)findViewById(R.id.txtUnit);
        txtSymbol=(EditText)findViewById(R.id.txtUnitSymbol);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void Button_Click(View v){

        Button btn=(Button)v;
        //System.out.println("@###################### PASSS 1");
        String btn_text=btn.getText().toString().toLowerCase();

        if (btn_text.equals("ok")){
            addNewUnit();
        }

        if (btn_text.equals("clear")){
          clearBox();
        }


    }

    void addNewUnit(){

        String unitName=txtUnit.getText().toString();
        String unitSymbol=txtSymbol.getText().toString();

        if (!unitName.equals("")&&(!unitSymbol.equals(""))) {

            Unit unit=new Unit(unitName,unitSymbol);

            DBHelper_Products dbHelper_products=new DBHelper_Products(this);

            int err=dbHelper_products.addUnit(unit);

                if (err==19) errMsg("Unit already exists!");
                if (!(err==19)&&!(err==0)) errMsg("Unknown error!");

                if (err==-1) {
                    errMsg("Unit added!");
                    clearBox();
                }
            }
    }

    void clearBox(){
        txtUnit.setText("");
        txtSymbol.setText("");
        txtUnit.requestFocus();
    }

    void errMsg(String errString){
        Toast.makeText(this,errString,Toast.LENGTH_SHORT).show();
    }
}
