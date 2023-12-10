package maze.heuristics;

import maze.core.MazeExplorer;
import java.util.function.ToIntFunction;



public class NonMono implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer node){
        if (node.getM().isStart(node.getLocation())){
            return 1;
        }
        else{
            int distance = Math.abs(node.getM().getStart().getX() - node.getLocation().getX()) + Math.abs(node.getM().getStart().getY() - node.getLocation().getY());
            return distance;

        }

    }

}
