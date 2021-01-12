package com.johanlind.gameoflife.View;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class StartView {
    public static void render() {
        JFrame frame = new JFrame("Game of life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,768);
        frame.setLayout(new GridLayout(3,1));

        JPanel readMePanel = getReadMePanel();
        JPanel textFieldPanel = getTextfieldPanel();
        JPanel confirmButtonPanel = getConfirmButtonPanel();

        frame.getContentPane().add(readMePanel);
        frame.getContentPane().add(textFieldPanel);
        frame.getContentPane().add(confirmButtonPanel);
        frame.setVisible(true);
    }

    private static JPanel getReadMePanel() {
        String readMe = "Game of Life by Johan Lind";
        JLabel readMeLabel = new JLabel(readMe);
        JPanel readMePanel = new JPanel();
        readMePanel.setVisible(true);
        readMePanel.add(readMeLabel);
        return readMePanel;
    }

    private static JPanel getConfirmButtonPanel() {
        JButton button = new JButton("Confirm");
        button.setSize(200,30);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));
        buttonPanel.setVisible(true);
        buttonPanel.setSize(600, 30);
        buttonPanel.add(button);
        return buttonPanel;
    }

    public static NumberFormat getNumberFormatter() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(numberFormat);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        return numberFormat;
    }

    public static JPanel getTextfieldPanel() {
        JFormattedTextField inputHeightTextField = new JFormattedTextField(getNumberFormatter());
        inputHeightTextField.setVisible(true);
        inputHeightTextField.setSize(200, 20);

        JFormattedTextField inputWidthTextField = new JFormattedTextField(getNumberFormatter());
        inputHeightTextField.setVisible(true);
        inputHeightTextField.setSize(200, 20);

        JLabel heightLabel = new JLabel("Playfield cell height (numbers only):");
        heightLabel.setVisible(true);

        JLabel widthLabel = new JLabel("Playfield cell width (numbers only):");

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(2,2));
        textFieldPanel.add(heightLabel);
        textFieldPanel.add(inputHeightTextField);
        textFieldPanel.add(widthLabel);
        textFieldPanel.add(inputWidthTextField);
        return textFieldPanel;
    }
}
