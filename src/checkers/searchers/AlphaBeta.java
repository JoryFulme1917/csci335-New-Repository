package checkers.searchers;

import checkers.core.Checkerboard;
import checkers.core.CheckersSearcher;
import checkers.core.Move;
import core.Duple;

import java.util.Optional;
import java.util.function.ToIntFunction;

public class AlphaBeta extends CheckersSearcher {
    private int numNodes = 0;

    public AlphaBeta(ToIntFunction<Checkerboard> e) {
        super(e);
    }

    @Override
    public int numNodesExpanded() {
        return numNodes;
    }

    @Override
    public Optional<Duple<Integer, Move>> selectMove(Checkerboard board) {
        return selectMoveHelp(board, 0, -1 * Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private Optional<Duple<Integer, Move>> selectMoveHelp(Checkerboard board, int depth, int alpha, int beta) {
        Optional<Duple<Integer, Move>> best = Optional.empty();

        if (board.gameOver()) {
            if (board.playerWins(board.getCurrentPlayer())) {
                return Optional.of(new Duple<>(Integer.MAX_VALUE, board.getLastMove()));
            } else if (board.playerWins(board.getCurrentPlayer().opponent())) {
                return Optional.of(new Duple<>(-1 * Integer.MAX_VALUE, board.getLastMove()));
            } else {
                return Optional.of(new Duple<>(0, board.getLastMove()));
            }
        }
        if (getDepthLimit() < depth){
            return Optional.of(new Duple<>(getEvaluator().applyAsInt(board), board.getLastMove()));
        }
        for (Checkerboard alternative: board.getNextBoards()){
            numNodes += 1;
            int negation = board.getCurrentPlayer() != alternative.getCurrentPlayer() ? -1 : 1;
            int recur = negation * selectMoveHelp(alternative, depth + 1, negation * beta, negation * alpha).get().getFirst();
            if (best.isEmpty() || best.get().getFirst() <recur){
                best = Optional.of(new Duple<>(recur, alternative.getLastMove()));
                alpha = Math.max(alpha, recur);
            }
            if (alpha >= beta){
                return best;
            }
        }
        return best;
    }
}