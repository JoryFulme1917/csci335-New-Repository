package checkers.searchers;

import checkers.core.Checkerboard;
import checkers.core.CheckersSearcher;
import checkers.core.Move;
import core.Duple;

import java.util.Optional;
import java.util.function.ToIntFunction;

public class NegaMax extends CheckersSearcher{
    private int numNodes = 0;
    public NegaMax(ToIntFunction<Checkerboard> e) {super(e);}

    @Override
    public int numNodesExpanded() {
        return numNodes;
    }

    private Optional<Duple<Integer, Move>> helpPlease(Checkerboard board, int depth){
        if(board.gameOver()){
            if(board.playerWins(board.getCurrentPlayer())){
                return Optional.of(new Duple<>(Integer.MAX_VALUE, board.getLastMove()));
            }
            else if( board.playerWins(board.getCurrentPlayer().opponent())){
                return Optional.of(new Duple<>(-Integer.MAX_VALUE, board.getLastMove()));
            }
            else{
                return Optional.of(new Duple<>(0, board.getLastMove()));
            }
        }
        if(getDepthLimit() < depth){
            return Optional.of(new Duple<>(getEvaluator().applyAsInt(board), board.getLastMove()));
        }
        Optional<Duple<Integer, Move>> best_move = Optional.empty();
        for (Checkerboard alternative: board.getNextBoards()) {
            numNodes += 1;
            int negation = board.getCurrentPlayer() != alternative.getCurrentPlayer() ? -1 : 1;
            int scoreFor = negation * helpPlease(alternative, depth + 1).get().getFirst();
            if (best_move.isEmpty() || best_move.get().getFirst() < scoreFor) {
                best_move = Optional.of(new Duple<>(scoreFor, alternative.getLastMove()));
            }
        }
        return best_move;
    }

    @Override
    public Optional<Duple<Integer, Move>> selectMove(Checkerboard board) {
        return helpPlease(board, 0);

    }

}
