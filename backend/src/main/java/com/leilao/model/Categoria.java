package com.leilao.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nome;

    @Column(length = 500)
    private String descricao;

    private String icone;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leilao> leiloes;


    public Categoria() {}


    public Categoria(String nome, String descricao, String icone, List<Leilao> leiloes) {
        this.nome = nome;
        this.descricao = descricao;
        this.icone = icone;
        this.leiloes = leiloes;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getIcone() { return icone; }
    public void setIcone(String icone) { this.icone = icone; }

    public List<Leilao> getLeiloes() { return leiloes; }
    public void setLeiloes(List<Leilao> leiloes) { this.leiloes = leiloes; }
}
