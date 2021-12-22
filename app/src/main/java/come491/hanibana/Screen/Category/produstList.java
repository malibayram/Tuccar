package come491.hanibana.Screen.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import come491.hanibana.Adapter.categoryAdapter;
import come491.hanibana.Adapter.productAdapter;
import come491.hanibana.Model.categoryModel;
import come491.hanibana.Model.productModel;
import come491.hanibana.R;
import come491.hanibana.Screen.home.home;

public class produstList extends AppCompatActivity {
    // ürünlerimizi yerleştirecegimiz listemiz
    private RecyclerView productList;
    //ürüneri listemize yerleştirmek için bize gereken adaptor sınıfımız
    private productAdapter product_adapter;
    // veritabanından çektigimiz verileri tutacagımız listemiz
    private ArrayList<productModel> products = new ArrayList<>();
    //katogory adını yazdıgımız textviev
    private TextView catagory_name;
    //gelen kategori id si
    private String categoryId;
    //gelen alt kategori id si
    private String subCategoryId;
    //gelen kategori ismi
    private String catagoryName;
    // database e erişmek için gereken referans nesnemiz
    DatabaseReference reference;

    private void init() {
        // katogory sayfasından gelen katogori id si
        categoryId = getIntent().getStringExtra("categoryId");
        subCategoryId = getIntent().getStringExtra("subCategoryId");
        catagoryName = getIntent().getStringExtra("categoryName");
        // arayüzdeki kompanente ulaşmak için id sini kullanıyoruz
        productList = findViewById(R.id.categoryList);
        catagory_name = findViewById(R.id.catagory_name);
        productList.setHasFixedSize(true);

        //gelen katogori adını ekrana yazdırıyoruz
        catagory_name.setText(catagoryName);
        // grit viev görümünü verebilmek için grid Layout manager ı Recycler View imize ekliyoruz
        GridLayoutManager gm = new GridLayoutManager(getApplicationContext(), 2);
        productList.setLayoutManager(gm);


        // Firebase database e baglanmak için gerekli alandan referansımızı alıyoruz
        reference = FirebaseDatabase.getInstance().getReference()
                .child("products");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produst_list);
        init();
        loadProdust();

    }

    private void loadProdust() {
        // bu aldıgımız referansa bir sürekli dinleyici atıyoruz
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // listenerr her çalıştıgında listenin üstüne eklemesin diye her seferinde clear ediyoruz
                products.clear();
                // veritabanından gelen katagorileri tek tek almak için for llop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    productModel product = snap.getValue(productModel.class);
                    // ve liste mize ekliyoruz
                    if (product.getProductSubCategoryId().equals(subCategoryId))
                        products.add(product);
                }
                // Recycler viev e veri göndermemiz için yarattıgımız adaptorumuzden bir nesne türetiyoruz
                product_adapter = new productAdapter(getApplicationContext(), products, produstList.this);
                //ardından bu nesneyi Recycler viev  e veriyoruzz
                productList.setAdapter(product_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}