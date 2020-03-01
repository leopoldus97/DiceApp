package com.example.diceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnRoll, btnIncrementDice, btnDecrementDice, btnHistory;
    private LinearLayout llDice;
    private TextView txtDiceCount;
    private int HISTORY_ACTIVITY = 8, currentNum;
    private int[] dices;
    private Context ctx;
    ImageView dice;
    private RollManager manager;
    private ArrayList<Roll> rolls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentNum = 1;
        ctx = getWindow().getContext();
        rolls = new ArrayList<>();
        manager = RollManager.init();
        initComponents();
        initButtonActions();
    }

    private void initButtonActions() {
        btnRoll.setOnClickListener((v) -> roll());
        btnHistory.setOnClickListener((v) -> {
            Intent intent = new Intent(ctx, HistoryActivity.class);
            startActivity(intent);
        });
        btnIncrementDice.setOnClickListener((v) -> {
            currentNum = currentNum == 6 ? 6 : Integer.parseInt(txtDiceCount.getText().toString()) + 1;
            txtDiceCount.setText(String.valueOf(currentNum));
        });
        btnDecrementDice.setOnClickListener((v) -> {
            currentNum = currentNum == 1 ? 1 : Integer.parseInt(txtDiceCount.getText().toString()) - 1;
            txtDiceCount.setText(String.valueOf(currentNum));
        });
    }

    private void roll() {
        llDice.removeAllViews();
        dices = manager.roll(currentNum);
        generateDiceField(dices);
    }

    private void generateDiceField(int[] dices) {
        for (Integer diceRoll : dices) {
            dice = new ImageView(ctx);
            getDiceImage(diceRoll);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(5,0,5,0);
            dice.setLayoutParams(params);
            llDice.addView(dice);
        }
    }

    private void getDiceImage(int rndNum) {
        switch (rndNum) {
            case 1:
                setDiceImage(R.drawable.dice1);
                break;
            case 2:
                setDiceImage(R.drawable.dice2);
                break;
            case 3:
                setDiceImage(R.drawable.dice3);
                break;
            case 4:
                setDiceImage(R.drawable.dice4);
                break;
            case 5:
                setDiceImage(R.drawable.dice5);
                break;
            case 6:
                setDiceImage(R.drawable.dice6);
                break;
        }
    }

    private void setDiceImage(int id) {
        dice.setImageResource(id);
    }

    private void initComponents() {
        btnRoll = findViewById(R.id.btnRoll);
        btnIncrementDice = findViewById(R.id.btnIncrementDice);
        btnDecrementDice = findViewById(R.id.btnDecrementDice);
        btnHistory = findViewById(R.id.btnHistory);
        llDice = findViewById(R.id.llDice);
        txtDiceCount = findViewById(R.id.txtDiceCount);
    }
}
