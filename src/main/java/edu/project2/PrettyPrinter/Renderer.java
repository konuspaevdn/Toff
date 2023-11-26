package edu.project2.PrettyPrinter;

import edu.project2.structure.Coordinate;
import edu.project2.structure.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Renderer {
    private Renderer() {

    }

    public static String render(Maze a) {
        ArrayList<ArrayList<Character>> maze = new ArrayList<>(a.getHeight() * 2 + 1);
        for (int i = 0; i < a.getHeight() * 2 + 1; ++i) {
            maze.add(new ArrayList<>(Collections.nCopies(a.getWidth() * 2 + 1, '#')));
        }
        for (int i = 0; i < a.getHeight(); ++i) {
            for (int j = 0; j < a.getWidth(); ++j) {
                maze.get(i * 2 + 1).set(j * 2 + 1, ' ');
                for (var direction: a.getDirections(new Coordinate(i, j))) {
                    switch (direction) {
                        case UP -> maze.get(i * 2).set(j * 2 + 1, ' ');
                        case DOWN -> maze.get((i + 1) * 2).set(j * 2 + 1, ' ');
                        case LEFT -> maze.get(i * 2 + 1).set(j * 2, ' ');
                        case RIGHT -> maze.get(i * 2 + 1).set((j + 1) * 2, ' ');
                        default -> throw new RuntimeException();
                    }
                }
            }
        }
        StringBuilder str = new StringBuilder();
        for (var row : maze) {
            str.append(row.stream().map(Object::toString).collect(Collectors.joining("")));
            str.append('\n');
        }
        return str.toString();
    }

    public static String render(Maze a, List<Coordinate> path) {
        ArrayList<ArrayList<Character>> maze = new ArrayList<>(a.getHeight() * 2 + 1);
        for (int i = 0; i < a.getHeight() * 2 + 1; ++i) {
            maze.add(new ArrayList<>(Collections.nCopies(a.getWidth() * 2 + 1, '#')));
        }
        for (int i = 0; i < a.getHeight(); ++i) {
            for (int j = 0; j < a.getWidth(); ++j) {
                maze.get(i * 2 + 1).set(j * 2 + 1, ' ');
                for (var direction: a.getDirections(new Coordinate(i, j))) {
                    switch (direction) {
                        case UP -> maze.get(i * 2).set(j * 2 + 1, ' ');
                        case DOWN -> maze.get((i + 1) * 2).set(j * 2 + 1, ' ');
                        case LEFT -> maze.get(i * 2 + 1).set(j * 2, ' ');
                        case RIGHT -> maze.get(i * 2 + 1).set((j + 1) * 2, ' ');
                        default -> throw new RuntimeException();

                    }
                }
            }
        }
        Coordinate start = path.removeFirst();
        maze.get(start.row() * 2 + 1).set(start.col() * 2 + 1, 'S');
        Coordinate previous = start;
        for (var coordinate : path) {
            maze.get(coordinate.row() * 2 + 1).set(coordinate.col() * 2 + 1, '@');
            maze.get(coordinate.row() + previous.row() + 1).set(coordinate.col() + previous.col() + 1, '@');
            previous = coordinate;
        }
        Coordinate end = previous;
        maze.get(end.row() * 2 + 1).set(end.col() * 2 + 1, 'E');
        StringBuilder str = new StringBuilder();
        for (var row : maze) {
            str.append(row.stream().map(Object::toString).collect(Collectors.joining("")));
            str.append('\n');
        }
        return str.toString();
    }
}
