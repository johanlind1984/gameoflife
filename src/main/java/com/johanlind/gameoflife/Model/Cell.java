package com.johanlind.gameoflife.Model;

import lombok.Data;

@Data
public class Cell {
    private boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
