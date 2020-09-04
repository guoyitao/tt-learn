package com.guo.javaconcurrencyinpractice.eightThreadPool.demomiti;

import java.util.Set;

public interface Puzzle<P,M> {
    P initialPosition();

    boolean isGoal(P postition);

    Set<M> legalMoves(P postition);

    P move(P postition,M move);


}
