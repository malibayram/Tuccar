package come491.hanibana.Screen.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import come491.hanibana.Adapter.productAdapter;
import come491.hanibana.LoginActivity;
import come491.hanibana.Model.productModel;
import come491.hanibana.ProfileActivity;
import come491.hanibana.R;
import come491.hanibana.Screen.detail.basket;
import come491.hanibana.Screen.home.home;

public class favorite extends AppCompatActivity {
    // ürünlerimizi yerleştirecegimiz listemiz
    private RecyclerView productList;
    //ürüneri listemize yerleştirmek için bize gereken adaptor sınıfımız
    private productAdapter product_adapter;
    // veritabanından çektigimiz verileri tutacagımız listemiz
    private ArrayList<productModel> favorites = new ArrayList<>();
    private ArrayList<String> favoriId = new ArrayList<>();
    // database e erişmek için gereken referans nesnemiz
    DatabaseReference reference;
    private FirebaseAuth mAuth;

    // ekrana bos mesajı vermek için text ekliyoruz
    private TextView eempty;

    private void init() {
        // arayüzdeki kompanente ulaşmak için id sini kullanıyoruz
        productList = findViewById(R.id.categoryList);
        mAuth = FirebaseAuth.getInstance();
        productList.setHasFixedSize(true);
        eempty = findViewById(R.id.empty);
        // grit viev görümünü verebilmek için grid Layout manager ı Recycler View imize ekliyoruz
        GridLayoutManager gm = new GridLayoutManager(getApplicationContext(), 2);
        productList.setLayoutManager(gm);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.favorite);
        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent i = new Intent(favorite.this, home.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(i);
                    return true;

                case R.id.favorite:
                    // bottomNavigationView.setSelectedItemId(R.id.favorite);
                    return false;

                case R.id.person:
                    Intent intent = new Intent(favorite.this, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                    return true;
                default:
                    return false;

            }


        });


        // Firebase database e baglanmak için gerekli alandan referansımızı alıyoruz
        reference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        init();
        getFav();
    }

    private void getFav() {
        // bu aldıgımız referansa bir sürekli dinleyici atıyoruz
        reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Fav").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // listenerr her çalıştıgında listenin üstüne eklemesin diye her seferinde clear ediyoruz
                favoriId.clear();
                // veritabanından gelen katagorileri tek tek almak için for loop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    String product = snap.getValue().toString();
                    // ve liste mize ekliyoruz
                    favoriId.add(product);
                }
                if (favoriId.isEmpty())
                    eempty.setText("Favorites are empty");
                else
                    eempty.setText("");
                Log.d("veri", favoriId.toString());
                loadFav();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void loadFav() {
        // bu aldıgımız referansa bir sürekli dinleyici atıyoruz
        reference.child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // listenerr her çalıştıgında listenin üstüne eklemesin diye her seferinde clear ediyoruz
                favorites.clear();
                // veritabanından gelen katagorileri tek tek almak için for llop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    productModel product = snap.getValue(productModel.class);
                    // ve liste mize ekliyoruz
                    if (favoriId.contains(product.getId()))
                        favorites.add(product);
                }
                Log.d("veri2", favorites.toString());
                // Recycler viev e veri göndermemiz için yarattıgımız adaptorumuzden bir nesne türetiyoruz
                product_adapter = new productAdapter(getApplicationContext(), favorites, favorite.this);
                //ardından bu nesneyi Recycler viev  e veriyoruzz
                productList.setAdapter(product_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

            Intent intent = new Intent(favorite.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.basket) {
            Intent intent = new Intent(favorite.this, basket.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}