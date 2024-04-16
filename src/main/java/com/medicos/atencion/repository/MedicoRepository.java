package com.medicos.atencion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.medicos.atencion.model.Medico;


public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
