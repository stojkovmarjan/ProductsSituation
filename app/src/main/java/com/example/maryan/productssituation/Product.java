package com.example.maryan.productssituation;

/**
 * Created by Maryan on 06.12.2016.
 */

public class Product {
    int _id;
    int _unit;
    int _category;
    String _name;
    String _description;

    //constructors
    public Product(){
        _category=-1;
        _description="";
        _id=-1;
        _unit=-1;
        _name="";
    }

    public Product(int id,int unit,int cat,String name,String dsc){
        _category=cat;
        _description=dsc;
        _id=id;
        _unit=unit;
        _name=name;
    }

    // getters
    public int get_id(){return _id;}
    public int get_unit(){return _unit;}
    public int get_category(){return _category;}
    public String get_name(){return _name;}
    public String get_description(){return _description;}

    // setters
    public void set_id(int id){_id=id;}
    public void set_unit(int unit){_unit=unit;}
    public void set_category(int category){_category=category;}
    public void set_name(String name){_name=name;}
    public void set_description(String description){_description=description;}

}
