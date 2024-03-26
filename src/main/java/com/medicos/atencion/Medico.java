package com.medicos.atencion;

import java.util.List;

public class Medico {
    
    private String rutmed;
    private String nombre;
    private int edad;
    private String especialidad;
    private List<Paciente> pacientes;

    public Medico(String rutmed, String nombre, int edad, String especialidad, List<Paciente> pacientes){

        this.rutmed = rutmed;
        this.nombre = nombre;
        this.edad = edad;
        this.especialidad = especialidad;
        this.pacientes = pacientes;
    }



    //Getters @

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
