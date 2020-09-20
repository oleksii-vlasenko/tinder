package service;

public interface Storage<T> {
    Iterable<T> get();
    void save(T object);
}
