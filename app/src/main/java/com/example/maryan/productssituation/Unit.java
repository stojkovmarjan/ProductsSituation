package com.example.maryan.productssituation;
/**
 * Created by Maryan on 06.12.2016.
 */
public class Unit {
    int _id=-1;
    String _unit="";
    String _symbol="";
    // constructors -----------------------------------------
    public Unit(){
        _id=-1;
        _unit="";
        _symbol="";
    }
    public Unit( String unit,String symbol ){
        //_id=id; nema potreba
        _unit=unit;
       _symbol=symbol;
    }
    public Unit( int id,String unit,String symbol ){
        _id=id;
        _unit=unit;
        _symbol=symbol;
    }
    // getters------------------------------------------------
    public int get_id(){
        return _id;
    }
    public String get_unit(){
        return _unit;
    }
    public String get_symbol(){
        return _symbol;
    }


    // setters -------------------------------------------
    public void set_id(int id){
        _id=id;
    }
    public void set_unit(String unit){
        _unit=unit;
    }
    public void set_symbol(String symbol){
        _symbol=symbol;
    }


}
