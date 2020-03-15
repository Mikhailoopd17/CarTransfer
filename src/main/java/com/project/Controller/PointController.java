package com.project.Controller;

import com.project.Entity.Point;
import com.project.Service.PointService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PointController {
    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/points")
    public String points(Model model){
        List<Point> point = pointService.getAll();
        model.addAttribute("points", point);
        return "index";
    }

    @RequestMapping(path = "/points/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPoint(@RequestParam(name="name", required = true, defaultValue = "none") String name) {
        pointService.save(new Point(name));
    }
    @GetMapping("/points/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransfer(@PathVariable Long id){
        pointService.delete(id);
    }
}
