package com.example.eyeball.model;

public class Position {
    public int row;
    public int col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.col;
    }
}
