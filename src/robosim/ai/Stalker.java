package robosim.ai;
import robosim.core.Action;
import robosim.core.Controller;
import robosim.core.Simulator;
import robosim.reinforcement.QTable;

public class Stalker implements Controller{
    private QTable qValues = new QTable(StalkerStates.values().length, Action.values().length, 0, 8, 200, 0.5 );
    @Override
    public void control(Simulator sim){
        StalkerStates state = getState(sim);
        int chosenAction = qValues.senseActLearn(state.getIndex(), reward(sim));
        Action.values()[chosenAction].applyTo(sim);
    }
    public  StalkerStates getState(Simulator sim){
        if(sim.findClosestProblem() < 10){
            return StalkerStates.CLOSE;
        } else {
            return  StalkerStates.FAR;
        }
    }
    public double reward(Simulator sim){
        if(sim.wasHit()){
            return -100.0;
        } else if (Action.values()[qValues.getLastAction()].equals(Action.FORWARD)) {
            return -10.0;
        } else {
            return 0.0;
        }
    }

}