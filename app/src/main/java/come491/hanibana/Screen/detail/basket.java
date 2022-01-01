package come491.hanibana.Screen.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import come491.hanibana.Adapter.basketAdapter;
import come491.hanibana.Model.productModel;
import come491.hanibana.R;
import come491.hanibana.Screen.home.home;

public class basket extends AppCompatActivity {

    // ürünlerimizi yerleştirecegimiz listemiz
    private RecyclerView productList;
    //ürüneri listemize yerleştirmek için bize gereken adaptor sınıfımız
    private basketAdapter basket_adapter;
    // veritabanından çektigimiz verileri tutacagımız listemiz
    private ArrayList<productModel> products = new ArrayList<>();
    private ArrayList<String> baskets = new ArrayList<>();
    //katogory adını yazdıgımız textviev
    private TextView basketSize,totalPriceText;
    //gelen kategori id si
    private double totalPrice;
    private Button confirmBasket;
    // database e erişmek için gereken referans nesnemiz
    DatabaseReference reference;

    FirebaseAuth mAuth= FirebaseAuth.getInstance();

    private void init() {

        // arayüzdeki kompanente ulaşmak için id sini kullanıyoruz
        productList = (RecyclerView) findViewById(R.id.bl);
        basketSize = findViewById(R.id.basketSize);
        totalPriceText = findViewById(R.id.totalPriceText);
        confirmBasket = findViewById(R.id.confirmBasket);
        productList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        productList.setLayoutManager(llm);


        // Firebase database e baglanmak için gerekli alandan referansımızı alıyoruz
        reference = FirebaseDatabase.getInstance().getReference();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        init();
        loadBasket();
        addListener();

    }
    private void addListener() {
        confirmBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(basket.this, confirm_basket.class);
                intent.putExtra("totalPrice",String.valueOf(totalPrice));
                startActivity(intent);
            }
        });
    }
    private void loadBasket() {
        // bu aldıgımız referansa bir sürekli dinleyici atıyoruz
        reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Basket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // listenerr her çalıştıgında listenin üstüne eklemesin diye her seferinde clear ediyoruz
                baskets.clear();
                // veritabanından gelen katagorileri tek tek almak için for llop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    String product = snap.getValue().toString();
                    // ve liste mize ekliyoruz
                        baskets.add(product);
                }
                basketSize.setText("My basket ( "+baskets.size()+" )");
                loadProdust();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loadProdust() {
        // bu aldıgımız referansa bir sürekli dinleyici atıyoruz
        reference.child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // listenerr her çalıştıgında listenin üstüne eklemesin diye her seferinde clear ediyoruz
                products.clear();
                totalPrice = 0.0;
                // veritabanından gelen katagorileri tek tek almak için for llop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    productModel product = snap.getValue(productModel.class);
                    // ve liste mize ekliyoruz
                    if (baskets.contains(product.getId())){
                        products.add(product);
                        totalPrice += product.getProductPrice();
                    }

                }
                totalPriceText.setText("Total : "+totalPrice+ " TL");
                // Recycler viev e veri göndermemiz için yarattıgımız adaptorumuzden bir nesne türetiyoruz
                basket_adapter = new basketAdapter(getApplicationContext(), products, basket.this);
                //ardından bu nesneyi Recycler viev  e veriyoruzz
                productList.setAdapter(basket_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}