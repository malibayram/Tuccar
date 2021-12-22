package come491.hanibana.Screen.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
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

public class home extends AppCompatActivity {


    // ürünlerimizi yerleştirecegimiz listemiz
    private RecyclerView categoryList;
    //ürüneri listemize yerleştirmek için bize gereken adaptor sınıfımız
    private categoryAdapter category_adapter;
    // veritabanından çektigimiz verileri tutacagımız listemiz
    private ArrayList<categoryModel> categorys = new ArrayList<>();
    private DatabaseReference reference;

    private BottomNavigationView bottomNavigationView;

    private void init(){
        // arayüzdeki kompanente ulaşmak için id sini kullanıyoruz
        categoryList = findViewById(R.id.categoryList);

        categoryList.setHasFixedSize(true);
        // grit viev görümünü verebilmek için grid Layout manager ı Recycler View imize ekliyoruz
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        categoryList.setLayoutManager(llm);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.toString().equals("Favorite")){

                }

                Log.d("seçilen",item.toString());
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Firebase database e baglanmak için gerekli alandan referansımızı alıyoruz
        reference = FirebaseDatabase.getInstance().getReference("Category");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        loadCategory();
    }

    private void loadCategory(){
        // bu aldıgımız referansa bir sürekli dinleyici atıyoruz
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // listenerr her çalıştıgında listenin üstüne eklemesin diye her seferinde clear ediyoruz
                categorys.clear();
                // veritabanından gelen katagorileri tek tek almak için for llop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    categoryModel category = snap.getValue(categoryModel.class);
                    //Log.d("gelen",snap.getValue().toString());
                    // ve liste mize ekliyoruz
                    categorys.add(category);
                }
                // Recycler viev e veri göndermemiz için yarattıgımız adaptorumuzden bir nesne türetiyoruz
                category_adapter = new categoryAdapter(getApplicationContext(), categorys, home.this);
                //ardından bu nesneyi Recycler viev  e veriyoruzz
                categoryList.setAdapter(category_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}