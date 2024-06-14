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

public class Inverse {
    @FXML private TextField id00, id01, id02, id10, id11, id12, id20, id21, id22;
    @FXML private Button idBack;

    Determinant determinant = new Determinant();

    int size = 3;
    List<TextField> txfList;
    double[][] matrix = new double[size][size];

    public void initialize() {
        txfList = List.of(id00, id01, id02, id10, id11, id12, id20, id21, id22);
    }

    public void onCreateClicked() {
        createMatrix();
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
    }
    public void onInverseClicked() {
        inverseMatrix();
    }

    private double[][] cofactors() {
        double[][] cof = new double[3][3];
        cof[0][0] = matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1];
        cof[0][1] = -(matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]);
        cof[0][2] = matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0];
        cof[1][0] = -(matrix[0][1] * matrix[2][2] - matrix[0][2] * matrix[2][1]);
        cof[1][1] = matrix[0][0] * matrix[2][2] - matrix[0][2] * matrix[2][0];
        cof[1][2] = -(matrix[0][0] * matrix[2][1] - matrix[0][1] * matrix[2][0]);
        cof[2][0] = matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1];
        cof[2][1] = -(matrix[0][0] * matrix[1][2] - matrix[0][2] * matrix[1][0]);
        cof[2][2] = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        return cof;
    }
    private double[][] transposed(double[][] matrix) {
        double[][] transposed = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                transposed[i][j] = transposed[j][i];
            }
        }
        return transposed;
    }
    public void  inverseMatrix() {
        double det = determinant.calculateDeterminant(matrix);
        if(det == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert Result");
            alert.setContentText("The determinant is equal to 0, the inverse of matrix doesn't exist!");
            Optional<ButtonType> result = alert.showAndWait();
        }
        double[][] cof = cofactors();
        double[][] transposed = transposed(cof);
        double[][] inverse = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverse[i][j] = transposed[i][j] / det;
            }
        }
        updateTextFields(inverse);
    }
    public void updateTextFields(double[][] inverse) {
        int i = 0;
        for (int row = 0; row < inverse.length; row++) {
            for (int col = 0; col < inverse[row].length; col++) {
                try {
                    double value = inverse[row][col];
                    txfList.get(i).setText(String.valueOf(value));
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("error");
                }
                i++;
            }
        }
    }
    public void onBackClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Matrix.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idBack.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
}
