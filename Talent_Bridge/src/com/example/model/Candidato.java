package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Candidato {
    private Long id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String curriculo;
    private String experienciaProfissional;
    private List<Vaga> vagasCandidatas; // Lista de vagas às quais o candidato se candidatou

    public Candidato() {
        this.vagasCandidatas = new ArrayList<>();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public String getExperienciaProfissional() {
        return experienciaProfissional;
    }

    public void setExperienciaProfissional(String experienciaProfissional) {
        this.experienciaProfissional = experienciaProfissional;
    }

    public List<Vaga> getVagasCandidatas() {
        return vagasCandidatas;
    }

    public void atualizarCurriculo(String novoCurriculo) {
        if (novoCurriculo == null || novoCurriculo.isEmpty()) {
            throw new IllegalArgumentException("Currículo não pode ser vazio.");
        }
        this.curriculo = novoCurriculo;
        System.out.println("Currículo atualizado com sucesso!");
    }

    public void editarPerfil(String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
        if (novoNome == null || novoNome.isEmpty() || novoEmail == null || novoEmail.isEmpty()) {
            throw new IllegalArgumentException("Nome e email não podem ser vazios.");
        }
        this.nome = novoNome;
        this.endereco = novoEndereco;
        this.telefone = novoTelefone;
        this.email = novoEmail;
        System.out.println("Perfil editado com sucesso!");
    }

    public void visualizarVagasProximas() {
        // Lógica de visualização de vagas próximas (a ser implementada)
        System.out.println("Visualizando vagas próximas... (lógica não implementada)");
    }

    public void seCandidatar(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("Vaga não pode ser nula.");
        }
        this.vagasCandidatas.add(vaga);
        System.out.println("Candidatura realizada com sucesso para a vaga: " + vaga.getNome());
    }

    public void retirarCandidatura(Vaga vaga) {
        if (vaga == null || !this.vagasCandidatas.contains(vaga)) {
            throw new IllegalArgumentException("Vaga não pode ser nula ou não está na lista de candidaturas.");
        }
        this.vagasCandidatas.remove(vaga);
        System.out.println("Candidatura retirada com sucesso para a vaga: " + vaga.getNome());
    }
}
