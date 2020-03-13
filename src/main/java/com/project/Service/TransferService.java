package com.project.Service;

import com.project.Entity.Car;
import com.project.Entity.Transfer;
import com.project.Repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;

    @Transactional
    public List<Transfer> getAll(){
        return (List<Transfer>) transferRepository.findAll();
    }
}
