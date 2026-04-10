package fr.iut.androidprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListExercicesCultureActivity extends AppCompatActivity {

    // VIEW
    private LinearLayout exerciceGeo;
    private LinearLayout exerciceHistoire;
    private LinearLayout exerciceFrancais;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_exercices_culture);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérer les vues
        exerciceGeo = findViewById(R.id.geo);
        exerciceHistoire = findViewById(R.id.histoire);
        exerciceFrancais = findViewById(R.id.francais);
        btnBack = findViewById(R.id.btn_back);

        // --- REDIRECTION
        // retour
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // geo
        exerciceGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExercicesCultureActivity.this, QCMActivity.class);

                intent.putExtra("THEME", "Géographie");

                startActivity(intent);
            }
        });

        // histoire
        exerciceHistoire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExercicesCultureActivity.this, QCMActivity.class);

                intent.putExtra("THEME", "Histoire");

                startActivity(intent);
            }
        });

        // francais
        exerciceFrancais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExercicesCultureActivity.this, QCMActivity.class);

                intent.putExtra("THEME", "Français");

                startActivity(intent);
            }
        });
    }
}