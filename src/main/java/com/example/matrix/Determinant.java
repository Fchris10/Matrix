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

public class Determinant {
    @FXML private TextField id00, id01, id02, id10, id11, id12, id20, id21, id22, idDeterminantValue;
    @FXML private Button idBack, idCalculate;

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
        double determinant = calculateDeterminant(matrix);
        idDeterminantValue.setText(String.valueOf(determinant));
    }

    public void onBackClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Matrix.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idBack.getScene().getWindow();
        stage1.setScene(new Scene(root));
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

    public double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        } else if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double determinant = 0;
        for (int i = 0; i < n; i++) {
            double[][] subMatrix = getSubMatrix(matrix, 0, i);
            determinant += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(subMatrix);
        }
        return determinant;
    }

    public static double[][] getSubMatrix(double[][] matrix, int excludingRow, int excludingCol) {
        int n = matrix.length;
        double[][] subMatrix = new double[n - 1][n - 1];
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i == excludingRow) {
                continue;
            }
            r++;
            int c = -1;
            for (int j = 0; j < n; j++) {
                if (j == excludingCol) {
                    continue;
                }
                subMatrix[r][++c] = matrix[i][j];
            }
        }
        return subMatrix;
    }
}