package com.project.Service;

import com.project.Entity.Car;
import com.project.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServise {
    private final CarRepository carRepository;

    public CarServise(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public List<Car> getAll(){
        return (List<Car>) carRepository.findAll();
    }

    @Transactional
    public  void save(Car car){
        carRepository.save(car);
    }

    @Transactional
    public Car getById(Long id){
        return carRepository.findById(id).get();
    }

    @Transactional
    public void delete(Long id){
        carRepository.deleteById(id);
    }

}
