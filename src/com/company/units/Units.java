package com.company.units;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * Abstract class.
 * This is a parent-class of all units.
 * All units have row, col position, player number, and a boolean variable that tells if a unit is alive.
 * All child-class must override possibleMoves() method, which returns a list of all possible moves.
 */
public abstract class Units {
    //position from 0 to 7;
    protected int row;
    protected int col;
    //player 1 or 2? this is needed for Pawns.
    protected int player;
    //Is unit alive?
    protected boolean alive = true;

    /**
     * This creates a unit with player index 0.
     * This is to be used for an empty tile.
     */
    public Units(){
        player = 0;
        row = -1;
        col = -1;
    }

    //Return list of possible moves.
    //abstract method.
    public abstract CoordiList possibleMoves(Units[][] gameBoard);

    /**
    * Move units to (x,y) position.
    */
    public void move(int row, int col){
        this.row = row;
        this.col = col;
    };

    public void killed(){
        row = -1;
        col = -1;
        alive = false;
    }

    public void revive(int row, int col){
        this.row = row;
        this.col = col;
        alive = true;
    }
    public int getRow(){ return row; }
    public int getCol(){ return col; }
    public int getPlayer(){ return player; }
    public boolean isAlive(){ return alive; }
}


