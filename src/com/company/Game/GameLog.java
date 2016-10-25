package com.company.Game;

import com.company.units.Units;

/**
 * Created by kimsehwan on 2016. 2. 15..
 */
public class GameLog {
    private int[] from = new int[2];
    private int[] to = new int[2];
    private Units unitKilled;

    public GameLog(int fromRow, int fromCol, int toRow, int toCol, Units unitKilled){
        from[0] = fromRow;
        from[1] = fromCol;
        to[0] = toRow;
        to[1] = toCol;

        this.unitKilled = unitKilled;
    }

    public int[] getFrom() {
        return from;
    }

    public int[] getTo() {
        return to;
    }
    public Units getUnitKilled(){
        return unitKilled;
    }
}
