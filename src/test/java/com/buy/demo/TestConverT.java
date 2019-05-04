package com.buy.demo;

@FunctionalInterface
public interface TestConverT<T, F> {
    F convert(T t);
}
