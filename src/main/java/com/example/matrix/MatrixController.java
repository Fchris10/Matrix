package com.example.matrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MatrixController {
    @FXML private TextField id00, id01, id02, id10, id11, id12, id20, id21, id22;
    @FXML private TextField idR00, idR01, idR02, idR10, idR11, idR12, idR20, idR21, idR22;
    @FXML private TextField idValuePow;
    @FXML private Button idMin, idDeterminant, idRank, idInverse;

    SimpleOperators simpleOperators = new SimpleOperators();

    int size = 3, numOfPow;
    double[][] matrix = new double[size][size];
    List<TextField> txfList, txfEquals;

    double[][] matrixTmp = {{1,2,3},
                            {4,5,6},
                            {7,0,9}}  ;

    @FXML
    public void initialize() {
        numOfPow = Integer.parseInt(idValuePow.getText());
        txfList = List.of(id00, id01, id02, id10, id11, id12, id20, id21, id22);
        txfEquals = List.of(idR00, idR01, idR02, idR10, idR11, idR12, idR20, idR21, idR22);
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
    public void onCreateClicked() {
        createMatrix();
    }
    public void onSumClicked(){
        double[][] resultMatrix = simpleOperators.sumAB(size, matrix, matrixTmp);
        updateTextFieldsWithMatrixResult(resultMatrix, txfEquals);
    }
    public void onMinusClicked(){
        double[][] resultMatrix = simpleOperators.minusAB(size, matrix, matrixTmp);
        updateTextFieldsWithMatrixResult(resultMatrix, txfEquals);
    }
    public void onMultiplicationClicked(){
        double[][] resultMatrix = simpleOperators.multiplicationAB(size, matrix, matrixTmp);
        updateTextFieldsWithMatrixResult(resultMatrix, txfEquals);
    }

    public void onPlusClicked(){
        numOfPow++;
        idValuePow.setText(String.valueOf(numOfPow));
        idMin.setDisable(false);
    }
    public void onMinClicked(){
        if(numOfPow == 2){
            idMin.setDisable(true);
        } else {
            numOfPow--;
            idValuePow.setText(String.valueOf(numOfPow));
        }
    }
    public void onPowClicked(){
        double[][] resultMatrix = simpleOperators.powA(size, matrix, numOfPow);
        updateTextFieldsWithMatrixResult(resultMatrix, txfEquals);
    }
    public void onTransposedClicked(){
        double[][] resultMatrix = simpleOperators.transposed(size, matrix);
        updateTextFieldsWithMatrixResult(resultMatrix, txfList);
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