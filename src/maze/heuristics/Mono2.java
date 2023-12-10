package maze.heuristics;

import maze.core.MazeExplorer;
import java.util.function.ToIntFunction;



public class Mono2 implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer node){

        return (int) Math.log(Math.abs(node.getGoal().getLocation().getX() - node.getLocation().getX()) + Math.abs(node.getGoal().getLocation().getY() - node.getLocation().getY()));
    }

}
