package com.guo.javaconcurrencyinpractice.eightThreadPool.demomiti;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

//递归转化成并发
//问题：{可能会耗尽内存,    要使用无界线程池，不然可能会死锁 ，如果找不到答案会一直在solution.getValue();等待 {记录提交的任务数量，若等于0了就临时设置答案为null}}
//TODO P155
public class ConcurrentPuzzleSolver<P,M> {
    private final Puzzle<P,M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P,Boolean> seen;
    final ValueLatch<Node2<P,M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec) {
        this.puzzle = puzzle;
        this.exec = exec;

        this.seen = new ConcurrentHashMap<>();
    }

    //解决
    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p,null,null));
            Node2<P, M> solutionValue = solution.getValue();
            return (solutionValue == null)? null:solutionValue.asModeList();
        } finally {
            exec.shutdown();
        }

    }

    protected Runnable newTask(P p,M m ,Node2<P,M> node2){
        return new SolverTask(p,m,node2);
    }

    class SolverTask extends Node2<P,M> implements Runnable{

        public SolverTask(P pos, M move, Node2<P, M> prex) {
            super(pos, move, prex);
        }

        @Override
        public void run() {
            if (solution.isSet() || seen.putIfAbsent(pos,true) != null){
                return; //已经找到了答案或者已经遍历过这个位子
            }
            if (puzzle.isGoal(pos)){
                solution.setValue(this);
            }else {
                for (M m : puzzle.legalMoves(pos)) {
                    //又提交任务
                    exec.execute(newTask(puzzle.move(pos,m),m,this));
                }

            }
        }
    }

    static class Node2<P,M>{
        final P pos;
        final M move;
        final Node2<P,M> prex;

        public Node2(P pos, M move, Node2<P, M> prex) {
            this.pos = pos;
            this.move = move;
            this.prex = prex;
        }

        List<M> asModeList(){
            LinkedList<M> solution = new LinkedList<>();
            for (Node2<P,M> n = this; n.move != null; n = n.prex){
                solution.add(0,n.move);
            }
            return solution;
        }
    }
}
