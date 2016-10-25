package com.company.units;

import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * King. It can move 1 space in any direction.
 */
public class UnitsKing extends Units {
    public UnitsKing (int player){
        this.player = player;
        col = 4;
        if(player == 1){
            row = 7;
        }
        if(player == 2){
            row = 0;
        }
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

        if(row+1 <= 7){
            if(gameBoard[row+1][col].getPlayer() == 0 )
                moves.add(row+1, col);

            if(gameBoard[row+1][col].getPlayer() == otherPlayer)
                moves.add(row+1, col, true);

            if(col-1 >= 0){
                if(gameBoard[row+1][col-1].getPlayer() == 0)
                    moves.add(row+1, col-1);

                if(gameBoard[row+1][col-1].getPlayer() == otherPlayer)
                    moves.add(row+1, col-1, true);
            }
            if(col+1 <= 7) {
                if (gameBoard[row+1][col+1].getPlayer() == 0)
                    moves.add(row+1, col+1);

                if(gameBoard[row+1][col+1].getPlayer() == otherPlayer)
                    moves.add(row+1, col+1, true);
            }
        }

        if(row-1 >= 0){
            if(gameBoard[row-1][col].getPlayer() == 0 )
                moves.add(row-1, col);

            if(gameBoard[row-1][col].getPlayer() == otherPlayer)
                moves.add(row-1, col, true);

            if(col-1 >= 0){
                if(gameBoard[row-1][col-1]==null)
                    moves.add(row-1, col-1);

                if(gameBoard[row-1][col-1].getPlayer() == otherPlayer)
                    moves.add(row-1, col-1, true);
            }
            if(col+1 <= 7) {
                if (gameBoard[row-1][col+1].getPlayer() == 0)
                    moves.add(row-1, col+1);

                if(gameBoard[row-1][col+1].getPlayer() == otherPlayer)
                    moves.add(row-1, col+1, true);
            }
        }

        if(col-1 >= 0){
            if(gameBoard[row][col-1].getPlayer() == 0)
                moves.add(row, col-1);

            if(gameBoard[row][col-1].getPlayer() == otherPlayer)
                moves.add(row, col-1, true);
        }
        if(col+1 <= 7) {
            if(gameBoard[row][col+1].getPlayer() == 0)
                moves.add(row, col+1);

            if(gameBoard[row][col+1].getPlayer() == otherPlayer)
                moves.add(row, col+1, true);
        }

        return moves;
    }

}
