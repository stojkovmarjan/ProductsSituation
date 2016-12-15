package com.example.maryan.productssituation;
/**
 * Created by Maryan on 06.12.2016.
 */
public class Category {
    int _id;
    String _category;
    String _description;
    // constructors
    public Category(){
        _category="";
        _description="";
        _id=-1;
    }
    public Category(String cat, String dsc){
        _category=cat;
        _description=dsc;
        _id=-1;
    }
    public Category(int id,String cat, String dsc){
        _category=cat;
        _description=dsc;
        _id=id;
    }

    // getters ------------------
    public int get_id(){ return _id;}
    public String get_category(){return _category;}
    public String get_description(){return _description;}

    // setters ------------
    public void set_id(int id){ _id=id;}
    public void set_category(String category){_category=category;}
    public void set_description(String description){_description=description;}


}
