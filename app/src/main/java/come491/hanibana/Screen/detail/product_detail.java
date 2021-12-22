package come491.hanibana.Screen.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private TextView productName,productPrice,productDes;
    private Button addBasket,addFavorite;

    private String productId;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        loadProduct();
        addButtonListener();
    }


    private void init(){
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productDes = findViewById(R.id.productDes);
        addBasket = findViewById(R.id.addBasket);
        addFavorite = findViewById(R.id.addFavorite);
        productId = getIntent().getStringExtra("productId");
        // Firebase database e baglanmak için gerekli alandan referansımızı alıyoruz
        reference = FirebaseDatabase.getInstance().getReference("products").child(productId);
    }

    private void loadProduct() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productModel product = snapshot.getValue(productModel.class);
                Glide.with(getApplicationContext()).load(product.getProductImage()).into(productImage);
                productName.setText(product.getProductName());
                productPrice.setText(product.getProductPrice().toString()+" TL");
                productDes.setText(product.getProductDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addButtonListener(){
        addBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}