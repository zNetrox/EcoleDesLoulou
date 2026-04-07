package fr.iut.androidprojet.maths_exercices;


// TODO ne pas avoir de resultat negatif

public class Calcul {
    private char operator;
    private int operand1;
    private int operand2;
    private Integer userAnswer = null; // Integer pour avoir null
    private boolean isCorrectAnswer = false;

    public Calcul(int nbDigits1, int nbDigits2, char operator) {
        this.operator = operator;
        this.operand1 = initNumber(nbDigits1);
        this.operand2 = initNumber(nbDigits2);

        // pour ne pas avoir de reponse en négaif pour les soustraction
        if (
            this.operator == '-'
            && this.operand1 < this.operand2)
        {
            // on inverse les openrandes
            int temp = this.operand1;
            this.operand1 = this.operand2;
            this.operand2 = temp;
        }
    }

    private int initNumber(int nbDigits) {
        if (nbDigits <= 0) return (int) (Math.random() * 10);

        int min = (int) Math.pow(10, nbDigits - 1);
        int max = (int) Math.pow(10, nbDigits) - 1;

        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public int getResult() {
        switch (this.operator) {
            case '+':
                return operand1 + operand2;
            case 'x':
                return operand1 * operand2;
            case '-':
                return operand1 - operand2;
            default:
                return 0; // exception
        }
    }

    public void setUserAnswer(Integer answer) {
        this.userAnswer = answer;

        if (answer != null) {
            this.isCorrectAnswer = (answer == getResult());
        }
    }

    public Integer getUserAnswer() {
        return this.userAnswer;
    }

    public boolean isCorrectAnswer() {
        return this.isCorrectAnswer;
    }

    @Override
    public String toString() {
        return operand1 + " " + operator + " " + operand2 + " =";
    }

}
