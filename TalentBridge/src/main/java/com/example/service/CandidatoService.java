package main.java.com.example.service;

import java.sql.SQLException;
import java.util.List;

import main.java.com.example.model.Candidato;
import main.java.com.example.repository.CandidatoRepository;

public class CandidatoService {

    private CandidatoRepository candidatoRepository;

    // Construtor
    public CandidatoService() {
        this.candidatoRepository = new CandidatoRepository();
    }

    // Método para adicionar um candidato
    public void addCandidato(Candidato candidato) {
        try {
            candidatoRepository.save(candidato);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os candidatos
    public List<Candidato> listCandidatos() {
        try {
            return candidatoRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para excluir um candidato
    public void deleteCandidato(int id) {
        try {
            candidatoRepository.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um candidato pelo id
    public Candidato getCandidatoById(int id) {
        try {
            return candidatoRepository.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizarCurriculoById(int id, String novoCurriculo) {
        try {
            candidatoRepository.atualizarCurriculo(id, novoCurriculo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editarPerfilById(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) {
        try {
            candidatoRepository.editarPerfil(id, novoNome, novoEndereco, novoTelefone, novoEmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
