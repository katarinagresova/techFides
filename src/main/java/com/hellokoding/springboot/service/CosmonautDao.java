package com.hellokoding.springboot.service;

import java.util.List;

import com.hellokoding.springboot.Cosmonaut;

public interface CosmonautDao {

    Cosmonaut findById(Integer id);

    List<Cosmonaut> findAll();

    void save(Cosmonaut cosmonaut);

    void update(Cosmonaut cosmonaut);

    void delete(Integer id);

}