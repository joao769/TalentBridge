package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Vaga {
    private Long id;
    private String nome;
    private String descricao;
    private double salario;
    private String requisitos;
    private String endereco; // Referência para Geolocalização
    private String status;
    private Long empresaId;
    private List<Candidato> candidatos; // Lista de candidatos

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    // Construtor sem parâmetros
    public Vaga() {
        this.status = "Disponível"; // Status padrão
        this.candidatos = new ArrayList<>(); // Inicializa a lista de candidatos
    }

    // Construtor com parâmetros
    public Vaga(Long id, String nome, String descricao, double salario, String requisitos, String endereco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        setSalario(salario);
        this.requisitos = requisitos;
        this.endereco = endereco;
        this.status = "Disponível"; // Status padrão
        this.candidatos = new ArrayList<>(); // Inicializa a lista de candidatos
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo.");
        }
        this.salario = salario;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status não pode ser vazio.");
        }
        this.status = status;
        System.out.println("Status da vaga atualizado para: " + status);
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void adicionarCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException("Candidato não pode ser nulo.");
        }
        if (candidatos.contains(candidato)) {
            throw new IllegalArgumentException("Candidato já se candidatou a esta vaga.");
        }
        this.candidatos.add(candidato);
        System.out.println("Candidato " + candidato.getNome() + " adicionado à vaga: " + this.nome);
    }

    public void removerCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException("Candidato não pode ser nulo.");
        }
        if (!candidatos.remove(candidato)) {
            throw new IllegalArgumentException("Candidato não encontrado nesta vaga.");
        }
        System.out.println("Candidato " + candidato.getNome() + " removido da vaga: " + this.nome);
    }

    // Métodos
    public void definirStatus(String status) {
        setStatus(status); // Usando o setter para aproveitar a mensagem
    }

    public void atualizarRequisitos(String novosRequisitos) {
        this.requisitos = novosRequisitos;
        System.out.println("Requisitos da vaga atualizados.");
    }

    public void associarEmpresa(Empresa empresa) {
        if (empresa == null) {
            throw new IllegalArgumentException("Empresa não pode ser nula.");
        }
        empresa.cadastrarVaga(this);
        System.out.println("Vaga associada à empresa: " + empresa.getNome());
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", salario=" + salario +
                ", requisitos='" + requisitos + '\'' +
                ", endereco='" + endereco + '\'' +
                ", status='" + status + '\'' +
                ", candidatos=" + candidatos.size() + // Para mostrar quantos candidatos foram cadastrados
                '}';
    }
}