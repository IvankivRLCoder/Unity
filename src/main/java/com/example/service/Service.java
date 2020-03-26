package com.example.service;

import java.util.List;

public interface Service<K> {

    K getById(int id);

    List<K> getAll();

}

