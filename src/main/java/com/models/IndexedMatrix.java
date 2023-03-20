package com.models;

import java.util.Objects;

public class IndexedMatrix extends Base {
    private Index index;

    public IndexedMatrix(int[][] matrix, int row, int col) {
        super(matrix);
        this.index = new Index(row, col);
    }

    public Index getIndex() {
        return index;
    }

    public void setIndex(int row, int col) {
        this.index = new Index(row, col);
    }
}