package edu.project2.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class Maze {
    private final int height;
    private final int width;
    private final ArrayList<ArrayList<Cell>> grid;

    public Maze(int a, int b) {
        height = a;
        width = b;
        grid = new ArrayList<>(height);
        for (int i = 0; i < height; ++i) {
            grid.add(new ArrayList<Cell>(width));
            for (int j = 0; j < width; ++j) {
                Set<Cell.Direction> directions = new HashSet<>();
                grid.get(i).add(new Cell(i, j, directions));
            }
        }
    }

    public Cell getCell(int row, int column) {
        return grid.get(row).get(column);
    }
}
