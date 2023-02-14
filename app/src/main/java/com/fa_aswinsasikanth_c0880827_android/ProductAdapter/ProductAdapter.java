
package com.fa_aswinsasikanth_c0880827_android.ProductAdapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fa_aswinsasikanth_c0880827_android.Models.ProductModel;
import com.fa_aswinsasikanth_c0880827_android.ModifyProductDetails;
import com.fa_aswinsasikanth_c0880827_android.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> products;

    private List<ProductModel> productsTemp = new ArrayList<>();
    private Context context;

    public ProductAdapter(List<ProductModel> products, Context context) {
        this.products = products;
        this.context = context;
        this.productsTemp.addAll(products);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View contactView = layoutInflater.inflate(R.layout.custom_product_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new ProductViewHolder(contactView);
        return (ProductViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        if (products.size() > 0) {
            holder.name.setText(products.get(position).getName());
            holder.des.setText(products.get(position).getDescription());
            //holder.location.setText(products.get(position).getLocation());
            holder.Price.setText(products.get(position).getPrice());

            holder.contactCardView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ModifyProductDetails.class);
                intent.putExtra("id", products.get(position).getId());
                intent.putExtra("name", products.get(position).getName());
                intent.putExtra("des", products.get(position).getDescription());
                intent.putExtra("price", products.get(position).getPrice());
                intent.putExtra("address", products.get(position).getLocation());

                ((Activity) context).startActivityForResult(intent, 1);

            });
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void filter(String text) {
        text = text.toLowerCase();
        products.clear();

        if (text.length() == 0) {
            products.addAll(productsTemp);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            products = productsTemp.stream().filter(contact -> contact.getName().toLowerCase().contains(sb))
                    .collect(Collectors.toList());

        }
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView des;
        private TextView Price;
        private CardView contactCardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            contactCardView = itemView.findViewById(R.id.card);
            name = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.desc);
            Price=itemView.findViewById(R.id.price);
        }
    }
}

