package com.github.cao.awa.conium.parameter;

@FunctionalInterface
public interface ParameterSelective2<P1, P2> extends ParameterSelective {
    boolean trigger(P1 p1, P2 p2);
}
