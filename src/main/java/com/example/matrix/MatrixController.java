package com.example.matrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML private Button idDeterminant, idRank, idInverse, idBack, idSum, idMinus, idMultiplication;
    @FXML private Button idTriangularA, idTriangularB, idTransposedA, idTransposedB, idPowA, idPowB, idMultiplicationAFor, idMultiplicationBFor;
    @FXML private Label idLabel;

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

    public void onCleanClicked() {
        upperA = upperB = false;
        idLabel.setText(" ");
        double[][] matrixRet = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                matrixRet[row][col] = 0;
            }
        }
        updateTextFieldsWithMatrixResult(matrixRet, txfListRet);
        updateTextFieldsWithMatrixResult(matrixRet, txfListA);
        updateTextFieldsWithMatrixResult(matrixRet, txfListB);
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
        idLabel.setText("The result of A + B is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onMinusClicked(){
        double[][] resultMatrix = simpleOperators.minusAB(size, matrixA, matrixB);
        idLabel.setText("The result of A - B is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onMultiplicationClicked(){
        double[][] resultMatrix = simpleOperators.multiplicationAB(size, matrixA, matrixB);
        idLabel.setText("The result of A x B is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onPowAClicked(){
        double[][] resultMatrix = simpleOperators.powA(size, matrixA, numOfPowA);
        idLabel.setText("The result of A ^ " + numOfPowA + " is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onPowBClicked(){
        double[][] resultMatrix = simpleOperators.powA(size, matrixB, numOfPowB);
        idLabel.setText("The result of B ^ " + numOfPowB + " is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onTransposedAClicked(){
        double[][] resultMatrix = simpleOperators.transposed(size, matrixA);
        idLabel.setText("The transposed of A is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onTransposedBClicked(){
        double[][] resultMatrix = simpleOperators.transposed(size, matrixB);
        idLabel.setText("The transposed of B is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onTriangularAClicked(){
        double[][] resultMatrix = simpleOperators.triangular(size, matrixA, upperA);
        idLabel.setText("The triangular matrix of A is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onTriangularBClicked(){
        double[][] resultMatrix = simpleOperators.triangular(size, matrixB, upperB);
        idLabel.setText("The triangular matrix of B is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onMultiplicationANumClicked(){
        double[][] resultMatrix = simpleOperators.multiplicationNumber(size, numForA, matrixA);
        idLabel.setText("The result of A x " + numForA + " is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    public void onMultiplicationBNumClicked(){
        double[][] resultMatrix = simpleOperators.multiplicationNumber(size, numForB, matrixB);
        idLabel.setText("The result of B x " + numForB + " is:");
        updateTextFieldsWithMatrixResult(resultMatrix, txfListRet);
    }
    private void updateTextFieldsWithMatrixResult(double[][] matrixResult, List<TextField> txfEquals) {
        int i = 0;
        for (int row = 0; row < matrixResult.length; row++) {
            for (int col = 0; col < matrixResult[row].length; col++) {
                    double value = matrixResult[row][col];
                    txfEquals.get(i).setText(String.valueOf(value));
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
    public void onBackClicked() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Home.fxml"));
        Parent root = fxmlLoader1.load();
        Stage stage1 = (Stage) idBack.getScene().getWindow();
        stage1.setScene(new Scene(root));
    }
}