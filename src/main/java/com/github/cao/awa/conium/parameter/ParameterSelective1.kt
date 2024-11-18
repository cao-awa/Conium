package com.github.cao.awa.conium.parameter;

@FunctionalInterface
public interface ParameterSelective1<R, P> extends ParameterSelective {
    R arise(P p1);
}
