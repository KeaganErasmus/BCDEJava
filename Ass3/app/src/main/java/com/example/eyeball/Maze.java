package com.example.eyeball;

public class Maze {
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
