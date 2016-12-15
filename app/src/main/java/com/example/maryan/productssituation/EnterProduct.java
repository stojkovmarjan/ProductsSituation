package com.example.maryan.productssituation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


public class EnterProduct extends AppCompatActivity {

    EditText txtProduct;
    EditText txtDescription;
    AutoCompleteTextView cmbUnit;
    AutoCompleteTextView cmbCategory;
    //Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_product);

        txtProduct=(EditText)findViewById(R.id.txtProduct);
        txtDescription=(EditText)findViewById(R.id.txtDescription);
        cmbUnit=(AutoCompleteTextView) findViewById(R.id.autoUnit);
        cmbCategory=(AutoCompleteTextView)findViewById(R.id.autoCategory);
        // citam focus change na cmbs za da izvadam drop down od niv
        cmbCategory.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) cmbCategory.showDropDown();
            }
        });

        cmbUnit.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) cmbUnit.showDropDown();
            }
        });

        //sp=(Spinner)findViewById(R.id.spinner);
        loadCMBS();

    }

    void loadCMBS(){

        cmbUnit.clearListSelection();
        cmbCategory.clearListSelection();

        String [] categories;
        String [] units;

        DBHelper_Products db=new DBHelper_Products(this);

        categories=db.getCats_Array();
        ArrayAdapter<String> adapterCats=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,categories);
        cmbCategory.setThreshold(1);
        cmbCategory.setAdapter(adapterCats);

        units=db.getUnitsSymbols_Array();
        ArrayAdapter<String> adapterUnits=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,units);
        cmbUnit.setThreshold(1);
        cmbUnit.setAdapter(adapterUnits);

    }
    public void Button_Click(View v){
        Button btn=(Button)v;
        String btnString=btn.getText().toString().toLowerCase();

        if (btnString.equals("ok")) addProduct();

        if (btnString.equals("clear")) clearBox();

    }

    void addProduct(){

        //TODO: proveri vneseno, posebno za unit i kategorija

        // proveri dali postoi unit i zemi id_unit
        String unitSymbol=cmbUnit.getText().toString();

        DBHelper_Products dbHelper_products=new DBHelper_Products(this);

        int unit_ID=0;
        unit_ID=dbHelper_products.getUnitID(unitSymbol);
        // ako id=-1 dodadi kako nov unit i zemi mu id_unit
        if (unit_ID<0) {
            Unit unit = new Unit("", unitSymbol);       //new unit
            int err=-1;                                 //
            err=dbHelper_products.addUnit(unit);        //zapisi nov unit
            unit_ID=dbHelper_products.getUnitID(unitSymbol); //zemi id_unit od noviot za vnesuvanje product
        }

        //proveri dali postoi CATEGORY i zaem id cat
        String categoryName=cmbCategory.getText().toString();
        int cat_ID=0;
        cat_ID=dbHelper_products.getCategoryID(categoryName);
        //ako id=-1 dodadi nova kategorija
        if (cat_ID<0) {
            Category cat = new Category(categoryName,"");      //new unit
            int err=-1;                                 //
            err=dbHelper_products.addCategory(cat);        //zapisi nov unit
            cat_ID=dbHelper_products.getCategoryID(categoryName); //zemi id_unit od noviot za vnesuvanje product
        }


        String productName=txtProduct.getText().toString();

        // public Product(int unit,int cat,String name,String dsc)
        Product product=new Product(unit_ID,cat_ID,productName,"");
        dbHelper_products.addProduct(product);
        clearBox();
        loadCMBS();
    }

    void clearBox(){
        txtDescription.setText("");
        txtProduct.setText("");
        loadCMBS();
    }
}