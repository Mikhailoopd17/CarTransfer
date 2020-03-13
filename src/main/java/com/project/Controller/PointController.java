package com.project.Controller;

import com.project.Entity.Point;
import com.project.Repository.PointRepository;
import com.project.Service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PointController {

    @Autowired
    private PointService pointService;


    @GetMapping("/points")
    public String points(Model model){
        List<Point> point = pointService.getAllPoint();
        model.addAttribute("points", point);
        return "index";
    }

//    @GetMapping("/points/{id}")
//    public String home(@PathVariable Long id, Model model){
//        Point point = pointService.getById(id);
//        model.addAttribute("point", point);
//        return "view";
//    }

    @PostMapping(path = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPoint(@RequestParam(name="name", required = true, defaultValue = "none") String name) {
        pointService.add(new Point(name));
    }
}
