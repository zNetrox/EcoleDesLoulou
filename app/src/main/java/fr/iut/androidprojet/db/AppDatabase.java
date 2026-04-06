package fr.iut.androidprojet.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.iut.androidprojet.db.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();

}