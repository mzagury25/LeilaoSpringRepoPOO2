package com.leilao.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(name = "data_nascimento")
    private String dataNascimento;


    public Usuario() {
    }

    public Usuario(String nomeUsuario, String cpf, String email, String dataNascimento) {
        this.nomeUsuario = nomeUsuario;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Usuario(String nomeUsuario) {
        this(nomeUsuario, "", "", "");
    }



    public Long getId() {
        return id;
    }

    public void setId(Long usuarioId) {
        this.id = usuarioId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(cpf, usuario.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}