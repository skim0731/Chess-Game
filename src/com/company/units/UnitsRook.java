package com.company.units;


import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * Rook. It may move as far as it wants, but only forward, backward, and to the sides.
 */
public class UnitsRook extends Units {
    public UnitsRook (int player, int num){
        this.player = player;
        if(player == 1){
            row = 7;
            if(num == 0){
                col = 0;
            }
            if(num == 1){
                col = 7;
            }
        }
        if(player == 2){
            row = 0;
            if(num == 0){
                col = 0;
            }
            if(num == 1){
                col = 7;
            }
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

        for(int i = row+1; i <= 7; i++){
            if(gameBoard[i][col].getPlayer() == 0){
                moves.add(i, col);
            }
            if(gameBoard[i][col].getPlayer() == otherPlayer){
                moves.add(i, col, true);
                break;
            }
            if(gameBoard[i][col].getPlayer() == this.player){
                break;
            }
        }

        for(int i = row-1; i >= 0; i--){
            if(gameBoard[i][col].getPlayer() == 0){
                moves.add(i, col);
            }
            if(gameBoard[i][col].getPlayer() == otherPlayer){
                moves.add(i, col, true);
                break;
            }
            if(gameBoard[i][col].getPlayer() == this.player){
                break;
            }
        }

        for(int i = col+1; i <= 7; i++){
            if(gameBoard[row][i].getPlayer() == 0){
                moves.add(row, i);
            }
            if(gameBoard[row][i].getPlayer() == otherPlayer){
                moves.add(row, i, true);
                break;
            }
            if(gameBoard[row][i].getPlayer() == this.player){
                break;
            }
        }

        for(int i = col-1; i >= 0; i--){
            if(gameBoard[row][i].getPlayer() == 0){
                moves.add(row, i);
            }
            if(gameBoard[row][i].getPlayer() == otherPlayer){
                moves.add(row, i, true);
                break;
            }
            if(gameBoard[row][i].getPlayer() == this.player){
                break;
            }
        }

        return moves;
    }
}
