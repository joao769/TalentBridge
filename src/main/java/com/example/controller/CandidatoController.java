package main.java.com.example.controller;


import java.util.List;

import main.java.com.example.model.Candidato;
import main.java.com.example.service.CandidatoService;

public class CandidatoController {

    private CandidatoService candidatoService;

    // Construtor
    public CandidatoController() {
        this.candidatoService = new CandidatoService();
    }

    // Método para adicionar um candidato
    public void addCandidato(Candidato candidato) {
        candidatoService.addCandidato(candidato);
    }

    // Método para listar todos os candidatos
    public List<Candidato> listCandidatos() {
        return candidatoService.listCandidatos();
    }

    // Método para excluir um candidato
    public void deleteCandidato(int id) {
        candidatoService.deleteCandidato(id);
    }

    // Método para buscar um candidato pelo id
    public Candidato getCandidatoById(int id) {
        return candidatoService.getCandidatoById(id);
    }
    
    public void atualizarCurriculoById(int id, String novoCurriculo) {
        candidatoService.atualizarCurriculoById(id, novoCurriculo);
    }
    
    public void editarPerfilById(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
        candidatoService.editarPerfilById(id, novoNome, novoEndereco, novoTelefone, novoEmail);
    }
    
}
