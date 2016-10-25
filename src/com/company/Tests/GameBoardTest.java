package com.company.Tests;

import com.company.Game.GameBoard;

import static org.junit.Assert.*;

/**
 * Created by kimsehwan on 2016. 2. 4..
 */
public class GameBoardTest {

    @org.junit.Test
    public void testCheckCheck1() throws Exception {
        GameBoard gameBoard = new GameBoard();
        gameBoard.players[0].getKing().move(2,0);
        gameBoard.updateBoard();
        int underCheck = gameBoard.checkCheck();
        assertEquals(1, underCheck);
    }

    @org.junit.Test
    public void testCheckCheck2() throws Exception {
        GameBoard gameBoard = new GameBoard();
        gameBoard.players[1].getKing().move(5,0);
        gameBoard.updateBoard();
        int underCheck = gameBoard.checkCheck();
        assertEquals(2, underCheck);
    }

    //public void testCheck
}