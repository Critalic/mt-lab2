package com.newfox;

import com.models.Matrix;

import java.util.List;

public class MultiplierThread implements Runnable {
    private List<Matrix> matrixList;
    private Matrix first;
    private Matrix second;

    private Coordinates firstC;
    private Coordinates secondC;

    public MultiplierThread(List<Matrix> matrixList, Matrix first, Matrix second, Coordinates firstC, Coordinates secondC) {
        this.matrixList = matrixList;
        this.first = first;
        this.second = second;
        this.firstC = firstC;
        this.secondC = secondC;
    }

    @Override
    public void run() {
        matrixList.add(new Matrix(multiply(first.getMatrix(), second.getMatrix(), firstC, secondC)));
    }

    private int[][] multiply(int[][] first, int[][] second, Coordinates firstC, Coordinates secondC) {
        int fR = firstC.getEndRow() - firstC.getStartRow();
        int sC = secondC.getEndCol() - secondC.getStartCol();

        int[][] result = new int[fR][sC];

        for (int fRc = firstC.getStartRow(); fRc < firstC.getEndRow(); fRc++) {
            for (int sCc = secondC.getStartCol(); sCc < secondC.getEndCol(); sCc++) {
                int elem = 0;
                for (int sRc = firstC.getStartCol(); sRc < firstC.getEndCol(); sRc++) {
                    elem += first[fRc][sRc] * second[sRc][sCc];
                }
                result[fRc%fR][sCc%sC] = elem;
            }
        }
        return result;
    }
}
