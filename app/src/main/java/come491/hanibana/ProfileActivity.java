package come491.hanibana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import come491.hanibana.Screen.Category.favorite;
import come491.hanibana.Screen.home.home;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        TextView tvNameSurname = findViewById(R.id.nameSurname);
        TextView tvEmail = findViewById(R.id.email);

        tvNameSurname.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getDisplayName());
        tvEmail.setText(Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.person);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent i = new Intent(ProfileActivity.this, home.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(i);
                    return true;
                case R.id.person:
                    // bottomNavigationView.setSelectedItemId(R.id.person);
                    return false;

                case R.id.favorite:
                    Intent intent = new Intent(ProfileActivity.this, favorite.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                    return true;
                default:
                    return false;

            }
        });
    }
}