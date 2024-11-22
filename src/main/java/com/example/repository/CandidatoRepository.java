package main.java.com.example.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import main.java.com.example.database.DatabaseConnector;
import main.java.com.example.model.Candidato;

public class CandidatoRepository {

    // Método para salvar um candidato
    public void save(Candidato candidato) throws SQLException {
        String query = "INSERT INTO candidato (nome, cpf, endereco, telefone, email, curriculo, experiencia_profissional) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Definindo os valores dos parâmetros na query
            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getCpf());
            stmt.setString(3, candidato.getEndereco());
            stmt.setString(4, candidato.getTelefone());
            stmt.setString(5, candidato.getEmail());
            stmt.setString(6, candidato.getCurriculo());
            stmt.setString(7, candidato.getExperienciaProfissional());

            // Executa a inserção
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar candidato: " + e.getMessage());
            throw e; // Relança a exceção para tratamento externo, se necessário
        }
    }

    // Método para listar todos os candidatos
    public List<Candidato> getAll() throws SQLException {
        List<Candidato> candidatos = new ArrayList<>();
        String query = "SELECT * FROM candidato";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setId(rs.getInt("id"));
                candidato.setNome(rs.getString("nome"));
                candidato.setCpf(rs.getString("cpf"));
                candidato.setEndereco(rs.getString("endereco"));
                candidato.setTelefone(rs.getString("telefone"));
                candidato.setEmail(rs.getString("email"));
                candidato.setCurriculo(rs.getString("curriculo"));
                candidato.setExperienciaProfissional(rs.getString("experiencia_profissional"));
                candidatos.add(candidato);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar candidatos: " + e.getMessage());
            throw e;
        }
        return candidatos;
    }

    // Método para buscar um candidato pelo ID
    public Candidato getById(int id) throws SQLException {
        Candidato candidato = null;
        String query = "SELECT * FROM candidato WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id); // Corrigido para usar setInt para IDs
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    candidato = new Candidato();
                    candidato.setId(rs.getInt("id"));
                    candidato.setNome(rs.getString("nome"));
                    candidato.setCpf(rs.getString("cpf"));
                    candidato.setEndereco(rs.getString("endereco"));
                    candidato.setTelefone(rs.getString("telefone"));
                    candidato.setEmail(rs.getString("email"));
                    candidato.setCurriculo(rs.getString("curriculo"));
                    candidato.setExperienciaProfissional(rs.getString("experiencia_profissional"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar candidato por ID: " + e.getMessage());
            throw e;
        }
        return candidato;
    }

    // Método para excluir um candidato pelo ID
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM candidato WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id); // Corrigido para usar setInt para IDs
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Candidato com ID " + id + " foi excluído com sucesso.");
            } else {
                System.out.println("Nenhum candidato encontrado com ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir candidato: " + e.getMessage());
            throw e;
        }
    }

    // Método para atualizar o currículo de um candidato
    public void atualizarCurriculo(int id, String novoCurriculo) throws SQLException {
        String sql = "UPDATE candidato SET curriculo = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoCurriculo);
            stmt.setInt(2, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Currículo do candidato com ID " + id + " atualizado com sucesso.");
            } else {
                System.out.println("Nenhum candidato encontrado com ID " + id + ".");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o currículo: " + e.getMessage());
            throw e;
        }
    }

    // Método para editar o perfil de um candidato
    public void editarPerfil(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) throws SQLException {
        String sql = "UPDATE candidato SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Definindo os valores dos parâmetros
            stmt.setString(1, novoNome);
            stmt.setString(2, novoEndereco);
            stmt.setString(3, novoTelefone);
            stmt.setString(4, novoEmail);
            stmt.setInt(5, id);

            // Executando a atualização
            int rowsAffected = stmt.executeUpdate();

            // Verificando se a atualização foi bem-sucedida
            if (rowsAffected > 0) {
                System.out.println("Perfil do candidato com ID " + id + " atualizado com sucesso.");
            } else {
                System.out.println("Nenhum candidato encontrado com ID " + id + ".");
            }
        } catch (SQLException e) {
            // Exibindo mensagem de erro em caso de exceção
            System.err.println("Erro ao atualizar o perfil do candidato: " + e.getMessage());
            throw e; // Lançando a exceção novamente após o log
        }
    }
}
