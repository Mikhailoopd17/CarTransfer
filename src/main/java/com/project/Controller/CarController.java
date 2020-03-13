package com.project.Controller;

import com.project.Entity.Car;
import com.project.Entity.Point;
import com.project.Service.CarServise;
import com.project.Service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarServise carServise;
    @Autowired
    private PointService pointService;

    @RequestMapping("/cars")
    public String cars(Model model){
        List<Car> cars = carServise.getAll();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @PostMapping(path = "/addcar")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPoint(@RequestParam(name="mark") String mark,
                         @RequestParam(name="number") String number,
                         @RequestParam(name = "id") Long id) {
        Point point = pointService.getById(id);
        Car car = new Car(mark, number, point);
        carServise.add(car);
    }

    @GetMapping("/cars/{id}")
    public String home(@PathVariable Long id, Model model){
        Car car = carServise.getById(id);
        model.addAttribute("car", car);
        return "view";
    }

}
