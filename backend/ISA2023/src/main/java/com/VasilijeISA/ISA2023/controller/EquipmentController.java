package com.VasilijeISA.ISA2023.controller;

import com.VasilijeISA.ISA2023.dto.PurchaseEquipmentDto;
import com.VasilijeISA.ISA2023.model.Equipment;
import com.VasilijeISA.ISA2023.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAll() {
        List<Equipment> equipment = equipmentService.getAll();
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @GetMapping("/byCompanyID/{id}")
    public ResponseEntity<List<Equipment>> getByCompanyID(@PathVariable int id) {
        List<Equipment> equipment = equipmentService.getByCompanyID(id);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @GetMapping("/byID/{id}")
    public ResponseEntity<Equipment> getByID(@PathVariable Long id) {
        Equipment equipment = equipmentService.getByID(id);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

        @PostMapping("/purchase")
        public ResponseEntity<Boolean> purchase(@RequestBody PurchaseEquipmentDto p) {
            try {
                Boolean result = equipmentService.purchase(p);
                if (result) {
                    return new ResponseEntity<Boolean>(true, HttpStatus.OK);
                } else {
                    return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
                }
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

}
