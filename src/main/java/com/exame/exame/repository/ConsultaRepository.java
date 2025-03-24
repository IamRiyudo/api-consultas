    package com.exame.exame.repository;

    import com.exame.exame.model.Consulta;
    import com.exame.exame.model.Paciente;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.time.LocalDate;
    import java.util.List;

    public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
        List<Consulta> findByPacienteAndDataAndHorario(Paciente paciente, LocalDate data, String horario);
    }
