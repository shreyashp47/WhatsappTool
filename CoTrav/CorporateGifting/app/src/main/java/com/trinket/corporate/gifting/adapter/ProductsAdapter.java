package com.trinket.corporate.gifting.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.trinket.corporate.gifting.R;
import com.trinket.corporate.gifting.model.products.Product;
import com.trinket.corporate.gifting.retrofit.ApiUrl;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    List<Product> productsList;
    Activity activity;
    AddRemoveListener addRemoveListener;


    public ProductsAdapter(List<Product> productsList, Activity activity) {
        this.productsList = productsList;
        this.activity = activity;
        this.addRemoveListener = (AddRemoveListener) activity;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product list = productsList.get(position);
        holder.subTitle.setText(list.getProductsName());

        Picasso.get().
                load(ApiUrl.url + list.getPath()).
                into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subTitle;
        TextView add, remove;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            subTitle = itemView.findViewById(R.id.subtitle);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);
            imageView = itemView.findViewById(R.id.product_image);
            subTitle.setOnClickListener(this::onClick);
            add.setOnClickListener(this::addToCart);
            remove.setOnClickListener(this::removeToCart);

        }

        private void addToCart(View view) {
            addRemoveListener.add(productsList.get(getAdapterPosition()));
        }

        private void removeToCart(View view) {
            addRemoveListener.remove(productsList.get(getAdapterPosition()));
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            switch (view.getId()) {

                case R.id.subtitle:
                    Toast.makeText(activity, productsList.get(pos).getCategoriesName(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public interface AddRemoveListener {
        void add(Product product);

        void remove(Product product);
    }

}
