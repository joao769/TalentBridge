package main.java.com.example.service;

import java.sql.SQLException;
import java.util.List;

import main.java.com.example.model.Candidato;
import main.java.com.example.model.Vaga;
import main.java.com.example.repository.VagaRepository;

public class VagaService {

	private VagaRepository vagaRepository;

    public VagaService() {
        this.vagaRepository = new VagaRepository();
    }

    public void adicionarVaga(Vaga vaga) {
        try {
            vagaRepository.adicionarVaga(vaga);
        } catch (Exception e) {
            System.err.println("Erro ao adicionar vaga: " + e.getMessage());
        }
    }
    
    public void removerVaga(int vagaId) {
        try {
            vagaRepository.removerVaga(vagaId);
        } catch (SQLException e) {
            System.err.println("Erro ao remover vaga: " + e.getMessage());
        }    
    }
    
    public List<Vaga> listarVagasDisponiveis() {
        try {
            return vagaRepository.getVagasDisponiveis();
        } catch (SQLException e) {
            System.err.println("Erro ao listar vagas disponíveis: " + e.getMessage());
        }
        return null;
    }
    
    public List<Vaga> listarVagasPorEmpresa(int empresaId) {
        try {
            return vagaRepository.listarVagasPorEmpresa(empresaId);
        } catch (SQLException e) {
            System.err.println("Erro ao listar vagas por empresa: " + e.getMessage());
        }
        return null;
    }
    
    public void adicionarCandidato(int candidatoLogadoId, int vagaId) {
        try {
            vagaRepository.adicionarCandidato(candidatoLogadoId, vagaId);
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar candidato: " + e.getMessage());
        }
    }
    
    public void removerCandidatura(int candidatoLogadoId, int vagaId) {
        try {
            vagaRepository.removerCandidatura(candidatoLogadoId, vagaId);
        } catch (SQLException e) {
            System.err.println("Erro ao remover candidatura: " + e.getMessage());
        }
    }

    
    public List<Candidato> listarCandidatosAplicados(int vagaId) {
        try {
            return vagaRepository.listarCandidatosAplicados(vagaId);
        } catch (SQLException e) {
            System.err.println("Erro ao listar candidatos aplicados: " + e.getMessage());
        }
        return null;
    }
    
    public String consultarStatusVaga(int vagaId) throws SQLException {
    	try {
            return vagaRepository.consultarStatusVaga(vagaId);
    	} catch (SQLException e) {
            System.err.println("Erro ao consultar status da vaga: " + e.getMessage());
        }
		return null;
    }
    
    public String consultarStatusCandidatura(int candidatoLogadoId, int vagaId) {
        try {
            return vagaRepository.consultarStatusCandidatura(candidatoLogadoId, vagaId);
        } catch (SQLException e) {
            System.err.println("Erro ao consultar status da candidatura: " + e.getMessage());
        }
        return null;
    }
    
    public void selecionarCandidato(int vagaId, int candidatoId){
    	try {
            vagaRepository.selecionarCandidato(vagaId, candidatoId);
    	} catch (SQLException e) {
            System.err.println("Erro ao selecionar candidato: " + e.getMessage());
        }
    }
}
