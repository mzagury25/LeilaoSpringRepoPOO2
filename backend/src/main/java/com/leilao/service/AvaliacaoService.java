package com.leilao.service;

import com.leilao.model.Avaliacao;
import com.leilao.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }


    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }


    public List<Avaliacao> listarTodos() {
        return avaliacaoRepository.findAll();
    }


    public Optional<Avaliacao> buscarPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }


    public Optional<Avaliacao> atualizar(Long id, Avaliacao avaliacaoAtualizada) {
        return avaliacaoRepository.findById(id).map(avaliacao -> {
            avaliacao.setAvaliador(avaliacaoAtualizada.getAvaliador());
            avaliacao.setAvaliado(avaliacaoAtualizada.getAvaliado());
            avaliacao.setPontuacao(avaliacaoAtualizada.getPontuacao());
            avaliacao.setComentario(avaliacaoAtualizada.getComentario());
            avaliacao.setDataAvaliacao(avaliacaoAtualizada.getDataAvaliacao());
            avaliacao.setLeilao(avaliacaoAtualizada.getLeilao());
            return avaliacaoRepository.save(avaliacao);
        });
    }


    public boolean deletar(Long id) {
        return avaliacaoRepository.findById(id).map(avaliacao -> {
            avaliacaoRepository.delete(avaliacao);
            return true;
        }).orElse(false);
    }
}
