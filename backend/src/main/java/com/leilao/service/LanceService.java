package com.leilao.service;

import com.leilao.model.Lance;
import com.leilao.repository.LanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanceService {

    private final LanceRepository lanceRepository;

    public LanceService(LanceRepository lanceRepository) {
        this.lanceRepository = lanceRepository;
    }

    public Lance salvar(Lance lance) {
        return lanceRepository.save(lance);
    }

    public List<Lance> listarTodos() {
        return lanceRepository.findAll();
    }

    public Optional<Lance> buscarPorId(Long id) {
        return lanceRepository.findById(id);
    }

    public Optional<Lance> atualizar(Long id, Lance lanceAtualizado) {
        return lanceRepository.findById(id).map(lance -> {
            lance.setParticipante(lanceAtualizado.getParticipante());
            lance.setValor(lanceAtualizado.getValor());
            lance.setData(lanceAtualizado.getData());
            lance.setLeilao(lanceAtualizado.getLeilao());
            return lanceRepository.save(lance);
        });
    }

    public boolean deletar(Long id) {
        return lanceRepository.findById(id).map(lance -> {
            lanceRepository.delete(lance);
            return true;
        }).orElse(false);
    }
}
