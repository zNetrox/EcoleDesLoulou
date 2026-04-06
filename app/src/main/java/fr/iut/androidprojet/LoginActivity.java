package fr.iut.androidprojet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import fr.iut.androidprojet.db.DatabaseClient;
import fr.iut.androidprojet.db.Student;

public class LoginActivity extends AppCompatActivity {

    //
    private static final int REQUEST_CODE_ADD = 0;

    // DATA
    private DatabaseClient mDb;
    private StudentAdapter adapter;

    // VIEW
    private Button btnCreateAccount;
    private ListView listStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Récupérer les vues
        btnCreateAccount = findViewById(R.id.btn_create_account);
        listStudent = findViewById(R.id.list_student);
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Lier l'adapter au listView
        adapter = new StudentAdapter(this, new ArrayList<Student>());
        listStudent.setAdapter(adapter);

        // redirection
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });

        // EXEMPLE : Ajouter un événement click à la listView
        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récuperer l'étudiant
                Student student = adapter.getItem(position);

                // 2. Sauvegarder son nom dans les SharedPreferences
                getSharedPreferences("USER_PREFS", MODE_PRIVATE)
                        .edit()
                        .putString("user_full_name", student.getFullName())
                        .putString("user_name", student.getName())
                        .putString("user_login", student.getLogin())
                        .apply();

                //Rediriger vers l'accueil
                Intent intent = new Intent(LoginActivity.this, AccueilActivity.class);
                startActivity(intent);
            }
        });

        // Mise à jour des taches
        //getTasks();

    }


    private void getStudent() {
        ///////////////////////
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class GetTasks extends AsyncTask<Void, Void, List<Student>> {

            @Override
            protected List<Student> doInBackground(Void... voids) {
                List<Student> studentList = mDb.getAppDatabase()
                        .studentDAO()
                        .getAll();
                return studentList;
            }

            @Override
            protected void onPostExecute(List<Student> tasks) {
                super.onPostExecute(tasks);

                // Mettre à jour l'adapter avec la liste de taches
                adapter.clear();
                adapter.addAll(tasks);

                // Now, notify the adapter of the change in source
                adapter.notifyDataSetChanged();
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetTasks gt = new GetTasks();
        gt.execute();
    }


    @Override
    protected void onStart() {
        super.onStart();

        // Mise à jour des taches
        getStudent();

    }
}