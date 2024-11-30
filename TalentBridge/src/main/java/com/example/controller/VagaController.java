package main.java.com.example.controller;

import java.sql.SQLException;
import java.util.List;

import main.java.com.example.model.Candidato;
import main.java.com.example.model.Vaga;
import main.java.com.example.service.VagaService;

public class VagaController {

    private VagaService vagaService;

    public VagaController() {
        this.vagaService = new VagaService();
    }
	
    public void adicionarVaga(int empresaLogadaId, String titulo, String descricao, String cargo, String cargaHoraria, double salario, String status) {
        Vaga vaga = new Vaga(0, empresaLogadaId, titulo, descricao, cargo, cargaHoraria, salario, status);
        vagaService.adicionarVaga(vaga);
    }

    public void removerVaga(int vagaId) {
    	vagaService.removerVaga(vagaId); 
    }
    
    public List<Vaga> listarVagasDisponiveis() {
        return vagaService.listarVagasDisponiveis(); 
    }
    
    public List<Vaga> listarVagasPorEmpresa(int empresaId) {
        return vagaService.listarVagasPorEmpresa(empresaId); 
    }

    public void adicionarCandidato(int candidatoLogadoId, int vagaId) {
        vagaService.adicionarCandidato(candidatoLogadoId, vagaId); 
    }
    
    public String consultarStatusVaga(int vagaId) {
        try {
            return vagaService.consultarStatusVaga(vagaId);
        } catch (SQLException e) {
            System.err.println("Erro ao consultar status da vaga: " + e.getMessage());
            return "Erro ao consultar status";
        }
    }

    public void removerCandidatura(int candidatoLogadoId, int vagaId) {
        vagaService.removerCandidatura(candidatoLogadoId, vagaId); 
    }

    public List<Candidato> listarCandidatosAplicados(int vagaId) {
        return vagaService.listarCandidatosAplicados(vagaId); 
    }

    public String consultarStatusCandidatura(int candidatoLogadoId, int vagaId) {
        return vagaService.consultarStatusCandidatura(candidatoLogadoId, vagaId); 
    }
 
    public void selecionarCandidato(int vagaId, int candidatoId) {
        vagaService.selecionarCandidato(vagaId, candidatoId);
		System.out.println("\nCandidato selecionado com sucesso! Status da vaga atualizado.");
    }
}
