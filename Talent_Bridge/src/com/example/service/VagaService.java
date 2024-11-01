package com.example.service;

import com.example.database.DatabaseConnection;
import com.example.model.Candidato;
import com.example.model.Vaga;
import com.example.repository.VagaRepository;
import com.example.repository.EmpresaRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VagaService {
    private final VagaRepository vagaRepository = new VagaRepository();
    private final EmpresaRepository empresaRepository = new EmpresaRepository();

    // Método para salvar uma vaga
    public void salvar(Vaga vaga) {
        String sql = "INSERT INTO vagas (nome, descricao, salario, requisitos, endereco, status, empresa_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Definir parâmetros da consulta
            preparedStatement.setString(1, vaga.getNome());
            preparedStatement.setString(2, vaga.getDescricao());
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(vaga.getSalario())); // Convertendo para BigDecimal
            preparedStatement.setString(4, vaga.getRequisitos());
            preparedStatement.setString(5, vaga.getEndereco());
            preparedStatement.setString(6, vaga.getStatus());
            preparedStatement.setLong(7, vaga.getEmpresaId());

            // Executar a consulta
            preparedStatement.executeUpdate();
            System.out.println("Vaga salva com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar a vaga: " + e.getMessage());
        }
    }

    // Método para associar uma vaga a uma empresa existente
    public boolean associarEmpresa(Long vagaId, Long empresaId) {
        Optional<Vaga> vagaOptional = vagaRepository.buscarPorId(vagaId);
        if (vagaOptional.isEmpty()) {
            System.out.println("Erro: Vaga com ID " + vagaId + " não encontrada.");
            return false;
        }

        // Verifica se a empresa existe
        if (empresaRepository.buscarPorId(empresaId) == null) {
            System.out.println("Erro: Empresa com ID " + empresaId + " não encontrada.");
            return false;
        }

        // Associa a empresa à vaga
        Vaga vaga = vagaOptional.get();
        vaga.setEmpresaId(empresaId); // Atualiza o ID da empresa na vaga

        // Salva a vaga atualizada no repositório
        vagaRepository.atualizar(vaga);
        System.out.println("Vaga associada à empresa com sucesso.");
        return true;
    }

    // Método para listar todas as vagas
    public List<Vaga> listarVagas() {
        return vagaRepository.listarTodas();
    }

    // VagaService.java
    public Vaga buscarVagaPorId(Long vagaId) {
        Optional<Vaga> vagaOptional = vagaRepository.buscarPorId(vagaId);
        return vagaOptional.orElse(null); // Retorna a vaga ou null se não existir
    }

    public void salvarCandidato(Candidato candidato, Long vagaId) {
        vagaRepository.salvarCandidato(candidato, vagaId);
    }
}