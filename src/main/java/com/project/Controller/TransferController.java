package com.project.Controller;

import com.project.Entity.Car;
import com.project.Entity.Transfer;
import com.project.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TransferController {
    @Autowired
    private TransferService transferService;

    @RequestMapping("/transfers")
    public String transfers(Model model){
        List<Transfer> transfers = transferService.getAll();
        model.addAttribute("transfers", transfers);
        return "transfers";
    }
}
