package com.medicos.atencion;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class AtencionController {

    private List<Medico> medicos = new ArrayList<>();

    
    public AtencionController() {

        // Inicializar la lista con datos

        medicos.add(new Medico("15.154.245-4", "Felipe Hurtado", 38,"Medicina General",Arrays.asList(new Paciente("18.060.353-5","Alonso Muñoz",24,"Rinofaringitis","14/01/2024"),
                                                                                                                                     new Paciente("13.888.212-3","Matias Vargas",47,"Neumotitis","18/01/2024"),
                                                                                                                                     new Paciente("14.999.212-k","Juan Perez",35,"Neumotitis","02/02/2024"))));
        medicos.add(new Medico("11.222.987-2", "Antonio Mechado", 44,"Medicina General",Arrays.asList(new Paciente("18.352.123-2","Bruno Miranda",25,"Amigdalitis","13/01/2024"),
                                                                                                                                     new Paciente("12.818.219-5","Omar Garcia",49,"Bronquitis","17/01/2024"),
                                                                                                                                     new Paciente("13.919.876-1","Fabian Rodriguez",39,"Neumotitis","04/02/2024"))));
        medicos.add(new Medico("11.281.555-7", "Osman Pincsower", 45,"Pediatria",Arrays.asList(new Paciente("24.679.786-1","Antonio Pavez",9,"Bronquitis","12/01/2024"),
                                                                                                                                     new Paciente("24.779.786-2","Omar Garcia",49,"Bronquitis","17/01/2024"),
                                                                                                                                     new Paciente("24.879.786-3","Felipe Zabala",39,"Neumotitis","05/02/2024"),
                                                                                                                                     new Paciente("24.979.786-4","Andres Nuñez",39,"Amigdalitis","06/02/2024"),
                                                                                                                                     new Paciente("24.079.786-5","Cristian Ampuero",39,"Rinofaringitis","07/02/2024"))));

    }

    @GetMapping("/medicos")
    public List<Medico> getMedicos() {
        return medicos;
    }

    

    @GetMapping("/medicos/{rut}")
    public Medico getMedicoByRut(@PathVariable String rut) {
        for (Medico medico : medicos) {
            if (medico.getRutMed().equals(rut)) {
                return medico;
            }
        }
        return null;
    }



    //Metodo que permite traer al medico y la cantidad de atenciones tiene
    @GetMapping("/medicos/{rut}/info")
    public String getMedicoInfo(@PathVariable String rut) {
        for (Medico medico : medicos) {
            if (medico.getRutMed().equals(rut)) {
                return "Nombre del médico: " + medico.getNombre() + ", Cantidad de pacientes atendidos: " + medico.getPacientes().size();
            }
        }
        return "Médico no encontrado";
    }

    //Metodo que permite traer a todos lo medicos con la cantidad de atenciones que tienen
    @GetMapping("/medicos/info")
    public List<String> getMedicosInfo() {
    List<String> medicosInfo = new ArrayList<>();
    for (Medico medico : medicos) {
        medicosInfo.add("Nombre del médico: " + medico.getNombre() + ", Cantidad de pacientes atendidos: " + medico.getPacientes().size());
    }
    return medicosInfo;
}



}

