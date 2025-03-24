package com.exame.exame.service;

import com.exame.exame.model.Paciente;
import com.exame.exame.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado.");
        }
        pacienteAtualizado.setId(id);
        return pacienteRepository.save(pacienteAtualizado);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
