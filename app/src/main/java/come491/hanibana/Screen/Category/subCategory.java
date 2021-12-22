package come491.hanibana.Screen.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import come491.hanibana.Adapter.categoryAdapter;
import come491.hanibana.Adapter.productAdapter;
import come491.hanibana.Adapter.subCategoryAdapter;
import come491.hanibana.Model.productModel;
import come491.hanibana.Model.subCategoryModel;
import come491.hanibana.R;

public class subCategory extends AppCompatActivity {
    // ürünlerimizi yerleştirecegimiz listemiz
    private RecyclerView subCategoryList;
    //ürüneri listemize yerleştirmek için bize gereken adaptor sınıfımız
    private subCategoryAdapter subCategory_adapter;
    // veritabanından çektigimiz verileri tutacagımız listemiz
    private ArrayList<subCategoryModel> subCategorys = new ArrayList<>();
    //katogory adını yazdıgımız textviev
    private TextView catagory_name;
    //gelen kategori id si
    private String categoryId;
    //gelen kategori ismi
    private String catagoryName;
    // database e erişmek için gereken referans nesnemiz
    DatabaseReference reference;
    private void init(){
        // katogory sayfasından gelen katogori id si
        categoryId = getIntent().getStringExtra("categoryId");
        catagoryName = getIntent().getStringExtra("categoryName");
        // arayüzdeki kompanente ulaşmak için id sini kullanıyoruz
        subCategoryList = findViewById(R.id.subCategoryList);
        catagory_name = findViewById(R.id.catagory_name);
        subCategoryList.setHasFixedSize(true);

        //gelen katogori adını ekrana yazdırıyoruz
        catagory_name.setText(catagoryName);
        // List viev görümünü verebilmek için Linear Layout manager ı Recycler View imize ekliyoruz
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        subCategoryList.setLayoutManager(llm);


        // Firebase database e baglanmak için gerekli alandan referansımızı alıyoruz
        reference = FirebaseDatabase.getInstance().getReference("subCategory");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        init();
        loadSubCategory();

    }

    private void loadSubCategory(){
        // bu aldıgımız referansa bir sürekli dinleyici atıyoruz
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // listenerr her çalıştıgında listenin üstüne eklemesin diye her seferinde clear ediyoruz
                subCategorys.clear();
                // veritabanından gelen katagorileri tek tek almak için for llop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    subCategoryModel subCategory = snap.getValue(subCategoryModel.class);
                    // ve liste mize ekliyoruz
                    if(subCategory.getTopCategoryid().equals(categoryId))
                        subCategorys.add(subCategory);




                }
                // Recycler viev e veri göndermemiz için yarattıgımız adaptorumuzden bir nesne türetiyoruz
                subCategory_adapter = new subCategoryAdapter(getApplicationContext(), subCategorys, subCategory.this);
                //ardından bu nesneyi Recycler viev  e veriyoruzz
                subCategoryList.setAdapter(subCategory_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}