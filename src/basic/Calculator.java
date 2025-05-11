package com.project.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField textField;
    private JPanel panel, topPanel, bottomPanel;

    private JButton[] numberButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decimalButton, equalButton, clearButton;
    private JButton percentButton, ceButton, backspaceButton;
    private JButton sqrtButton, squareButton, reciprocalButton;
    private JButton plusMinusButton;
    private JButton mcButton, mrButton, mPlusButton, mMinusButton, msButton, mvButton;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initComponents();
        setLayout();
        setVisible(true);
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new GridLayout(6, 4, 10, 10));

        textField = new JTextField();
        textField.setEditable(true);
        textField.setPreferredSize(new Dimension(300, 100));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 55));

        numberButtons = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        clearButton = new JButton("C");
        percentButton = new JButton("%");
        ceButton = new JButton("CE");
        backspaceButton = new JButton("⌫"); // ⌫
        sqrtButton = new JButton("√"); // √
        squareButton = new JButton("x²");
        reciprocalButton = new JButton("1/x");
        plusMinusButton = new JButton("+/-");
        mcButton = new JButton("MC");
        mrButton = new JButton("MR");
        mPlusButton = new JButton("M+");
        mMinusButton = new JButton("M-");
        msButton = new JButton("MS");
        mvButton = new JButton("Mv");

        JButton[] buttons = {
                addButton, subButton, mulButton, divButton,
                decimalButton, equalButton, clearButton, percentButton,
                ceButton, backspaceButton, sqrtButton, squareButton,
                reciprocalButton, plusMinusButton,
                mcButton, mrButton, mPlusButton, mMinusButton, msButton, mvButton
        };

        for (JButton button : buttons) {
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
        }

        for (JButton button : numberButtons) {
            button.setFont(new Font("Arial", Font.PLAIN, 18));
        }
    }

    private void setLayout() {
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        topPanel.add(textField, BorderLayout.CENTER);
        panel.add(topPanel, BorderLayout.NORTH);

        // First row (memory)
        bottomPanel.add(mcButton);
        bottomPanel.add(mrButton);
        bottomPanel.add(mPlusButton);
        bottomPanel.add(mMinusButton);

        // Second row
        bottomPanel.add(msButton);
        bottomPanel.add(mvButton);
        bottomPanel.add(percentButton);
        bottomPanel.add(clearButton);

        // Third row
        bottomPanel.add(ceButton);
        bottomPanel.add(backspaceButton);
        bottomPanel.add(divButton);
        bottomPanel.add(mulButton);

        // Fourth row
        bottomPanel.add(numberButtons[7]);
        bottomPanel.add(numberButtons[8]);
        bottomPanel.add(numberButtons[9]);
        bottomPanel.add(subButton);

        // Fifth row
        bottomPanel.add(numberButtons[4]);
        bottomPanel.add(numberButtons[5]);
        bottomPanel.add(numberButtons[6]);
        bottomPanel.add(addButton);

        // Sixth row
        bottomPanel.add(numberButtons[1]);
        bottomPanel.add(numberButtons[2]);
        bottomPanel.add(numberButtons[3]);
        bottomPanel.add(equalButton);

        // Last row (0, ., +/-, 1/x)
        bottomPanel.add(plusMinusButton);
        bottomPanel.add(numberButtons[0]);
        bottomPanel.add(decimalButton);
        bottomPanel.add(reciprocalButton);

        panel.add(bottomPanel, BorderLayout.CENTER);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        for (int i = 0; i < 10; i++) {
            if (src == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
                return;
            }
        }

        if (src == decimalButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }

        if (src == clearButton) {
            textField.setText("");
            num1 = num2 = result = 0;
        }

        if (src == ceButton) {
            textField.setText("");
        }

        if (src == backspaceButton) {
            String current = textField.getText();
            if (!current.isEmpty()) {
                textField.setText(current.substring(0, current.length() - 1));
            }
        }

        if (src == addButton || src == subButton || src == mulButton || src == divButton) {
            try {
                num1 = Double.parseDouble(textField.getText());
                operator = ((JButton) src).getText().charAt(0);
                textField.setText("");
            } catch (NumberFormatException ignored) {}
        }

        if (src == equalButton) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': result = num2 != 0 ? num1 / num2 : 0; break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            } catch (NumberFormatException ignored) {}
        }

        if (src == sqrtButton) {
            try {
                double val = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(Math.sqrt(val)));
            } catch (NumberFormatException ignored) {}
        }

        if (src == squareButton) {
            try {
                double val = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(val * val));
            } catch (NumberFormatException ignored) {}
        }

        if (src == reciprocalButton) {
            try {
                double val = Double.parseDouble(textField.getText());
                textField.setText(val != 0 ? String.valueOf(1 / val) : "Infinity");
            } catch (NumberFormatException ignored) {}
        }

        if (src == plusMinusButton) {
            try {
                double val = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(-val));
            } catch (NumberFormatException ignored) {}
        }

        if (src == percentButton) {
            try {
                double val = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(val / 100));
            } catch (NumberFormatException ignored) {}
        }

        // Memory buttons - placeholders
        if (src == mcButton || src == mrButton || src == mPlusButton ||
                src == mMinusButton || src == msButton || src == mvButton) {
            JOptionPane.showMessageDialog(this, "Memory function not implemented.");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

