package com.leilao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lances")
public class Lance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // Um participante pode ter vários lances
    @JoinColumn(name = "participante_id", nullable = false)
    private Usuario participante;

    @Column(nullable = false)
    private int valor;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "leilao_id", nullable = false)
    private Leilao leilao;


    public Lance() {}


    public Lance(Usuario participante, int valor, LocalDate data, Leilao leilao) {
        this.participante = participante;
        this.valor = valor;
        this.data = data;
        this.leilao = leilao;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getParticipante() { return participante; }
    public void setParticipante(Usuario participante) { this.participante = participante; }

    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public Leilao getLeilao() { return leilao; }
    public void setLeilao(Leilao leilao) { this.leilao = leilao; }
}
