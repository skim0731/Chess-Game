package com.company.GUI;

import com.company.Game.GameBoard;
import com.company.units.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by kimsehwan on 2016. 2. 17..
 */
public class ChessView implements Observer {
    private GameBoard game;
    private int[] gameScore = new int[2];
    private String playerName1, playerName2;
    private ActionListener controller = null;
    private Container container;
    private final int frameWidth = 960;
    private final int frameHeight = 560;
    private JFrame mainFrame = new JFrame("Chess");
    private JPanel boardPanel;
    private JPanel menuPanel;
    private JButton[][] tiles = new JButton[8][8];
    private JLabel consoleLabel;
    private JLabel scoreLabel;
    private JButton startButton;
    private JButton undoButton;

    private ImageIcon black_king   = new ImageIcon("./src/img/black_king.png");
    private ImageIcon black_queen  = new ImageIcon("./src/img/black_queen.png");
    private ImageIcon black_bishop = new ImageIcon("./src/img/black_bishop.png");
    private ImageIcon black_knight = new ImageIcon("./src/img/black_knight.png");
    private ImageIcon black_rook   = new ImageIcon("./src/img/black_rook.png");
    private ImageIcon black_pawn   = new ImageIcon("./src/img/black_pawn.png");
    private ImageIcon white_king   = new ImageIcon("./src/img/white_king.png");
    private ImageIcon white_queen  = new ImageIcon("./src/img/white_queen.png");
    private ImageIcon white_bishop = new ImageIcon("./src/img/white_bishop.png");
    private ImageIcon white_knight = new ImageIcon("./src/img/white_knight.png");
    private ImageIcon white_rook   = new ImageIcon("./src/img/white_rook.png");
    private ImageIcon white_pawn   = new ImageIcon("./src/img/white_pawn.png");

    private ImageIcon black_custom1 = new ImageIcon("./src/img/black_custom1.png");
    private ImageIcon black_custom2 = new ImageIcon("./src/img/black_custom2.png");
    private ImageIcon white_custom1 = new ImageIcon("./src/img/white_custom1.png");
    private ImageIcon white_custom2 = new ImageIcon("./src/img/white_custom2.png");

    public ChessView(){
        //pick classic or custom game.
        showGameModePopUp();
        //ask for names.
        showPlayerNameInputPopUp();

        //container that connects JFrame and JPanel.
        container = mainFrame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        boardPanel = initBoardPanel();
        menuPanel = initMenuPanel();
        container.add(menuPanel, 0);
        container.add(boardPanel, 1);

        mainFrame.setMinimumSize(new Dimension(frameWidth, frameHeight));
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Display a pop up window with two text fields.
     * Get Players' names.
     */
    private void showPlayerNameInputPopUp(){
        JTextField namefield1 = new JTextField();
        JTextField namefield2 = new JTextField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("Player1, white"),
                namefield1,
                new JLabel("Player2, black"),
                namefield2
        };
        JOptionPane.showMessageDialog(null, inputs, "Names", JOptionPane.PLAIN_MESSAGE);

        playerName1 = namefield1.getText();
        playerName2 = namefield2.getText();
    }

    /**
     * Display pop-up dialogue to choose game mode, and initialize gameBoard.
     */
    public void showGameModePopUp(){
        Object[] options = {"Classic", "Custom"};
        int gameMode = JOptionPane.showOptionDialog(
                mainFrame,
                "Custom Game? or Classic Game?",
                "Game Mode",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);


        if(gameMode == JOptionPane.NO_OPTION) {
            this.game = new GameBoard(true);
        }
        else{
            this.game = new GameBoard(false);
        }

        game.addObserver(this);
    }

    /**
     * initialize JPanel that contains a start button, forfeit button, and undo button.
     * @return
     */
    private JPanel initMenuPanel(){
        JPanel menu = new JPanel();
        menu.setMinimumSize(new Dimension(560,400));
        menu.setLayout(new GridLayout(4,1));

        startButton = new JButton("Start");
        undoButton = new JButton("Undo");
        menu.add(startButton);
        menu.add(undoButton);

        consoleLabel = new JLabel("console");
        consoleLabel.setBackground(Color.white);
        consoleLabel.setOpaque(true);
        consoleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        scoreLabel = new JLabel("scores- "+ playerName1 + ": " + gameScore[0] + "   " + playerName2 + ": " + gameScore[1]);
        scoreLabel.setBackground(Color.white);
        scoreLabel.setOpaque(true);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        menu.add(scoreLabel);
        menu.add(consoleLabel);

        return menu;
    }

    /**
     * Create a JPanel that contains 64 JButtons in 8x8 grid with units in their positions.
     * @return
     */
    private JPanel initBoardPanel(){
        Units[][] gameBoard = game.getGameBoard();
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(8,8));
        board.setPreferredSize(new Dimension(560,560));
        board.setMaximumSize(new Dimension(560,560));
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //if there is no unit.
                if(gameBoard[i][j].getPlayer() == 0){
                    board.add(initGridButton(i,j, null));
                }
                //if player is 1, white,
                else if(gameBoard[i][j].getPlayer() == 1){
                    if(gameBoard[i][j].getClass() == UnitsKing.class)
                        board.add(initGridButton(i,j,white_king));
                    if(gameBoard[i][j].getClass() == UnitsQueen.class)
                        board.add(initGridButton(i,j,white_queen));
                    if(gameBoard[i][j].getClass() == UnitsBishop.class)
                        board.add(initGridButton(i,j,white_bishop));
                    if(gameBoard[i][j].getClass() == UnitsKnight.class)
                        board.add(initGridButton(i,j,white_knight));
                    if(gameBoard[i][j].getClass() == UnitsRook.class)
                        board.add(initGridButton(i,j,white_rook));
                    if(gameBoard[i][j].getClass() == UnitsPawn.class)
                        board.add(initGridButton(i,j,white_pawn));
                    if(gameBoard[i][j].getClass() == UnitsCustom1.class)
                        board.add(initGridButton(i,j,white_custom1));
                    if(gameBoard[i][j].getClass() == UnitsCustom2.class)
                        board.add(initGridButton(i,j,white_custom2));
                }

