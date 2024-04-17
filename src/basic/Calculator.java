package basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decimalButton, equalButton, clearButton;
    private JPanel panel, topPanel, bottomPanel;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    Calculator() {
        setTitle("Calculator");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setLayout();
        setVisible(true);
    }

    private void initComponents() {
        panel = new JPanel(new BorderLayout());
        topPanel = new JPanel();
        bottomPanel = new JPanel(new GridLayout(5, 4, 10, 10));

        textField = new JTextField();
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(300, 80)); // Adjusted height
        textField.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        
        // Increase font size
        Font font = new Font("Times New Roman", Font.PLAIN, 55); // Change the font name and size as desired
        textField.setFont(font);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        functionButtons = new JButton[4];
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;

        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        clearButton = new JButton("C");

        for (JButton button : functionButtons) {
            button.addActionListener(this);
        }

        decimalButton.addActionListener(this);
        equalButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    private void setLayout() {
        topPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(300, 300));

        topPanel.add(textField, BorderLayout.CENTER);
        topPanel.add(clearButton, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);

        for (int i = 1; i <= 9; i++) {
            bottomPanel.add(numberButtons[i]);
        }
        bottomPanel.add(numberButtons[0]);
        bottomPanel.add(decimalButton);

        for (JButton button : functionButtons) {
            bottomPanel.add(button);
        }

        bottomPanel.add(equalButton);

        panel.add(bottomPanel, BorderLayout.CENTER);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (source == decimalButton) {
            textField.setText(textField.getText().concat("."));
        }

        if (source == clearButton) {
            textField.setText("");
        }

        if (source == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (source == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (source == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (source == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (source == equalButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
