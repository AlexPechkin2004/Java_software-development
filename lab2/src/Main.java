/*
Номер залікової книжки: 1124
C5 = 4 (Дія з матрицями: C=A×B)
C7 = 4 (Тип елементів матриці: long)
C11 = 2 (Обчислити суму найбільших елементів кожного стовпця матриці)

Створити клас, який складається з виконавчого методу, що виконує дію з
матрицями із зазначеним типом елементів та дію із результуючою
матрицею С. Вивести на екран результати першої та другої дій. Необхідно
обробити всі виключні ситуації, що можуть виникнути під час виконання
програмного коду. Всі змінні повинні бути описані та значення їх задані у
виконавчому методі.
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static int inputMatrixSideLength(String hint) {
        int matrixSideLength;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(hint);
                matrixSideLength = scanner.nextInt();
                if (matrixSideLength <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Введене значення некоректне. Спробуйте ще раз.");
                scanner.nextLine();
            }
        }
        return matrixSideLength;
    }

    public static long[][] inputMatrix(int rows, int columns) {
        long[][] matrix = new long[rows][columns];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < rows; i++) {
            System.out.print(" Рядок " + (i + 1) + ": ");
            String[] rowElements = scanner.nextLine().split(" ");
            if (rowElements.length != columns) {
                System.out.println("  Некоректна кількість елементів. Спробуйте ще раз.");
                i--; // повторити цикл для цього рядка
                continue;
            }
            try {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = Long.parseLong(rowElements[j]);
                }
            } catch (NumberFormatException e) {
                System.out.println("  Некоректне значення. Спробуйте ще раз.");
                i--; // повторити цикл для цього рядка
            }
        }
        return matrix;
    }

    public static long[][] multiplyMatrices(long[][] matrix1, long[][] matrix2) throws ArithmeticException {
        int rowsMatrix1 = matrix1.length;
        int columnsMatrix1 = matrix1[0].length;
        int columnsMatrix2 = matrix2[0].length;

        long[][] resultMatrix = new long[rowsMatrix1][columnsMatrix2];

        for (int i = 0; i < rowsMatrix1; i++) {
            for (int j = 0; j < columnsMatrix2; j++) {
                for (int k = 0; k < columnsMatrix1; k++) {
                    resultMatrix[i][j] = Math.addExact(resultMatrix[i][j], Math.multiplyExact(matrix1[i][k], matrix2[k][j]));
                }
            }
        }
        return resultMatrix;
    }


    public static void outputMatrix(long[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static long sumOfBiggestColumnElements(long[][] matrix) throws ArithmeticException {
        long sum = 0;
        long currentBiggestElement;

        for (int i = 0; i < matrix[0].length; i++) {
            currentBiggestElement = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] > currentBiggestElement) {
                    currentBiggestElement = matrix[j][i];
                }
            }
            sum = Math.addExact(sum, currentBiggestElement);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Множення матриць типу long та обчислення суми найбільших елементів кожного стовпця матриці\n");

        int rowsMatrixA = inputMatrixSideLength("Введіть кількість рядків матриці A: ");
        int columnsMatrixARowsMatrixB = inputMatrixSideLength("Введіть кількість стовпців матриці A (рядків матриці B): ");
        int columnsMatrixB = inputMatrixSideLength("Введіть кількість стовпців матриці B: ");

        System.out.println("\nВведіть елементи матриці A по рядку через пробіл:");
        long[][] matrixA = inputMatrix(rowsMatrixA, columnsMatrixARowsMatrixB);
        System.out.println("Введіть елементи матриці B по рядку через пробіл:");
        long[][] matrixB = inputMatrix(columnsMatrixARowsMatrixB, columnsMatrixB);

        System.out.println("\nВведена матриця A: ");
        outputMatrix(matrixA);
        System.out.println("\nВведена матриця B: ");
        outputMatrix(matrixB);

        try {
            long[][] matrixC = multiplyMatrices(matrixA, matrixB);
            System.out.println("\nМатриця C=A×B: ");
            outputMatrix(matrixC);
            System.out.print("\nСума найбільших елементів кожного стовпця матриці C: " + sumOfBiggestColumnElements(matrixC));
        } catch (ArithmeticException a) {
            System.out.println("\nПомилка: при виконанні поточної операції значення виходять за межі типу long.");
        }
    }
}