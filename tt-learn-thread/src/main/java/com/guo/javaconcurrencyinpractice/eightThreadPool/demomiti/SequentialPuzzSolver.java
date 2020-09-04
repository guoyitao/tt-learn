package com.guo.javaconcurrencyinpractice.eightThreadPool.demomiti;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
//串行
//深度优先
public class SequentialPuzzSolver<P,M> {
    private final Puzzle<P,M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve(){
        P pos = puzzle.initialPosition();
        return search(new Node<>(pos,null,null));
    }

    private List<M> search(Node<P,M> node){
        if (!seen.contains(node.pos)){
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos)){
                return node.asModeList();
            }
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);
                Node<P, M> child = new Node<>(pos, move, node);
                List<M> result = search(child);
                if (result != null){
                    return result;
                }

            }
        }
        return null;

    }

    static class Node<P,M>{
        final P pos;
        final M move;
        final Node<P,M> prex;

        public Node(P pos, M move, Node<P, M> prex) {
            this.pos = pos;
            this.move = move;
            this.prex = prex;
        }

        List<M> asModeList(){
            LinkedList<M> solution = new LinkedList<>();
            for (Node<P,M> n = this;n.move != null;n = n.prex){
                solution.add(0,n.move);
            }
            return solution;
        }
    }

}
