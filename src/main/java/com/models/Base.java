package com.models;

import java.util.Arrays;

public class Base {
    protected final int[][] matrix;

    public Base(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

//    public int[][] getSubMatrix(int )

    public void print() {
        Arrays.stream(matrix).forEach(arr -> {
            Arrays.stream(arr).forEach(elem -> System.out.print(elem + " "));
            System.out.println();
        });
    }
}
