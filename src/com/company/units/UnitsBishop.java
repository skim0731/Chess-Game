package com.company.units;

import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * Bishop. It only moves in diagonal directions.
 *
 */
public class UnitsBishop extends Units {
    public UnitsBishop (int player, int num){
        this.player = player;
        if(num == 0)
            col = 2;

        if(num == 1)
            col = 5;

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

        //check down-right diagonal.
        int a = row+1;
        int b = col+1;
        while(a <= 7 && b <= 7){
            if(gameBoard[a][b].getPlayer() == 0){
                moves.add(a,b);
            }
            if(gameBoard[a][b].getPlayer() == otherPlayer){
                moves.add(a,b, true);
                break;
            }
            if(gameBoard[a][b].getPlayer() == this.player){
                break;
            }
            a++;
            b++;
        }

        //check up-left diagonal.
        a = row-1;
        b = col-1;
        while(a >= 0 && b >= 0){
            if(gameBoard[a][b].getPlayer() == 0){
                moves.add(a,b);
            }
            if(gameBoard[a][b].getPlayer() == otherPlayer){
                moves.add(a,b, true);
                break;
            }
            if(gameBoard[a][b].getPlayer() == this.player){
                break;
            }
            a--;
            b--;
        }

        //check down-left diagonal.
        a = row+1;
        b = col-1;
        while(a <= 7 && b >= 0){
            if(gameBoard[a][b].getPlayer() == 0){
                moves.add(a,b);
            }
            if(gameBoard[a][b].getPlayer() == otherPlayer){
                moves.add(a,b, true);
                break;
            }
            if(gameBoard[a][b].getPlayer() == this.player){
                break;
            }
            a++;
            b--;
        }

        //check up-right diagonal.
        a = row-1;
        b = col+1;
        while(a >= 0 && b <= 7){
            if(gameBoard[a][b].getPlayer() == 0){
                moves.add(a,b);
            }
            if(gameBoard[a][b].getPlayer() == otherPlayer){
                moves.add(a,b,true);
                break;
            }
            if(gameBoard[a][b].getPlayer() == this.player){
                break;
            }
            a--;
            b++;
        }
        return moves;
    }


}
