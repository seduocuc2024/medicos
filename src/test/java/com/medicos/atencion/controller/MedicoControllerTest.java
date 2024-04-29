package com.medicos.atencion.controller;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.medicos.atencion.model.Medico;
import com.medicos.atencion.service.MedicoService;
import com.medicos.atencion.service.MedicoServicelmpl;




@WebMvcTest(MedicoControllerTest.class)
public class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicoServicelmpl medicoServicioMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        //Arrange
        //Creacion de medicos
        Medico  medico1 = new Medico();
        medico1.setNombre("John");
        medico1.setId(1L);
        Medico medico2 = new Medico();
        medico2.setNombre("Doe");
        medico2.setId(2L);
        List<Medico> medicos = Arrays.asList(medico1,medico2);
        when(medicoServicioMock.getAllMedicos()).thenReturn(medicos);
        
        //Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/medicos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombre", Matchers.is("Doe")));

    }
}
