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

public class Inverse {
    @FXML private TextField id00, id01, id02, id03, id10, id11, id12, id13, id20, id21, id22, id23, id30, id31, id32, id33;
    @FXML private Button idBack;

    int size = 2;
    List<TextField> txfList2x2, txfList3x3, txfList4x4;
    double[][] matrix;

    public void initialize() {
        txfList2x2 = List.of(id00, id01, id10, id11);
        txfList3x3 = List.of(id00, id01, id02, id10, id11, id12, id20, id21, id22);
        txfList4x4 = List.of(id00, id01, id02, id03, id10, id11, id12, id13, id20, id21, id22, id23, id30, id31, id32, id33);
        setTextFieldVisibility(size);
    }

    public void onCreateClicked() {
        createMatrix();
    }

    public void createMatrix() {
        matrix = new double[size][size];
        List<TextField> txfList = getTextFieldList(size);
        int i = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                try {
                    String text = txfList.get(i).getText();
                    matrix[row][col] = Double.parseDouble(text);
                } catch (Exception e) {
                    System.out.print("Error parsing number");
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
            setTextFieldVisibility(size);
        }
    }
    public void onPlusClicked() {
        if (size < 4) {
            size++;
            setTextFieldVisibility(size);
        }
    }

    public void setTextFieldVisibility(int size) {
        List<TextField> allFields = List.of(id00, id01, id02, id03, id10, id11, id12, id13, id20, id21, id22, id23, id30, id31, id32, id33);
        allFields.forEach(tf -> tf.setVisible(false));

        List<TextField> visibleFields = getTextFieldList(size);
        visibleFields.forEach(tf -> tf.setVisible(true));
    }

    public void onInverseClicked() {
        double[][] inverse = calcolaInverse(matrix);
        if (inverse != null) {
            updateTextFields(inverse);
        } else {
            showError("Determinant is zero, inverse cannot be calculated.");
        }
    }

    public double[][] calcolaInverse(double[][] matrix) {
        int n = matrix.length;
        double[][] augmentedMatrix = new double[n][2 * n];
        initiateAugmentedMatrix(matrix, augmentedMatrix);

        for (int i = 0; i < n; i++) {
            if (augmentedMatrix[i][i] == 0) {
                boolean swapped = false;
                for (int j = i + 1; j < n; j++) {
                    if (augmentedMatrix[j][i] != 0) {
                        swapRows(augmentedMatrix, i, j);
                        swapped = true;
                        break;
                    }
                }
                if (!swapped) {
                    return null;
                }
            }
            double pivot = augmentedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double factor = augmentedMatrix[j][i];
                    for (int k = 0; k < 2 * n; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }
            }
        }

        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, inverse[i], 0, n);
        }
        return inverse;
    }

    private void swapRows(double[][] matrix, int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    private void initiateAugmentedMatrix(double[][] A, double[][] augmentedMatrix) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = A[i][j];
            }
            for (int j = n; j < 2 * n; j++) {
                augmentedMatrix[i][j] = (i == j - n) ? 1.0 : 0.0;
            }
        }
    }
    public void updateTextFields(double[][] inverse) {
        List<TextField> txfList = getTextFieldList(size);
        int i = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                try {
                    txfList.get(i).setText(String.valueOf(inverse[row][col]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("error");
                }
                i++;
            }
        }
    }

    public void onBackClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idBack.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}