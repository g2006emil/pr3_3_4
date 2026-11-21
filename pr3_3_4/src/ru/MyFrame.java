package ru;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

    JTextField display = new JTextField("0");

    double firstNumber = 0;
    String operation = "";
    boolean newNumber = true;

    public MyFrame() {
        setTitle("Калькулятор");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));

        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String text : buttons) {

            JButton button = new JButton(text);

            button.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (text.equals("+") ||
                            text.equals("-") ||
                            text.equals("*") ||
                            text.equals("/")) {

                        chooseOperation(text);

                    } else if (text.equals("=")) {

                        calculate();

                    } else {

                        addNumber(text);
                    }
                }
            });

            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel(
                "Калькулятор",
                SwingConstants.CENTER
        );

        add(label, BorderLayout.SOUTH);
    }

    public void addNumber(String number) {

        if (newNumber) {

            if (number.equals(".")) {
                display.setText("0.");
            } else {
                display.setText(number);
            }

            newNumber = false;

        } else {

            if (number.equals(".")) {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            } else {

                if (display.getText().equals("0")) {
                    display.setText(number);
                } else {
                    display.setText(display.getText() + number);
                }
            }
        }
    }

    public void chooseOperation(String op) {

        if (!operation.isEmpty() && !newNumber) {
            calculate();
        }

        firstNumber = Double.parseDouble(display.getText());
        operation = op;
        newNumber = true;
    }

    public void calculate() {

        if (operation.isEmpty() || newNumber) {
            return;
        }

        double secondNumber =
                Double.parseDouble(display.getText());

        double result = 0;

        switch (operation) {

            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":
                if (secondNumber == 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "На ноль делить нельзя!"
                    );

                    display.setText("0");
                    firstNumber = 0;
                    operation = "";
                    newNumber = true;
                    return;
                }

                result = firstNumber / secondNumber;
                break;
        }

        display.setText(String.valueOf(result));

        firstNumber = result;
        operation = "";
        newNumber = true;
    }
}