package com.exame.exame.controller;

import com.exame.exame.model.Consulta;
import com.exame.exame.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> listarTodas() {
        return consultaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        Optional<Consulta> consulta = consultaService.buscarPorId(id);
        return consulta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> agendarConsulta(@RequestBody Consulta consulta) {
        try {
            return ResponseEntity.ok(consultaService.agendarConsulta(consulta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarConsulta(@PathVariable Long id) {
        try {
            consultaService.cancelarConsulta(id);
            return ResponseEntity.ok("Consulta cancelada com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
