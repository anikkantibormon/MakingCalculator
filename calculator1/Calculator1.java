/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package calculator1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator1 extends JFrame {
    private final JTextField textField;
    private double firstNumber;
    private String operator;

    public Calculator1() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonLabel = ((JButton) e.getSource()).getText();
            String currentText = textField.getText();

            switch (buttonLabel) {
                case "=":
                    calculateResult();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    operator = buttonLabel;
                    firstNumber = Double.parseDouble(currentText);
                    textField.setText("");
                    break;
                default:
                    textField.setText(currentText + buttonLabel);
                    break;
            }
        }

        private void calculateResult() {
            String currentText = textField.getText();
            double secondNumber = Double.parseDouble(currentText);
            double result = 0.0;

            switch (operator) {
                case "+" -> result = firstNumber + secondNumber;
                case "-" -> result = firstNumber - secondNumber;
                case "*" -> result = firstNumber * secondNumber;
                case "/" -> result = firstNumber / secondNumber;
            }

            textField.setText(Double.toString(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calculator1 calculator = new Calculator1();
                calculator.setVisible(true);
            }
        });
    }
}


