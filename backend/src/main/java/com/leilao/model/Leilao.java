package com.leilao.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "leilao")
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_objeto", nullable = false)
    private String nomeObjeto;

    @Column(name = "lance_minimo", nullable = false)
    private int lanceMinimo;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_termino", nullable = false)
    private LocalDate dataTermino;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_leilao", nullable = false)
    private EstadoLeilao estadoLeilao = EstadoLeilao.INATIVO;

    // ADICIONADO: Relacionamento com Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "leilao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Lance> lances = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "leilao_participantes",
            joinColumns = @JoinColumn(name = "leilao_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> participantes = new HashSet<>();

    public Leilao() {

    }

    public Leilao(String nomeObjeto, int lanceMinimo, LocalDate dataInicio, LocalDate dataTermino) {
        if (dataInicio.isAfter(dataTermino)) {
            throw new IllegalArgumentException("A data de início deve ser anterior ou igual à data de término.");
        }
        this.nomeObjeto = nomeObjeto;
        this.lanceMinimo = lanceMinimo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.estadoLeilao = EstadoLeilao.INATIVO;
    }

    public void atualizarEstado(LocalDate dataAtual) {
        if (estadoLeilao == EstadoLeilao.INATIVO && (dataAtual.isEqual(dataInicio) || dataAtual.isAfter(dataInicio))) {
            estadoLeilao = EstadoLeilao.ABERTO;
        } else if (estadoLeilao == EstadoLeilao.ABERTO && dataAtual.isAfter(dataTermino)) {
            estadoLeilao = lances.isEmpty() ? EstadoLeilao.EXPIRADO : EstadoLeilao.FINALIZADO;
        }
    }

    public boolean registrarLance(Lance novoLance) {
        if (estadoLeilao != EstadoLeilao.ABERTO) return false;
        if (novoLance.getValor() < lanceMinimo) return false;

        if (!lances.isEmpty()) {
            Lance ultimo = lances.get(lances.size() - 1);
            boolean mesmoUsuario = ultimo.getParticipante().equals(novoLance.getParticipante());
            boolean valorInvalido = novoLance.getValor() <= ultimo.getValor();
            if (mesmoUsuario || valorInvalido) return false;
        }

        novoLance.setLeilao(this);
        lances.add(novoLance);
        participantes.add(novoLance.getParticipante());
        return true;
    }

    public Optional<Usuario> getGanhador() {
        if (estadoLeilao != EstadoLeilao.FINALIZADO) {
            return Optional.empty();
        }

        Optional<Lance> maiorLance = lances.stream()
                .max(Comparator.comparingInt(Lance::getValor));

        maiorLance.ifPresent(l -> System.out.println(
                String.format("Parabéns ao ganhador: %s! Com o lance de R$%d no objeto \"%s\".",
                        l.getParticipante().getNomeUsuario(),
                        l.getValor(),
                        nomeObjeto)
        ));

        return maiorLance.map(Lance::getParticipante);
    }

    public OptionalInt getMaiorLance() {
        return lances.stream().mapToInt(Lance::getValor).max();
    }

    public OptionalInt getMenorLance() {
        return lances.stream().mapToInt(Lance::getValor).min();
    }

    public List<Lance> filtrarLancesPorData(LocalDate dataInicial, LocalDate dataFinal) {
        return lances.stream()
                .filter(l -> !l.getData().isBefore(dataInicial) && !l.getData().isAfter(dataFinal))
                .collect(Collectors.toList());
    }

    public boolean podeRemoverParticipante(Usuario u) {
        return lances.stream().noneMatch(l -> l.getParticipante().equals(u));
    }

    public List<Lance> getLancesOrdenados() {
        return lances.stream()
                .sorted(Comparator.comparingInt(Lance::getValor).reversed())
                .collect(Collectors.toList());
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getNomeObjeto() {
        return nomeObjeto;
    }

    public void setNomeObjeto(String nomeObjeto) {
        this.nomeObjeto = nomeObjeto;
    }

    public int getLanceMinimo() {
        return lanceMinimo;
    }

    public void setLanceMinimo(int lanceMinimo) {
        this.lanceMinimo = lanceMinimo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public EstadoLeilao getEstadoLeilao() {
        return estadoLeilao;
    }

    public void setEstadoLeilao(EstadoLeilao estadoLeilao) {
        this.estadoLeilao = estadoLeilao;
    }

    // ADICIONADO: Getter e Setter para Categoria
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

    public Set<Usuario> getParticipantes() {
        return Collections.unmodifiableSet(participantes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leilao)) return false;
        Leilao leilao = (Leilao) o;
        return Objects.equals(id, leilao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}