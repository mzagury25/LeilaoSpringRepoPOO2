package com.leilao.controller;

import com.leilao.model.Lance;
import com.leilao.service.LanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lances")
public class LanceController {

    private final LanceService lanceService;

    public LanceController(LanceService lanceService) {
        this.lanceService = lanceService;
    }


    @PostMapping
    public ResponseEntity<Lance> criarLance(@RequestBody Lance lance) {
        Lance novoLance = lanceService.salvar(lance);
        return ResponseEntity.ok(novoLance);
    }


    @GetMapping
    public ResponseEntity<List<Lance>> listarLances() {
        return ResponseEntity.ok(lanceService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Lance> buscarPorId(@PathVariable Long id) {
        return lanceService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Lance> atualizarLance(@PathVariable Long id, @RequestBody Lance lanceAtualizado) {
        return lanceService.atualizar(id, lanceAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLance(@PathVariable Long id) {
        if (lanceService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
