package com.exame.exame.service;

import com.exame.exame.model.Medico;
import com.exame.exame.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado.");
        }
        medicoAtualizado.setId(id);
        return medicoRepository.save(medicoAtualizado);
    }

    public void deletar(Long id) {
        medicoRepository.deleteById(id);
    }
}
