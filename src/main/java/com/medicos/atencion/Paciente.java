package com.medicos.atencion;

public class Paciente {

        private String rut;   
        private String nombre;
        private int edad;
        private String diagnostico;
        private String fechaAtencion;
    
    
    
        public Paciente(String rut, String nombre, int edad, String diagnostico, String fechaAtencion) {
    
            this.rut = rut;  
            this.nombre = nombre;
            this.edad = edad;
            this.diagnostico = diagnostico;
            this.fechaAtencion = fechaAtencion;
        }
    
    
    
        public String getRut(){    
    
            return rut;
    
        }
    
        public String getNombre(){
       
            return nombre;
    
        }
    
        public int getEdad(){    
    
            return edad;
    
        }

        public String getDiagnostico(){    
    
            return diagnostico;
    
        }

        public String getFechaAtencion(){    
    
            return fechaAtencion;
    
        }
        
    
}
