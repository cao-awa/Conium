package com.github.cao.awa.conium.parameter;

@FunctionalInterface
public interface ParameterSelective1<P> extends ParameterSelective {
    boolean trigger(P p1);
}
