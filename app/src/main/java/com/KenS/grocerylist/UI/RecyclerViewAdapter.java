package com.KenS.grocerylist.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.KenS.grocerylist.Activities.DetailActivity;
import com.KenS.grocerylist.Model.Grocery;
import com.KenS.grocerylist.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Grocery> groceryList;

    public RecyclerViewAdapter(Context context, List<Grocery> groceryList) {
        this.context = context;
        this.groceryList = groceryList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView groceryItemName;
        public TextView quantity;
        public TextView dateAdded;
        public Button editButton;
        public Button deleteButton;
        public int id;

        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);

            context = ctx;

            groceryItemName = (TextView) view.findViewById(R.id.nameRow);
            quantity = (TextView) view.findViewById(R.id.quantityRow);
            dateAdded = (TextView) view.findViewById(R.id.dateAddedRow);

            editButton = (Button) view.findViewById(R.id.editButton);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //go to next screen to the detail activity
                    int position = getAdapterPosition();

                    Grocery grocery = groceryList.get(position);
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("name", grocery.getName());
                    intent.putExtra("quantity", grocery.getQuantity());
                    intent.putExtra("id", grocery.getId());
                    intent.putExtra("date", grocery.getDateItemAdded());

                    context.startActivity(intent);
                }
            });

        }

        @Override
        public void onClick(View view) {

            switch(view.getId()) {
                case R.id.editButton:
                    Log.d("editButton||","clicked");
                    break;

                case R.id.deleteButton:
                    Log.d("deleteButton||","clicked");
                    break;
            }

        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {

        Grocery grocery = groceryList.get(i);

        viewHolder.groceryItemName.setText(grocery.getName());
        viewHolder.quantity.setText(grocery.getQuantity());
        viewHolder.dateAdded.setText(grocery.getDateItemAdded());

    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }


}
