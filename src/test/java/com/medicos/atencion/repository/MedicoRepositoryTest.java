package com.medicos.atencion.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.medicos.atencion.model.Medico;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    public void guardarMedicoTest(){
        //arrange
        Medico medico = new Medico();
        medico.setNombre("John Doe");

        //Act
        Medico resultado = medicoRepository.save(medico);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("John Doe", resultado.getNombre());
    }

}
