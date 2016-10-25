package com.company.Game;

import com.company.units.Units;
import com.company.units.CoordiList;
import com.company.units.UnitsKing;
import com.company.units.UnitsPawn;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by kimsehwan on 2016. 1. 28..
 * GameBoard contains two player objects.
 * This class organizes all positions of pieces and checks if a player is in check or checkmate.
 *
 *  | 0 1 2 3 4 5 6 7
 * 0| * * * * * * * *  <- Player 2 (Black)
 * 1| * * * * * * * *
 * 2|
 * 3|
 * 4|
 * 5|
 * 6| % % % % % % % %  <- Player 1 (White)
 * 7| % % % % % % % %
 *
 *
 *
 *  Coordinate = (row, column)
 */


public class GameBoard extends Observable{
    private int playerInCheck = 0;
    private int playerInCheckMate = 0;
    private int playerInStaleMate = 0;
    private ArrayList<GameLog> gameLogs = new ArrayList<>();
    private Units[][] gameBoard = new Units[8][8];
    private Color[][] tileColors = new Color[8][8];
    private boolean customGame;
    private int numMove  = 0;
    public Player[] players = new Player[2];

    /**
     * Default constructor. Creates classic game.
     */
    public GameBoard(){
        init(false);
    }

    /**
     * Create classic or custom game based on input.
     * If input is true, creates custom game.
     * @param customGame
     */
    public GameBoard(boolean customGame){
        init(customGame);
    }

