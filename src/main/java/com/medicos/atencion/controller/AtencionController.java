package com.medicos.atencion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.medicos.atencion.model.Medico;
import com.medicos.atencion.service.MedicoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/medicos")
public class AtencionController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public CollectionModel<EntityModel<Medico>> getAllMedicos(){
        List<Medico> medicos = medicoService.getAllMedicos();

        List<EntityModel<Medico>> medicoResources = medicos.stream()
                .map(medico -> EntityModel.of(medico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(medico.getId())).withSelfRel()
                )).collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMedicos());
        CollectionModel<EntityModel<Medico>> resources = CollectionModel.of(medicoResources, linkTo.withRel("medicos"));
        
        return resources;
    }
    
    @GetMapping("/{id}")
    public EntityModel<Medico> getMedicoById(@PathVariable Long id){
        Optional <Medico> medico = medicoService.getMedicoById(id);

        if(medico.isPresent()){
            return EntityModel.of(medico.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMedicos()).withRel("all-medicos"));   
        } else {
            throw new MedicoNotFoundException("Medico not found with id: "+ id);
        }
    }
    
    @PostMapping
    public EntityModel<Medico> createMedico(@RequestBody Medico medico){
        Medico createdMedico = medicoService.createMedico(medico);
        return EntityModel.of(createdMedico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(createdMedico.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMedicos()).withRel("all-medicos"));
    }
    
    @PutMapping("/{id}")
    public EntityModel<Medico> updateMedico(@PathVariable Long id, @RequestBody Medico medico){
        Medico updatedMedico = medicoService.updateMedico(id, medico);
        return EntityModel.of(updatedMedico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllMedicos()).withRel("all-medicos"));

        

        // Validaciones
        // if (medico.getRutmed() == null || medico.getRutmed().isEmpty()) {
        //     return ResponseEntity.badRequest().body(new ErrorResponse("El rut del medico es obligatorio"));
        // }
        // if (medico.getNombre() == null || medico.getNombre().isEmpty()) {
        //     return ResponseEntity.badRequest().body(new ErrorResponse("El nombre del medico es obligatorio"));
        // }
        // if (medico.getEdad() <= 0) {
        //     return ResponseEntity.badRequest().body(new ErrorResponse("La edad del medico debe ser mayor a cero"));
        // }

        // Actualizar el medico existente
        // existingMedico.setRutmed(medico.getRutmed());
        // existingMedico.setNombre(medico.getNombre());
        // existingMedico.setEdad(medico.getEdad());
        // existingMedico.setEspecialidad(medico.getEspecialidad());

        // Medico updatedMedico = medicoService.updateMedico(id, existingMedico);
        // return ResponseEntity.ok(updatedMedico);
    }

    


    @DeleteMapping("/{id}")
    public void deleteMedico(@PathVariable Long id) {
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

