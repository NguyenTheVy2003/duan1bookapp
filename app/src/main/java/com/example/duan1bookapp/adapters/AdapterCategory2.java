package com.example.duan1bookapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1bookapp.activities.PdfListAdminActivity;
import com.example.duan1bookapp.activities.PdfListUserActivity;
import com.example.duan1bookapp.databinding.RowCategory2Binding;
import com.example.duan1bookapp.databinding.RowCategoryBinding;
import com.example.duan1bookapp.filters.FilterCategory;
import com.example.duan1bookapp.filters.FilterCategory2;
import com.example.duan1bookapp.models.ModelCategory;
import com.example.duan1bookapp.models.ModelCategory2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterCategory2 extends RecyclerView.Adapter<AdapterCategory2.HolderCategory> implements Filterable {

    private Context context;
    public ArrayList<ModelCategory2> categoryArrayList, filterList;

    public AdapterCategory2(Context context, ArrayList<ModelCategory2> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
        this.filterList = categoryArrayList;
    }

    //View binding
    private RowCategory2Binding binding;

    //instance of our filter class
    private FilterCategory2 filer;

    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //binding row_category.xml
        binding = RowCategory2Binding.inflate(LayoutInflater.from(context), parent, false);
        return new HolderCategory(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position) {
        //get data
        ModelCategory2 model = categoryArrayList.get(position);
        String category = model.getCategory();
        String id = model.getId();
        long timestamp = model.getTimestamp();
        String uid = model.getUid();

        //set data
        holder.categoryTV.setText(category);

        //handle click , delete category
        // handle item click ,goto PdfListAdminActivity, also pass pdf category and categoryId
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PdfListUserActivity.class);
                intent.putExtra("categoryId",id);
                intent.putExtra("categoryTitle",category);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if (filer == null) {
            filer = new FilterCategory2(filterList, this);
        }
        return filer;
    }

    /*View holder class to hold UI views for row_category.xml*/
    class HolderCategory extends RecyclerView.ViewHolder {
        //ui  view of row_category.xml

        TextView categoryTV;
        ImageButton deleteBtn;

        public HolderCategory(@NonNull View itemView) {
            super(itemView);
            //init ui views
            categoryTV = binding.categoryTv;
        }
    }
}
