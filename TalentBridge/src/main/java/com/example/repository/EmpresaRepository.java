package main.java.com.example.repository;

import main.java.com.example.database.DatabaseConnector;
import main.java.com.example.model.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository {

    public void adicionarEmpresa(Empresa empresa) throws SQLException {
        String query = "INSERT INTO empresa (nome, cnpj, endereco, telefone, email, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCnpj());
            stmt.setString(3, empresa.getEndereco());
            stmt.setString(4, empresa.getTelefone());
            stmt.setString(5, empresa.getEmail());
            stmt.setString(6, empresa.getSenha()); 

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar empresa no banco de dados: " + e.getMessage());
            throw e; 
        }
    }

    public Empresa buscarPorEmail(String email) throws SQLException {
        String query = "SELECT * FROM empresa WHERE email = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Empresa(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cnpj"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }
        }
        return null;  
    }
    
    public void atualizarDados(int id, String novoNome, String novoEndereco, String novoTelefone, String novoEmail) throws SQLException {
        String query = "UPDATE empresa SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novoEndereco);
            stmt.setString(3, novoTelefone);
            stmt.setString(4, novoEmail);
            stmt.setInt(5, id);  

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar empresa no banco: " + e.getMessage());
            throw e;
        }
    }

    public void deletarEmpresa(int empresaId) throws SQLException {
        String query = "DELETE FROM empresa WHERE id = ?";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, empresaId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Empresa deletada com sucesso!");
            } else {
                System.out.println("Nenhuma empresa encontrada com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar empresa: " + e.getMessage());
            throw e;
        }
    }

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

            if (empresas.isEmpty()) {
                System.out.println("Nenhuma empresa encontrada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar empresas: " + e.getMessage());
            throw e;
        }
        return empresas;
    }

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
