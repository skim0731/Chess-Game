package com.company.units;

import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * Knight. It can move in "L" shape: going two squares in one direction, and then one more move at a 90 degree angle, just like the shape of an “L”
 */
public class UnitsKnight extends Units {
    public UnitsKnight (int player, int num){
        this.player = player;

        if(num == 0)
            col = 1;

        if(num == 1)
            col = 6;

        if(player == 1)
            row = 7;

        if(player == 2)
            row = 0;
    }

    public CoordiList possibleMoves(Units[][] gameBoard){
        CoordiList moves = new CoordiList();

        if(!alive)
            return moves;

        int otherPlayer;
        if(this.player == 1)
            otherPlayer = 2;
        else
            otherPlayer = 1;

        if(row-2 >= 0){
            if(col-1 >= 0){
                if(gameBoard[row-2][col-1].getPlayer() == 0)
                    moves.add(row-2, col-1);
                if(gameBoard[row-2][col-1].getPlayer() == otherPlayer)
                    moves.add(row-2, col-1, true);
            }
            if(col+1 <= 7){
                if(gameBoard[row-2][col+1].getPlayer() == 0)
                    moves.add(row-2, col+1);
                if(gameBoard[row-2][col+1].getPlayer() == otherPlayer)
                    moves.add(row-2, col+1, true);
            }
        }
        if(row-1 >= 0){
            if(col-2 >= 0){
                if(gameBoard[row-1][col-2].getPlayer() == 0)
                    moves.add(row-1, col-2);
                if(gameBoard[row-1][col-2].getPlayer() == otherPlayer)
                    moves.add(row-1, col-2, true);
            }
            if(col+2 <= 7){
                if(gameBoard[row-1][col+2].getPlayer() == 0)
                    moves.add(row-1, col+2);
                if(gameBoard[row-1][col+2].getPlayer() == otherPlayer)
                    moves.add(row-1, col+2, true);
            }
        }
        if(row+1 <= 7){
            if(col-2 >= 0){
                if(gameBoard[row+1][col-2].getPlayer() == 0)
                    moves.add(row+1, col-2);
                if(gameBoard[row+1][col-2].getPlayer() == otherPlayer)
                    moves.add(row+1, col-2, true);
            }
            if(col+2 <= 7){
                if(gameBoard[row+1][col+2].getPlayer() == 0)
                    moves.add(row+1, col+2);
                if(gameBoard[row+1][col+2].getPlayer() == otherPlayer)
                    moves.add(row+1, col+2, true);
            }
        }
        if(row+2 <= 7){
            if(col-1 >= 0){
                if(gameBoard[row+2][col-1].getPlayer() == 0)
                    moves.add(row+2, col-1);
                if(gameBoard[row+2][col-1].getPlayer() == otherPlayer)
                    moves.add(row+2, col-1, true);
            }
            if(col+1 <= 7){
                if(gameBoard[row+2][col+1].getPlayer() == 0)
                    moves.add(row+2, col+1);
                if(gameBoard[row+2][col+1].getPlayer() == otherPlayer)
                    moves.add(row+2, col+1, true);
            }
        }

        return moves;
    }

}
