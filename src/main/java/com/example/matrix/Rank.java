package com.example.matrix;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Rank {
    @FXML
    private TextField idRankValue, id00, id01, id02, id03, id10, id11, id12, id13, id20, id21, id22, id23, id30, id31, id32, id33;
    @FXML private Button idCalculate, idBack;

    int size = 2;
    List<TextField> txfList2x2, txfList3x3, txfList4x4;
    double[][] matrix = new double[size][size];

    public void initialize() {
        txfList2x2 = List.of(id00, id01, id10, id11);
        txfList3x3 = List.of(id00, id01, id02, id10, id11, id12, id20, id21, id22);
        txfList4x4 = List.of(id00, id01, id02, id03, id10, id11, id12, id13, id20, id21, id22, id23, id30, id31, id32, id33);
    }

    public void onCreateClicked() {
        createMatrix(size);
    }

    public void onCalculateClicked() {
        double determinant = calculateRank(matrix);
        idRankValue.setText(String.valueOf(determinant));
    }

    public void createMatrix(int size) {
        matrix = new double[size][size];
        List<TextField> txfList = getTextFieldList(size);
        int i = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                try {
                    String text = txfList.get(i).getText();
                    matrix[row][col] = Double.parseDouble(text);
                } catch (NumberFormatException e) {
                    System.out.print("error");
                }
                i++;
            }
        }
    }
    public List<TextField> getTextFieldList(int size) {
        switch (size) {
            case 2: return txfList2x2;
            case 3: return txfList3x3;
            case 4: return txfList4x4;
            default: throw new IllegalArgumentException("Invalid matrix size: " + size);
        }
    }

    public void onMinusClicked() {
        if (size > 2) {
            size--;
            retMinPlus(size, -1);
        }
    }

    public void onPlusClicked() {
        if (size < 4) {
            size++;
            retMinPlus(size, 1);
        }
    }

    public void retMinPlus(int size, int n) {
        if (size == 2 && n == -1) {
            txfList3x3.forEach(tf -> tf.setVisible(false));
            txfList4x4.forEach(tf -> tf.setVisible(false));
            txfList2x2.forEach(tf -> tf.setVisible(true));
        } else if (size == 3 && n == -1) {
            txfList4x4.forEach(tf -> tf.setVisible(false));
            txfList3x3.forEach(tf -> tf.setVisible(true));
        } else if (size == 3 && n == 1) {
            txfList3x3.forEach(tf -> tf.setVisible(true));
        } else if (size == 4 && n == 1) {
            txfList4x4.forEach(tf -> tf.setVisible(true));
        }
    }

    public static int calculateRank(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int row = 0; row < rows; row++) {
            if (matrix[row][row] != 0) {
                for (int col = 0; col < rows; col++) {
                    if (col != row) {
                        double mult = matrix[col][row] / matrix[row][row];
                        for (int i = 0; i < cols; i++) {
                            matrix[col][i] -= mult * matrix[row][i];
                        }
                    }
                }
            } else {
                boolean reduce = true;

                for (int i = row + 1; i < rows; i++) {
                    if (matrix[i][row] != 0) {
                        double[] temp = matrix[row];
                        matrix[row] = matrix[i];
                        matrix[i] = temp;
                        reduce = false;
                        break;
                    }
                }
                if (reduce) {
                    rows--;
                    for (int i = 0; i < rows; i++) {
                        matrix[i][row] = matrix[i + 1][row];
                    }
                    row--;
                }
            }
        }
        int rank = 0;
        for (int row = 0; row < rows; row++) {
            boolean nonZeroRow = false;
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] != 0) {
                    nonZeroRow = true;
                    break;
                }
            }
            if (nonZeroRow) {
                rank++;
            }
        }
        return rank;
    }
    public void onBackClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Matrix.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idBack.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
}