package fr.iut.androidprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListExercicesMathsActivity extends AppCompatActivity {

    // VIEW
    private LinearLayout exerciceAddition;
    private LinearLayout exerciceSoustraction;
    private LinearLayout exerciceMultiplication;
    private LinearLayout exerciceDivision;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_exercices_maths);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérer les vues
        exerciceAddition = findViewById(R.id.addition);
        exerciceSoustraction = findViewById(R.id.soustraction);
        exerciceMultiplication = findViewById(R.id.multiplication);
        exerciceDivision = findViewById(R.id.division);
        btnBack = findViewById(R.id.btn_back);


        // --- REDIRECTION
        // retour
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // Addition
        exerciceAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExercicesMathsActivity.this, MathsActivity.class);
                // envoie des valeur dans MathsActivity
                intent.putExtra("NAME_EXERCICE", "Addition");
                intent.putExtra("OPERATOR", '+');

                startActivity(intent);
            }
        });

        // Soustaction
        exerciceSoustraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExercicesMathsActivity.this, MathsActivity.class);
                // envoie des valeur dans MathsActivity
                intent.putExtra("NAME_EXERCICE", "Soustaction");
                intent.putExtra("OPERATOR", '-');

                startActivity(intent);
            }
        });

        // Multiplication
        exerciceMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExercicesMathsActivity.this, MathsActivity.class);
                // envoie des valeur dans MathsActivity
                intent.putExtra("NAME_EXERCICE", "Multiplication");
                intent.putExtra("OPERATOR", 'x');

                startActivity(intent);
            }
        });

        // Division
        exerciceDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListExercicesMathsActivity.this, MathsActivity.class);
                // envoie des valeur dans MathsActivity
                intent.putExtra("NAME_EXERCICE", "Division");
                intent.putExtra("OPERATOR", '/');

                startActivity(intent);
            }
        });
    }
}