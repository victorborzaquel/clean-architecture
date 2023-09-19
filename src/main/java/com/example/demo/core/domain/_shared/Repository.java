package com.example.demo.core.domain._shared;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {

  void save(T entity);

  T find(UUID id);

  List<T> findAll();

}
