package come491.hanibana.Screen.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import come491.hanibana.Adapter.categoryAdapter;
import come491.hanibana.LoginActivity;
import come491.hanibana.Model.categoryModel;
import come491.hanibana.ProfileActivity;
import come491.hanibana.R;
import come491.hanibana.Screen.Category.favorite;
import come491.hanibana.Screen.detail.basket;

import androidx.appcompat.widget.Toolbar;

public class home extends AppCompatActivity {

    private FirebaseAuth mAuth;

    // ürünlerimizi yerleştirecegimiz listemiz
    private RecyclerView categoryList;
    //ürüneri listemize yerleştirmek için bize gereken adaptor sınıfımız
    private categoryAdapter category_adapter;
    // veritabanından çektigimiz verileri tutacagımız listemiz
    private final ArrayList<categoryModel> categorys = new ArrayList<>();
    private DatabaseReference reference;


    private void init() {
        // arayüzdeki kompanente ulaşmak için id sini kullanıyoruz
        categoryList = findViewById(R.id.categoryList);

        categoryList.setHasFixedSize(true);
        // grit viev görümünü verebilmek için grid Layout manager ı Recycler View imize ekliyoruz
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        categoryList.setLayoutManager(llm);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.favorite:
                    Intent i = new Intent(home.this, favorite.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(i);
                    return true;

                case R.id.home:
                    // bottomNavigationView.setSelectedItemId(R.id.home);
                    return false;

                case R.id.person:
                    Intent intent = new Intent(home.this, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                    return true;

                default:
                    return false;

            }
        });

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

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.miLogout) {
            mAuth.signOut();

            Intent intent = new Intent(home.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.basket) {
            Intent intent = new Intent(home.this, basket.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadCategory() {
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
                // ardından bu nesneyi Recycler viev e veriyoruzz
                categoryList.setAdapter(category_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}