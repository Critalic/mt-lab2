package com.stripe;

import com.models.Matrix;
import com.models.Result;

public class MultiplierThreadStripe implements Runnable {
    private Matrix first;
    private Matrix second;
    private Result result;
    private int row;
    private int column;
    private int columnCount;

    public MultiplierThreadStripe(Matrix first, Matrix second, Result result, int row, int column) {
        this.first = first;
        this.second = second;
        this.row = row;
        this.column = column;
        this.result = result;

        columnCount = second.getMatrix()[0].length;
    }

    @Override
    public void run() {
        int[] rowFirst = first.getRow(row);
        for (int i = 0; i < columnCount; i++) {
            int elem = 0;
            column = getColumnIndex(column);
            int[] colSecond = second.getColumn(column);

            for (int j = 0; j < rowFirst.length; j++) {
                elem += rowFirst[j] * colSecond[j];
            }
            result.append(row, column, elem);
        }

    }

    private int getColumnIndex(int col) {
        int resp = col - 1;
        return resp >= 0 ? resp : columnCount - 1;
    }
}
