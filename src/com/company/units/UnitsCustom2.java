package com.company.units;

import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 2. 10..
 * CustomUnit2. This unit can move one space in a diagonal direction.
 */
public class UnitsCustom2 extends Units {
    public UnitsCustom2(int player){
        if(player == 1){
            this.player = 1;
            row = 5;
            col = 7;
        }
        if(player == 2){
            this.player = 2;
            row = 2;
            col = 0;
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
            if(col-1 >= 0){
                if(gameBoard[row-1][col-1].getPlayer() == 0)
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

        return moves;
    }


}
