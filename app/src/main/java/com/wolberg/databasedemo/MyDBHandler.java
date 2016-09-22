package com.wolberg.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 660251521 on 9/21/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper{

    //define database variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id"; //PRIMARY KEY
    public static final String COLUMN_PRODUCTNAME = "productname";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override //this is for creating
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //" INTEGERPRIMARY KEY AUTOINCREMENT,"
                COLUMN_PRODUCTNAME + " TEXT " + ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override //this is for updating
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    //ADD new row to db
    public void addProduct(Products product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getProductName());
        //get reference to db
        SQLiteDatabase db = getWritableDatabase();   //TODO can create new method for this
        db.insert(TABLE_PRODUCTS, null, values);
        db.close(); //do this everytime to avoid memory leaks
    }

    //DELETE a row from the database
    public void deleteProduct (String productName){
        //get reference to db
        SQLiteDatabase db = getWritableDatabase(); //TODO can create new method for this
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS+ " WHERE " + COLUMN_PRODUCTNAME + "=\"" +productName + "\";");
    }

    //SHOW ALL db as string
    public List<Products> databaseToString(){
        List<Products> products = new ArrayList<Products>();
        SQLiteDatabase db = getWritableDatabase();  //TODO can create new method for this
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE !";

        //cursor point to a location in results
        Cursor c = db.rawQuery(query,null);
        //move to first row in results
        c.moveToFirst();

        while (!c.isAfterLast()){
            Products product = cursorToProduct(c);
            products.add(product);
            c.moveToNext();
        }
        db.close();
        return products;
    }

    private Products cursorToProduct(Cursor c) {
        Products product = new Products();
        product.set_id(c.getInt(0));
        product.setProductName(c.getString(1));
        return product;
    }

}
