package com.leilao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // Um avaliador pode fazer várias avaliações
    @JoinColumn(name = "avaliador_id", nullable = false)
    private Usuario avaliador;

    @ManyToOne(optional = false) // Um avaliado pode receber várias avaliações
    @JoinColumn(name = "avaliado_id", nullable = false)
    private Usuario avaliado;

    @Column(nullable = false)
    private int pontuacao; // 1-5 estrelas

    @Column(length = 500)
    private String comentario;

    @Column(name = "data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "leilao_id", nullable = false)
    private Leilao leilao; // Leilão relacionado


    public Avaliacao() {}

    public Avaliacao(Usuario avaliador, Usuario avaliado, int pontuacao, String comentario, LocalDate dataAvaliacao, Leilao leilao) {
        this.avaliador = avaliador;
        this.avaliado = avaliado;
        this.pontuacao = pontuacao;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
        this.leilao = leilao;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getAvaliador() { return avaliador; }
    public void setAvaliador(String avaliador) { this.avaliador = avaliador; }

    public Usuario getAvaliado() { return avaliado; }
    public void setAvaliado(String avaliado) { this.avaliado = avaliado; }

    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDate dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }

    public Leilao getLeilao() { return leilao; }
    public void setLeilao(String leilao) { this.leilao = leilao; }
}
