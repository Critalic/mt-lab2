package com.newfox;

import com.models.Matrix;
import com.models.Result;

import java.util.List;

public class AddThread implements Runnable {
    private final List<Matrix> toAdd;
    private final Result result;

    private final int row;
    private final int column;

    public AddThread(List<Matrix> toAdd, Result result, int row, int column) {
        this.toAdd = toAdd;
        this.result = result;
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        toAdd.forEach(matrix -> add(row, column, matrix.getMatrix(), result.getMatrix()));
    }

    private void add(int row, int column, int[][] add, int[][] result) {
        for (int i = 0; i < add.length; i++) {
            for (int j = 0; j < add[0].length; j++) {
                result[i+row][j+column] += add[i][j];
            }
        }
    }

}
