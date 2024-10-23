package com.github.cao.awa.conium.parameter;

@FunctionalInterface
public interface ParameterSelective3<R, P1, P2, P3> extends ParameterSelective {
    R arise(P1 p1, P2 p2, P3 p3);
}
