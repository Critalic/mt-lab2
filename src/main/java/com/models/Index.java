package com.models;

import java.util.Objects;

public class Index {
    private int row;
    private int col;

    public Index(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return row == index.row && col == index.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
