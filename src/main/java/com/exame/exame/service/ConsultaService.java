package com.exame.exame.service;

import com.exame.exame.model.Consulta;
import com.exame.exame.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta agendarConsulta(Consulta consulta) {
        List<Consulta> consultasNoHorario = consultaRepository.findByPacienteAndDataAndHorario(
                consulta.getPaciente(), consulta.getData(), consulta.getHorario()
        );

        if (!consultasNoHorario.isEmpty()) {
            throw new RuntimeException("Erro: Paciente já tem uma consulta agendada neste horário.");
        }

        consulta.setStatus("Agendada");

        return consultaRepository.save(consulta);
    }

    public void cancelarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada."));
        consulta.setStatus("Cancelada");
        consultaRepository.save(consulta);
    }

    public void realizarConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada."));
        consulta.setStatus("Realizada");
        consultaRepository.save(consulta);
    }
}