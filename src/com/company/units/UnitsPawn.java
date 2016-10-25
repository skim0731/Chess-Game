package com.company.units;


import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 1. 28.
 * Pawns are unusual because they move and capture in different ways: they move forward, but capture diagonally.
 * Pawns can only move forward one square at a time, except for their very first move where they can move forward two squares.
 * Pawns can only capture one square diagonally in front of them. They can never move or capture backwards.
 * If there is another piece directly in front of a pawn he cannot move past or capture that piece.
 */
public class UnitsPawn extends Units {
    private int numMove = 0;

    public UnitsPawn (int player, int num){
        this.player = player;
        if(player == 1)
            row = 6;
        if(player == 2)
            row = 1;
        col = num;
    }

    public CoordiList possibleMoves(Units[][] gameBoard){
        CoordiList moves = new CoordiList();
        if(!alive)
            return moves;

        if(player == 1){
            //check for 1 space move
            if(row-1 >= 0){
                if(gameBoard[row-1][col].getPlayer() == 0) {
                    moves.add(row-1, col);
                }
            }

            //check for 2 space move
            if(numMove == 0 && gameBoard[row-2][col].getPlayer() == 0 && gameBoard[row-1][col].getPlayer() == 0){
                moves.add(row-2, col);
            }

            //check if this Pawn can kill any enemy unit.
            if(row-1 >= 0 && col-1 >= 0){
                if(gameBoard[row-1][col-1].getPlayer() == 2){
                    moves.add(row-1, col-1, true);
                }
            }
            if(row-1 >= 0 && col+1 <= 7){
                if(gameBoard[row-1][col+1].getPlayer() == 2){
                    moves.add(row-1, col+1, true);
                }
            }
        }

        if(player == 2){
            //check for 1 space move
            if(row+1 <= 7){
                if(gameBoard[row+1][col].getPlayer() == 0) {
                    moves.add(row+1, col);
                }
            }

            //check for 2 space move
            if(numMove == 0 && gameBoard[row+2][col].getPlayer() == 0 && gameBoard[row+1][col].getPlayer() == 0){
                moves.add(row+2, col);
            }
            //check if this Pawn can kill any enemy unit.
            if(row+1 <= 7 && col-1 >= 0){
                if(gameBoard[row+1][col-1].getPlayer() == 1){
                    moves.add(row+1, col-1, true);
                }
            }
            if(row+1 <= 7 && col+1 <= 7){
                if(gameBoard[row+1][col+1].getPlayer() == 1){
                    moves.add(row+1, col+1, true);
                }
            }
        }

        return moves;
    }

    public void move(int row, int col){
        this.row = row;
        this.col = col;
        numMove++;
    }

    //Pawn needs this special case, because on the first move it should be able to move 2 forward.
    public void moveBack(int row, int col){
        this.row = row;
        this.col = col;
        numMove--;
    }

}
