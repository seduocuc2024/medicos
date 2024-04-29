package com.medicos.atencion.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "medico")
public class Medico extends RepresentationModel<Medico>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rutmed")
    private String rutmed;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private int edad;

    @Column(name = "especialidad")
    private String especialidad;

    // Nuevo atributo
    @Column(name = "paciente_rut")
    private String pacienteRut;
  

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRutmed() {
        return rutmed;
    }

    public void setRutmed(String rutmed) {
        this.rutmed = rutmed;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPacienteRut() {
        return pacienteRut;
    }

    public void setPacienteRut(String pacienteRut) {
        this.pacienteRut = pacienteRut;
    }
}