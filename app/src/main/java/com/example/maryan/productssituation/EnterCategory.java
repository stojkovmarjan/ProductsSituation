package com.example.maryan.productssituation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterCategory extends AppCompatActivity {

    private EditText txtCategory;
    private EditText txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_category);

        this.setTitle("Enter new category");

        txtCategory=(EditText)findViewById(R.id.txtCategory);
        txtDescription=(EditText)findViewById(R.id.txtDescription);
    }

    public void Button_Click(View v){
        Button btn=(Button)v;
        String btnString=((Button) v).getText().toString().toLowerCase();

        if (btnString.equals("ok")){
            addCategory();
        }

        if (btnString.equals("clear")){
            clearBox();
        }
    }

    void addCategory() {

        String catString=txtCategory.getText().toString();
        String descString=txtDescription.getText().toString();

        Category category=new Category(catString,descString);

        DBHelper_Products dbHelper_products=new DBHelper_Products(this);

        int errCode=dbHelper_products.addCategory(category);

        if (errCode==19) errMsg("Category allready exists!");

        if ((!(errCode==-1))&&(!(errCode==19))) errMsg("Unknown error! Data not added. "+String.valueOf(errCode));

        if (errCode==-1) {
            errMsg("Category added!");
            clearBox();
        }

    }

    void clearBox(){
        txtDescription.setText("");
        txtCategory.setText("");
        txtCategory.requestFocus();
    }

    void errMsg(String errString){
        Toast.makeText(this,errString,Toast.LENGTH_SHORT).show();
    }

}
