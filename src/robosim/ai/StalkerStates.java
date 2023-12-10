package robosim.ai;

public enum StalkerStates {
    CLOSE, FAR;
    public int getIndex(){
        for (int i = 0; i < StalkerStates.values().length; i++){
            if (StalkerStates.values()[i].equals(this)){
                return i;
            }
        }
        throw new IllegalArgumentException("this should never happen");
    }
}