package com.company.GUI;

import com.company.Game.GameBoard;
import com.company.units.CoordiList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kimsehwan on 2016. 2. 17..
 */
public class ChessController implements ActionListener {
    ChessView view;

    private int selectedRow;
    private int selectedCol;

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start")){
            int buttonClicked = JOptionPane.showConfirmDialog(
                    view.getMainFrame(),
                    "Do you really want to start a new Game?",
                    "New Game?",
                    JOptionPane.YES_NO_OPTION);
            if(buttonClicked == JOptionPane.YES_OPTION){
                view.incrementGameScore(1);
                view.incrementGameScore(2);
                view.startNewGame();
            }
        }

        if(e.getActionCommand().equals("Undo")){
            view.getGame().performUndo();
            view.getGame().updateBoard();
        }

        if(e.getActionCommand().substring(0,4).equals("tile")){
            int i = e.getActionCommand().charAt(4) - '0';
            int j = e.getActionCommand().charAt(5) - '0';
            //Perform an non-attacking moved to clicked position.
            if(view.getGame().getTileColor(i,j) == Color.ORANGE){
                //move selected unit to i,j position.
                view.getGame().performMoveAndDisplay(selectedRow,selectedCol,i,j,false);
                return;
            }

            //Perform an attacking moved to clicked position.
            if(view.getGame().getTileColor(i,j) == Color.RED){
                //move selected unit to i,j position.
                view.getGame().performMoveAndDisplay(selectedRow,selectedCol,i,j,true);

                return;
            }

            view.getGame().removeHighlightTiles();

            //Execute when button is pressed
            view.setConsoleLabelText("You clicked the button"+i+j);

            //if selected unit can move.
            if(view.getGame().getGameBoard()[i][j].getPlayer() == view.getGame().getTurn()){
                selectedRow = i;
                selectedCol = j;

                view.setConsoleLabelText("selected: " + i + ", " + j);

                CoordiList moves = view.getGame().getGameBoard()[i][j].possibleMoves(view.getGame().getGameBoard());
                view.getGame().setTileColor(i,j,Color.PINK);
                view.getGame().highlightTiles(moves);
            }
        }
    }

    public void addView(ChessView view){
        this.view = view;
    }
}
