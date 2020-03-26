package com.example.dao;

import java.util.List;

public interface Crud<V> {
    V save(V v);

    V getById(int id);

    V update(V v);

    void delete(V v);

    List<V> getAll();
}
