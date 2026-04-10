package fr.iut.androidprojet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.androidprojet.db.DatabaseClient;
import fr.iut.androidprojet.db.Student;

public class SignUpActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;

    // VIEW
    private EditText editTextLogin;
    private EditText editTextName;
    private EditText editTextLastName;
    private Button btnSave;
    private Button btnBackToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        editTextLogin = findViewById(R.id.login);
        editTextName = findViewById(R.id.sign_up_first_name);
        editTextLastName = findViewById(R.id.sign_up_last_name);
        btnSave = findViewById(R.id.sign_up_btn);
        btnBackToLogin = findViewById(R.id.btn_back_to_login);

        // Associer un événement au bouton save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStudent();
            }
        });

        // redirection
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });
    }


    private void saveStudent() {

        // Récupérer les informations contenues dans les vues
        final String sLogin = editTextLogin.getText().toString().trim();
        final String sName = editTextName.getText().toString().trim();
        final String sLastName = editTextLastName.getText().toString().trim();

        // Vérifier les informations fournies par l'utilisateur
        if (sLogin.isEmpty()) {
            editTextLogin.setError("Login required");
            editTextLogin.requestFocus();
            return;
        }

        if (sName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (sLastName.isEmpty()) {
            editTextLastName.setError("Lastname required");
            editTextLastName.requestFocus();
            return;
        }

        /**
         * Création d'une classe asynchrone pour sauvegarder la tache donnée par l'utilisateur
         */
        class SaveTask extends AsyncTask<Void, Void, Student> {

            @Override
            protected Student doInBackground(Void... voids) {

                // creating a student
                Student student = new Student();
                student.setLogin(sLogin);
                student.setName(sName);
                student.setLastName(sLastName);

                // adding to database
                long id = mDb.getAppDatabase()
                        .studentDAO()
                        .insert(student);

                student.setId(id);

                return student;
            }

            @Override
            protected void onPostExecute(Student task) {
                super.onPostExecute(task);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        SaveTask st = new SaveTask();
        st.execute();
    }




}