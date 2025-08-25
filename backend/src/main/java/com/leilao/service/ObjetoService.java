package com.leilao.service;

import com.leilao.model.Objeto;
import com.leilao.repository.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetoService {

    private final ObjetoRepository objetoRepository;

    public ObjetoService(ObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
    }

    public List<Objeto> listarTodos() {
        return objetoRepository.findAll();
    }

    public Optional<Objeto> buscarPorId(Integer id) {
        return objetoRepository.findById(id);
    }

    public Objeto salvar(Objeto objeto) {
        return objetoRepository.save(objeto);
    }

    public void deletar(Integer id) {
        objetoRepository.deleteById(id);
    }

    public boolean existePorId(Integer id) {
        return objetoRepository.existsById(id);
    }
}
