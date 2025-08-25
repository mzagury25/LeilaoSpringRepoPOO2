package com.leilao.repository;

import com.leilao.model.Notificacao;
import com.leilao.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByUsuarioOrderByDataEnvioDesc(Usuario usuario);

    List<Notificacao> findByUsuarioAndLidaFalseOrderByDataEnvioDesc(Usuario usuario);
}
