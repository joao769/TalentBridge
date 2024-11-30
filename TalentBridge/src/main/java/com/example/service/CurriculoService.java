package main.java.com.example.service;

import main.java.com.example.model.Curriculo;
import main.java.com.example.repository.CurriculoRepository;
import java.sql.SQLException;

public class CurriculoService {

    private final CurriculoRepository curriculoRepository;

    public CurriculoService() {
        this.curriculoRepository = new CurriculoRepository();
    }
    
    public void adicionarCurriculo(int candidatoId, String experienciaProfissional, String formacaoAcademica, String habilidades) {
    	try {
            curriculoRepository.adicionarCurriculo(candidatoId, experienciaProfissional, formacaoAcademica, habilidades);
    	} catch (SQLException e) {
            System.err.println("Erro ao adicionar currículo no repositório: " + e.getMessage());
        }
    }

    public boolean existeCurriculo(int candidatoId) {
    	try {
            return curriculoRepository.existeCurriculo(candidatoId);
    	} catch (SQLException e) {
            System.err.println("Erro ao verificar se existe currículo no repositório: " + e.getMessage());
        }
		return false;
    }

    public void atualizarCurriculo(int candidatoId, String experienciaProfissional, String formacaoAcademica, String habilidades) {
    	try {
            curriculoRepository.atualizarCurriculo(candidatoId, experienciaProfissional, formacaoAcademica, habilidades);
    	} catch (SQLException e) {
            System.err.println("Erro ao atualizar currículo no repositório: " + e.getMessage());
        }
    }

    public Curriculo buscarCurriculo(int candidatoId) {
    	try {
            return curriculoRepository.buscarCurriculoPorCandidato(candidatoId);
    	} catch (SQLException e) {
            System.err.println("Erro ao buscar currículo no repositório: " + e.getMessage());
        }
		return null;
    }
}
