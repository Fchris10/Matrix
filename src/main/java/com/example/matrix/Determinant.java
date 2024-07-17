package com.example.matrix;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Determinant {
    @FXML private TextField idDeterminantValue, id00, id01, id02, id03, id10, id11, id12, id13, id20, id21, id22, id23, id30, id31, id32, id33;
    @FXML private Button idBack, idCalculate;

    int size = 2;
    List<TextField> txfList2x2, txfList3x3, txfList4x4;
    double[][] matrix;

    public void initialize() {
        txfList2x2 = List.of(id00, id01, id10, id11);
        txfList3x3 = List.of(id00, id01, id02, id10, id11, id12, id20, id21, id22);
        txfList4x4 = List.of(id00, id01, id02, id03, id10, id11, id12, id13, id20, id21, id22, id23, id30, id31, id32, id33);
    }

    public void onCreateClicked() {
        createMatrix(size);
    }

    public void onCalculateClicked() {
        double determinant = calculateDeterminant(matrix);
        idDeterminantValue.setText(String.valueOf(determinant));
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

    public double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;

         if (n == 2) {
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

    public void onBackClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idBack.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
}