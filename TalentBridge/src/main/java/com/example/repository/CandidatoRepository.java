package main.java.com.example.repository;

import java.sql.*;

import main.java.com.example.database.DatabaseConnector;
import main.java.com.example.model.Candidato;

public class CandidatoRepository {

    public void adicionarCandidato(Candidato candidato) throws SQLException {
        String query = "INSERT INTO candidato (nome, cpf, endereco, telefone, email, senha) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";  

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, candidato.getNome());
            stmt.setString(2, candidato.getCpf());
            stmt.setString(3, candidato.getEndereco());
            stmt.setString(4, candidato.getTelefone());
            stmt.setString(5, candidato.getEmail());
            stmt.setString(6, candidato.getSenha());  

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar candidato: " + e.getMessage());
            throw e; 
        }
    }

	public Candidato buscarPorEmail(String email) throws SQLException {
	    String query = "SELECT * FROM candidato WHERE email = ?";

	    try (Connection conn = DatabaseConnector.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, email);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return new Candidato(
	                rs.getInt("id"),
	                rs.getString("nome"),
	                rs.getString("cpf"),
	                rs.getString("endereco"),
	                rs.getString("telefone"),
	                rs.getString("email"),
	                rs.getString("senha")
	            );
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao buscar candidato por e-mail: " + e.getMessage());
	        throw e;
	    }
	    return null;
	}
    
    public void editarPerfil(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) throws SQLException {
        String sql = "UPDATE candidato SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novoEndereco);
            stmt.setString(3, novoTelefone);
            stmt.setString(4, novoEmail);
            stmt.setInt(5, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar candidato no banco: " + e.getMessage());
            throw e;
        }
    }
}
