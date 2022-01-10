package come491.hanibana.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import come491.hanibana.Model.productModel;
import come491.hanibana.R;
import come491.hanibana.Screen.detail.product_detail;

public class basketAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<productModel> products;
    private Activity mActivity;

    public basketAdapter(Context mContext, List<productModel> products, Activity mActivity) {
        this.products = products;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(mContext).inflate(R.layout.basket_item, parent, false);
        vh = new UViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        productModel product = products.get(position);

         DatabaseReference reference= FirebaseDatabase.getInstance().getReference();

         FirebaseAuth mAuth= FirebaseAuth.getInstance();
        Glide.with(mContext).load(product.getProductImage()).into(((UViewHolder) holder).productImage);
        ((UViewHolder) holder).productName.setText(product.getProductName());
        ((UViewHolder) holder).productPrice.setText(product.getProductPrice().toString()+" TL");
        ((UViewHolder) holder).root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, product_detail.class);
                i.putExtra("productId",product.getId());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

        ((UViewHolder) holder).remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Basket").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snap : snapshot.getChildren()){
                            String products = snap.getValue().toString();
                            if(product.getId().equals(products)){
                                reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Basket").child(snap.getKey().toString()).removeValue();
                                Toast.makeText(mActivity, "product remove to cart", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class UViewHolder extends RecyclerView.ViewHolder {

        public TextView productName, productPrice;
        public ImageView productImage;
        public ImageButton remove;
        public FrameLayout root;

        public UViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            root = itemView.findViewById(R.id.root);
            remove = itemView.findViewById(R.id.remove);
        }

    }


}

