package com.example.eyeball;

import android.widget.ImageView;

public class Maze {
//    final ImageView[] imageViews = new ImageView[5];
//    final int[] eyeBallImages = new int[4];
//    final int[] imageSrcs = new int[5];
//    imageViews[0] = findViewById(R.id.imageView1);
//    imageViews[1] = findViewById(R.id.imageView2);
//    imageViews[2] = findViewById(R.id.imageView3);
//    imageViews[3] = findViewById(R.id.imageView4);
//    imageViews[4] = findViewById(R.id.imageView5);
//
//    imageSrcs[0] = R.drawable.b_cross;
//    imageSrcs[1] = R.drawable.r_flower;
//    imageSrcs[2] = R.drawable.b_star;
//    imageSrcs[3] = R.drawable.r_star;
//    imageSrcs[4] = R.drawable.goal;
//
//    // Load eyeball images
//    eyeBallImages[0] = R.drawable.eyes_up;
//    eyeBallImages[1] = R.drawable.eyes_down;
//    eyeBallImages[2] = R.drawable.eyes_left;
//    eyeBallImages[3] = R.drawable.eyes_right;
    private String[] maze;
    private int playerPos;

    public Maze() {
        maze = new String[5];
        maze[0] = "a1";
        maze[1] = "b3";
        maze[2] = "a2";
        maze[3] = "b2";
        maze[4] = "c3";

        playerPos = 0;
    }

    public int getPlayerPos() {
        return this.playerPos;
    }

    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public String[] getMaze() {
        return this.maze;
    }

}
