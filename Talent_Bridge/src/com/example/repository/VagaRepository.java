package com.example.repository;

import com.example.database.DatabaseConnection;
import com.example.model.Candidato;
import com.example.model.Vaga;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VagaRepository {
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

    // Atualiza a vaga no banco de dados
    public void atualizar(Vaga vaga) {
        String sql = "UPDATE vagas SET nome = ?, descricao = ?, salario = ?, requisitos = ?, endereco = ?, status = ?, empresa_id = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, vaga.getNome());
            preparedStatement.setString(2, vaga.getDescricao());
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(vaga.getSalario()));
            preparedStatement.setString(4, vaga.getRequisitos());
            preparedStatement.setString(5, vaga.getEndereco());
            preparedStatement.setString(6, vaga.getStatus());
            preparedStatement.setLong(7, vaga.getEmpresaId());
            preparedStatement.setLong(8, vaga.getId());

            preparedStatement.executeUpdate();
            System.out.println("Vaga atualizada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a vaga: " + e.getMessage());
        }
    }

    public void salvarCandidato(Candidato candidato, Long vagaId) {
        String sql = "INSERT INTO candidatos (vaga_id, nome, cpf) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, vagaId);
            preparedStatement.setString(2, candidato.getNome());
            preparedStatement.setString(3, candidato.getCpf()); // Presumindo que você tenha um CPF no candidato
            preparedStatement.executeUpdate();
            System.out.println("Candidato salvo com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar candidato: " + e.getMessage());
        }
    }

    public void remover(Long vagaId) {
        boolean removed = vagas.removeIf(vaga -> vaga.getId().equals(vagaId));
        if (!removed) {
            throw new IllegalArgumentException("Vaga com ID " + vagaId + " não encontrada.");
        }
    }

    public long getLastVagaId() {
        return nextId - 1; // Retorna o último ID gerado
    }
}