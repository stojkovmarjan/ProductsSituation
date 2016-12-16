package com.example.maryan.productssituation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
/**
 * Created by Maryan on 06.12.2016.
 */
public class DBHelper_Products extends SQLiteOpenHelper {

    private static final String DB_NAME="ProductsDB";
    private static final int DB_VERSION=1;

    public DBHelper_Products(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String createUnits="CREATE TABLE units (" +
                //"id_unit    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "unit   TEXT," +
                "symbol TEXT NOT NULL UNIQUE)";
        db.execSQL(createUnits);

        String createCategories="CREATE TABLE categories (" +
                //"id_cat INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "category TEXT NOT NULL UNIQUE," +
                "description TEXT)";
        db.execSQL(createCategories);

        String createProducts="CREATE TABLE products (" +
                //"id_product INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," +
                "product TEXT NOT NULL UNIQUE," +
                "unit INTEGER NOT NULL," +
                "category INTEGER NOT NULL," +
                "description TEXT)";

        db.execSQL(createProducts);

        String createProductsView="CREATE TEMP VIEW IF NOT EXISTS view_products AS " +
                "SELECT products.rowid, products.product, units.symbol,categories.category,products.description  " +
                "FROM products, categories,units " +
                "WHERE ( products.unit=units.rowid AND products.category=categories.rowid) " +
                "ORDER BY products.product";

        db.execSQL(createProductsView);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){

        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS units");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP VIEW IF EXISTS view_products");

        onCreate(db);

    }
    // vnesuvanje na nova edinica merka
    public int addUnit(Unit unit){

        SQLiteDatabase ddbase=this.getWritableDatabase();
        ContentValues vals=new ContentValues();

        vals.put("unit",unit.get_unit());
        vals.put("symbol",unit.get_symbol());

        try{
            ddbase.insertOrThrow("units",null,vals);// da vrate greska ako ima nesto problem
        } catch (SQLiteConstraintException ex){//
            //System.out.println(ex.toString());
            String exStr=ex.toString().toLowerCase();
            //System.out.println("############################################\n"+ex.toString());
            //success=false;
            if (exStr.indexOf("is not unique")>=0) {
                //Toast.makeText(this,"sasasasasasadsfdfd",Toast.LENGTH_LONG).show();
                return 19;
            }

            if (exStr.indexOf("code")>=0) {
                //Toast.makeText(this,"sasasasasasadsfdfd",Toast.LENGTH_LONG).show();
                return 1;
            }
        }
        return -1;
    }

    // vnesuvanje na nova kategorija
    public int addCategory(Category category){

        SQLiteDatabase ddbase=this.getWritableDatabase();
        ContentValues vals=new ContentValues();

        vals.put("category",category.get_category());
        vals.put("description",category.get_description());

        try{
            ddbase.insertOrThrow("categories",null,vals);// da vrate greska ako ima nesto problem
        } catch (SQLiteConstraintException ex){//
            //System.out.println(ex.toString());
            String exStr=ex.toString().toLowerCase();
           // System.out.println("############################################\n"+ex.toString());
            //success=false;
            if (exStr.indexOf("is not unique")>=0) {
                //Toast.makeText(this,"sasasasasasadsfdfd",Toast.LENGTH_LONG).show();
                return 19;
            }

            if (exStr.indexOf("code")>=0) {
                //Toast.makeText(this,"sasasasasasadsfdfd",Toast.LENGTH_LONG).show();
                return 1;
            }
        }
        return -1;
    }

    // vnesuvanje na nov PRODUCT ------------------------------------------------------
    public int addProduct(Product product){

        SQLiteDatabase ddbase=this.getWritableDatabase();
        ContentValues vals=new ContentValues();

        vals.put("product",product.get_name());
        vals.put("unit",product.get_unit());
        vals.put("category",product.get_category());
        vals.put("description",product.get_description());

        try{
            ddbase.insertOrThrow("products",null,vals);// da vrate greska ako ima nesto problem
        } catch (SQLiteConstraintException ex){//
            //System.out.println(ex.toString());
            String exStr=ex.toString().toLowerCase();
            //System.out.println("############################################\n"+ex.toString());
            //success=false;
            if (exStr.indexOf("is not unique")>=0) {
                //Toast.makeText(this,"sasasasasasadsfdfd",Toast.LENGTH_LONG).show();
                return 19;
            }

            if (exStr.indexOf("code")>=0) {
                //Toast.makeText(this,"sasasasasasadsfdfd",Toast.LENGTH_LONG).show();
                return 1;
            }
        }
        return -1;
    }

    public ArrayList<Category> getCategories(){// vrakja lista kategorii
        ArrayList <Category> categories=new ArrayList<Category>();
        String selectSQL="SELECT * FROM categories ORDER BY category";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectSQL,null);

        if (cursor.moveToFirst()){
            do{
                Category cat=new Category();
                cat.set_category(cursor.getString(0));
                cat.set_description(cursor.getString(1));
                categories.add(cat);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return categories;
    }

    public String[]getCats_Array(){//vrakja String niza od iminja na kategorii
        ArrayList<Category> listCats=getCategories();
        int i=listCats.size();
        String [] cats=new String[i];
        //cats=listCats.toArray(new String[i]);
      for (int n=0;n<i;n++){
          cats[n]=listCats.get(n).get_category();
          //System.out.println("---------------------------------------- "+cats[n]);
      }
        return cats;
    }

    public ArrayList<Unit> getUnits(){//vrakja lista Units
        ArrayList<Unit> units=new ArrayList<Unit>();
        String selectQuery="SELECT * FROM units ORDER BY symbol";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
              Unit u=new Unit();
                u.set_unit(cursor.getString(0));// measure unit name
                u.set_symbol(cursor.getString(1));// measure unit symbol
                //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ "+u.get_symbol().toString());
                units.add(u);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return units;
    }

    public String[] getUnitsSymbols_Array(){// vrakja lista niza iminja units
        ArrayList<Unit> units=getUnits();
        String [] uSymbols=new String[units.size()];
        for (int n=0;n<units.size();n++){
            uSymbols[n]=units.get(n).get_symbol();
            //System.out.println("---------------------------------------- "+uSymbols[n]);
        }
        return uSymbols;
    }

    public int getUnitID(String unitSymbol){
        int unitID=-1;

        String selectUnitID="SELECT rowid FROM units WHERE (symbol='"+unitSymbol+"')";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectUnitID,null);
        if (cursor.moveToFirst()){
            unitID=cursor.getInt(0);
        }
        //System.out.print("############################################## ");
        //System.out.println(String.valueOf(unitID));
        return unitID;
    }

    public int getCategoryID(String category){
        int categoryID=-1;
        String selectUnitID="SELECT rowid FROM categories WHERE (category='"+category+"')";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectUnitID,null);
        if (cursor.moveToFirst()){
            categoryID=cursor.getInt(0);
        }
        //System.out.print("################# CATEGORY ##################### ");
        //System.out.println(String.valueOf(categoryID));
        return categoryID;
    }

    public int getCount(String table){// vrakja count od tabela
        int i=0;
        String countQuery="SELECT * FROM "+table;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        i=cursor.getCount();
        cursor.close();
        return i;
    }
}
