package come491.hanibana.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import come491.hanibana.Model.productModel;
import come491.hanibana.R;

public class productAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<productModel> products;
    private Activity mActivity;

    public productAdapter(Context mContext, List<productModel> products, Activity mActivity) {
        this.products = products;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent, false);
        vh = new UViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        productModel product = products.get(position);
        Glide.with(mContext).load(product.getProductImage()).into(((UViewHolder) holder).productImage);
        ((UViewHolder) holder).productName.setText(product.getProductName());
        ((UViewHolder) holder).productPrice.setText(product.getProductPrice().toString()+" TL");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class UViewHolder extends RecyclerView.ViewHolder {

        public TextView productName, productPrice;
        public ImageView productImage;
        public FrameLayout root;

        public UViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            root = itemView.findViewById(R.id.root);

        }

    }


}

