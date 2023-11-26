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
        if (a <= 0 || b <= 0) {
            throw new RuntimeException("Side of maze must be a positive integer");
        }
        grid = new ArrayList<>(height);
        for (int i = 0; i < height; ++i) {
            grid.add(new ArrayList<>(width));
            for (int j = 0; j < width; ++j) {
                Set<Cell.Direction> directions = new HashSet<>();
                grid.get(i).add(new Cell(i, j, directions));
            }
        }
    }

    public Maze(ArrayList<ArrayList<Cell>> a) {
        height = a.size();
        width = a.getFirst().size();
        grid = new ArrayList<>(a);
    }

    public void addDirection(Coordinate coordinate, Cell.Direction dir) {
        grid.get(coordinate.row()).get(coordinate.col()).directions().add(dir);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Cell.Direction> getDirections(Coordinate coordinate) {
        return new ArrayList<>(grid.get(coordinate.row()).get(coordinate.col()).directions());
    }
}
