package edu.project2.structure;

import java.util.Set;

public record Cell(int row, int col, Set<Direction> directions) {
    public enum Direction { UP, DOWN, LEFT, RIGHT }
}
