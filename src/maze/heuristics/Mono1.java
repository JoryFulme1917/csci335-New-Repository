package maze.heuristics;

import maze.core.MazeExplorer;
import java.util.function.ToIntFunction;



public class Mono1 implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer node){
        // sqrt( (x_1 - x_0)^2 + (y_1 - y_0)^2) -> Euclidean Distance

        return (int) Math.sqrt(Math.pow(node.getGoal().getLocation().getX() - node.getLocation().getX(), 2) + Math.pow(node.getGoal().getLocation().getY() - node.getLocation().getY(), 2));
    }

}

