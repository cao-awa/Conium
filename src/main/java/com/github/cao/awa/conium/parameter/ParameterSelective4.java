package com.github.cao.awa.conium.parameter;

@FunctionalInterface
public interface ParameterSelective4<P1, P2, P3, P4> extends ParameterSelective {
    boolean trigger(P1 p1, P2 p2, P3 p3, P4 p4);
}
