package com.example.diceapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class RollAdapter extends ArrayAdapter<Roll> {

    private ArrayList<Roll> rolls;

    public RollAdapter(@NonNull Context context, @NonNull ArrayList<Roll> rolls) {
        super(context, 0, rolls);
        this.rolls = rolls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }

        Roll roll = rolls.get(position);
        TextView txtScore = view.findViewById(R.id.txtScore);
        txtScore.setText(MessageFormat.format((position + 1) + ".Score = {0}", roll.getScore()));

        LinearLayout llDice = view.findViewById(R.id.llImages);
        int[] dices = roll.getDices();
        llDice.removeAllViews();
        for (Integer imgID : dices) {
            ImageView dice = new ImageView(getContext());
            dice.setImageResource(imgID);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(5,5,5,5);
            dice.setLayoutParams(params);
            llDice.addView(dice);
        }
        return view;
    }
}
