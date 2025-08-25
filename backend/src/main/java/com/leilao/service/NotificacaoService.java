package com.leilao.service;

import com.leilao.model.Notificacao;
import com.leilao.model.Usuario;
import com.leilao.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public List<Notificacao> listarPorUsuario(Usuario usuario) {
        return notificacaoRepository.findByUsuarioOrderByDataEnvioDesc(usuario);
    }

    public List<Notificacao> listarNaoLidasPorUsuario(Usuario usuario) {
        return notificacaoRepository.findByUsuarioAndLidaFalseOrderByDataEnvioDesc(usuario);
    }

    public Optional<Notificacao> buscarPorId(Long id) {
        return notificacaoRepository.findById(id);
    }

    public Notificacao salvar(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public void deletar(Long id) {
        notificacaoRepository.deleteById(id);
    }

    public Notificacao marcarComoLida(Long id) {
        Optional<Notificacao> opt = notificacaoRepository.findById(id);
        if (opt.isPresent()) {
            Notificacao notificacao = opt.get();
            notificacao.setLida(true);
            return notificacaoRepository.save(notificacao);
        }
        throw new RuntimeException("Notificação não encontrada com id: " + id);
    }
}
