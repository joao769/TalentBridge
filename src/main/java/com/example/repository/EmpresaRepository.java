package main.java.com.example.repository;

import main.java.com.example.database.DatabaseConnector;
import main.java.com.example.model.Empresa;
import main.java.com.example.model.Vaga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository {

    // Criar empresa
    public void criarEmpresa(Empresa empresa) throws SQLException {
        String query = "INSERT INTO empresa (nome, endereco, cnpj, telefone, email) " +
                       "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getEndereco());
            stmt.setString(3, empresa.getCnpj());
            stmt.setString(4, empresa.getTelefone());
            stmt.setString(5, empresa.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar empresa: " + e.getMessage());
            throw e; 
        }
    }

    public void atualizarEmpresa(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) throws SQLException {
        String query = "UPDATE empresa SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novoEndereco);
            stmt.setString(3, novoTelefone);
            stmt.setString(4, novoEmail);
            stmt.setInt(5, id);

            stmt.executeUpdate();
            System.out.println("Empresa atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar empresa: " + e.getMessage());
            throw e;
        }
    }
    
    public void deletarEmpresa(int empresaId) throws SQLException {
        String query = "DELETE FROM empresa WHERE id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, empresaId);
            stmt.executeUpdate();
            System.out.println("Empresa deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar empresa: " + e.getMessage());
            throw e;
        }
    }
    
    // Listar todas as empresas
    public List<Empresa> listarEmpresas() throws SQLException {
        List<Empresa> empresas = new ArrayList<>();
        String query = "SELECT * FROM empresa";

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("id"));
                empresa.setNome(rs.getString("nome"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setTelefone(rs.getString("telefone"));
                empresa.setEmail(rs.getString("email"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empresas: " + e.getMessage());
            throw e;
        }
        return empresas;
    }

    // Buscar empresa pelo ID
    public Empresa getById(int id) throws SQLException {
        Empresa empresa = null;
        String query = "SELECT * FROM empresa WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id); 
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empresa = new Empresa();
                    empresa.setId(rs.getInt("id"));
                    empresa.setNome(rs.getString("nome"));
                    empresa.setEndereco(rs.getString("endereco"));
                    empresa.setCnpj(rs.getString("cnpj"));
                    empresa.setTelefone(rs.getString("telefone"));
                    empresa.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empresa por ID: " + e.getMessage());
            throw e;
        }
        return empresa;
    }
}
