package edu.project2;

import edu.project2.structure.Cell;
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
        Set<Set<Cell>> sets = new HashSet<>();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Set<Cell> setOfCell = new HashSet<>();
                setOfCell.add(maze.getCell(i, j));
                sets.add(setOfCell);
            }
        }
        ArrayList<Partition> partitions = getPartitions(height, width);
        Random idxGenerator = new Random();
        while (!partitions.isEmpty()) {
            int idx = idxGenerator.nextInt(partitions.size());
            Partition partition = partitions.remove(idx);

            Predicate<Set<Cell>> isAdjacentToPartition = set -> {
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

            List<Set<Cell>> list = sets.stream()
                .filter(isAdjacentToPartition).toList();
            //System.out.println(list);
            if (list.size() == 2) {
                Set<Cell> set1 = list.getFirst();
                Set<Cell> set2 = list.getLast();
                sets.remove(set1);
                sets.remove(set2);
                updateAdjacentCell(set1, partition);
                updateAdjacentCell(set2, partition);
                Set<Cell> newSet = new HashSet<>();
                newSet.addAll(set1);
                newSet.addAll(set2);
                sets.add(newSet);
            }
        }
        return maze;
    }

    void updateAdjacentCell(Set<Cell> set, Partition partition) {
        Cell adjCell;
        if (partition.type() == Partition.Type.HORIZONTAL) {
            adjCell = set.stream()
                .filter(cell -> cell.row() == partition.i() && cell.col() == partition.j()
                    || cell.row() - 1 == partition.i() && cell.col() == partition.j()).findFirst().orElseThrow();
            if (adjCell.row() == partition.i()) {
                adjCell.directions().add(Cell.Direction.DOWN);
            } else {
                adjCell.directions().add(Cell.Direction.UP);
            }
        } else {
            adjCell = set.stream()
                .filter(cell -> cell.row() == partition.i() && cell.col() == partition.j()
                    || cell.row() == partition.i() && cell.col() - 1 == partition.j()).findFirst().orElseThrow();
            if (adjCell.col() == partition.j()) {
                adjCell.directions().add(Cell.Direction.RIGHT);
            } else {
                adjCell.directions().add(Cell.Direction.LEFT);
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
