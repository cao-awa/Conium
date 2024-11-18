package com.github.cao.awa.conium.parameter;

@FunctionalInterface
public interface ParameterSelective2<R, P1, P2> extends ParameterSelective {
    R arise(P1 p1, P2 p2);
}
