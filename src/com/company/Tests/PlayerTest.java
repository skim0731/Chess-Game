package com.company.Tests;

import com.company.Game.GameBoard;
import com.company.Game.Player;
import com.company.units.UnitsKing;
import com.company.units.CoordiList;
import com.company.units.Units;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kimsehwan on 2016. 2. 4..
 */
public class PlayerTest {

    @Test
    public void testPlayerInit() throws Exception {
        Player player = new Player(1, false);
        int row = player.getKing().getRow();
        int col = player.getKing().getCol();

        assertEquals(row, 7);
        assertEquals(col, 4);
    }
    @Test
    public void testPickUnit() throws Exception {
        Player player = new Player(1, false);
        Units king = new UnitsKing(1);
        Units picked = player.getKing();
        assertEquals(king.getCol(), picked.getCol());
        assertEquals(king.getRow(), picked.getRow());

    }

    @Test
    public void testMoveUnit() throws Exception {
        GameBoard gameBoard = new GameBoard();
        Units unit = gameBoard.players[0].getKnights()[0];
        CoordiList moves = unit.possibleMoves(gameBoard.getGameBoard());
        int[] targetPosition = moves.pickNode(0);

        int[] expected = new int[3];
        expected[0] = 5;
        expected[1] = 0;
        expected[2] = 0;

        assertArrayEquals(targetPosition, expected);
    }
}