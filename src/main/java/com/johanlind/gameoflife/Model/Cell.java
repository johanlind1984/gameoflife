package com.johanlind.gameoflife.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Cell {
    private boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = false;
    }
}