package com.example.matrix;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;

public class Rank {
    @FXML
    private TextField id00, id01, id02, id10, id11, id12, id20, id21, id22, idRankValue;
    @FXML private Button idCalculate;

    int size = 3;
    List<TextField> txfList;
    double[][] matrix = new double[size][size];

    public void initialize() {
        txfList = List.of(id00, id01, id02, id10, id11, id12, id20, id21, id22);
    }

    public void onCreateClicked() {
        createMatrix();
    }

    public void onCalculateClicked() {
        double determinant = calculateRank(matrix);
        idRankValue.setText(String.valueOf(determinant));
    }

    public void createMatrix() {
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
        idCalculate.setVisible(true);
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
}