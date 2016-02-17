/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class CalculatorController implements Initializable {
    public Text text;
    public Button butMC, butM, butDEL, butC, butDot;
    public Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but0;
    public Button butEq, butAdd, butSub, butMul, butDiv;

    public static final int NULL = 0, ADD = 1, SUB = 2, MUL = 3, DIV = 4;
    public static final int UI_LIMIT = 12;
    int operation, decimal, length;
    double value, operand;
    double memory;
    DecimalFormat format = new DecimalFormat("#0.######");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        decimal = 1;
        value = operand = memory = 0;
        operation = NULL;
        length = 0;
        updateUI();
    }

    public void numberClick(ActionEvent event) {
        if (length < UI_LIMIT) {
            int number = Integer.valueOf(((Button) event.getSource()).getText());
            if (decimal == 1) {
                value *= 10;
            } else {
                number /= decimal;
                decimal *= 10;
            }
            value += number;
            length++;
            updateUI();
        }
    }

    public void operationClick(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        if (operation != NULL) evaluate();
        operand = value;
        switch (clicked.getText()) {
            case "+": operation = ADD; break;
            case "-": operation = SUB; break;
            case "*": operation = MUL; break;
            case "/": operation = DIV; break;
            default: operation = NULL;
        }
        value = 0;
        length = 0;
    }

    public void functionClick(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        switch (clicked.getText()) {
            case "=":
                evaluate();
                break;
            case "C":
                decimal = 1;
                value = operand = operation = 0;
                length = 0;
                updateUI();
                break;
            case "DEL":
                if (decimal > 10) {
                    value -= value % decimal;
                    decimal /= 10;
                } else if (decimal == 10) {
                    decimal /= 10;
                } else {
                    value /= 10;
                    value -= value % 1;
                }
                length--;
                updateUI();
                break;
            case "M+":
                memory += value;
                break;
            case "MC":
                value = memory;
                updateUI();
                break;
            case ".":
                decimal = 10;
                break;
        }
    }

    private void evaluate() {
        switch (operation) {
            case ADD: operand += value; break;
            case SUB: operand -= value; break;
            case MUL: operand *= value; break;
            case DIV: operand /= value; break;
            default: System.err.println("Evaluating no operation");
        }
        double aux = value;
        value = operand;
        operand = aux;
        length = String.valueOf((int)Math.floor(value)).length();
        decimal = 1;
        while (value % decimal != 0 && length < 12) {
            decimal *= 10;
            length++;
        }
        updateUI();
        operation = NULL;
    }

    private void updateUI() {
        text.setText(
                format.format(value)
        );
    }
}
