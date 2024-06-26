package com.iqbal.spring.services;

import com.iqbal.spring.model.entity.Suplier;
import com.iqbal.spring.model.repository.SuplierRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SuplierService {

    @Autowired
    SuplierRepo suplierRepo;

    public Suplier save(Suplier suplier){
        return suplierRepo.save(suplier);
    }

    public Suplier findOne(Long id){
        Optional<Suplier> suplier = suplierRepo.findById(id);

        if (!suplier.isPresent()){
            return  null;
        }

        return  suplier.get();
    }

    public Iterable<Suplier> findAll(){
        return  suplierRepo.findAll();
    }

    public void removeOne (Long id){
        suplierRepo.deleteById(id);
    }


}
