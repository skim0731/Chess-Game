package com.company.GUI;

import com.company.Game.GameBoard;

/**
 * Created by kimsehwan on 2016. 2. 17..
 */
public class RunChessGUI {

    public RunChessGUI(){
        ChessView view = new ChessView();

        view.getGame().addObserver(view);

        ChessController controller = new ChessController();
        controller.addView(view);
        view.addController(controller);
    }
}
