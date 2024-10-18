package com.example.controller;

import com.example.model.Candidato;
import com.example.model.Empresa;
import com.example.model.Vaga;
import com.example.service.EmpresaService;

import java.util.List;

public class EmpresaController {
    private EmpresaService empresaService = new EmpresaService();

    public void adicionarEmpresa(Empresa empresa) {
        try {
            if (empresa == null) {
                throw new IllegalArgumentException("A empresa não pode ser nula.");
            }
            empresaService.adicionarEmpresa(empresa);
            System.out.println("Empresa adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao adicionar empresa: " + e.getMessage());
        }
    }

    public List<Empresa> listarEmpresas() {
        try {
            List<Empresa> empresas = empresaService.listarEmpresas();
            if (empresas.isEmpty()) {
                System.out.println("Nenhuma empresa cadastrada.");
            }
            return empresas;
        } catch (Exception e) {
            System.out.println("Erro ao listar empresas: " + e.getMessage());
            return null;
        }
    }

    public void editarVaga(Long empresaId, Long vagaId, Vaga vagaAtualizada) {
        try {
            if (vagaAtualizada == null) {
                throw new IllegalArgumentException("A vaga a ser atualizada não pode ser nula.");
            }
            empresaService.editarVaga(empresaId, vagaId, vagaAtualizada);
            System.out.println("Vaga editada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: IDs inválidos ou vaga não encontrada. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao editar vaga: " + e.getMessage());
        }
    }

    public void removerVaga(Long empresaId, Long vagaId) {
        try {
            empresaService.removerVaga(empresaId, vagaId);
            System.out.println("Vaga removida com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao remover vaga: " + e.getMessage());
        }
    }

    public void visualizarCandidatos(Long empresaId) {
        try {
            List<Candidato> candidatos = empresaService.visualizarCandidatos(empresaId);
            if (candidatos == null || candidatos.isEmpty()) {
                System.out.println("Nenhum candidato encontrado.");
            } else {
                for (Candidato c : candidatos) {
                    System.out.println("Candidato: " + c.getNome());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao visualizar candidatos: " + e.getMessage());
        }
    }
}
