package com.leilao.service;

import com.leilao.model.Avaliacao;
import com.leilao.repository.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AvaliacaoService.class)
class AvaliacaoServiceTest {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AvaliacaoService avaliacaoService;

    private Avaliacao avaliacaoBase;

    @BeforeEach
    void setup() {
        avaliacaoBase = new Avaliacao();
        avaliacaoBase.setAvaliador("Morgana");
        avaliacaoBase.setAvaliado("Leiloeiro");
        avaliacaoBase.setPontuacao(5);
        avaliacaoBase.setComentario("Excelente experiência!");
        avaliacaoBase.setDataAvaliacao(LocalDate.now());
        avaliacaoBase.setLeilao("Leilão de Arte");
    }

    @Test
    void deveSalvarAvaliacao() {
        Avaliacao salvo = avaliacaoService.salvar(avaliacaoBase);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getAvaliador()).isEqualTo("Morgana");
    }

    @Test
    void deveListarAvaliacoes() {
        avaliacaoService.salvar(avaliacaoBase);

        assertThat(avaliacaoService.listarTodos()).hasSize(1);
    }

    @Test
    void deveBuscarPorId() {
        Avaliacao salvo = avaliacaoService.salvar(avaliacaoBase);

        Optional<Avaliacao> encontrado = avaliacaoService.buscarPorId(salvo.getId());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getAvaliador()).isEqualTo("Morgana");
    }

    @Test
    void deveAtualizarAvaliacao() {
        Avaliacao salvo = avaliacaoService.salvar(avaliacaoBase);

        Avaliacao atualizado = new Avaliacao();
        atualizado.setAvaliador("Outro usuário");
        atualizado.setAvaliado("Novo avaliado");
        atualizado.setPontuacao(3);
        atualizado.setComentario("Bom, mas poderia melhorar");
        atualizado.setDataAvaliacao(LocalDate.now());
        atualizado.setLeilao("Leilão de Carros");

        Optional<Avaliacao> resultado = avaliacaoService.atualizar(salvo.getId(), atualizado);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getAvaliador()).isEqualTo("Outro usuário");
        assertThat(resultado.get().getPontuacao()).isEqualTo(3);
    }

    @Test
    void deveDeletarAvaliacao() {
        Avaliacao salvo = avaliacaoService.salvar(avaliacaoBase);

        boolean deletado = avaliacaoService.deletar(salvo.getId());

        assertThat(deletado).isTrue();
        assertThat(avaliacaoRepository.findAll()).isEmpty();
    }
}
