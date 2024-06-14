package com.example.matrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MatrixController {
    @FXML private TextField idA00, idA01, idA02, idA10, idA11, idA12, idA20, idA21, idA22;
    @FXML private TextField idB00, idB01, idB02, idB10, idB11, idB12, idB20, idB21, idB22;
    @FXML private TextField idR00, idR01, idR02, idR10, idR11, idR12, idR20, idR21, idR22;
    @FXML private TextField idValPowA,idValPowB, idValA, idValB;
    @FXML private Button idDeterminant, idRank, idInverse;

    Boolean upperA = false;
    Boolean upperB = false;
    SimpleOperators simpleOperators = new SimpleOperators();

    int size = 3, numOfPowA, numOfPowB, numForA, numForB;
    double[][] matrixA = new double[size][size];
    double[][] matrixB = new double[size][size];
    List<TextField> txfListA, txfListB, txfListRet;

    @FXML
    public void initialize() {
        numOfPowA = Integer.parseInt(idValPowA.getText());
        numOfPowB = Integer.parseInt(idValPowB.getText());
        numForA = Integer.parseInt(idValA.getText());
        numForB = Integer.parseInt(idValB.getText());
        txfListA = List.of(idA00, idA01, idA02, idA10, idA11, idA12, idA20, idA21, idA22);
        txfListB = List.of(idB00, idB01, idB02, idB10, idB11, idB12, idB20, idB21, idB22);
        txfListRet = List.of(idR00, idR01, idR02, idR10, idR11, idR12, idR20, idR21, idR22);
    }

    public void createMatrix() {
        int i = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                try {
                    String text = txfListA.get(i).getText();
                    matrixA[row][col] = Double.parseDouble(text);
                } catch (NumberFormatException e) {
                    System.out.print("error");
                }
                i++;
            }
        }
    }
    public void createMatrixB() {
        int i = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                try {
                    String text = txfListB.get(i).getText();
                    matrixB[row][col] = Double.parseDouble(text);
                } catch (NumberFormatException e) {
                    System.out.print("error");
                }
                i++;
            }
        }
    }
    public void onCreateAClicked() {
        createMatrix();
    }
    public void onCreateBClicked() {
        createMatrixB();
    }

    public void onUpperAClicked() { upperA = !upperA; }
    public void onUpperBClicked() { upperB = !upperB; }

    public void onSumClicked(){
        double[][] resultMatrix = simpleOperators.sumAB(size, matrixA, matrixB);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onMinusClicked(){
        double[][] resultMatrix = simpleOperators.minusAB(size, matrixA, matrixB);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onMultiplicationClicked(){
        double[][] resultMatrix = simpleOperators.multiplicationAB(size, matrixA, matrixB);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }

    public void onPowAClicked(){
        double[][] resultMatrix = simpleOperators.powA(size, matrixA, numOfPowA);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListA);
    }
    public void onPowBClicked(){
        double[][] resultMatrix = simpleOperators.powA(size, matrixB, numOfPowB);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListB);
    }
    public void onTransposedAClicked(){
        double[][] resultMatrix = simpleOperators.transposed(size, matrixA);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListA);
    }
    public void onTransposedBClicked(){
        double[][] resultMatrix = simpleOperators.transposed(size, matrixB);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListB);
    }

    public void onTriangularAClicked(){
        double[][] resultMatrix = simpleOperators.triangular(size, matrixA, upperA);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListA);
    }
    public void onTriangularBClicked(){
        double[][] resultMatrix = simpleOperators.triangular(size, matrixB, upperB);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListB);
    }
    public void onMultiplicationANumClicked(){
        double[][] resultMatrix = simpleOperators.multiplicationNumber(size, numForA, matrixA);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListA);
    }
    public void onMultiplicationBNumClicked(){
        double[][] resultMatrix = simpleOperators.multiplicationNumber(size, numForB, matrixB);
        updateTextFieldsWithMatrixResult(resultMatrix, txfListB);
    }
    private void updateTextFieldsWithMatrixResult(double[][] matrixResult, List<TextField> txfEquals) {
        int i = 0;
        for (int row = 0; row < matrixResult.length; row++) {
            for (int col = 0; col < matrixResult[row].length; col++) {
                try {
                    double value = matrixResult[row][col];
                    txfEquals.get(i).setText(String.valueOf(value));
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("error");
                }
                i++;
            }
        }
    }
    public void onDeterminantClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Determinant.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idDeterminant.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
    public void onRankClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Rank.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idRank.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
    public void onInverseClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Inverse.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idInverse.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
}