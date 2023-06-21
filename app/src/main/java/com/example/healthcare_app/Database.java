package com.example.healthcare_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text,mail text,password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2="create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3="create table orderplace(username text,fullname text,phone text,address text,pin int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register(String username,String mail,String password){
        ContentValues cv =new ContentValues();
        cv.put("username",username);
        cv.put("mail",mail);
        cv.put("password",password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String password){
        int result=0;
        String str[] = new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return  result;

    }
    public void cart(String username,String product,Float price,String otype){
        ContentValues cv =new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkcart(String username,String product){
        int res=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username=? and product=?",str);
        if(c.moveToFirst()){
            res=1;
        }
        db.close();
        return  res;

    }
    public void removecart(String username,String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();

        db.delete("cart","username=? and otype=?",str);
        db.close();
    }
    public ArrayList getCartdata(String username, String otype){
            ArrayList<String> arr=new ArrayList<>();
            SQLiteDatabase db=getReadableDatabase();
            String str[]=new String[2];
            str[0]=username;
            str[1]=otype;
            Cursor c =db.rawQuery("select * from cart where username=? and otype=?",str);
            if(c.moveToFirst()){
                do{
                    String prod=c.getString(1);
                    String price=c.getString(2);
                    arr.add(prod+"$"+price);
                }while(c.moveToNext());
            }
            db.close();
            return arr;
    }
    public void addorder(String user,String fullname,String phone,String add,int pin,String date,String time,Float amount,String otype){
        ContentValues cv =new ContentValues();
        cv.put("username",user);
        cv.put("fullname",fullname);
        cv.put("phone",phone);
        cv.put("address",add);
        cv.put("pin",pin);
        cv.put("Date",date);
        cv.put("Time",time);
        cv.put("Amount",amount);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }
    public ArrayList getOrderData(String username){
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        String str[]= new String[1];
        str[0]=username;
        Cursor c=db.rawQuery("select * from orderplace where username = ?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));

            }while(c.moveToNext());

        }
        db.close();
        return arr;
    }
}
