package com.leilao.service;

import com.leilao.model.Leilao;
import com.leilao.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;

    @Autowired
    public LeilaoService(LeilaoRepository leilaoRepository) {
        this.leilaoRepository = leilaoRepository;
    }

    public List<Leilao> listarTodos() {
        return leilaoRepository.findAll();
    }

    public Optional<Leilao> buscarPorId(Long id) {
        return leilaoRepository.findById(id);
    }

    public Leilao criarLeilao(Leilao leilao) {
        // Estado inicial já é INATIVO pelo construtor
        return leilaoRepository.save(leilao);
    }

    public Leilao atualizarLeilao(Long id, Leilao leilaoAtualizado) {
        return leilaoRepository.findById(id).map(leilao -> {
            leilao.setNomeObjeto(leilaoAtualizado.getNomeObjeto());
            leilao.setLanceMinimo(leilaoAtualizado.getLanceMinimo());
            leilao.setDataInicio(leilaoAtualizado.getDataInicio());
            leilao.setDataTermino(leilaoAtualizado.getDataTermino());
            return leilaoRepository.save(leilao);
        }).orElseThrow(() -> new RuntimeException("Leilão não encontrado com id " + id));
    }

    public void deletarLeilao(Long id) {
        leilaoRepository.deleteById(id);
    }

    public void atualizarEstadoLeilao(Long id, LocalDate dataAtual) {
        leilaoRepository.findById(id).ifPresent(leilao -> {
            leilao.atualizarEstado(dataAtual);
            leilaoRepository.save(leilao);
        });
    }
}
