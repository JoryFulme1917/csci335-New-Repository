package search.bestfirst;

import search.SearchNode;
import search.SearchQueue;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class BestFirstQueue<T> implements SearchQueue<T> {
    private PriorityQueue<SearchNode<T>> queue;
    private HashMap<T, Integer> heuristicValues; // Store heuristic values for visited nodes

    public BestFirstQueue(ToIntFunction<T> heuristic) {
        Comparator<SearchNode<T>> comparator = Comparator.comparingInt(node -> {
            int i = heuristic.applyAsInt(node.getValue());
            return i;
        });
        queue = new PriorityQueue<>(comparator);
        heuristicValues = new HashMap<>();
    }

    @Override
    public void enqueue(SearchNode<T> node) {
        T value = node.getValue();
        int currentDepth = node.getDepth();
        if (!heuristicValues.containsKey(value) || currentDepth < heuristicValues.get(value)) {
            heuristicValues.put(value, currentDepth);
            queue.add(node);
        }
    }

    @Override
    public Optional<SearchNode<T>> dequeue() {
        return Optional.ofNullable(queue.poll());
    }
}
