package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;
    private String telefone;
    private String email;
    private List<Vaga> listaDeVagas;
    private List<Candidato> listaDeCandidatos; // Lista de candidatos

    public Empresa() {
        this.listaDeVagas = new ArrayList<>();
        this.listaDeCandidatos = new ArrayList<>(); // Inicializando a lista de candidatos
    }

    // Getters e Setters
    public List<Vaga> getListaDeVagas() {
        return listaDeVagas;
    }

    public void setListaDeVagas(List<Vaga> listaDeVagas) {
        this.listaDeVagas = listaDeVagas;
    }

    public List<Candidato> getListaDeCandidatos() { // Método getter para a lista de candidatos
        return listaDeCandidatos;
    }

    public void setListaDeCandidatos(List<Candidato> listaDeCandidatos) {
        this.listaDeCandidatos = listaDeCandidatos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void cadastrarVaga(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("Vaga não pode ser nula.");
        }
        if (this.listaDeVagas.contains(vaga)) {
            throw new IllegalArgumentException("A vaga já está cadastrada.");
        }
        this.listaDeVagas.add(vaga);
        System.out.println("Vaga cadastrada com sucesso!");
    }

    public void editarVaga(Long vagaId, Vaga vagaAtualizada) {
        if (vagaId == null || vagaAtualizada == null) {
            throw new IllegalArgumentException("ID ou vaga não podem ser nulos.");
        }
        Vaga vaga = this.listaDeVagas.stream()
                .filter(v -> v.getId().equals(vagaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada."));

        // Atualiza os atributos da vaga conforme necessário
        vaga.setNome(vagaAtualizada.getNome());
        vaga.setDescricao(vagaAtualizada.getDescricao());
        vaga.setSalario(vagaAtualizada.getSalario());
        vaga.setRequisitos(vagaAtualizada.getRequisitos());
        vaga.setEndereco(vagaAtualizada.getEndereco());
        vaga.setStatus(vagaAtualizada.getStatus());
        System.out.println("Vaga editada com sucesso!");
    }

    public void removerVaga(Long vagaId) {
        Vaga vaga = this.listaDeVagas.stream()
                .filter(v -> v.getId().equals(vagaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada."));
        this.listaDeVagas.remove(vaga);
        System.out.println("Vaga removida com sucesso!");
    }

    public void visualizarCandidatos() {
        if (listaDeCandidatos.isEmpty()) {
            System.out.println("Nenhum candidato cadastrado.");
        } else {
            System.out.println("Candidatos cadastrados:");
            for (Candidato candidato : listaDeCandidatos) {
                System.out.println("- " + candidato.getNome()); // Supondo que Candidato tenha um método getNome()
            }
        }
    }

    public void adicionarCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new IllegalArgumentException("Candidato não pode ser nulo.");
        }
        this.listaDeCandidatos.add(candidato);
        System.out.println("Candidato adicionado com sucesso!");
    }

    public void cadastrarCandidatoParaVaga(Long vagaId, Candidato candidato) {
        if (vagaId == null || candidato == null) {
            throw new IllegalArgumentException("ID da vaga ou candidato não pode ser nulo.");
        }

        // Verifica se a vaga existe
        Vaga vaga = this.listaDeVagas.stream()
                .filter(v -> v.getId().equals(vagaId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada."));

        // Adiciona o candidato à vaga
        vaga.adicionarCandidato(candidato);
        // Adiciona o candidato à lista de candidatos da empresa
        this.adicionarCandidato(candidato);
    }
}
