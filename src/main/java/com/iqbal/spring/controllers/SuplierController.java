package com.iqbal.spring.controllers;

import com.iqbal.spring.dto.ResponseData;
import com.iqbal.spring.dto.SuplierDTO;
import com.iqbal.spring.model.entity.Suplier;
import com.iqbal.spring.services.SuplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliers")
public class SuplierController {

    @Autowired
    private SuplierService suplierService;

    public ResponseEntity<ResponseData<Suplier>> create (@Valid @RequestBody SuplierDTO suplierDTO, Errors errors){
        ResponseData<Suplier> responseData = new ResponseData<>();

        // Kita cek ada error apa ngak
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);

            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Kalau ngak ada error kita jalanin ini
        // Sebelumnya kita ubah/transform supplier jadi supplierDto karena pada controler ini mintanya object suplierDto

        Suplier suplier = new Suplier();
        suplier.setName(suplierDTO.getName());
        suplier.setAddress(suplierDTO.getAddress());
        suplier.setEmail(suplierDTO.getEmail());

        responseData.setStatus(true);
        responseData.setPayload(suplierService.save(suplier));
        return ResponseEntity.ok(responseData);
    }




}
