package fr.iut.androidprojet.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Insert
    long insert(Student student);

    @Insert
    long[] insertAll(Student... student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

}
