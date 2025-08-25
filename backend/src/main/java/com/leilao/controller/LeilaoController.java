package com.leilao.controller;

import com.leilao.model.Leilao;
import com.leilao.service.LeilaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leiloes")
public class LeilaoController {

    private final LeilaoService leilaoService;

    @Autowired
    public LeilaoController(LeilaoService leilaoService) {
        this.leilaoService = leilaoService;
    }

    @GetMapping
    public List<Leilao> listarTodos() {
        return leilaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leilao> buscarPorId(@PathVariable Long id) {
        return leilaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Leilao> criarLeilao(@RequestBody Leilao leilao) {
        Leilao criado = leilaoService.criarLeilao(leilao);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leilao> atualizarLeilao(@PathVariable Long id, @RequestBody Leilao leilaoAtualizado) {
        try {
            Leilao atualizado = leilaoService.atualizarLeilao(id, leilaoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLeilao(@PathVariable Long id) {
        leilaoService.deletarLeilao(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para atualizar estado do leilão com data atual passada como parâmetro
    @PutMapping("/{id}/atualizar-estado")
    public ResponseEntity<Void> atualizarEstado(@PathVariable Long id,
                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataAtual) {
        leilaoService.atualizarEstadoLeilao(id, dataAtual);
        return ResponseEntity.ok().build();
    }
}
