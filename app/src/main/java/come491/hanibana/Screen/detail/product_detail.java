package come491.hanibana.Screen.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import come491.hanibana.Adapter.subCategoryAdapter;
import come491.hanibana.Model.categoryModel;
import come491.hanibana.Model.productModel;
import come491.hanibana.R;

public class product_detail extends AppCompatActivity {
    private ImageView productImage;
    private TextView productName, productPrice, productDes;
    private Button addBasket, addFavorite;

    private String productId;

    private DatabaseReference reference;

    private FirebaseAuth mAuth;

    boolean fav = false;

    boolean inBasket = false;

    private boolean basket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        loadProduct();
        addButtonListener();
    }


    private void init() {
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productDes = findViewById(R.id.productDes);
        addBasket = findViewById(R.id.addBasket);
        addFavorite = findViewById(R.id.addFavorite);
        productId = getIntent().getStringExtra("productId");
        // Firebase database e baglanmak için gerekli alandan referansımızı alıyoruz
        reference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        Control();
    }

    private void loadProduct() {
        reference.child("products").child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModel product = snapshot.getValue(productModel.class);
                Glide.with(getApplicationContext()).load(product.getProductImage()).into(productImage);
                productName.setText(product.getProductName());
                productPrice.setText(product.getProductPrice().toString() + " TL");
                productDes.setText(product.getProductDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void Control() {
        reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Fav").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fav = false;
                // favorilerden gelen id ler
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    // favorilerdeki id leri bizim ürün ile kıyaslıyoruz
                    String product = snap.getValue().toString();
                    Log.d("productId", product);
                    Log.d("productId", productId);
                    if (product.equals(productId)) {
                        fav = true;
                    }
                }
                if (fav) {
                    addFavorite.setText("remove favorites");
                } else {
                    addFavorite.setText("add favorites");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Basket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                inBasket = false;
                // favorilerden gelen id ler
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    // favorilerdeki id leri bizim ürün ile kıyaslıyoruz
                    String product = snap.getValue().toString();
                    Log.d("productId", product);
                    Log.d("productId", productId);
                    if (product.equals(productId)) {
                        inBasket = true;
                    }
                }
                if (inBasket) {
                    addBasket.setText("remove from basket");
                } else {
                    addBasket.setText("add basket");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addButtonListener() {
        addBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inBasket) {
                    reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Basket").child(productId).removeValue();
                    Toast.makeText(getApplicationContext(), "product removed from cart", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Basket").child(productId).setValue(productId);
                    Toast.makeText(getApplicationContext(), "product added to cart", Toast.LENGTH_SHORT).show();
                }
                Control();

            }
        });
        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fav) {
                    reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Fav").child(productId).removeValue();
                    Toast.makeText(getApplicationContext(), "product removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Fav").child(productId).setValue(productId);
                    Toast.makeText(getApplicationContext(), "product added from favorites", Toast.LENGTH_SHORT).show();
                }
                Control();
            }
        });

    }

}