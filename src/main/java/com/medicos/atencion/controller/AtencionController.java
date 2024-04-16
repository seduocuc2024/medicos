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
    public ResponseEntity<Object> updateMedico(@PathVariable Long id, @RequestBody Medico medico){
        Optional<Medico> existingMedicoOptional = medicoService.getMedicoById(id);
        if(existingMedicoOptional.isEmpty()){
            log.error("No se encontro el medico con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro el medico con ID "+ id));
        }

        Medico existingMedico = existingMedicoOptional.get();

        // Validaciones
        if (medico.getRutmed() == null || medico.getRutmed().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El rut del medico es obligatorio"));
        }
        if (medico.getNombre() == null || medico.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El nombre del medico es obligatorio"));
        }
        if (medico.getEdad() <= 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("La edad del medico debe ser mayor a cero"));
        }

        // Actualizar el medico existente
        existingMedico.setRutmed(medico.getRutmed());
        existingMedico.setNombre(medico.getNombre());
        existingMedico.setEdad(medico.getEdad());
        existingMedico.setEspecialidad(medico.getEspecialidad());

        Medico updatedMedico = medicoService.updateMedico(id, existingMedico);
        return ResponseEntity.ok(updatedMedico);
    }

    


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMedico(@PathVariable Long id){
    Optional<Medico> existingMedicoOptional = medicoService.getMedicoById(id);
    if(existingMedicoOptional.isEmpty()){
        log.error("No se encontro el medico con ID {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontro el medico con ID "+ id));
    }

    medicoService.deleteMedico(id);
    return ResponseEntity.ok().build();
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

