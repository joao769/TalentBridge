package main.java.com.example.service;

import java.sql.SQLException;

import main.java.com.example.model.Candidato;
import main.java.com.example.model.Empresa;
import main.java.com.example.model.Vaga;
import main.java.com.example.repository.VagaRepository;

public class VagaService {

	private VagaRepository vagaRepository;

    public VagaService() {
        this.vagaRepository = new VagaRepository();
    }

    public void adicionarVaga(Vaga vaga, int empresa_id) {
        try {
            vagaRepository.salvar(vaga, empresa_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void removerVaga(int vagaId) {
        try {
            vagaRepository.removerVaga(vagaId);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void addCandidato(int vagaId, Candidato candidato) {
        try {
            vagaRepository.adicionarCandidato(vagaId, candidato);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void delCandidato(int vagaId, int candidatoId) {
        try {
            vagaRepository.deletarCandidato(vagaId, candidatoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void listarCadindatosNasVagas(int vagaId) {
        try {
            vagaRepository.listarCandidatosAplicados(vagaId);
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
}
