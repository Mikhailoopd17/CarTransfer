package com.project.Controller;

import com.project.Entity.Car;
import com.project.Entity.Point;
import com.project.Entity.Tariff;
import com.project.Service.CarServise;
import com.project.Service.PointService;
import com.project.Service.TariffService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {
    private final CarServise carServise;
    private final PointService pointService;
    private final TariffService tariffService;

    public CarController(CarServise carServise, PointService pointService, TariffService tariffService) {
        this.carServise = carServise;
        this.pointService = pointService;
        this.tariffService = tariffService;
    }

    @RequestMapping("/cars")
    public String cars(Model model){
        List<Car> cars = carServise.getAll();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @PostMapping(path = "/cars/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestParam(name="mark") String mark,
                         @RequestParam(name="number") String number,
                         @RequestParam(name = "id") Long id,
                         @RequestParam(name = "id_tariff") Long idTariff) {
        Point point = pointService.getById(id);
        Tariff tariff = tariffService.getTariffById(idTariff);
        Car car = new Car(mark, number, point, tariff);
        carServise.save(car);
    }

    @GetMapping("/cars/{id}")
    public String detailCarInform(@PathVariable Long id, Model model){
        Car car = carServise.getById(id);
        model.addAttribute("car", car);
        return "view";
    }

    @GetMapping("/cars/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable Long id){
        carServise.delete(id);
    }

}
