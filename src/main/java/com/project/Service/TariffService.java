package com.project.Service;

import com.project.Entity.Tariff;
import com.project.Repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TariffService {
    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Transactional
    public Tariff getTariffById(Long id) {
        return tariffRepository.findById(id).get();
    }

    @Transactional
    public void save(Tariff tariff){
        tariffRepository.save(tariff);
    }

    @Transactional
    public void delete(Long id){
        tariffRepository.deleteById(id);
    }
}
