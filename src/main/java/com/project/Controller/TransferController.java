package com.project.Controller;

import com.project.Entity.Car;
import com.project.Entity.Point;
import com.project.Entity.Transfer;
import com.project.Service.CarServise;
import com.project.Service.PointService;
import com.project.Service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

@Controller
public class TransferController {
    private final TransferService transferService;
    private final CarServise carServise;
    private final PointService pointService;

    public TransferController(TransferService transferService, CarServise carServise, PointService pointService) {
        this.transferService = transferService;
        this.carServise = carServise;
        this.pointService = pointService;
    }

    @GetMapping("/transfers")
    public String transfers(Model model){
        List<Transfer> transfers = transferService.getAll();
        model.addAttribute("transfers", transfers);
        return "transfers";
    }


    @RequestMapping(path = "/transfers/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTransfer(@RequestParam(name="tenant") String tenant,
                            @RequestParam(name="car_id") Long car_id,
                            @RequestParam(name="date_begin") String date1,
                            @RequestParam(name="date_end") String date2,
                            @RequestParam(name="point_id") Long point_id) throws ParseException {
        Point pointEnd = pointService.getById(point_id);
        Car car = carServise.getById(car_id);

        transferService.save(new Transfer(tenant, car, date1, date2, pointEnd.getName()));
        car.toTransfer(pointEnd); //меняем точку расположения авто
        carServise.save(car); //и вносим изменения в бд
    }

    @GetMapping("/transfers/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransfer(@PathVariable Long id){
        transferService.delete(id);
    }
}
