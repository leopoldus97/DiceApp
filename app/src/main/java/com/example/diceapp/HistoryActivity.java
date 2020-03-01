package com.example.diceapp;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private Context ctx;
    private Button btnBack, btnClear;
    private ArrayList<Roll> rolls;
    private ListView lstHistory;
    ArrayAdapter adapter;
    private RollManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        ctx = getWindow().getContext();
        manager = RollManager.init();
        initBtn();
        initLst();
    }

    private void initLst() {
        lstHistory = findViewById(R.id.lstHistory);
        rolls = manager.getHistory();
        ArrayList<String> scores = new ArrayList<>();
        //String s = "";
        for (Roll roll : rolls) {
            /*for (int i = 0; i < roll.getDices().length; i++) {
                s += " " + roll.getDices()[i];
            }*/
            scores.add("Date: " + roll.getTimeStamp().toString() + " Score: " + roll.getScore());
        }
        setAdapter(scores);
    }

    private void setAdapter(ArrayList<String> rolls) {
        //adapter = new RollAdapter(ctx, rolls);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rolls);
        adapter.notifyDataSetChanged();
        lstHistory.setAdapter(adapter);
        lstHistory.setSelection(adapter.getCount() - 1);
    }

    private void initBtn() {
        btnBack = findViewById(R.id.btnBackHistory);
        btnClear = findViewById(R.id.btnClearHistory);
        btnBack.setOnClickListener((v) -> goBack());
        btnClear.setOnClickListener((v) -> clearList());
    }

    private void clearList() {
        if (!rolls.isEmpty()) {
            manager.clearHistory();
            lstHistory.setAdapter(null);
            showMessage("History is cleared!");
        } else {
            showMessage("History is empty!");
        }
    }

    private void goBack() {
        finish();
    }

    private void showMessage(String text) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }

}
