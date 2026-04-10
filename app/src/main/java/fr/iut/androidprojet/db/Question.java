package fr.iut.androidprojet.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(tableName = "questions_table")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String theme;
    private String questionText;

    private String correctAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;

    // ignorer par la bd pour l'exerice
    @Ignore
    private String userAnswer = null;
    @Ignore
    private List<String> shuffledAnswers = null;

    public Question(String theme, String questionText, String correctAnswer, String wrongAnswer1, String wrongAnswer2) {
        this.theme = theme;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
    }

    public List<String> getShuffledAnswers() {
        if (shuffledAnswers == null) {
            shuffledAnswers = new ArrayList<>();
            shuffledAnswers.add(correctAnswer);
            shuffledAnswers.add(wrongAnswer1);
            shuffledAnswers.add(wrongAnswer2);
            Collections.shuffle(shuffledAnswers); // Mélange
        }
        return shuffledAnswers;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer.equals(userAnswer);
    }

    /*
     * Getters and Setters
     * */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }
    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}