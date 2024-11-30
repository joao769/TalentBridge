package main.java.com.example.service;

import java.sql.SQLException;

import main.java.com.example.model.Candidato;
import main.java.com.example.repository.CandidatoRepository;

public class CandidatoService {

    private CandidatoRepository candidatoRepository;

    public CandidatoService() {
        this.candidatoRepository = new CandidatoRepository();
    }

    public void adicionarCandidato(Candidato candidato) {
        try {
            candidatoRepository.adicionarCandidato(candidato);
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar candidato no repositório: " + e.getMessage());
        }
    }

    public int fazerLogin(String email, String senha) {
        try {
            Candidato candidato = candidatoRepository.buscarPorEmail(email);
            if (candidato != null && senha.equals(candidato.getSenha())) { 
                return candidato.getId();  
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fazer login: " + e.getMessage());
        }
        return -1;  
    }

    public void editarPerfil(int candidatoLogadoId, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
        try {
            candidatoRepository.editarPerfil(candidatoLogadoId, novoNome, novoEndereco, novoTelefone, novoEmail);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar candidato: " + e.getMessage());
        }
    }
}
