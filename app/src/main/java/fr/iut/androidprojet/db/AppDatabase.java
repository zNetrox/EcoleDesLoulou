package fr.iut.androidprojet.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class, Question.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();

    public abstract QuestionDao questionDao();
}