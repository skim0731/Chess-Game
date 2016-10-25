package com.company.units;

import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 2. 10..
 * CustomUnit1. This unit can move one space forward, backward, or to sides.
 */
public class UnitsCustom1 extends Units {
    public UnitsCustom1(int player){
        if(player == 1){
            this.player = 1;
            row = 5;
            col = 0;
        }
        if(player == 2){
            this.player = 2;
            row = 2;
            col = 7;
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
        //check top
        if(row+1 <= 7){
            if(gameBoard[row+1][col].getPlayer() == 0 )
                moves.add(row+1, col);

            if(gameBoard[row+1][col].getPlayer() == otherPlayer)
                moves.add(row+1, col, true);
        }
        //check bottom.
        if(row-1 >= 0){
            if(gameBoard[row-1][col].getPlayer() == 0 )
                moves.add(row-1, col);

            if(gameBoard[row-1][col].getPlayer() == otherPlayer)
                moves.add(row-1, col, true);
        }

        //check sides
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
