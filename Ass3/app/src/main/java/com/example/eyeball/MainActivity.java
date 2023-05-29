package com.example.eyeball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.eyeball.model.*;

public class MainActivity extends AppCompatActivity {

    //Level
    private int levelWidth = 5;
    private int levelHeight = 5;

    androidx.gridlayout.widget.GridLayout levelGrid;

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLUMNS = 4;
    protected Level currentLevel;

//    Goal
    public int goalCount = 0;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        levelGrid = findViewById(R.id.LevelGrid);

        levelGrid.setRowCount(NUM_ROWS);
        levelGrid.setColumnCount(NUM_COLUMNS);

//      create the grid
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                Button button = new Button(this);
                button.setLayoutParams(new GridLayout.LayoutParams(
                        GridLayout.spec(row, GridLayout.FILL, 1f),
                        GridLayout.spec(col, GridLayout.FILL, 1f)));
                button.setOnClickListener(toastListener);
                button.setText("yeeeet");
                levelGrid.addView(button);
            }
        }

        setLevelName("Level 1");
    }

    private final View.OnClickListener toastListener = v -> toast();

    private void toast(){
        Toast.makeText(this, "ya yeet", Toast.LENGTH_SHORT).show();
    }

    public int getLevelWidth() {
        return this.currentLevel.width;
    }

    public int getLevelHeight() {
        return this.currentLevel.height;
    }

    private int addGoalCount(){
        return goalCount += 1;
    }

    private void setGoalCount(){
        TextView text=(TextView)findViewById(R.id.goalCount);
        text.setText(String.valueOf(goalCount));
    }

    private void setLevelName(String levelName){
        TextView text = (TextView) findViewById(R.id.LevelName);
        text.setText(levelName);
    }

    public void restartClicked(View view) {
        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        } else {
            Intent intent = getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);

            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    }
}