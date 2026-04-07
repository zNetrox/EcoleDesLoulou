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

    public void previousCalcul() {
        if(currentCalculIndex > 0) {
            currentCalculIndex--;
        }
    }

    public void nextCalcul() {
        if(currentCalculIndex < nbCalcul - 1) {
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

    public long getTimeWhenStopped() {
        return timeWhenStopped;
    }

    public void setTimeWhenStopped(long timeWhenStopped) {
        this.timeWhenStopped = timeWhenStopped;
    }
}
