package com.project.Service;


import com.project.Entity.Point;
import com.project.Repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PointService {

    @Autowired
    private PointRepository repository;

    @Transactional
    public List getAllPoint(){
        return (List) repository.findAll();
    }

    @Transactional
    public void add(Point point){
       repository.save(point);
    }
    @Transactional
    public Point getById(Long id){
        return repository.findById(id).get();
    }

}
