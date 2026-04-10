package fr.iut.androidprojet.db;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        // à l'aide du "Room database builder"
        // MyToDos est le nom de la base de données
        //appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyToDos").build();

        ////////// REMPLIR LA BD à la première création à l'aide de l'objet roomDatabaseCallback
        // Ajout de la méthode addCallback permettant de populate (remplir) la base de données à sa création
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "EcoleDesLouLou")
                .fallbackToDestructiveMigration()
                .addCallback(roomDatabaseCallback).build(); // TODO A suppr
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Compte invité
            db.execSQL("INSERT INTO student (name, lastName, login) VALUES ('Invité', '', 'Invité');");

            // GÉOGRAPHIE
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quelle est la capitale de l''Australie ?', 'Canberra', 'Sydney', 'Melbourne');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quel est le plus grand pays du monde ?', 'La Russie', 'Le Canada', 'La Chine');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Sur quel continent se trouve le Brésil ?', 'Amérique du Sud', 'Afrique', 'Asie');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quelle est la plus haute montagne du monde ?', 'Le Mont Everest', 'Le Mont Blanc', 'Le Kilimandjaro');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quel océan sépare l''Europe de l''Amérique ?', 'L''Océan Atlantique', 'L''Océan Pacifique', 'L''Océan Indien');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quelle est la capitale du Japon ?', 'Tokyo', 'Kyoto', 'Osaka');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quel est le plus grand désert chaud du monde ?', 'Le Sahara', 'Le Gobi', 'L''Atacama');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quelle chaîne de montagnes traverse l''Europe ?', 'Les Alpes', 'Les Andes', 'L''Himalaya');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quel pays européen a la forme d''une botte ?', 'L''Italie', 'L''Espagne', 'La Grèce');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Géographie', 'Quelle est la capitale de l''Espagne ?', 'Madrid', 'Barcelone', 'Séville');");

            // FRANCAIS
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Quel est le verbe dans la phrase : \"Le chat mange la souris\" ?', 'mange', 'chat', 'souris');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Quel est l''infinitif du verbe dans : \"Nous jouons\" ?', 'jouer', 'jouons', 'joue');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Complète la phrase : \"Tu ... très gentil.\"', 'es', 'est', 'a');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Complète la phrase : \"J''... un beau vélo.\"', 'ai', 'es', 'a');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'À quel temps est la phrase : \"Demain, il pleuvra\" ?', 'Au futur', 'Au présent', 'Au passé');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Trouve le verbe dans : \"La fille court vite.\"', 'court', 'fille', 'vite');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Lequel de ces mots est un verbe à l''infinitif ?', 'Dormir', 'Je dors', 'Il dort');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Complète : \"Les oiseaux ... dans le ciel.\"', 'volent', 'vole', 'volons');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'À quel groupe appartient le verbe \"chanter\" ?', '1er groupe', '2ème groupe', '3ème groupe');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Français', 'Complète avec le verbe aller : \"Nous ... à l''école.\"', 'allons', 'allez', 'vont');");

            // --- HISTOIRE ---
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Quel chef gaulois a combattu Jules César ?', 'Vercingétorix', 'Astérix', 'Charlemagne');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Qui a fait construire le célèbre château de Versailles ?', 'Louis XIV', 'François 1er', 'Henri IV');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Comment s''appelait la célèbre héroïne qui a délivré la ville d''Orléans ?', 'Jeanne d''Arc', 'Marie-Antoinette', 'Cléopâtre');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'En quelle année a commencé la Première Guerre mondiale ?', '1914', '1939', '1804');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Comment s''appelaient les bateaux utilisés par les Vikings ?', 'Les drakkars', 'Les caravelles', 'Les canoës');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Quel célèbre empereur français a perdu la bataille de Waterloo ?', 'Napoléon Bonaparte', 'Louis XVI', 'Jules César');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Comment s''appelaient les habitants de la France dans l''Antiquité ?', 'Les Gaulois', 'Les Romains', 'Les Francs');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Sur quel continent sont apparus les tous premiers hommes préhistoriques ?', 'L''Afrique', 'L''Europe', 'L''Asie');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Qui a découvert l''Amérique en 1492 ?', 'Christophe Colomb', 'Magellan', 'Marco Polo');");
            db.execSQL("INSERT INTO questions_table (theme, questionText, correctAnswer, wrongAnswer1, wrongAnswer2) VALUES ('Histoire', 'Qui étaient les pharaons ?', 'Les rois d''Égypte', 'Des guerriers grecs', 'Des chefs romains');");
        }
    };
}
