package com.example.diceapp;

import android.app.Application;
import android.util.Log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class RollManager extends Application {
    private static ArrayList<Roll> rolls;
    private static RollManager instance;
    private static int diceNum;
    private Random rnd;

    public static RollManager init() {
        if (instance == null) {
            instance = new RollManager();
        }
        return instance;
    }

    private RollManager() {
        rolls = new ArrayList<>();
        rnd = new Random();
    }

    public ArrayList<Roll> getHistory() {
        return rolls;
    }

    public boolean clearHistory() {
        rolls.clear();
        return rolls.isEmpty();
    }

    public void setHistory(ArrayList<Roll> rolls) {
        this.rolls = rolls;
    }

    public int[] roll(int currentNum) {
        int sum = 0;
        diceNum = currentNum;
        int[] rollResult = new int[currentNum];
        for (int i = 0; i < currentNum; i++) {
            int rndNum = rnd.nextInt(6) + 1;
            sum += rndNum;
            rollResult[i] = rndNum;
        }
        rolls.add(new Roll(sum, rollResult, LocalDate.now()));
        return rollResult;
    }
}
