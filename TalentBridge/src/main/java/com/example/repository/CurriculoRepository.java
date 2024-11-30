package main.java.com.example.repository;

import java.sql.*;
import main.java.com.example.database.DatabaseConnector;
import main.java.com.example.model.Curriculo;

public class CurriculoRepository {

    public void adicionarCurriculo(int candidatoId, String experienciaProfissional, String formacaoAcademica, String habilidades) throws SQLException {
        String query = "INSERT INTO curriculo (candidato_id, experiencia_profissional, formacao_academica, habilidades) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Verificação simples de dados antes de inserir
            if (experienciaProfissional == null || experienciaProfissional.isEmpty() ||
                formacaoAcademica == null || formacaoAcademica.isEmpty() ||
                habilidades == null || habilidades.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos do currículo devem ser preenchidos.");
            }

            stmt.setInt(1, candidatoId);
            stmt.setString(2, experienciaProfissional);
            stmt.setString(3, formacaoAcademica);
            stmt.setString(4, habilidades);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar currículo para o candidato " + candidatoId + ": " + e.getMessage());
            throw e;
        }
    }

    public boolean existeCurriculo(int candidatoId) throws SQLException {
        String query = "SELECT COUNT(*) FROM curriculo WHERE candidato_id = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, candidatoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    
    public void atualizarCurriculo(int candidatoId, String experienciaProfissional, String formacaoAcademica, String habilidades) throws SQLException {
        String query = "UPDATE curriculo SET experiencia_profissional = ?, formacao_academica = ?, habilidades = ? WHERE candidato_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Verificação simples de dados antes de atualizar
            if (experienciaProfissional == null || experienciaProfissional.isEmpty() ||
                formacaoAcademica == null || formacaoAcademica.isEmpty() ||
                habilidades == null || habilidades.isEmpty()) {
                throw new IllegalArgumentException("Todos os campos do currículo devem ser preenchidos.");
            }

            stmt.setString(1, experienciaProfissional);
            stmt.setString(2, formacaoAcademica);
            stmt.setString(3, habilidades);
            stmt.setInt(4, candidatoId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar currículo para o candidato " + candidatoId + ": " + e.getMessage());
            throw e;
        }
    }

    public Curriculo buscarCurriculoPorCandidato(int candidatoId) throws SQLException {
        String query = "SELECT * FROM curriculo WHERE candidato_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, candidatoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Curriculo(
                    candidatoId, 
                    rs.getString("experiencia_profissional"),
                    rs.getString("formacao_academica"),
                    rs.getString("habilidades")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar currículo para o candidato " + candidatoId + ": " + e.getMessage());
            throw e;
        }
        return null;
    }
}
