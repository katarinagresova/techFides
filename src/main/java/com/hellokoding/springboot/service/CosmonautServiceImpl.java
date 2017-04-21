package com.hellokoding.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellokoding.springboot.*;

@Service("userService")
public class CosmonautServiceImpl implements CosmonautService {

    CosmonautDao cosmonautDao;

    @Autowired
    public void setCosmonautDao(CosmonautDao cosmonautDao) {
        this.cosmonautDao = cosmonautDao;
    }

    @Override
    public Cosmonaut findById(Integer id) {
        return cosmonautDao.findById(id);
    }

    @Override
    public List<Cosmonaut> findAll() {
        return cosmonautDao.findAll();
    }

    @Override
    public void save(Cosmonaut cosmonaut) {
        cosmonautDao.save(cosmonaut);

    }

    @Override
    public void delete(int id) {
        cosmonautDao.delete(id);
    }

}