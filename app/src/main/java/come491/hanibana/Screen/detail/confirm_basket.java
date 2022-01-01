package come491.hanibana.Screen.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import come491.hanibana.Adapter.basketAdapter;
import come491.hanibana.Model.orderModel;
import come491.hanibana.Model.productModel;
import come491.hanibana.R;
import come491.hanibana.Screen.home.home;

public class confirm_basket extends AppCompatActivity {

    private EditText adres;
    private RadioButton doorPay, onlinePay;
    private RadioGroup radioGroup;
    private Button confirmPay;
    private String totalPrice;
    private String paymentType;
    private ArrayList<productModel> products = new ArrayList<>();
    private ArrayList<String> baskets = new ArrayList<>();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    private void init() {
        totalPrice = getIntent().getExtras().getString("totalPrice");
        radioGroup = findViewById(R.id.radioGroup);
        adres = findViewById(R.id.adres);
        doorPay = findViewById(R.id.doorPay);
        onlinePay = findViewById(R.id.onlinePay);
        confirmPay = findViewById(R.id.confirmPay);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_basket);
        init();
        addListener();
        loadBasket();
    }

    private void addListener() {

        confirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderModel order = new orderModel();
                String key = reference.child("orders").push().getKey();
                order.setId(key);
                Random rand = new Random();
                int value = rand.nextInt(5000);
                order.setOrderNumber(String.valueOf(value));
                order.setDeliveryAddress(adres.getText().toString());
                order.setProducts(products);
                Date date = new Date();
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(date.getTime());
                order.setPurchaseDate(timeStamp);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (R.id.onlinePay == selectedId)
                    paymentType = "Online";
                if (R.id.doorPay == selectedId)
                    paymentType = "Door";
                order.setPaymentType(paymentType);
                order.setAmount(totalPrice);
                order.setCustomerId(mAuth.getCurrentUser().getUid());
                order.setCustomerName(mAuth.getCurrentUser().getDisplayName());
                reference.child("orders").child(key).setValue(order);
                reference.child("users").child(mAuth.getCurrentUser().getUid()).child("Basket").removeValue();
                Intent intent = new Intent(confirm_basket.this, home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Your order has been created", Toast.LENGTH_SHORT).show();
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
                // veritabanından gelen katagorileri tek tek almak için for llop a sokuyoruz
                for (DataSnapshot snap : snapshot.getChildren()) {
                    // gelen veriyi categoryModel tipine çevirip alıyoruz
                    productModel product = snap.getValue(productModel.class);
                    // ve liste mize ekliyoruz
                    if (baskets.contains(product.getId())) {
                        products.add(product);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}