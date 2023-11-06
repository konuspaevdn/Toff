package edu.project2.Solver;

import edu.project2.structure.Coordinate;
import edu.project2.structure.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
