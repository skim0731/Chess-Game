package com.company.units;

import com.company.units.CoordiList;
import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * Queen. It can move in one straight direction.
 */
public class UnitsQueen extends Units {
    public UnitsQueen (int player){
        this.player = player;
        col = 3;
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

        //check down straight line.
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

        //check up straight line.
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

        //check right straight line.
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

        //check left straight line.
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
                moves.add(a,b, true);
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
