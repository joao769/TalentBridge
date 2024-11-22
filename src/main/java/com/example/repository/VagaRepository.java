package main.java.com.example.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.example.database.DatabaseConnector;
import main.java.com.example.model.Candidato;
import main.java.com.example.model.Empresa;
import main.java.com.example.model.Vaga;

public class VagaRepository {

    // Método para salvar uma vaga
    public void salvar(Vaga vaga, int empresa_id) throws SQLException {
        String query = "INSERT INTO vaga (descricao, cargo, empresa_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, vaga.getDescricao());
            stmt.setString(2, vaga.getCargo());
            stmt.setInt(3, vaga.getEmpresaId());

            // Executa a inserção
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar vaga: " + e.getMessage());
            throw e; // Relança a exceção para tratamento externo, se necessário
        }
    }
    
    public void removerVaga(int vagaId) throws SQLException {
        String query = "DELETE FROM vaga WHERE id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vagaId); // Define o ID da vaga a ser removida
            stmt.executeUpdate(); // Executa a exclusão no banco de dados
            System.out.println("Vaga removida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao remover vaga: " + e.getMessage());
            throw e; // Propaga a exceção
        }
    }

    // Método para adicionar candidato à vaga
    public void adicionarCandidato(int vagaId, Candidato candidato) throws SQLException {
        String sql = "INSERT INTO vaga_candidato (vaga_id, candidato_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, vagaId); // Passando o ID da vaga
            stmt.setInt(2, candidato.getId());

            // Executando a inserção
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar candidato: " + e.getMessage());
            throw e;
        }
    }

    // Método para excluir um candidato da vaga
    public void deletarCandidato(int vagaId, int candidatoId) throws SQLException {
        String query = "DELETE FROM vaga_candidato WHERE vaga_id = ? AND candidato_id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vagaId); // Usando o ID da vaga
            stmt.setInt(2, candidatoId); // Usando o ID do candidato

            // Executa a exclusão
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar candidato: " + e.getMessage());
            throw e;
        }
    }

    // Método para listar os candidatos aplicados a uma vaga
    public List<Candidato> listarCandidatosAplicados(int vagaId) throws SQLException {
        List<Candidato> candidatos = new ArrayList<>();
        String sql = "SELECT c.id, c.nome, c.email FROM candidato c " +
                     "JOIN vaga_candidato vc ON c.id = vc.candidato_id WHERE vc.vaga_id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, vagaId); // Passando o ID da vaga

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Candidato candidato = new Candidato();
                    candidato.setId(rs.getInt("id"));
                    candidato.setNome(rs.getString("nome"));
                    candidato.setEmail(rs.getString("email"));
                    candidatos.add(candidato);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar candidatos: " + e.getMessage());
            throw e;
        }
        return candidatos;
    }
}
