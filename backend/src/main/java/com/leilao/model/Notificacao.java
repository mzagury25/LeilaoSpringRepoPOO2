package com.leilao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String mensagem;

    @Column(name = "data_envio", nullable = false)
    private LocalDate dataEnvio;

    @Column(nullable = false)
    private boolean lida;

    /*
    // Caso queira implementar tipos de notificação:
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_notificacao")
    private TipoNotificacao tipo;
    */


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leilao_id")
    private Leilao leilaoRelacionado;

    public Notificacao() {

    }

    public Notificacao(Usuario usuario, String mensagem, LocalDate dataEnvio, boolean lida, Leilao leilaoRelacionado) {
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
        this.lida = lida;
        this.leilaoRelacionado = leilaoRelacionado;
    }



    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public Leilao getLeilaoRelacionado() {
        return leilaoRelacionado;
    }

    public void setLeilaoRelacionado(Leilao leilaoRelacionado) {
        this.leilaoRelacionado = leilaoRelacionado;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notificacao)) return false;
        Notificacao that = (Notificacao) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
