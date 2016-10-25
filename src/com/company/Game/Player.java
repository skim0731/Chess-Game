package com.company.Game;

import com.company.units.*;

/**
 * Created by kimsehwan on 2016. 1. 28..
 *
 * This class constains units for one player.
 * 1 king, 1 queen, 2 bishops, 2 knights, 2 rooks, and 8 pawns.
 * Also has a player number. 1 is white and 2 is black.
 *  | 0 1 2 3 4 5 6 7
 * 0| * * * * * * * *  <- Player 2
 * 1| * * * * * * * *
 * 2|
 * 3|
 * 4|
 * 5|
 * 6| % % % % % % % %  <- Player 1
 * 7| % % % % % % % %
 */
public class Player {
    //player 1 or 2.
    private int player;

    private UnitsKing king;
    private UnitsQueen queen;
    private UnitsBishop[] bishops = new UnitsBishop[2];
    private UnitsRook[] rooks = new UnitsRook[2];
    private UnitsKnight[] knights = new UnitsKnight[2];
    private UnitsPawn[] pawns = new UnitsPawn[8];

    private UnitsCustom1 custom1;
    private UnitsCustom2 custom2;

    public Player(int player, boolean customGame){
        this.player = player;
        InitUnits(customGame);
    }

    /**
     * Initialize player's units.
     * If customGame, add two custom units.
     * @param customGame
     */
    private void InitUnits(boolean customGame){
        king = new UnitsKing(player);
        queen = new UnitsQueen(player);

        for(int i = 0; i < 2; i++){
            bishops[i] = new UnitsBishop(player, i);
            rooks[i] = new UnitsRook(player, i);
            knights[i] = new UnitsKnight(player, i);
        }
        for(int i = 0; i < 8; i++){
            pawns[i] = new UnitsPawn(player, i);
        }

        if(customGame){
            custom1 = new UnitsCustom1(player);
            custom2 = new UnitsCustom2(player);
        }
    }


    public UnitsCustom1 getCustom1() {
        return custom1;
    }

    public UnitsCustom2 getCustom2() {
        return custom2;
    }

    public UnitsKing getKing() {
        return king;
    }

    public UnitsQueen getQueen() {
        return queen;
    }

    public UnitsBishop[] getBishops() {
        return bishops;
    }

    public UnitsRook[] getRooks() {
        return rooks;
    }

    public UnitsKnight[] getKnights() {
        return knights;
    }

    public UnitsPawn[] getPawns() {
        return pawns;
    }
}
