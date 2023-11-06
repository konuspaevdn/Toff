package edu.project2.Solver.DFS;

import edu.project2.Solver.Solver;
import edu.project2.structure.Cell;
import edu.project2.structure.Coordinate;
import edu.project2.structure.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSSolver implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        ArrayList<ArrayList<Boolean>> visited = new ArrayList<>(maze.getHeight());
        for (int i = 0; i < maze.getHeight(); ++i) {
            visited.add(new ArrayList<>(Collections.nCopies(maze.getWidth(), Boolean.FALSE)));
        }
        Stack<Coordinate> path = new Stack<>();
        path.push(start);
        dfs(maze, path, visited, end);
        return path;
    }

    private void dfs(Maze maze, Stack<Coordinate> path, ArrayList<ArrayList<Boolean>> visited, Coordinate end) {
        Coordinate current = path.peek();
        setToVisited(visited, current);
        if (current == end) {
            return;
        }
        ArrayList<Cell.Direction> directions = maze.getDirections(current);
        for (var neighbour : getNeighbours(current, directions)) {
            if (checkIfVisited(visited, neighbour) || checkIfVisited(visited, end)) {
                continue;
            }
            path.push(neighbour);
            dfs(maze, path, visited, end);
            if (!checkIfVisited(visited, end)) {
                path.pop();
            }
        }
    }

    private boolean checkIfVisited(ArrayList<ArrayList<Boolean>> visited, Coordinate coordinate) {
        return visited.get(coordinate.row()).get(coordinate.col());
    }

    private void setToVisited(ArrayList<ArrayList<Boolean>> visited, Coordinate coordinate) {
        visited.get(coordinate.row()).set(coordinate.col(), Boolean.TRUE);
    }

    private ArrayList<Coordinate> getNeighbours(Coordinate coordinate, ArrayList<Cell.Direction> directions) {
        ArrayList<Coordinate> neighbours = new ArrayList<>();
        for (var direction : directions) {
            switch (direction) {
                case UP -> neighbours.add(new Coordinate(coordinate.row() - 1, coordinate.col()));
                case DOWN -> neighbours.add(new Coordinate(coordinate.row() + 1, coordinate.col()));
                case LEFT -> neighbours.add(new Coordinate(coordinate.row(), coordinate.col() - 1));
                case RIGHT -> neighbours.add(new Coordinate(coordinate.row(), coordinate.col() + 1));
                default -> throw new RuntimeException();
            }
        }
        return neighbours;
    }
}
