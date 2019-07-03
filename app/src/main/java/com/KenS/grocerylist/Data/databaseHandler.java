package com.KenS.grocerylist.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.KenS.grocerylist.Model.Grocery;
import com.KenS.grocerylist.Util.Constants;

import java.util.Date;
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

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATE_MADE, java.lang.System.currentTimeMillis());

        //Insert the row
        db.insert(Constants.TABLE_NAME,null,values);

        Log.d("addGrocery||","success");

    }

    //get one grocery
    public Grocery getOneGrocery(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID,
        Constants.KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER, Constants.KEY_DATE_MADE},
                Constants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

            Grocery grocery = new Grocery();
            grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
            grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

            //convert timestamp to something readable
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_MADE)))
            .getTime());

            grocery.setDateItemAdded(formatedDate);

        return grocery;
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
