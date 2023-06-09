package com.models;

import java.util.Arrays;

public class Matrix extends Base {

    private int[][] columns;

    public Matrix(int[][] matrix) {
        super(matrix);
        this.columns = new int[matrix[0].length][matrix.length];

        for(int i =0; i<columns.length; i++) {
            int[] temp = new int[matrix.length];
            for(int b =0; b< matrix.length; b++) {
                temp[b] = matrix[b][i];
            }
            columns[i] = temp;
        }
    }

//    public int[] getRow(int index) {
//        return Arrays.copyOf(matrix[index], matrix[index].length);
//    }
//
//    public int[] getColumn(int index) {
//        return Arrays.stream(matrix).mapToInt(arr -> arr[index]).toArray();
//    }

    public int[] getRow(int index) {
        return matrix[index];
    }

    public int[] getColumn(int index) {
        return columns[index];
    }
}
