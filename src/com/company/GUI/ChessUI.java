package com.company.GUI;

import com.company.Game.GameBoard;
import com.company.units.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kimsehwan on 2016. 2. 10..
 */
public class ChessUI   {
    private int selectedRow;
    private int selectedCol;

    private int[] playerWins = new int[2];
    private String playername1;
    private String playername2;
    private Container container;

    private GameBoard game;

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

    private ImageIcon black_king   = new ImageIcon("./src/com/company/img/black_king.png");
    private ImageIcon black_queen  = new ImageIcon("./src/com/company/img/black_queen.png");
    private ImageIcon black_bishop = new ImageIcon("./src/com/company/img/black_bishop.png");
    private ImageIcon black_knight = new ImageIcon("./src/com/company/img/black_knight.png");
    private ImageIcon black_rook   = new ImageIcon("./src/com/company/img/black_rook.png");
    private ImageIcon black_pawn   = new ImageIcon("./src/com/company/img/black_pawn.png");
    private ImageIcon white_king   = new ImageIcon("./src/com/company/img/white_king.png");
    private ImageIcon white_queen  = new ImageIcon("./src/com/company/img/white_queen.png");
    private ImageIcon white_bishop = new ImageIcon("./src/com/company/img/white_bishop.png");
    private ImageIcon white_knight = new ImageIcon("./src/com/company/img/white_knight.png");
    private ImageIcon white_rook   = new ImageIcon("./src/com/company/img/white_rook.png");
    private ImageIcon white_pawn   = new ImageIcon("./src/com/company/img/white_pawn.png");

    private ImageIcon black_custom1 = new ImageIcon("./src/com/company/img/black_custom1.png");
    private ImageIcon black_custom2 = new ImageIcon("./src/com/company/img/black_custom2.png");
    private ImageIcon white_custom1 = new ImageIcon("./src/com/company/img/white_custom1.png");
    private ImageIcon white_custom2 = new ImageIcon("./src/com/company/img/white_custom2.png");

    //constructor
    public ChessUI(){
        //ask for names.
        showPlayerNameInputPopUp();
        //pick classic or custom game.
        showGameModePopUp();
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

        playername1 = namefield1.getText();
        playername2 = namefield2.getText();
    }

    /**
     * Display pop-up dialogue to choose game mode.
     */
    private void showGameModePopUp(){
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
    }
    /**
     * initialize JPanel that contains a start button, forfeit button, and undo button.
     * @return
     */
    private JPanel initMenuPanel(){
        JPanel menu = new JPanel();
        menu.setMinimumSize(new Dimension(560,400));
        menu.setLayout(new GridLayout(4,1));

        initStartButton();
        initUndoButton();
        menu.add(startButton);
        menu.add(undoButton);

        consoleLabel = new JLabel("console");
        consoleLabel.setBackground(Color.white);
        consoleLabel.setOpaque(true);
        consoleLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        scoreLabel = new JLabel("scores- "+ playername1 + ": " + playerWins[0] + "   " + playername2 + ": " + playerWins[1]);
        scoreLabel.setBackground(Color.white);
        scoreLabel.setOpaque(true);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        menu.add(scoreLabel);
        menu.add(consoleLabel);

        return menu;
    }

