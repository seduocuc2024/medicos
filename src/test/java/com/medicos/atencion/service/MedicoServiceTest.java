package com.medicos.atencion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medicos.atencion.model.Medico;
import com.medicos.atencion.repository.MedicoRepository;
import com.medicos.atencion.service.MedicoServicelmpl;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {
    @InjectMocks
    private MedicoServicelmpl medicoServicelmpl;

    @Mock
    private MedicoRepository medicoRepositorioMock;

    @Test
    public void guardarMedicoTest(){
        // Arrange
        Medico  medico = new Medico();
        medico.setNombre("Jose Rondon");

        when(medicoRepositorioMock.save(any())).thenReturn(medico);

        // Act
        Medico resultado = medicoServicelmpl.createMedico(medico);

        // Assert
        assertEquals("Jose Rondon", resultado.getNombre());
    }

    //Otras pruebas para el servicio
}
