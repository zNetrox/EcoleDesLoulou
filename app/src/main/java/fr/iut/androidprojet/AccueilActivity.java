package fr.iut.androidprojet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AccueilActivity extends AppCompatActivity {
    // VIEW
    private LinearLayout btnMaths;
    private LinearLayout btnCulture;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérer les vues
        btnMaths = findViewById(R.id.btn_maths_exercices);
        btnCulture = findViewById(R.id.btn_culture_exercices);
        welcome = findViewById(R.id.welcome);

        String userName = getSharedPreferences("USER_PREFS", MODE_PRIVATE)
                .getString("user_name", "Invité");

        welcome.setText("Salut " + userName + ",");

        // --- REDIRECTION
        // exercies maths / geometrie
        btnMaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, ListExercicesMathsActivity.class);

                startActivity(intent);
            }
        });

        // exercies culture général
        btnCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, ListExercicesCultureActivity.class);

                startActivity(intent);
            }
        });
    }
}