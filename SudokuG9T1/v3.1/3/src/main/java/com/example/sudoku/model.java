package com.example.sudoku;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class model implements Initializable {
    public static TextField[][] board;
    public static final int gridSize = 9;

    @FXML
    public TextField textField00, textField01, textField02, textField03, textField04, textField05, textField06, textField07, textField08, textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17, textField18, textField20, textField21, textField22, textField23, textField24, textField25, textField26, textField27, textField28, textField30, textField31, textField32, textField33, textField34, textField35, textField36, textField37, textField38, textField41, textField42, textField43, textField44, textField45, textField46, textField47, textField48, textField40, textField50, textField51, textField52, textField53, textField54, textField55, textField56, textField57, textField58, textField60, textField61, textField62, textField63, textField64, textField65, textField66, textField67, textField68, textField70, textField71, textField72, textField73, textField74, textField75, textField76, textField77, textField78, textField80, textField81, textField82, textField83, textField84, textField85, textField86, textField87, textField88;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = new TextField[][]{{textField00, textField01, textField02, textField03, textField04, textField05, textField06, textField07, textField08}, {textField10, textField11, textField12, textField13, textField14, textField15, textField16, textField17, textField18}, {textField20, textField21, textField22, textField23, textField24, textField25, textField26, textField27, textField28}, {textField30, textField31, textField32, textField33, textField34, textField35, textField36, textField37, textField38}, {textField40, textField41, textField42, textField43, textField44, textField45, textField46, textField47, textField48}, {textField50, textField51, textField52, textField53, textField54, textField55, textField56, textField57, textField58}, {textField60, textField61, textField62, textField63, textField64, textField65, textField66, textField67, textField68}, {textField70, textField71, textField72, textField73, textField74, textField75, textField76, textField77, textField78}, {textField80, textField81, textField82, textField83, textField84, textField85, textField86, textField87, textField88},};
    }

    static int numGenerator() {

        Random randomNum = new Random();
        int Chance = randomNum.nextInt(90);
        if (Chance <= 70) {
            return (randomNum.nextInt(9) + 1);
        }
        return 0;
    }

    public void autoFill() {
        for (TextField[] textFields : board) {
            for (TextField textField : textFields) {
                int temp = numGenerator();
                if (temp != 0) {
                    textField.setText(Integer.toString(temp));
                    textField.setStyle("-fx-text-fill: black");
                    textField.setDisable(true);
                } else {
                    textField.setText("");
                    textField.setDisable(false);
                }

            }
        }
        doubleNum();
    }


    public void doubleNum() {

        // Row
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                var input = board[i][j].getText();
                var intInput = getTextFieldValue(board[i][j]);

                board[i][j].setText("");

                if (isNumberInRow(intInput, i)) {
                    board[i][j].setText("");
                    board[i][j].setDisable(false);
                } else {
                    board[i][j].setText(input);
                }
            }
        }

        // Column
        for (TextField[] textFields : board) {
            for (int j = 0; j < textFields.length; j++) {
                var input = textFields[j].getText();
                var intInput = getTextFieldValue(textFields[j]);

                textFields[j].setText("");

                if (isNumberInColumn(intInput, j)) {
                    textFields[j].setText("");
                    textFields[j].setDisable(false);
                } else {
                    textFields[j].setText(input);
                }
            }
        }

        // box
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                var input = board[i][j].getText();
                var intInput = getTextFieldValue(board[i][j]);

                board[i][j].setText("");

                if (isNumberInBox(intInput, i, j)) {
                    board[i][j].setText("");
                    board[i][j].setDisable(false);
                } else {
                    board[i][j].setText(input);
                }
            }
        }
    }

    public void check() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                var input = board[i][j].getText();
                var intInput = getTextFieldValue(board[i][j]);
                board[i][j].setText("");

                if ((isNumberInRow(intInput, i)
                        || isNumberInColumn(intInput, j)
                        || isNumberInBox(intInput, i, j))
                        && intInput != 0) {
                    board[i][j].setStyle("-fx-text-fill: red");
                    board[i][j].setStyle("-fx-background-color: #db483d");
                } else {
                    board[i][j].setStyle("-fx-text-fill: black");
                }
                board[i][j].setText(input);
            }
        }
    }

    public boolean isNumberInRow(int number, int row) {
        for (int i = 0; i < gridSize; i++) {
            var exNum = getTextFieldValue(board[row][i]);
            if (exNum == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isNumberInColumn(int number, int column) {
        for (int i = 0; i < gridSize; i++) {
            var exNum = getTextFieldValue(board[i][column]);
            if (exNum == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isNumberInBox(int number, int column, int row) {
        int localBoxColumn = row - row % 3;
        int localBoxRow = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                var x = getTextFieldValue(board[i][j]);
                if (x == number) {
                    return true;
                }

            }
        }
        return false;
    }


    public int getTextFieldValue(TextField textField) {
        var input = textField.getText();

        if (Objects.equals(input, "")) {
            return 0;
        }

        return Integer.parseInt(input);
    }

}