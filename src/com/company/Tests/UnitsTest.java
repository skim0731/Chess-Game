package com.company.Tests;

import com.company.Game.GameBoard;
import com.company.units.CoordiList;
import com.company.units.Units;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kimsehwan on 2016. 2. 4..
 */
public class UnitsTest {
    GameBoard gameBoard = new GameBoard();
    Units unit;

    @Test
    public void testPawn() throws Exception {
        unit = gameBoard.players[1].getPawns()[4];
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(2, 4);
        expected.add(3, 4);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

    @Test
    public void testKing() throws Exception {
        unit = gameBoard.players[0].getKing();
        unit.move(5, 0);
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(4, 0);
        expected.add(4, 1);
        expected.add(5, 0);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

    @Test
    public void testQueen() throws Exception {
        unit = gameBoard.players[0].getQueen();
        unit.move(5, 0);
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(4, 0);
        expected.add(3, 0);
        expected.add(2, 0);
        expected.add(1, 0);

        expected.add(5, 1);
        expected.add(5, 2);
        expected.add(5, 3);
        expected.add(5, 4);
        expected.add(5, 5);
        expected.add(5, 6);
        expected.add(5, 7);

        expected.add(4, 1);
        expected.add(3, 2);
        expected.add(2, 3);
        expected.add(1, 4);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

    @Test
    public void testBishop() throws Exception {
        unit = gameBoard.players[0].getBishops()[0];
        unit.move(5, 0);
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(4, 1);
        expected.add(3, 2);
        expected.add(2, 3);
        expected.add(1, 4);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

    @Test
    public void testKnight() throws Exception {
        unit = gameBoard.players[0].getKnights()[1];
        unit.move(5, 0);
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(3, 1);
        expected.add(4, 2);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

    @Test
    public void testRook() throws Exception {
        unit = gameBoard.players[0].getRooks()[1];
        unit.move(5,0);
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(4, 0);
        expected.add(3, 0);
        expected.add(2, 0);
        expected.add(1, 0);

        expected.add(5, 1);
        expected.add(5, 2);
        expected.add(5, 3);
        expected.add(5, 4);
        expected.add(5, 5);
        expected.add(5, 6);
        expected.add(5, 7);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

    @Test
    public void testCustom1() throws Exception {
        unit = gameBoard.players[0].getCustom1();
        unit.move(3,1);
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(4, 1);
        expected.add(2, 1);
        expected.add(3, 0);
        expected.add(3, 2);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

    @Test
    public void testCustom2() throws Exception {
        unit = gameBoard.players[0].getCustom2();
        unit.move(3,1);
        CoordiList actual = unit.possibleMoves(gameBoard.getGameBoard());
        CoordiList expected = new CoordiList();

        expected.add(4, 0);
        expected.add(4, 2);
        expected.add(2, 0);
        expected.add(2, 2);

        assertArrayEquals(actual.pickNode(0), expected.pickNode(0));
        assertArrayEquals(actual.pickNode(1), expected.pickNode(1));
    }

}