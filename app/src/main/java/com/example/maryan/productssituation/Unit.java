package com.example.maryan.productssituation;

/**
 * Created by Maryan on 06.12.2016.
 */

public class Unit {
    int _id;
    String _unit;
     int _sys;
    // constructors -----------------------------------------
    public Unit(){
        _id=-1;
        _unit="";
        _sys=-1;
    }
    public Unit(int id, String unit,int sys ){
        _id=id;
        _unit=unit;
        _sys=sys;
    }
    // getters------------------------------------------------
    public int get_id(){
        return _id;
    }
    public String get_unit(){
        return _unit;
    }
    public int get_sys(){
        return _sys;
    }

    // setters -------------------------------------------
    public void set_id(int id){
        _id=id;
    }
    public void set_unit(String unit){
        _unit=unit;
    }
    public void set_sys(int sys){
        _sys=sys;
    }

}