    public void init(boolean customGame){
        this.customGame = customGame;
        //initialize board to empty tiles.
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //Create an empty tile. This has player index 0.
                gameBoard[i][j] = new Units() {
                    @Override
                    public CoordiList possibleMoves(Units[][] gameBoard) {
                        return null;
                    }
                };

                if((i+j)%2 == 0)
                    tileColors[i][j] = Color.white;
                else
                    tileColors[i][j] = Color.gray;
            }
        }

        //create player objects.
        players[0] = new Player(1, customGame);
        players[1] = new Player(2, customGame);

        updateBoard();
    }

    /**
     * get positions of units and update those info on the board.
     */
    public void updateBoard(){
        //initialize tiles to empty tiles.
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //Create an empty tile. This has player index 0.
                gameBoard[i][j] = new Units() {
                    @Override
                    public CoordiList possibleMoves(Units[][] gameBoard) {
                        return null;
                    }
                };
            }
        }

        int row;
        int col;
        for(int i = 0; i < 2; i++){
            if(players[i].getKing().isAlive()){
                row = players[i].getKing().getRow();
                col = players[i].getKing().getCol();
                gameBoard[row][col] = players[i].getKing();
            }
            if(players[i].getQueen().isAlive()){
                row = players[i].getQueen().getRow();
                col = players[i].getQueen().getCol();
                gameBoard[row][col] = players[i].getQueen();
            }
            for(int j = 0; j < 2; j++){
                if(players[i].getBishops()[j].isAlive()){
                    row = players[i].getBishops()[j].getRow();
                    col = players[i].getBishops()[j].getCol();
                    gameBoard[row][col] = players[i].getBishops()[j];
                }
                if(players[i].getKnights()[j].isAlive()){
                    row = players[i].getKnights()[j].getRow();
                    col = players[i].getKnights()[j].getCol();
                    gameBoard[row][col] = players[i].getKnights()[j];
                }
                if(players[i].getRooks()[j].isAlive()){
                    row = players[i].getRooks()[j].getRow();
                    col = players[i].getRooks()[j].getCol();
                    gameBoard[row][col] = players[i].getRooks()[j];
                }
            }
            for(int j = 0; j < 8; j++){
                if(players[i].getPawns()[j].isAlive()){
                    row = players[i].getPawns()[j].getRow();
                    col = players[i].getPawns()[j].getCol();
                    gameBoard[row][col] = players[i].getPawns()[j];
                }
            }

            if(customGame){
                if(players[i].getCustom1().isAlive()){
                    row = players[i].getCustom1().getRow();
                    col = players[i].getCustom1().getCol();
                    gameBoard[row][col] = players[i].getCustom1();
                }
                if(players[i].getCustom2().isAlive()){
                    row = players[i].getCustom2().getRow();
                    col = players[i].getCustom2().getCol();
                    gameBoard[row][col] = players[i].getCustom2();
                }
            }
        }

        setChanged();
        notifyObservers(gameBoard);

    }

    /**
     * check if there is a stalemate.
     * @return return 0 if there is no stalemate.
     * return 1 if player 1 is in stalemate.
     * return 2 if player 2 is in stalemate.
     */
    public int checkStaleMate(){
        playerInStaleMate = 0;
        for(int i = 0; i < 2; i++){
            if(checkStaleMateHelper(i+1)){
                playerInStaleMate = i+1;
                break;
            }
        }

        setChanged();
        notifyObservers(playerInStaleMate);
        return playerInStaleMate;

    }

    /**
     * check if a specific player is in stalemate.
     * check if the player has any possible move.
     * @param player
     * @return true if the player can't make a move.
     * false if there is a possible move.
     */
    private boolean checkStaleMateHelper(int player){
        CoordiList moves;
        Player thisPlayer = players[player-1];
        moves = thisPlayer.getKing().possibleMoves(gameBoard);
        if(moves.getListSize() > 0)
            return false;
        moves = thisPlayer.getQueen().possibleMoves(gameBoard);
        if(moves.getListSize() > 0)
            return false;

        for(int i = 0; i < 2; i ++){
            moves = thisPlayer.getBishops()[i].possibleMoves(gameBoard);
            if(moves.getListSize() > 0)
                return false;
            moves = thisPlayer.getKnights()[i].possibleMoves(gameBoard);
            if(moves.getListSize() > 0)
                return false;
            moves = thisPlayer.getRooks()[i].possibleMoves(gameBoard);
            if(moves.getListSize() > 0)
                return false;
        }

        for(int i = 0; i < 8; i++){
            moves = thisPlayer.getPawns()[i].possibleMoves(gameBoard);
            if(moves.getListSize() > 0)
                return false;
        }

        if(customGame){
            moves = thisPlayer.getCustom1().possibleMoves(gameBoard);
            if(moves.getListSize() > 0)
                return false;
            moves = thisPlayer.getCustom2().possibleMoves(gameBoard);
            if(moves.getListSize() > 0)
                return false;
        }

        return true;
    }

    /**
     * check if any player is in check.
     * @return return 0 if false;
     * return 1 if player1's king is in check.
     * return 2 if player2's king is in check.
     */
    public int checkCheck(){
        playerInCheck = 0;

        for(int i = 0; i < 2; i++){
            if(checkCheckHelper(i+1)) {
                playerInCheck = i + 1;
                break;
            }
        }
        setChanged();
        notifyObservers(playerInCheck);
        return playerInCheck;
    }

    /**
     * check if a selected player is in check.
     * @return true if a selected player is in check.
     * false if a selected player is not in check.
     */
    private boolean checkCheckHelper(int player){
        Player thisPlayer = players[player-1];
        Player otherPlayer;

        if(player == 1)
            otherPlayer = players[1];
        else
            otherPlayer = players[0];

        int row = thisPlayer.getKing().getRow();
        int col = thisPlayer.getKing().getCol();

        CoordiList moves = otherPlayer.getKing().possibleMoves(gameBoard);

        if(moves.isFound(row, col))
            return true;
        if(otherPlayer.getQueen().isAlive()){
            moves = otherPlayer.getQueen().possibleMoves(gameBoard);
            if(moves.isFound(row, col))
                return true;
        }

        for(int k = 0; k < 2; k++){
            if(otherPlayer.getBishops()[k].isAlive()){
                moves = otherPlayer.getBishops()[k].possibleMoves(gameBoard);
                if(moves.isFound(row,col))
                    return true;
            }
            if(otherPlayer.getKnights()[k].isAlive()){
                moves = otherPlayer.getKnights()[k].possibleMoves(gameBoard);
                if(moves.isFound(row,col))
                    return true;
            }
            if(otherPlayer.getRooks()[k].isAlive()){
                moves = otherPlayer.getRooks()[k].possibleMoves(gameBoard);
                if(moves.isFound(row,col))
                    return true;
            }
        }

        for(int k = 0; k < 8; k++){
            if(otherPlayer.getPawns()[k].isAlive()){
                moves = otherPlayer.getPawns()[k].possibleMoves(gameBoard);
                if(moves.isFound(row,col))
                    return true;
            }
        }

        if(customGame){
            if(otherPlayer.getCustom1().isAlive()){
                moves = otherPlayer.getCustom1().possibleMoves(gameBoard);
                if(moves.isFound(row,col));
            }
            if(otherPlayer.getCustom2().isAlive()){
                moves = otherPlayer.getCustom2().possibleMoves(gameBoard);
                if(moves.isFound(row,col));
            }
        }
        return false;
    }

    /**
     * Helper function for checkCheckMate().
     * Check if a specific player is in checkmate.
     * @param player player that we are going to check.
     * @return true if the player is in checkmate, false if not.
     */
    public boolean checkCheckMate(int player){
        Player thisPlayer = players[player-1];
        //check if king can break check.
        if(canBreakCheck(thisPlayer.getKing(), player))
            return false;
        //check if queen can break check.
        if(canBreakCheck(thisPlayer.getQueen(), player))
            return false;
        for(int i = 0; i < 2; i++){
            if(canBreakCheck(thisPlayer.getBishops()[i], player))
                return false;
            if(canBreakCheck(thisPlayer.getKnights()[i], player))
                return false;
            if(canBreakCheck(thisPlayer.getRooks()[i], player))
                return false;

        }

        for(int i = 0; i < 8; i++){
            if(canBreakCheck(thisPlayer.getPawns()[i],player))
                return false;
        }

        if(customGame){
            if(canBreakCheck(thisPlayer.getCustom1(), player))
                return false;
            if(canBreakCheck(thisPlayer.getCustom2(), player))
                return false;
        }
        playerInCheckMate = player;
        setChanged();
        notifyObservers(playerInCheckMate);
        return true;
    }

    /**
     * Takes a unit and check if that unit can make a move that can break the check.
     * @param unit
     * @param player
     * @return true if it can, false if it cannot.
     */
    private boolean canBreakCheck(Units unit, int player){
        if(!unit.isAlive())
            return false;

        CoordiList moves = unit.possibleMoves(gameBoard);
        final int originalRow = unit.getRow();
        final int originalCol = unit.getCol();
        boolean stillInCheck;
        int[] move;

        for(int i = 0; i < moves.getListSize(); i++){
            move = moves.pickNode(i);
            //move to selected position.
            unit.move(move[0], move[1]);
            //check if we still have a check.
            stillInCheck = checkCheckHelper(player);

            //move back to original position.
            unit.move(originalRow, originalCol);

            //if this move can break a check, we don't have a checkmate.
            if(!stillInCheck){
                return true;
            }
        }

        return false;
    }

    /**
     * Move a unit from its position to the selected position, and add that move to gameLogs.
     * @param fromRow current row position
     * @param fromCol current col position
     * @param toRow new row position
     * @param toCol new col position
     * @param attacking if attacking, killUnit() method should be called.
     */
    public void performMove(int fromRow, int fromCol, int toRow, int toCol, boolean attacking){
        Units unitKilled = null;
        if(attacking) {
            unitKilled = gameBoard[toRow][toCol];
            gameBoard[toRow][toCol].killed();
        }
        gameBoard[fromRow][fromCol].move(toRow, toCol);
        numMove++;

        gameLogs.add(new GameLog(fromRow, fromCol, toRow, toCol, unitKilled));
    }

    /**
     * Perform undo. (one move)
     */
    public void performUndo(){
        if(gameLogs.size() == 0)
            return;

        //Take the last move and undo.
        undoHelper(gameLogs.remove(gameLogs.size() - 1));
        removeHighlightTiles();
        return;

    }

    /**
     * private undoHelper. Takes a log (move) and undo that move.
     * If last move killed any unit, we have to put it back on the board.
     * @param log
     */
    private void undoHelper(GameLog log){
        int moveFromRow = log.getTo()[0];
        int moveFromCol = log.getTo()[1];
        int moveToRow = log.getFrom()[0];
        int moveToCol = log.getFrom()[1];

        //This special case is needed for Pawns, since they should be able to move 2 forward if they haven't moved.
        if(gameBoard[moveFromRow][moveFromCol].getClass() == UnitsPawn.class)
            ((UnitsPawn)gameBoard[moveFromRow][moveFromCol]).moveBack(moveToRow, moveToCol);
        else
            gameBoard[moveFromRow][moveFromCol].move(moveToRow, moveToCol);

        //if a unit was killed, revive.
        if(log.getUnitKilled() != null){
            log.getUnitKilled().revive(moveFromRow, moveFromCol);
        }

        numMove--;
    }

    /**
     * Move a unit and check if that move cause check, checkmate, or stalemate.
     * If it does, display a pop-up message and start a new game.
     * @param fromRow starting row position
     * @param fromCol starting col position
     * @param toRow destination row position
     * @param toCol destination col position
     * @param attacking attacking other unit or not
     */
    public void performMoveAndDisplay(int fromRow, int fromCol, int toRow, int toCol, boolean attacking){
        int winner = 0;

        //If King dies, game over.
        if(gameBoard[toRow][toCol].getClass() == UnitsKing.class){
            if(gameBoard[toRow][toCol].getPlayer() == 1)
                playerInStaleMate = 1;
            else
                playerInStaleMate = 2;

            setChanged();
            notifyObservers(playerInStaleMate);
        }

        //move selected unit to i,j position.
        performMove(fromRow, fromCol, toRow, toCol, attacking);
        removeHighlightTiles();
        updateBoard();

        int check = checkCheck();
        //if check, check for checkmate.
        if(check != 0){
            checkCheckMate(check);
        }
        //check for stalemate.
        checkStaleMate();
    }

    /**
     * Takes list of possible moves and highlight those positions on GUI.
     * @param moves
     */
    public void highlightTiles(CoordiList moves){
        int row, col;
        for(int i = 0; i < moves.getListSize(); i++){
            row = moves.pickNode(i)[0];
            col = moves.pickNode(i)[1];
            if(moves.pickNode(i)[2] == 1)
                tileColors[row][col] = Color.red;
            else
                tileColors[row][col] = Color.orange;
        }
        setChanged();
        notifyObservers(tileColors);
    }

    /**
     * This method removes all highlights on tiles.
     */
    public void removeHighlightTiles(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i+j) % 2 == 0)
                    tileColors[i][j] = Color.white;
                else
                    tileColors[i][j] = Color.gray;
            }
        }
        setChanged();
        notifyObservers(tileColors);
    }

    public Color getTileColor(int i, int j){
        return tileColors[i][j];
    }

    public void setTileColor(int i, int j, Color color){
        tileColors[i][j] = color;
        setChanged();
        notifyObservers(tileColors);
    }

    public Units[][] getGameBoard() { return gameBoard; }

    public int getTurn(){
        if(numMove % 2 == 0)
            return 1; //white's turn
        else
            return 2; //black's turn
    }

    public int getPlayerInCheck() {
        return playerInCheck;
    }

    public int getPlayerInCheckMate() {
        return playerInCheckMate;
    }

    public int getPlayerInStaleMate() {
        return playerInStaleMate;
    }
}
