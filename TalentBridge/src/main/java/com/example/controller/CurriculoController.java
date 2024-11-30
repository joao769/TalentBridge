package main.java.com.example.controller;

import main.java.com.example.service.CurriculoService;
import main.java.com.example.model.Curriculo;

import java.sql.SQLException;

public class CurriculoController {

    private final CurriculoService curriculoService;

    public CurriculoController() {
        this.curriculoService = new CurriculoService();
    }

    public void adicionarCurriculo(int candidatoId, String experienciaProfissional, String formacaoAcademica, String habilidades) throws SQLException {
        curriculoService.adicionarCurriculo(candidatoId, experienciaProfissional, formacaoAcademica, habilidades);
    }

    public boolean verificarCurriculo(int candidatoId) throws SQLException {
        return curriculoService.existeCurriculo(candidatoId);
    }

    public void atualizarCurriculo(int candidatoLogadoId, String experienciaProfissional, String formacaoAcademica, String habilidades) {
        curriculoService.atualizarCurriculo(candidatoLogadoId, experienciaProfissional, formacaoAcademica, habilidades);
    }

    public void visualizarCurriculo(int candidatoLogadoId) {
        Curriculo curriculo = curriculoService.buscarCurriculo(candidatoLogadoId);
		if (curriculo != null) {
		    System.out.println("\nCurrículo:\n" + curriculo);
		} else {
		    System.out.println("Currículo não encontrado.");
		}
    }
}
