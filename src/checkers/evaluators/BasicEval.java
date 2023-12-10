package checkers.evaluators;
import checkers.core.Checkerboard;
import checkers.core.PlayerColor;

import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public class BasicEval implements ToIntFunction<Checkerboard>{
    public int applyAsInt(Checkerboard c){return (c.numPiecesOf(c.getCurrentPlayer()) - c.numPiecesOf(c.getCurrentPlayer().opponent()));}

}
