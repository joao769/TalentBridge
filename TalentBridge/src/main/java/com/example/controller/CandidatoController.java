package main.java.com.example.controller;

import java.sql.SQLException;

import main.java.com.example.model.Candidato;
import main.java.com.example.service.CandidatoService;

public class CandidatoController {

    private CandidatoService candidatoService;    
    
    public CandidatoController() {
        this.candidatoService = new CandidatoService();
    }
    
    public void adicionarCandidato(String nome, String cpf, String endereco, String telefone, String email, String senha) {
        Candidato cansidato = new Candidato(0, nome, cpf, endereco, telefone, email, senha); 
        candidatoService.adicionarCandidato(cansidato);
    }

    public int fazerLogin(String email, String senha) throws SQLException {
        return candidatoService.fazerLogin(email, senha);  
    }
    
    public void editarPerfil(int candidatoLogadoId, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
        candidatoService.editarPerfil(candidatoLogadoId, novoNome, novoEndereco, novoTelefone, novoEmail);
    }  
}