    /**
     * initialize the StartButton.
     * Give a label "Start" and add an actionListner.
     * When clicked, a pop up message appears and asks for a confirmation.
     */
    private void initStartButton(){
        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int buttonClicked = JOptionPane.showConfirmDialog(
                        mainFrame,
                        "Do you really want to start a new Game?",
                        "New Game?",
                        JOptionPane.YES_NO_OPTION);
                if(buttonClicked == JOptionPane.YES_OPTION){
                    playerWins[0]++;
                    playerWins[1]++;
                    startNewGame();
                }

            }
        });
    }

    /**
     * initialize undoButton.
     * When clicked, it performs undo action.
     */
    private void initUndoButton(){
        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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

        if((i+j) % 2 == 0){
            tiles[i][j].setBackground(Color.white);
        }
        else{
            tiles[i][j].setBackground(Color.gray);
        }
        
        if(icon != null){
            JLabel unit_label = new JLabel();
            unit_label.setIcon(icon);
            tiles[i][j].add(unit_label);
        }
        tiles[i][j].setOpaque(true);
        tiles[i][j].setBorderPainted(false);
        
        tiles[i][j].addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Perform an non-attacking moved to clicked position.
                if(tiles[i][j].getBackground() == Color.ORANGE){
                    //move selected unit to i,j position.
                    performMoveAndDisplay(i,j,false);
                    return;
                }

                //Perform an attacking moved to clicked position.
                if(tiles[i][j].getBackground() == Color.RED){
                    //move selected unit to i,j position.
                    performMoveAndDisplay(i,j,true);

                    return;
                }

                removeHighlightTiles();

                //Execute when button is pressed
                consoleLabel.setText("You clicked the button"+i+j);

                //if selected unit can move.
                if(game.getGameBoard()[i][j].getPlayer() == game.getTurn()){
                    selectedRow = i;
                    selectedCol = j;

                    consoleLabel.setText("selected: " + i + ", " + j);

                    CoordiList moves = game.getGameBoard()[i][j].possibleMoves(game.getGameBoard());
                    tiles[i][j].setBackground(Color.PINK);
                    highlightTiles(moves);
                }
            }
        });

        return tiles[i][j];
    }

    /**
     * Move a unit and check if that move cause check, checkmate, or stalemate.
     * If it does, display a pop-up message and start a new game.
     * @param toRow destination row position
     * @param toCol destination col position
     * @param attacking
     */
    private void performMoveAndDisplay(int toRow, int toCol, boolean attacking){
        int winner;

        //If King dies, game over.
        if(game.getGameBoard()[toRow][toCol].getClass() == UnitsKing.class){
            if(game.getGameBoard()[toRow][toCol].getPlayer() == 1)
                winner = 2;
            else
                winner = 1;
            showGameOverPopUp(winner);
        }

        //move selected unit to i,j position.
        game.performMove(selectedRow, selectedCol, toRow, toCol, attacking);
        removeHighlightTiles();
        game.updateBoard();
        updateDisplay();
        int check = game.checkCheck();
        //if check, check for checkmate.
        if(check != 0){
            consoleLabel.setText("Player " + check + " is in check.");
            //if checkmate, game over.
            if(game.checkCheckMate(check)) {
                if(check == 1)
                    winner = 2;
                else
                    winner = 1;
                showGameOverPopUp(winner);
            }
        }
        //check for stalemate.
        int stalemate = game.checkStaleMate();
        if(stalemate > 0){
            if(stalemate == 1)
                winner = 2;
            else
                winner = 1;
            showGameOverPopUp(winner);
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
        playerWins[winner-1]++;
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
    private void startNewGame(){
        showGameModePopUp();
        updateDisplay();
    }

    /**
     * Private helper that creates new board panel and update.
     */
    private void updateDisplay(){
        boardPanel = initBoardPanel();

        container.remove(1);
        container.add(boardPanel, 1);
        scoreLabel = new JLabel("scores- "+ playername1 + ": " + playerWins[0] + "   " + playername2 + ": " + playerWins[1]);
        mainFrame.setVisible(false);
        mainFrame.setVisible(true);

    }


    /**
     * Takes list of possible moves and highlight those positions on GUI.
     * @param moves
     */
    private void highlightTiles(CoordiList moves){
        int row, col;
        for(int i = 0; i < moves.getListSize(); i++){
            row = moves.pickNode(i)[0];
            col = moves.pickNode(i)[1];
            if(moves.pickNode(i)[2] == 1)
                tiles[row][col].setBackground(Color.red);
            else
                tiles[row][col].setBackground(Color.orange);
        }
    }

    /**
     * This method removes all highlights on tiles.
     */
    private void removeHighlightTiles(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i+j) % 2 == 0)
                    tiles[i][j].setBackground(Color.white);
                else
                    tiles[i][j].setBackground(Color.gray);
            }
        }
    }
}
