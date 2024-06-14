package com.example.matrix;

public class SimpleOperators {

    public double[][] sumAB(int size,  double[][] matrixA, double[][] matrixB) {
        double[][] sumMatrix = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                sumMatrix[row][col] = matrixA[row][col] + matrixB[row][col];
            }
        }
        return sumMatrix;
    }

    public double[][] minusAB(int size, double[][] matrixA, double[][] matrixB) {
        double[][] minusMatrix = new double[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                minusMatrix[row][col] = matrixA[row][col] - matrixB[row][col];
            }
        }
        return minusMatrix;
    }

    public double[][] multiplicationAB(int size, double[][] matrixA, double[][] matrixB) {
        double[][] multiMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    multiMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return multiMatrix;
    }

    public double[][] powA(int size, double[][] matrixA, int n) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            result[i][i] = 1.0;
        }
        // Eleviamo la matrice alla potenza n usando l'esponenziazione rapida
        while (n > 0) {
            if (n % 2 == 1) {
                result = multiplicationAB(size, result, matrixA);
            }
            matrixA = multiplicationAB(size, matrixA, matrixA);
            n /= 2;
        }
        return result;
    }
    public double[][] transposed(int size, double[][] matrix) {
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }
    public double[][] triangular(int size, double[][] matrix) {
        double[][] result = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                result[i][j] = matrix[i][j];
            }
        }
        return result;
    }
    public double[][] multiplicationNumber(int size, int n, double[][] matrix){
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = matrix[i][j] * n;
            }
        }
        return result;
    }
}