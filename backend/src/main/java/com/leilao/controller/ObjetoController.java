package com.leilao.controller;

import com.leilao.model.Objeto;
import com.leilao.service.ObjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetos")
public class ObjetoController {

    private final ObjetoService objetoService;

    public ObjetoController(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    @GetMapping
    public ResponseEntity<List<Objeto>> listarTodos() {
        List<Objeto> objetos = objetoService.listarTodos();
        return ResponseEntity.ok(objetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Objeto> buscarPorId(@PathVariable Integer id) {
        return objetoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Objeto> criar(@RequestBody Objeto objeto) {
        Objeto salvo = objetoService.salvar(objeto);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Objeto> atualizar(@PathVariable Integer id, @RequestBody Objeto objeto) {
        if (!objetoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        objeto.setId(id);
        Objeto atualizado = objetoService.salvar(objeto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!objetoService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        objetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
