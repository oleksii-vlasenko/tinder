package com.fs11.step.tinder.service;

public interface Storage<T> {
    Iterable<T> get();
    void save(T object);
}
