package fr.iut.androidprojet.maths_exercices;

import java.util.ArrayList;

public class MathsExercice {
    private ArrayList<Calcul> listCalcul;
    private int nbCalcul = 10;
    private int currentCalculIndex = 0;
    private boolean isCorrectionMode = false;
    private long timeWhenStopped = 0;

    public MathsExercice(char operator, int nbDigits1, int nbDigits2) {
        // initialisation des calculs
        this.listCalcul = new ArrayList<>();

        for (int i = 0; i < nbCalcul; i++) {
            listCalcul.add(new Calcul(nbDigits1, nbDigits2, operator));
        }
    }

    public int getNbCalcul() {
        return nbCalcul;
    }

    public int getTotalCorrectAnswer() {
        int acc = 0;

        for (Calcul calc : listCalcul) {
            if(calc.isCorrectAnswer()) {
                acc++;
            }
        }

        return acc;
    }

    public int getCurrentCalculIndex() {
        return currentCalculIndex;
    }

    public Calcul getCurrentCalcul() {
        return listCalcul.get(currentCalculIndex);
    }

    public boolean canGoNextQuestion() {
        return currentCalculIndex < nbCalcul - 1;
    }

    public boolean canGoPreviousQuestion() {
        return currentCalculIndex > 0;
    }
    public void previousCalcul() {
        if(canGoPreviousQuestion()) {
            currentCalculIndex--;
        }
    }

    public void nextCalcul() {
        if(canGoNextQuestion()) {
            currentCalculIndex++;
        }
    }

    public boolean isFinished() {
        return currentCalculIndex >= nbCalcul - 1;
    }

    public void setCorrectionMode(boolean correctionMode) {
        isCorrectionMode = correctionMode;
    }

    public boolean isCorrectionMode() {
        return isCorrectionMode;
    }

    public void correctionMode() {
        this.currentCalculIndex = 0;
        this.setCorrectionMode(true);
    }

    // chrono
    public long getTimeWhenStopped() {
        return timeWhenStopped;
    }

    public void setTimeWhenStopped(long timeWhenStopped) {
        this.timeWhenStopped = timeWhenStopped;
    }
}
