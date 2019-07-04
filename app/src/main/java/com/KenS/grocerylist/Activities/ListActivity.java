package com.KenS.grocerylist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.KenS.grocerylist.Data.databaseHandler;
import com.KenS.grocerylist.Model.Grocery;
import com.KenS.grocerylist.R;
import com.KenS.grocerylist.UI.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Grocery> groceryList;
    private List<Grocery> listItem;
    private databaseHandler dbHandler;

    private AlertDialog dialog;
    private EditText groItem;
    private EditText groQty;
    private Button saveButton;
    private AlertDialog.Builder dialogBuilder;
    //private databaseHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                createPopupDialog();
            }
        });

        dbHandler = new databaseHandler(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryList = new ArrayList<>();
        listItem = new ArrayList<>();

        //Get item from database
        groceryList = dbHandler.getAllGrocery();

        for(Grocery c : groceryList){
            Grocery grocery = new Grocery();
            grocery.setName(c.getName());
            grocery.setQuantity("Qty: "+ c.getQuantity());
            grocery.setId(c.getId());
            grocery.setDateItemAdded(("Added on: " + c.getDateItemAdded()));

            listItem.add(grocery);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this, listItem);

        Log.d("empty",listItem.toString());

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void createPopupDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_popup, null);

        groItem = (EditText) view.findViewById(R.id.groceryItem);
        groQty = (EditText) view.findViewById(R.id.groceryQty);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!groItem.getText().toString().isEmpty() && !groQty.getText().toString().isEmpty()) {
                    saveGroceryToDB(view);
                }
            }
        });
    }

    private void saveGroceryToDB(View view) {
        Grocery grocery = new Grocery();

        String newGroceryName = groItem.getText().toString();
        String newGroceryQuantity = groQty.getText().toString();

        grocery.setName(newGroceryName);
        grocery.setQuantity(newGroceryQuantity);

        //save to DB
        dbHandler.addGrocery(grocery);

        Snackbar.make(view, "Item saved!", Snackbar.LENGTH_SHORT).show();

        Log.d("item added ID:", String.valueOf(dbHandler.getGroceryCount()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

                //start a new activity
                //startActivity(new Intent(MainActivity.this, ListActivity.class));

            }
        }, 1500); //delay 1.5 seconds

    }

}