                //if player is 2, black,
                else if(gameBoard[i][j].getPlayer() == 2){
                    if(gameBoard[i][j].getClass() == UnitsKing.class)
                        board.add(initGridButton(i,j,black_king));
                    if(gameBoard[i][j].getClass() == UnitsQueen.class)
                        board.add(initGridButton(i,j,black_queen));
                    if(gameBoard[i][j].getClass() == UnitsBishop.class)
                        board.add(initGridButton(i,j,black_bishop));
                    if(gameBoard[i][j].getClass() == UnitsKnight.class)
                        board.add(initGridButton(i,j,black_knight));
                    if(gameBoard[i][j].getClass() == UnitsRook.class)
                        board.add(initGridButton(i,j,black_rook));
                    if(gameBoard[i][j].getClass() == UnitsPawn.class)
                        board.add(initGridButton(i,j,black_pawn));
                    if(gameBoard[i][j].getClass() == UnitsCustom1.class)
                        board.add(initGridButton(i,j,black_custom1));
                    if(gameBoard[i][j].getClass() == UnitsCustom2.class)
                        board.add(initGridButton(i,j,black_custom2));
                }
            }
        }
        return board;
    }

    /**
     * Private helper that creates a JButton with a correct background color and unit image.
     * @param i this tile's row position.
     * @param j this tile's col position.
     * @param icon piece image
     * @return
     */
    private JButton initGridButton(final int i,final int j, final ImageIcon icon){
        tiles[i][j] = new JButton();
        tiles[i][j].setActionCommand("tile"+i+j);
        tiles[i][j].setBackground(game.getTileColor(i,j));

        if(icon != null){
            JLabel unit_label = new JLabel();
            unit_label.setIcon(icon);
            tiles[i][j].add(unit_label);
        }
        tiles[i][j].setOpaque(true);
        tiles[i][j].setBorderPainted(false);

        tiles[i][j].addActionListener(controller);

        return tiles[i][j];
    }

    /**
     * This method is called when there is called when there is a change in gameBoard(model).
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if(arg != null){
            if(arg.getClass() == Color[][].class)
                updateTileColor();

            if(arg.getClass() == Units[][].class){
                boardPanel = initBoardPanel();
                container.remove(1);
                container.add(boardPanel, 1);
                mainFrame.setVisible(false);
                mainFrame.setVisible(true);
            }

            if(arg.getClass() == Integer.class){
                if(game.getPlayerInCheck() != 0){
                    consoleLabel.setText("Player " + game.getPlayerInCheck() + " is in check.");
                }
                if(game.getPlayerInCheckMate() != 0){
                    int winner;
                    if(game.getPlayerInCheckMate() == 1)
                        winner = 2;
                    else
                        winner = 1;
                    showGameOverPopUp(winner);
                }
                if(game.getPlayerInStaleMate() != 0){
                    int winner;
                    if(game.getPlayerInStaleMate() == 1)
                        winner = 2;
                    else
                        winner = 1;
                    showGameOverPopUp(winner);
                }
                scoreLabel.setText("scores- "+ playerName1 + ": " + gameScore[0] + "   " + playerName2 + ": " + gameScore[1]);
            }
        }
    }

    /**
     * This private helper is called when a game is ended.
     * Shows game over message and tells who the winner is.
     * When the pop-up window closes, it starts a new game.
     * Also increment winner score.
     * @param winner 1, if player1 wins.
     *               2, if player2 wins.
     */
    private void showGameOverPopUp(int winner){
        gameScore[winner-1]++;
        JOptionPane.showMessageDialog(mainFrame,
                "Player " + (winner) + " wins.",
                "Game Over",
                JOptionPane.PLAIN_MESSAGE);
        startNewGame();
    }

    /**
     * Start a new game.
     * Create new GameBoard object.
     */
    public GameBoard startNewGame(){
        showGameModePopUp();
        updateDisplay();
        return game;
    }

    /**
     * Private helper that creates new board panel and update.
     */
    private void updateDisplay(){
        boardPanel = initBoardPanel();
        container.remove(1);
        container.add(boardPanel, 1);
        scoreLabel.setText("scores- "+ playerName1 + ": " + gameScore[0] + "   " + playerName2 + ": " + gameScore[1]);
        mainFrame.setVisible(false);
        mainFrame.setVisible(true);
    }

    private void updateTileColor(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j].setBackground(game.getTileColor(i,j));
            }
        }
    }

    public void addController(ActionListener controller){
        this.controller = controller;
        startButton.addActionListener(controller);
        undoButton.addActionListener(controller);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j].addActionListener(controller);
            }
        }
    } //addController()

    public void setConsoleLabelText(String text){
        consoleLabel.setText(text);
    }

    public JFrame getMainFrame(){ return mainFrame; }

    public void incrementGameScore(int winner){
        ++gameScore[winner-1];
    }

    public GameBoard getGame() {
        return game;
    }
}
