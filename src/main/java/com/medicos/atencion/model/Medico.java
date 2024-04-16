package com.medicos.atencion.model;

import java.util.List;

public class Medico {
    
    private Long id;
    private String rutmed;
    private String nombre;
    private int edad;
    private String especialidad;
    private List<Paciente> pacientes;

    public Medico(Long id, String rutmed, String nombre, int edad, String especialidad, List<Paciente> pacientes){
        this.id = id;
        this.rutmed = rutmed;
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
        this.pacientes = pacientes;
    }



    //Getters @
    public Long getId(){
        return id;
    }

    public String getRutMed() {
        return rutmed;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getEspecialidad(){
        return especialidad;
    }

    public List<Paciente> getPacientes(){
        return pacientes;
    }

}
