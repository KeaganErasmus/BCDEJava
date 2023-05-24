package com.example.eyeball;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    final Maze maze = new Maze();
    final ImageView[] imageViews = new ImageView[5];
    final int[] imageSrcs = new int[5];

//    Goal
    public int goal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViews[0] = findViewById(R.id.imageView1);
        imageViews[1] = findViewById(R.id.imageView2);
        imageViews[2] = findViewById(R.id.imageView3);
        imageViews[3] = findViewById(R.id.imageView4);
        imageViews[4] = findViewById(R.id.imageView5);

        imageSrcs[0] = R.drawable.b_cross;
        imageSrcs[1] = R.drawable.r_flower;
        imageSrcs[2] = R.drawable.b_star;
        imageSrcs[3] = R.drawable.r_star;
        imageSrcs[4] = R.drawable.goal;

        setLevelName("Level 1");
    }

    private void setPlayerInMaze(int location){
        Bitmap image1 = BitmapFactory.decodeResource(getResources(), imageSrcs[location]);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.eyes_up);
        Bitmap mergedImages = createSingleImageFromMultipleImages(image1, image2);
        imageViews[location].setImageBitmap(mergedImages);
    }
    public void onClickToStart(View view) {
        ImageButton imageButton = (ImageButton) view;
        imageButton.setImageResource(android.R.color.transparent);

        int currentLocationPlayer = maze.getPlayerPos();
        setPlayerInMaze(currentLocationPlayer);
    }

    private int getLocationImageView(ImageView imageView) {
        String name = getResources().getResourceEntryName(imageView.getId());
        name = name.replace("imageView", "");
        return Integer.parseInt(name) - 1;
    }

    public void onClickToMove(View view) {
        ImageView nextImageView = (ImageView) view;

        if (!isMoveable(maze.getPlayerPos(), getLocationImageView(nextImageView)))
            return;

        imageViews[maze.getPlayerPos()].setImageBitmap(BitmapFactory.decodeResource(getResources(), imageSrcs[maze.getPlayerPos()]));
        maze.setPlayerPos(getLocationImageView(nextImageView));

        int currentLocationPlayer = maze.getPlayerPos();
        setPlayerInMaze(currentLocationPlayer);

        if(currentLocationPlayer == 4){
            addGoal();
            setGoalCount();
            Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage) {
        Bitmap result = Bitmap.createBitmap(secondImage.getWidth(), secondImage.getHeight(), secondImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 10f, 10f, null);
        canvas.drawBitmap(secondImage, 10, 10, null);
        return result;
    }

    private boolean isMoveable(int currentLocationPlayer, int destination) {
        Set<String> current = new HashSet<>(Arrays.asList(maze.getMaze()[currentLocationPlayer].split("")));
        Set<String> des = new HashSet<>(Arrays.asList(maze.getMaze()[destination].split("")));
        current.remove("");
        des.remove("");

        current.retainAll(des);

        return !current.isEmpty();
    }

    private void addGoal(){
        goal += 1;
    }

    private void setGoalCount(){
        TextView text=(TextView)findViewById(R.id.goalCount);
        text.setText(String.valueOf(goal));
    }

    private void setLevelName(String levelName){
        TextView text = (TextView) findViewById(R.id.LevelName);
        text.setText(levelName);
    }
}