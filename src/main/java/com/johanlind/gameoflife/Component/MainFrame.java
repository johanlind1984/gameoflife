package com.johanlind.gameoflife.Component;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(int resolutionWidth, int resolutionHeight) {
        this.setPreferredSize(new Dimension(resolutionWidth,resolutionHeight));
        this.setLayout(new GridLayout(1,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
