package fr.iut.androidprojet;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import fr.iut.androidprojet.db.AppDatabase;
import fr.iut.androidprojet.db.DatabaseClient;
import fr.iut.androidprojet.db.Question;
import fr.iut.androidprojet.qcm_exercices.QCMExercice;

public class QCMActivity extends AppCompatActivity {

    // VIEW
    private TextView exerciceName, questionNumber, questionText, correctionIndication;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnPrevious, btnNext, btnFinish;
    private Chronometer chrono;
    private String themeExercice;
    private QCMExercice exercice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcm);

        // Récupérer les vues
        exerciceName = findViewById(R.id.exercice_name);
        questionNumber = findViewById(R.id.question_number);
        questionText = findViewById(R.id.question);
        correctionIndication = findViewById(R.id.correction_indication);
        chrono = findViewById(R.id.chrono);

        btnAnswer1 = findViewById(R.id.answer_1);
        btnAnswer2 = findViewById(R.id.answer_2);
        btnAnswer3 = findViewById(R.id.answer_3);

        btnPrevious = findViewById(R.id.previous);
        btnNext = findViewById(R.id.next);
        btnFinish = findViewById(R.id.finish);

        // recupere le theme
        themeExercice = getIntent().getStringExtra("THEME");
        exerciceName.setText("QCM : " + themeExercice);

        View.OnClickListener answerClickListener = v -> {
            Button clickedBtn = (Button) v;
            if (exercice != null) {
                exercice.getCurrentQuestion().setUserAnswer(clickedBtn.getText().toString());
                updateButtonColors();
            }
        };

        btnAnswer1.setOnClickListener(answerClickListener);
        btnAnswer2.setOnClickListener(answerClickListener);
        btnAnswer3.setOnClickListener(answerClickListener);

        // 4. Clics de Navigation
        btnPrevious.setOnClickListener(v -> {
            exercice.previousQuestion();
            displayCurrentQuestion();
        });

        btnNext.setOnClickListener(v -> {
            exercice.nextQuestion();
            displayCurrentQuestion();
        });

        btnFinish.setOnClickListener(v -> {
            showResultPopup();
        });

        loadQuestionsFromDatabase();
    }

    private void loadQuestionsFromDatabase() {
        AppDatabase db = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase();

        Executors.newSingleThreadExecutor().execute(() -> {
            // On récupère les 10 questions au hasard depuis la DB
            List<Question> questions = db.questionDao().getRandomQuestions(themeExercice);

            runOnUiThread(() -> {
                // On initialise notre exercice avec ces questions
                exercice = new QCMExercice(new ArrayList<>(questions));

                if (exercice.getTotalQuestions() > 0) {
                    chrono.setBase(SystemClock.elapsedRealtime());
                    chrono.start();
                    displayCurrentQuestion();
                } else {
                    questionText.setText("Aucune question trouvée pour " + themeExercice);
                }
            });
        });
    }

    private void displayCurrentQuestion() {
        Question q = exercice.getCurrentQuestion();

        questionNumber.setText("Question " + exercice.getCurrentQuestionNumber() + "/" + exercice.getTotalQuestions());
        questionText.setText(q.getQuestionText());

        List<String> answers = q.getShuffledAnswers();
        btnAnswer1.setText(answers.get(0));
        btnAnswer2.setText(answers.get(1));
        btnAnswer3.setText(answers.get(2));

        updateButtonColors();
        updateNavigationButtons();
    }

    private void updateButtonColors() {
        Question q = exercice.getCurrentQuestion();
        String savedAnswer = q.getUserAnswer();

        resetButtonVisuals(btnAnswer1);
        resetButtonVisuals(btnAnswer2);
        resetButtonVisuals(btnAnswer3);

        if (savedAnswer != null) {
            if (savedAnswer.equals(btnAnswer1.getText().toString())) highlightButton(btnAnswer1);
            else if (savedAnswer.equals(btnAnswer2.getText().toString())) highlightButton(btnAnswer2);
            else if (savedAnswer.equals(btnAnswer3.getText().toString())) highlightButton(btnAnswer3);
        }
    }

    private void resetButtonVisuals(Button b) {
        b.setBackgroundColor(Color.WHITE);
        b.setTextColor(Color.BLACK);
    }

    private void highlightButton(Button b) {
        b.setBackgroundColor(Color.parseColor("#E3F2FD"));
        b.setTextColor(Color.parseColor("#1976D2"));
    }

    private void updateNavigationButtons() {
        btnPrevious.setVisibility(exercice.canGoPreviousQuestion() ? View.VISIBLE : View.INVISIBLE);

        if (exercice.canGoNextQuestion()) {
            btnNext.setVisibility(View.VISIBLE);
            btnFinish.setVisibility(View.GONE);
        } else {
            btnNext.setVisibility(View.GONE);
            btnFinish.setVisibility(View.VISIBLE);
        }
    }

    // custom pop up end
    private void showResultPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QCMActivity.this);
        View customView = getLayoutInflater().inflate(R.layout.popup_result, null);

        TextView popScore = customView.findViewById(R.id.pop_score);
        TextView popTimer = customView.findViewById(R.id.pop_timer);
        Button btnOk = customView.findViewById(R.id.pop_btn_ok);
        Button btnCorrect = customView.findViewById(R.id.pop_btn_correct);

        // logique timer
        long elapsedMillis = SystemClock.elapsedRealtime() - chrono.getBase();
        exercice.setTimeWhenStopped(chrono.getBase() - SystemClock.elapsedRealtime());
        chrono.stop();

        String time = DateUtils.formatElapsedTime(elapsedMillis / 1000);

        popScore.setText(exercice.getTotalCorrectAnswer() + " / " + exercice.getTotalQuestions());
        popTimer.setText(time);

        // enleve le bouton de correction
        btnCorrect.setVisibility(View.GONE);

        builder.setView(customView);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        btnOk.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });

        dialog.show();
    }
}