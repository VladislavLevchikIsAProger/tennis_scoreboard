package com.vladislavlevchik.dao;

import java.util.List;

public interface CrudDao<T, ID> {

    List<T> findAll();

}
