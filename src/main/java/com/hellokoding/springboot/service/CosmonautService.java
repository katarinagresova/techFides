package com.hellokoding.springboot.service;

import java.util.List;
import com.hellokoding.springboot.Cosmonaut;

public interface CosmonautService {

    Cosmonaut findById(Integer id);

    List<Cosmonaut> findAll();

    void save(Cosmonaut cosmonaut);

    void delete(int id);

}