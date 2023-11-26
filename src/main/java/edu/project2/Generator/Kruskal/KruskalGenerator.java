package edu.project2.Generator.Kruskal;

import edu.project2.Generator.Generator;
import edu.project2.structure.Cell;
import edu.project2.structure.Coordinate;
import edu.project2.structure.Maze;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class KruskalGenerator implements Generator {
    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        Set<Set<Coordinate>> sets = new HashSet<>();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Set<Coordinate> setOfCell = new HashSet<>();
                setOfCell.add(new Coordinate(i, j));
                sets.add(setOfCell);
            }
        }
        ArrayList<Partition> partitions = getPartitions(height, width);
        Random idxGenerator = new Random();
        while (!partitions.isEmpty()) {
            int idx = idxGenerator.nextInt(partitions.size());
            Partition partition = partitions.remove(idx);

            Predicate<Set<Coordinate>> isAdjacentToPartition = set -> {
                if (partition.type() == Partition.Type.HORIZONTAL) {
                    return set.stream()
                        .anyMatch(cell -> cell.row() == partition.i() && cell.col() == partition.j()
                            || cell.row() - 1 == partition.i() && cell.col() == partition.j());
                } else {
                    return set.stream()
                        .anyMatch(cell -> cell.row() == partition.i() && cell.col() == partition.j()
                            || cell.row() == partition.i() && cell.col() - 1 == partition.j());
                }
            };

            List<Set<Coordinate>> list = sets.stream()
                .filter(isAdjacentToPartition).toList();
            if (list.size() == 2) {
                Set<Coordinate> set1 = list.getFirst();
                Set<Coordinate> set2 = list.getLast();
                sets.remove(set1);
                sets.remove(set2);
                updateAdjacentCell(maze, set1, partition);
                updateAdjacentCell(maze, set2, partition);
                Set<Coordinate> newSet = new HashSet<>();
                newSet.addAll(set1);
                newSet.addAll(set2);
                sets.add(newSet);
            }
        }
        return maze;
    }

    private void updateAdjacentCell(Maze maze, Set<Coordinate> set, Partition partition) {
        Coordinate adjCell;
        if (partition.type() == Partition.Type.HORIZONTAL) {
            adjCell = set.stream()
                .filter(cell -> cell.row() == partition.i() && cell.col() == partition.j()
                    || cell.row() - 1 == partition.i() && cell.col() == partition.j()).findFirst().orElseThrow();
            if (adjCell.row() == partition.i()) {
                maze.addDirection(adjCell, Cell.Direction.DOWN);
            } else {
                maze.addDirection(adjCell, Cell.Direction.UP);
            }
        } else {
            adjCell = set.stream()
                .filter(cell -> cell.row() == partition.i() && cell.col() == partition.j()
                    || cell.row() == partition.i() && cell.col() - 1 == partition.j()).findFirst().orElseThrow();
            if (adjCell.col() == partition.j()) {
                maze.addDirection(adjCell, Cell.Direction.RIGHT);
            } else {
                maze.addDirection(adjCell, Cell.Direction.LEFT);
            }
        }
    }

    private static ArrayList<Partition> getPartitions(int height, int width) {
        int numberOfPartitions = 2 * height * width - height - width;
        ArrayList<Partition> partitions = new ArrayList<>(numberOfPartitions);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (i != height - 1) {
                    partitions.add(new Partition(i, j, Partition.Type.HORIZONTAL));
                }
                if (j != width - 1) {
                    partitions.add(new Partition(i, j, Partition.Type.VERTICAL));
                }
            }
        }
        return partitions;
    }
}
