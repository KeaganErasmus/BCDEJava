package com.example.eyeball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.eyeball.model.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Game game = new Game();
    GridLayout levelGrid;
    FrameLayout frameLayout;

    TextView goalCount;
    TextView goalText;

    private String levelName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Play the game
        startGame();
        goalText = findViewById(R.id.goalCountText);
        goalText.setText("Number of goals complete:  " + (game.getCompletedGoalCount()) + "/" + (game.getGoalCount()));
    }

    @SuppressLint("SetTextI18n")
    private void startGame(){
        createLevel();
        setLevelName("Level 1");
    }


    private void generateLevel(){
        // Generate what the game should look like
        game.addSquare(new PlayableSquare(Color.RED, Shape.DIAMOND), 0, 0);
        game.addSquare(new PlayableSquare(Color.BLUE, Shape.CROSS), 0, 1);
        game.addSquare(new PlayableSquare(Color.RED, Shape.CROSS), 0, 2);
        game.addSquare(new PlayableSquare(Color.GREEN, Shape.FLOWER), 1, 0);
        game.addSquare(new BlankSquare(), 1, 1);
        game.addSquare(new PlayableSquare(Color.YELLOW, Shape.STAR), 1, 2);
        game.addSquare(new PlayableSquare(Color.YELLOW, Shape.FLOWER), 2, 0);
        game.addSquare(new PlayableSquare(Color.YELLOW, Shape.CROSS), 2, 1);
        game.addSquare(new PlayableSquare(Color.GREEN, Shape.STAR), 2, 2);
        game.addSquare(new PlayableSquare(Color.BLUE, Shape.DIAMOND), 3, 0);
        game.addSquare(new PlayableSquare(Color.GREEN, Shape.FLOWER), 3, 1);
        game.addSquare(new PlayableSquare(Color.RED, Shape.DIAMOND), 3, 2);

        game.addEyeball(3, 0, Direction.RIGHT);
        game.addGoal(1, 0);
    }

    @SuppressLint("DefaultLocale")
    private void createLevel(){
        game.addLevel(4,4);
        int levelHeight = game.currentLevel.height;
        int levelWidth = game.currentLevel.width;
        levelGrid = findViewById(R.id.LevelGrid);
        levelGrid.setRowCount(levelHeight);
        levelGrid.setColumnCount(levelWidth);

        generateLevel();

        for (int row = 0; row < levelHeight; row++) {
            for (int col = 0; col < levelWidth; col++) {
                Button button = new Button(this);
                button.setId(View.generateViewId());
                button.setTag(new int[]{row, col});

                String imgName = setImages(row, col);
                int resId = getResources().getIdentifier(imgName, "drawable", getPackageName());
                button.setBackgroundResource(resId);

                button.setOnClickListener(view -> {
                    int[] position = (int[]) view.getTag();
                    onClickHandle(position[0], position[1]);
                });

                frameLayout = new FrameLayout(this);
                frameLayout.addView(button);

                addImagesToSquares(row,col);
//                addGoalImg(row,col);

                levelGrid.addView(frameLayout);
            }
        }
    }

    public String setImages(int row, int column) {
        ArrayList<Square> allMySquares = game.currentLevel.allMySquares;
        String result = "";
        for (Square square : allMySquares) {
            if (row == square.getRow() && column == square.getCol()) {
                String squareColor = square.color.toString();
                String squareShape = square.shape.toString();
                result = squareColor.toLowerCase() + "_" + squareShape.toLowerCase();
            }
        }
        return result;
    }

    private void addImagesToSquares(int row, int col){
        if (row == game.getEyeballRow() && col == game.getEyeballColumn()) {
            ImageView playerImage = new ImageView(this);
            playerImage.setTag("playerImage");

            int playerImageResId = getResources().getIdentifier("eyes_right", "drawable", getPackageName());
            playerImage.setImageResource(playerImageResId);

            frameLayout.addView(playerImage);
        }
        if(game.hasGoalAt(row, col)){
            ImageView goalImage = new ImageView(this);
            goalImage.setTag("goalImage" + row + "_" + col);

            int goalImageResId = getResources().getIdentifier("goal", "drawable", getPackageName());
            goalImage.setImageResource(goalImageResId);

            frameLayout.addView(goalImage);
        }
    }


    private void setLevelName(String levelName){
        TextView text = findViewById(R.id.LevelName);
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

    @SuppressLint("SetTextI18n")
    public void onClickHandle(int row, int col) {
        TextView helpText = findViewById(R.id.help_text);
        goalCount = findViewById(R.id.goalCountText);

        if (game.canMoveTo(row, col)) {
            helpText.setText("");
            if (game.hasGoalAt(row, col)) {
                game.completedGoal();
                helpText.setText("You reached the Goal!!!!");
                goalCount.setText("Number of goals complete:  " + (game.getCompletedGoalCount()) + "/" + (game.getGoalCount()));
            }
            game.moveTo(row, col);
            eyeballImgRemove(row, col);
            DisplayEyeballImg(row, col, game.getEyeballDirection());

        } else {
            game.checkDirectionMessage(row,col);
            helpText.setText("Invalid Move: Eyeball can only move to the same Color or Shape, Eyeball can not move backwards or diagonally");
        }
    }


    private void eyeballImgRemove(int row, int col) {
        for (int i = 0; i < levelGrid.getChildCount(); i++) {
            View v = levelGrid.getChildAt(i);
            if (v instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) v;
                for (int j = 0; j < frameLayout.getChildCount(); j++) {
                    View subView = frameLayout.getChildAt(j);
                    if (subView.getTag() != null && subView.getTag().equals("playerImage")) {
                        frameLayout.removeView(subView);
                        return;
                    }
                }
            }
        }
    }

    private void DisplayEyeballImg(int row, int column, Direction direction) {
        FrameLayout frameLayout = (FrameLayout) levelGrid.getChildAt(row * levelGrid.getColumnCount() + column);
        ImageView playerImage = new ImageView(this);
        String eyeballImage = "";
        switch (direction) {
            case UP -> eyeballImage = "eyes_up";
            case DOWN -> eyeballImage = "eyes_down";
            case LEFT -> eyeballImage = "eyes_left";
            case RIGHT -> eyeballImage = "eyes_right";
        }

        int playerImageResId = getResources().getIdentifier(eyeballImage, "drawable", getPackageName());
        playerImage.setImageResource(playerImageResId);
        playerImage.setTag("playerImage");
        frameLayout.addView(playerImage);
    }
}