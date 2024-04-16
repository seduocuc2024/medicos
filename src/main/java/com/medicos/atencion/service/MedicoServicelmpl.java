package com.medicos.atencion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicos.atencion.model.Medico;
import com.medicos.atencion.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServicelmpl implements MedicoService{

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> getMedicoById(Long id) {
        return medicoRepository.findById(id);
    }

    @Override
    public Medico createMedico(Medico medico){
        return medicoRepository.save(medico);
    }

    @Override
    public Medico updateMedico(Long id, Medico medico){
        if(medicoRepository.existsById(id)){
            medico.setId(id);
            return medicoRepository.save(medico);
        }else{
            return null;
        }
    }

    @Override
    public void deleteMedico(Long id){
        medicoRepository.deleteById(id);
    }
}
