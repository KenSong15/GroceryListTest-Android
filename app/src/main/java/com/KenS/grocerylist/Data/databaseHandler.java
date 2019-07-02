package com.KenS.grocerylist.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.KenS.grocerylist.Model.Grocery;
import com.KenS.grocerylist.Util.Constants;

import java.util.List;

public class databaseHandler extends SQLiteOpenHelper {

    private Context ctx;

    public databaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_GROCERY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" +
                Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_GROCERY_ITEM + " TEXT," +
                Constants.KEY_QTY_NUMBER + " TEXT," +
                Constants.KEY_DATE_MADE + "LONG);";

        sqLiteDatabase.execSQL(CREATE_GROCERY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }


    /*
     *  CRUD method goes following: create readone readall update delete
     */

    //add method
    public void addGrocery(Grocery grocery){

    }

    //get one grocery
    public Grocery getOneGrocery(int id){
        return null;
    }

    //get all grocery
    public List<Grocery> getAllGrocery(){
        return null;
    }

    //update one grocery
    public int updateGrocery(Grocery grocery){
        return 0;
    }

    //delete a grocery
    public void deleteGrocery(int id){

    }

    //count of the grocery
    public int getGroceryCount(){
        return 1;
    }





}
