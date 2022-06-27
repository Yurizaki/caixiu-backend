package com.caixiu.backend.dao;

import java.util.List;

public interface ItemDao<T> {
    List<T> getAll();
}
