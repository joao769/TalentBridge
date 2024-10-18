package com.example.service;

import com.example.model.Candidato; // Certifique-se de que a classe Candidato está no pacote correto
import com.example.model.Empresa;
import com.example.model.Vaga; // Adicione a importação da classe Vaga
import com.example.repository.EmpresaRepository;

import java.util.List;

public class EmpresaService {
    private EmpresaRepository empresaRepository = new EmpresaRepository();

    public void adicionarEmpresa(Empresa empresa) {
        if (empresa == null || empresa.getNome() == null || empresa.getCnpj() == null) {
            throw new IllegalArgumentException("Os campos obrigatórios não podem ser nulos.");
        }

        try {
            empresaRepository.salvar(empresa); // Alterado para 'salvar' para corresponder ao repositório
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar empresa: " + e.getMessage());
        }
    }

    public List<Empresa> listarEmpresas() {
        try {
            return empresaRepository.listarTodas(); // Alterado para 'listarTodas' para corresponder ao repositório
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar empresas: " + e.getMessage());
        }
    }

    public void editarVaga(Long empresaId, Long vagaId, Vaga vagaAtualizada) {
        if (vagaAtualizada == null) {
            throw new IllegalArgumentException("A vaga a ser atualizada não pode ser nula.");
        }
        if (empresaId == null || vagaId == null) {
            throw new IllegalArgumentException("IDs de empresa ou vaga não podem ser nulos.");
        }

        try {
            empresaRepository.atualizarVaga(empresaId, vagaId, vagaAtualizada); // Alterado para 'atualizarVaga' para corresponder ao repositório
        } catch (Exception e) {
            throw new RuntimeException("Erro ao editar vaga: " + e.getMessage());
        }
    }

    public void removerVaga(Long empresaId, Long vagaId) {
        if (empresaId == null || vagaId == null) {
            throw new IllegalArgumentException("IDs de empresa ou vaga não podem ser nulos.");
        }

        try {
            empresaRepository.removerVaga(empresaId, vagaId); // Método do repositório para remover uma vaga
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover vaga: " + e.getMessage());
        }
    }

    public List<Candidato> visualizarCandidatos(Long empresaId) {
        if (empresaId == null) {
            throw new IllegalArgumentException("O ID da empresa não pode ser nulo.");
        }

        try {
            return empresaRepository.visualizarCandidatos(empresaId); // Certifique-se de que este método existe no repositório
        } catch (Exception e) {
            throw new RuntimeException("Erro ao visualizar candidatos: " + e.getMessage());
        }
    }
}
