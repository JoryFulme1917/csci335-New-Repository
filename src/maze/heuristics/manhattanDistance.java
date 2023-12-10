package maze.heuristics;

import maze.core.MazeExplorer;
import java.util.function.ToIntFunction;


public class manhattanDistance implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer node) {
        int distance = Math.abs(node.getGoal().getLocation().getX() - node.getLocation().getX()) + Math.abs(node.getGoal().getLocation().getY() - node.getLocation().getY());


        return distance;

    }
}
