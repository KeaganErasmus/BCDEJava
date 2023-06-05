package com.example.eyeball;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eyeball.model.Color;
import com.example.eyeball.model.Direction;
import com.example.eyeball.model.Game;
import com.example.eyeball.model.PlayableSquare;
import com.example.eyeball.model.Shape;
import com.example.eyeball.model.Square;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Game game = new Game();
    GridLayout levelGrid;
    FrameLayout images;

    TextView goalCount;
    TextView goalText;
    TextView moveText;
    TextView timerText;
    TextView helpText;

    MediaPlayer moveSound;
    MediaPlayer winSound;
    MediaPlayer lostSound;
    MediaPlayer wrongMoveSound;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch soundSwitch;
    boolean playSound;

    int moveCount = 0;

    CountDownTimer timer;
    long timeLeft;
    boolean timerRunning;
    Button timerButton;

    HashMap<String, Integer> resourceMap = new HashMap<>(){
        {
            put("blue_cross", R.drawable.blue_cross);
            put("blue_diamond", R.drawable.blue_diamond);
            put("blue_flower", R.drawable.blue_flower);
            put("blue_star", R.drawable.blue_star);

            put("red_cross", R.drawable.red_cross);
            put("red_diamond", R.drawable.red_diamond);
            put("red_flower", R.drawable.red_flower);
            put("red_star", R.drawable.red_star);

            put("green_cross", R.drawable.green_cross);
            put("green_diamond", R.drawable.green_diamond);
            put("green_flower", R.drawable.green_flower);
            put("green_star", R.drawable.green_star);

            put("yellow_cross", R.drawable.yellow_cross);
            put("yellow_diamond", R.drawable.yellow_diamond);
            put("yellow_flower", R.drawable.yellow_flower);
            put("yellow_star", R.drawable.yellow_star);

            put("purple_bolt", R.drawable.purple_bolt);

            put("goal", R.drawable.goal);

            put("eyes_down", R.drawable.eyes_down);
            put("eyes_left", R.drawable.eyes_left);
            put("eyes_right", R.drawable.eyes_right);
            put("eyes_up", R.drawable.eyes_up);
        }};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Play the game
        startGame();
        goalText = findViewById(R.id.goalCountText);
        goalText.setText("Number of goals complete:  "
                + game.getCompletedGoalCount() + "/" + game.getGoalCount());
        moveText = findViewById(R.id.moveCounter);
        moveText.setText("number of moves: " + moveCount);

        helpText = findViewById(R.id.help_text);

        soundSwitch = findViewById(R.id.soundSwitch);
        soundSwitch.setChecked(true);

        moveSound = MediaPlayer.create(this, R.raw.move);
        winSound = MediaPlayer.create(this, R.raw.win);
        lostSound = MediaPlayer.create(this, R.raw.lost);
        wrongMoveSound = MediaPlayer.create(this, R.raw.wrong);

        timerText = findViewById(R.id.timer);
        timerButton = findViewById(R.id.PausePlay);
        timerButton.setText("Play");

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
        updateTimer();
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
        game.addSquare(new PlayableSquare(Color.PURPLE, Shape.BOLT), 1, 1);
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
        game.addLevel(4,3);
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
                if (resourceMap.containsKey(setImages(row, col))) {
                    // Get the resource ID from the map
                    int resourceId = resourceMap.get(imgName);
                    button.setBackgroundResource(resourceId);
                }

                button.setOnClickListener(view -> {
                    int[] position = (int[]) view.getTag();
                    onClickMove(position[0], position[1]);
                });

                images = new FrameLayout(this);
                images.addView(button);

                addImagesToSquares(row,col);
                levelGrid.addView(images);
            }
        }
    }

    private void startStop() {
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }

    private long getTimerTime(long time){
        // gets time in miliseconds
        return timeLeft = time;
    }

    @SuppressLint("SetTextI18n")
    public void startTimer() {
        timer = new CountDownTimer(getTimerTime(30000), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                gameLost();
            }
        }.start();

        timerButton.setText("Pause");
        timerRunning = true;
    }

    @SuppressLint("SetTextI18n")
    public void stopTimer() {
        timer.cancel();
        timerButton.setText("Play");
        timerRunning = false;
    }

    @SuppressLint("SetTextI18n")
    private void updateTimer(){
        int min = (int) timeLeft / 60000;
        int sec = (int) timeLeft % 60000 / 1000;

        String timeLeftText = "" +min;
        timeLeftText += ":";
        if (sec < 10){
            timeLeftText += 0;
        }
        timeLeftText += sec;
        timerText.setText("Timer: " + timeLeftText);
    }

    @SuppressLint("SetTextI18n")
    public void gameLost(){
        helpText.setText("The timer ran out YOU WERE TOO SLOW");
        lostSound.start();
        onPause();
    }

    public String setImages(int row, int column) {
        ArrayList<Square> allMySquares = game.currentLevel.allMySquares;
        for (Square square : allMySquares) {
            if (row == square.getRow() && column == square.getCol()) {
                String squareColor = square.color.toString();
                String squareShape = square.shape.toString();
                return squareColor.toLowerCase() + "_" + squareShape.toLowerCase();
            }
        }
        return "";
    }

    private void addImagesToSquares(int row, int col){
        if (row == game.getEyeballRow() && col == game.getEyeballColumn()) {
            ImageView eyeball = new ImageView(this);
            eyeball.setTag("eyeballImage");

            int eyeballId = resourceMap.get("eyes_right");
            eyeball.setImageResource(eyeballId);

            images.addView(eyeball);
        }
        if(game.hasGoalAt(row, col)){
            ImageView goalImage = new ImageView(this);
            goalImage.setTag("goalImage" + row + "_" + col);

            int goalImageResId = resourceMap.get("goal");
            goalImage.setImageResource(goalImageResId);

            images.addView(goalImage);
        }
    }

    private void setLevelName(String levelName){
        TextView levelText = findViewById(R.id.LevelName);
        levelText.setText(levelName);

    }

    public void restartClicked(View view) {
        // check android version and run a different restart method
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
    public void onClickMove(int row, int col) {
        helpText = findViewById(R.id.help_text);
        goalCount = findViewById(R.id.goalCountText);

        playSound = soundSwitch.isChecked();

        if (game.canMoveTo(row, col)) {
            if(playSound) {
                moveSound.start();
            }
            moveCounter();
            helpText.setText("");
            if (game.hasGoalAt(row, col)) {
                if(playSound) {
                    winSound.start();
                }
                game.completedGoal();
                helpText.setText("You reached the Goal!!!!");
                goalCount.setText("Number of goals complete:  " + (game.getCompletedGoalCount()) + "/" + (game.getGoalCount()));
            }
            game.moveTo(row, col);
            removeImg();
            displayEyeballImg(row, col, game.getEyeballDirection());
        } else {
            if(playSound) {
                wrongMoveSound.start();
            }
            String message = game.checkDirectionMessage(row,col).toString();
            helpText.setText(message);
        }
    }

    private void removeImg() {
        // Remove Eyeball image
        for (int i = 0; i < levelGrid.getChildCount(); i++) {
            View v = levelGrid.getChildAt(i);
            if (v instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) v;
                for (int j = 0; j < frameLayout.getChildCount(); j++) {
                    View subView = frameLayout.getChildAt(j);
                    if (subView.getTag() != null && subView.getTag().equals("eyeballImage")) {
                        frameLayout.removeView(subView);
                        return;
                    }
                }
            }
        }
    }

    private void displayEyeballImg(int row, int column, Direction direction) {
        FrameLayout frameLayout = (FrameLayout) levelGrid.getChildAt(row * levelGrid.getColumnCount() + column);
        ImageView eyeball = new ImageView(this);
        String eyeballImage = "";

        if(direction == Direction.UP){
            eyeballImage = "eyes_up";
        }
        else if (direction == Direction.DOWN){
            eyeballImage = "eyes_down";
        }
        else if (direction == Direction.LEFT){
            eyeballImage = "eyes_left";
        }else {
            eyeballImage = "eyes_right";
        }

        int playerImageResId = resourceMap.get(eyeballImage);
        eyeball.setImageResource(playerImageResId);
        eyeball.setTag("eyeballImage");
        frameLayout.addView(eyeball);
    }

    @SuppressLint("SetTextI18n")
    private void moveCounter(){
        moveCount += 1;
        moveText.setText("number of moves: " + moveCount);
    }

}