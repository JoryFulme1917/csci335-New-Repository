package checkers.searchers;

import checkers.core.Checkerboard;
import checkers.core.CheckersSearcher;
import checkers.core.Move;
import core.Duple;

import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class ImprovedAlphaBeta extends CheckersSearcher{
    private int numNodes = 0;
    public ImprovedAlphaBeta(ToIntFunction<Checkerboard> e){
        super(e);
    }
    @Override
    public int numNodesExpanded(){
        return numNodes;
    }
    @Override
    public Optional<Duple<Integer, Move>> selectMove(Checkerboard board) {
        return selectMoveHelp(board, 0, -1 * Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private Optional<Duple<Integer, Move>> selectMoveHelp(Checkerboard board, int depth, int alpha, int beta){
        Optional<Duple<Integer, Move>> best = Optional.empty();

        if (board.gameOver()){
            if (board.playerWins(board.getCurrentPlayer())){
                return Optional.of(new Duple<>(Integer.MAX_VALUE, board.getLastMove()));
            } else if (board.playerWins(board.getCurrentPlayer().opponent())){
                return Optional.of(new Duple<>(0, board.getLastMove()));
            } else {
                return Optional.of(new Duple<>(0,board.getLastMove()));
            }
        }
        // new search one
        if (getDepthLimit() < depth && board.allCaptureMoves(board.getCurrentPlayer()).isEmpty()){
            return Optional.of(new Duple<>(getEvaluator().applyAsInt(board), board.getLastMove()));

        }
        //new search 2
        PriorityQueue<Checkerboard> boards = new PriorityQueue<>(Comparator.comparingInt(b -> -1 * getEvaluator().applyAsInt(b)));

        for (Checkerboard alt : board.getNextBoards()){
            boards.add(alt);
        }
        for (Checkerboard alternative: boards) {
            numNodes += 1;
            int negation = board.getCurrentPlayer() != alternative.getCurrentPlayer() ? -1 : 1;
            int recur = negation * selectMoveHelp(alternative, depth + 1, negation* beta,negation * alpha).get().getFirst();
            if (best.isEmpty() || best.get().getFirst()< recur){
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
