package com.johanlind.gameoflife.Component;

import javax.swing.*;
import java.awt.*;

public class GameBoardPanel extends JPanel {

    public GameBoardPanel(int boardWidth, int boardHeight) {
        this.setLayout(new GridLayout(boardWidth, boardHeight));
        this.setBackground(Color.WHITE);
    }
}
