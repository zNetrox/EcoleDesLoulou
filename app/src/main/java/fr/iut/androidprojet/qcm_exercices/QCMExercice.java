package fr.iut.androidprojet.qcm_exercices;

import java.util.ArrayList;

import fr.iut.androidprojet.db.Question;

public class QCMExercice {
    private ArrayList<Question> listQuestions;
    private int currentIndex = 0;
    private long timeWhenStopped = 0;

    public QCMExercice(ArrayList<Question> questions) {
        this.listQuestions = questions;
    }

    public Question getCurrentQuestion() {
        return listQuestions.get(currentIndex);
    }

    public boolean canGoNextQuestion() {
        return currentIndex < listQuestions.size() - 1;
    }

    public boolean canGoPreviousQuestion() {
        return currentIndex > 0;
    }

    public void nextQuestion() {
        if (canGoNextQuestion()) {
            currentIndex++;
        }
    }

    public void previousQuestion() {
        if (canGoPreviousQuestion()) {
            currentIndex--;
        }
    }

    public int getCurrentQuestionNumber() {
        return currentIndex + 1;
    }

    public int getTotalQuestions() {
        return listQuestions.size();
    }

    public int getTotalCorrectAnswer() {
        int acc = 0;

        for (Question question : listQuestions) {
            if(question.isCorrectAnswer()) {
                acc++;
            }
        }

        return acc;
    }

    // chrono
    public long getTimeWhenStopped() {
        return timeWhenStopped;
    }

    public void setTimeWhenStopped(long timeWhenStopped) {
        this.timeWhenStopped = timeWhenStopped;
    }
}
