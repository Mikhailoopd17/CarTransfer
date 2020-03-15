package com.project.Controller;

import com.project.Entity.Point;
import com.project.Entity.Tariff;
import com.project.Service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TariffController {
    final
    TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @RequestMapping(path = "/tariffs/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTariff(@RequestParam(name="value") float value,
                          @RequestParam(name="desc") String desc) {
        tariffService.save(new Tariff(value, desc));
    }

    @GetMapping("/tariffs/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTariff(@PathVariable Long id){
        tariffService.delete(id);
    }
}
