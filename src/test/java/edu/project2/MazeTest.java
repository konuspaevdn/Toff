package edu.project2;

import edu.project2.PrettyPrinter.Renderer;
import edu.project2.Solver.DFS.DFSSolver;
import edu.project2.structure.Cell;
import edu.project2.structure.Coordinate;
import edu.project2.structure.Maze;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeTest {

    @Test
    @DisplayName("Check if works")
    void generateAndFindAndPrint() {
        ArrayList<ArrayList<Cell>> cells = new ArrayList<>(3);
        for (int i = 0; i < 3; ++i) {
            cells.add(new ArrayList<>(4));
        }

        Set<Cell.Direction> set;
        Cell cell;

        set = new HashSet<>();
        set.add(Cell.Direction.RIGHT);
        set.add(Cell.Direction.DOWN);
        cell = new Cell(0, 0, set);
        cells.get(0).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.LEFT);
        set.add(Cell.Direction.DOWN);
        cell = new Cell(0, 1, set);
        cells.get(0).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.RIGHT);
        set.add(Cell.Direction.DOWN);
        cell = new Cell(0, 2, set);
        cells.get(0).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.LEFT);
        set.add(Cell.Direction.DOWN);
        cell = new Cell(0, 3, set);
        cells.get(0).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.UP);
        cell = new Cell(1, 0, set);
        cells.get(1).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.UP);
        set.add(Cell.Direction.DOWN);
        cell = new Cell(1, 1, set);
        cells.get(1).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.UP);
        cell = new Cell(1, 2, set);
        cells.get(1).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.UP);
        set.add(Cell.Direction.DOWN);
        cell = new Cell(1, 3, set);
        cells.get(1).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.RIGHT);
        cell = new Cell(2, 0, set);
        cells.get(2).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.LEFT);
        set.add(Cell.Direction.RIGHT);
        set.add(Cell.Direction.UP);
        cell = new Cell(2, 1, set);
        cells.get(2).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.LEFT);
        set.add(Cell.Direction.RIGHT);
        cell = new Cell(2, 2, set);
        cells.get(2).add(cell);

        set = new HashSet<>();
        set.add(Cell.Direction.LEFT);
        set.add(Cell.Direction.UP);
        cell = new Cell(2, 3, set);
        cells.get(2).add(cell);

        Maze maze = new Maze(cells);
        DFSSolver solver = new DFSSolver();
        List<Coordinate> path = solver.solve(maze, new Coordinate(1, 0), new Coordinate(1, 2));
        assertThat(path.size()).isEqualTo(11);
        assertThat(path.get(0)).isEqualTo(new Coordinate(1, 0));
        assertThat(path.get(1)).isEqualTo(new Coordinate(0, 0));
        assertThat(path.get(2)).isEqualTo(new Coordinate(0, 1));
        assertThat(path.get(3)).isEqualTo(new Coordinate(1, 1));
        assertThat(path.get(4)).isEqualTo(new Coordinate(2, 1));
        assertThat(path.get(5)).isEqualTo(new Coordinate(2, 2));
        assertThat(path.get(6)).isEqualTo(new Coordinate(2, 3));
        assertThat(path.get(7)).isEqualTo(new Coordinate(1, 3));
        assertThat(path.get(8)).isEqualTo(new Coordinate(0, 3));
        assertThat(path.get(9)).isEqualTo(new Coordinate(0, 2));
        assertThat(path.get(10)).isEqualTo(new Coordinate(1, 2));

        String mazePrint = """
                            #########
                            #   #   #
                            # # # # #
                            # # # # #
                            ### ### #
                            #       #
                            #########
                            """;
        String mazeWithPathPrint = """
                                   #########
                                   #@@@#@@@#
                                   #@#@#@#@#
                                   #S#@#E#@#
                                   ###@###@#
                                   #  @@@@@#
                                   #########
                                   """;
        assertThat(Renderer.render(maze)).isEqualTo(mazePrint);
        assertThat(Renderer.render(maze, path)).isEqualTo(mazeWithPathPrint);
    }

    @Test
    @DisplayName("Invoke exceptions")
    void Exceptions() {

    }

}
