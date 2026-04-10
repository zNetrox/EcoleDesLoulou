package fr.iut.androidprojet.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    // Récupère 10 questions au hasard selon le thème
    @Query("SELECT * FROM questions_table WHERE theme = :theme ORDER BY RANDOM() LIMIT 10")
    List<Question> getRandomQuestions(String theme);

    @Insert
    void insertAll(Question... questions);
}