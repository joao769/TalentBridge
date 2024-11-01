package com.example.repository;

import com.example.model.Vaga;

<<<<<<< HEAD
import java.sql.*;
=======
>>>>>>> 51da9c7e0b3b30c2a46b79eff28599efc8d7c368
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VagaRepository {
<<<<<<< HEAD
    public void salvar(Vaga vaga) {
        String sql = "INSERT INTO vagas (nome, descricao, salario, requisitos, endereco, status, empresa_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, vaga.getNome());
            stmt.setString(2, vaga.getDescricao());
            stmt.setDouble(3, vaga.getSalario());
            stmt.setString(4, vaga.getRequisitos());
            stmt.setString(5, vaga.getEndereco());
            stmt.setString(6, vaga.getStatus());
            stmt.setLong(7, vaga.getEmpresaId());

            stmt.executeUpdate();

            // Recuperar o ID gerado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    vaga.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o ID da vaga.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar a vaga: " + e.getMessage());
        }
    }

    public List<Vaga> listarTodas() {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vagas";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(rs.getLong("id"));
                vaga.setNome(rs.getString("nome"));
                vaga.setDescricao(rs.getString("descricao"));
                vaga.setSalario(rs.getDouble("salario"));
                vaga.setRequisitos(rs.getString("requisitos"));
                vaga.setEndereco(rs.getString("endereco"));
                vaga.setStatus(rs.getString("status"));
                vaga.setEmpresaId(rs.getLong("empresa_id"));

                vagas.add(vaga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar as vagas: " + e.getMessage());
        }
        return vagas;
    }

    public Optional<Vaga> buscarPorId(Long id) {
        String sql = "SELECT * FROM vagas WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vaga vaga = new Vaga();
                vaga.setId(rs.getLong("id"));
                vaga.setNome(rs.getString("nome"));
                vaga.setDescricao(rs.getString("descricao"));
                vaga.setSalario(rs.getDouble("salario"));
                vaga.setRequisitos(rs.getString("requisitos"));
                vaga.setEndereco(rs.getString("endereco"));
                vaga.setStatus(rs.getString("status"));
                vaga.setEmpresaId(rs.getLong("empresa_id"));

                return Optional.of(vaga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a vaga: " + e.getMessage());
        }
        return Optional.empty();
    }

    public void atualizar(Vaga vagaAtualizada) {
        String sql = "UPDATE vagas SET nome = ?, descricao = ?, salario = ?, requisitos = ?, endereco = ?, status = ?, empresa_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vagaAtualizada.getNome());
            stmt.setString(2, vagaAtualizada.getDescricao());
            stmt.setDouble(3, vagaAtualizada.getSalario());
            stmt.setString(4, vagaAtualizada.getRequisitos());
            stmt.setString(5, vagaAtualizada.getEndereco());
            stmt.setString(6, vagaAtualizada.getStatus());
            stmt.setLong(7, vagaAtualizada.getEmpresaId());
            stmt.setLong(8, vagaAtualizada.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Vaga com ID " + vagaAtualizada.getId() + " não encontrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a vaga: " + e.getMessage());
=======
    private List<Vaga> vagas = new ArrayList<>();
    private long nextId = 1; // Contador para gerar IDs

    public void salvar(Vaga vaga) {
        if (vaga == null) {
            throw new IllegalArgumentException("A vaga não pode ser nula.");
        }
        vaga.setId(nextId++); // Atribui um novo ID antes de salvar
        vagas.add(vaga);
    }

    public List<Vaga> listarTodas() {
        return new ArrayList<>(vagas); // Retorna uma cópia para evitar modificações externas
    }

    public Optional<Vaga> buscarPorId(Long id) {
        return vagas.stream().filter(vaga -> vaga.getId().equals(id)).findFirst();
    }

    public void atualizar(Vaga vagaAtualizada) {
        if (vagaAtualizada == null) {
            throw new IllegalArgumentException("A vaga a ser atualizada não pode ser nula.");
        }

        Optional<Vaga> vagaExistenteOpt = buscarPorId(vagaAtualizada.getId());
        if (vagaExistenteOpt.isPresent()) {
            int index = vagas.indexOf(vagaExistenteOpt.get());
            vagas.set(index, vagaAtualizada);
        } else {
            throw new IllegalArgumentException("Vaga com ID " + vagaAtualizada.getId() + " não encontrada.");
>>>>>>> 51da9c7e0b3b30c2a46b79eff28599efc8d7c368
        }
    }

    public void remover(Long vagaId) {
<<<<<<< HEAD
        String sql = "DELETE FROM vagas WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, vagaId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Vaga com ID " + vagaId + " não encontrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover a vaga: " + e.getMessage());
        }
    }
=======
        boolean removed = vagas.removeIf(vaga -> vaga.getId().equals(vagaId));
        if (!removed) {
            throw new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada.");
        }
    }

    public long getLastVagaId() {
        return nextId - 1; // Retorna o último ID gerado
    }
>>>>>>> 51da9c7e0b3b30c2a46b79eff28599efc8d7c368
}
