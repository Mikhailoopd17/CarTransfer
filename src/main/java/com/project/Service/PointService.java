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

    private final PointRepository repository;

    public PointService(PointRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Point> getAll(){
        return (List<Point>)repository.findAll();
    }

    @Transactional
    public void save(Point point){
       repository.save(point);
    }
    @Transactional
    public Point getById(Long id){
        return repository.findById(id).get();
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }
}
