package checkers.evaluators;

import checkers.core.Checkerboard;

import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public class NewEval implements ToIntFunction<Checkerboard> {
    public int applyAsInt(Checkerboard c){
        int kingCount = c.numKingsOf(c.getCurrentPlayer()) - c.numPiecesOf(c.getCurrentPlayer().opponent());
        int pieceCount = c.numPiecesOf(c.getCurrentPlayer()) - c.numPiecesOf(c.getCurrentPlayer().opponent());
        int kVP = c.numKingsOf(c.getCurrentPlayer()) - c.numPiecesOf(c.getCurrentPlayer());
        int kVPO = c.numKingsOf(c.getCurrentPlayer().opponent()) - c.numPiecesOf(c.getCurrentPlayer().opponent());

        return 2*kingCount + pieceCount + 2*(kVP - kVPO);
    }
}

