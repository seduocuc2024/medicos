package com.medicos.atencion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medicos.atencion.model.Medico;
import com.medicos.atencion.service.MedicoService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/medicos")
public class AtencionController {

    private static final Logger log = LoggerFactory.getLogger(AtencionController.class);

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public List<Medico> getAllMedicos(){
        log.info("GET /medicos");
        log.info("Retornando todos los medicos");
        return medicoService.getAllMedicos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMedicoById(@PathVariable Long id){
        Optional <Medico> medico = medicoService.getMedicoById(id);

        if(medico.isEmpty()){
            log.error("No se encontro el medico con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro el medico con ID "+ id));
        }
        return ResponseEntity.ok(medico);
    }
    
    @PostMapping
    public ResponseEntity<Object> createMedico(@RequestBody Medico medico){
        Medico createdMedico = medicoService.createMedico(medico);
        if(createdMedico == null){
            log.error("Error al crear al medico {}", medico);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Error al crear al medico"));
        }
        return ResponseEntity.ok(createdMedico);
    }
    
    @PutMapping("/{id}")
    public Medico updateMedico(@PathVariable Long id, @RequestBody Medico medico){
        return medicoService.updateMedico(id, medico);
    }

    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable Long id){
        medicoService.deleteMedico(id);           
    }
    
    static class ErrorResponse{
        private final String message;

        public ErrorResponse(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }
    }



}

