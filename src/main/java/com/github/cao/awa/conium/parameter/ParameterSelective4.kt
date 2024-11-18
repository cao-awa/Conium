package com.github.cao.awa.conium.parameter;

@FunctionalInterface
public interface ParameterSelective4<R, P1, P2, P3, P4> extends ParameterSelective {
    R arise(P1 p1, P2 p2, P3 p3, P4 p4);
}
