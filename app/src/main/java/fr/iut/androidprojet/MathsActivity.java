package fr.iut.androidprojet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.iut.androidprojet.maths_exercices.MathsExercice;

public class MathsActivity extends AppCompatActivity {

    // VIEW
    private TextView calcul;
    private TextView exerciceName;
    private TextView questionNumber;
    private TextView correctionIndication;
    private EditText answer;
    private Button next;
    private Button previous;
    private Button finishBtn;
    private Chronometer chrono;
    private MathsExercice exercice;
    private String nameExercice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maths);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérer les vues
        exerciceName = findViewById(R.id.exercice_name);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        finishBtn = findViewById(R.id.finish);
        calcul = findViewById(R.id.calcul);
        questionNumber = findViewById(R.id.question_number);
        answer = findViewById(R.id.answer);
        chrono = findViewById(R.id.chrono);
        correctionIndication = findViewById(R.id.correction_indication);

        nameExercice = getIntent().getStringExtra("NAME_EXERCICE");

        // affiche le nom de l'exercice actuel
        exerciceName.setText(nameExercice);

        // initialisation du bouton Précedent
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercice.previousCalcul();
                displayCurrentCalc();
            }
        });

        // initialisation du bouton suivant
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserAnswer();

                if (exercice.isFinished()) {
                    showResultPopup();
                } else {
                    exercice.nextCalcul();
                    displayCurrentCalc();
                }
            }
        });

        // initialisation du bouton finsih
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserAnswer();
                chrono.stop();
                showResultPopup();
            }
        });

        // affichage du popup de settings
        showSettingsPopup();
    }

    private void startExercise(int d1, int d2) {
        char operateur = getIntent().getCharExtra("OPERATOR", '+');
        exercice = new MathsExercice(operateur, d1, d2);

        // lance le chrono
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();

        displayCurrentCalc();
    }

    private void saveUserAnswer() {
        String valeurSaisie = answer.getText().toString().trim();

        // verifie si l'imput n'est pas vide et que il n'est que des chiffres
        if (!valeurSaisie.isEmpty()) {
            try {
                int reponseInt = Integer.parseInt(valeurSaisie);
                exercice.getCurrentCalcul().setUserAnswer(reponseInt);
            } catch (NumberFormatException e) {
                exercice.getCurrentCalcul().setUserAnswer(null);
            }
        }
    }

    private void displayCurrentCalc() {
        // mode correction
        if (exercice.isCorrectionMode()) {
            correctionIndication.setVisibility(View.VISIBLE);

            fr.iut.androidprojet.maths_exercices.Calcul current = exercice.getCurrentCalcul();
            Integer oldAnswer = current.getUserAnswer();

            // la reoponse est juste
            if (current.isCorrectAnswer()) {
                answer.setText(String.valueOf(oldAnswer));
                answer.setEnabled(false); // bloque l'input

                correctionIndication.setText("Bravo, c'est la bonne réponse !");
                correctionIndication.setTextColor(Color.parseColor("#C9E0DD"));
            }
            // reponse vide / fausse
            else {
                answer.setEnabled(true);

                // On remet en rouge pour signaler l'erreur
                correctionIndication.setTextColor(Color.parseColor("#B20000"));
                if (oldAnswer != null) {
                    answer.setText(String.valueOf(oldAnswer));

                    answer.setSelection(answer.getText().length());
                    correctionIndication.setText(oldAnswer + " n'est pas la bonne réponse !");
                } else {
                    answer.setText("");
                    correctionIndication.setText("Tu n'as rien répondu !");
                }
            }

        } else { // mode normal

            correctionIndication.setVisibility(View.GONE);

            answer.setEnabled(true);
            answer.setText("");
        }

        answer.requestFocus();
        calcul.setText(exercice.getCurrentCalcul().toString());

        questionNumber.setText("Question " + (exercice.getCurrentCalculIndex() + 1) + "/" + exercice.getNbCalcul());

        if (exercice.isFinished()) {
            next.setVisibility(View.GONE);
            finishBtn.setVisibility(View.VISIBLE);
        } else {
            next.setVisibility(View.VISIBLE);
            finishBtn.setVisibility(View.GONE);
        }
    }

    // custom pop up settings
    private void showSettingsPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.popup_settings, null);
        builder.setView(v);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();

        RadioGroup group1 = v.findViewById(R.id.group_digit1);
        RadioGroup group2 = v.findViewById(R.id.group_digit2);
        Button btnStart = v.findViewById(R.id.btn_start);
        Button btnBack = v.findViewById(R.id.btn_back);
        TextView nameExerciceSettings = v.findViewById(R.id.pop_title);

        nameExerciceSettings.setText(nameExercice);

        btnStart.setOnClickListener(view -> {
            int d1 = 1, d2 = 1;
            int checkedId1 = group1.getCheckedRadioButtonId();
            int checkedId2 = group2.getCheckedRadioButtonId();

            if (checkedId1 == R.id.btn_d1_2) d1 = 2;
            else if (checkedId1 == R.id.btn_d1_3) d1 = 3;

            if (checkedId2 == R.id.btn_d2_2) d2 = 2;
            else if (checkedId2 == R.id.btn_d2_3) d2 = 3;

            startExercise(d1, d2);
            dialog.dismiss();
        });

        btnBack.setOnClickListener(view -> {
            dialog.dismiss();
            finish();
        });

        // affiche la popup
        dialog.show();
    }

    // custom pop up end
    private void showResultPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MathsActivity.this);
        View customView = getLayoutInflater().inflate(R.layout.popup_result, null);

        TextView popScore = customView.findViewById(R.id.pop_score);
        TextView popTimer = customView.findViewById(R.id.pop_timer);
        Button btnOk = customView.findViewById(R.id.pop_btn_ok);
        Button btnCorrect = customView.findViewById(R.id.pop_btn_correct);

        // logique timer
        long elapsedMillis = SystemClock.elapsedRealtime() - chrono.getBase(); // temps total
        exercice.setTimeWhenStopped(chrono.getBase() - SystemClock.elapsedRealtime()); // sauvegarde du temps pour la correction
        chrono.stop();

        // affichage timer
        String time = DateUtils.formatElapsedTime(elapsedMillis / 1000);
        popScore.setText(exercice.getTotalCorrectAnswer() + " / " + exercice.getNbCalcul());
        popTimer.setText(time);

        // supprime le bouton de correction si tout est juste
        if (exercice.getTotalCorrectAnswer() == exercice.getNbCalcul()) {
            btnCorrect.setVisibility(View.GONE);
        } else {
            btnCorrect.setVisibility(View.VISIBLE);
        }

        builder.setView(customView);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();

        // Bouton de retour menu
        btnOk.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });

        // Bouton de correction
        btnCorrect.setOnClickListener(v -> {
            exercice.correctionMode();
            displayCurrentCalc();

            chrono.setBase(SystemClock.elapsedRealtime() + exercice.getTimeWhenStopped());
            chrono.start();

            dialog.dismiss();
        });

        // affiche la popup
        dialog.show();
    }

}