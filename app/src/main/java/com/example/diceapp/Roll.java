package com.example.diceapp;

import java.time.LocalDate;
import java.util.ArrayList;

public class Roll {

    private LocalDate timeStamp;
    private int score;
    private int[] dices;

    public Roll(int score, int[] dices) {
        setScore(score);
        setDices(dices);
    }

    public Roll(int score, int[] dices, LocalDate timeStamp) {
        setScore(score);
        setDices(dices);
        setTimeStamp(timeStamp);
    }

    private void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    private void setScore(int score) {
        this.score = score;
    }

    private void setDices(int[] dices) {
        this.dices = dices;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public int getScore() {
        return score;
    }

    public int[] getDices() {
        return dices;
    }
}
