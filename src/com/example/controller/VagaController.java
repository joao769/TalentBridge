package com.example.controller;

import com.example.model.Candidato;
import com.example.model.Vaga;
import com.example.service.VagaService;

import java.util.List;
import java.util.Scanner;

public class VagaController {
    private VagaService vagaService = new VagaService();
    private Scanner scanner = new Scanner(System.in);

    public void adicionarVaga() {
        Vaga vaga = new Vaga();
        System.out.print("Digite o título da vaga: ");
        vaga.setNome(scanner.nextLine());
        System.out.print("Digite a descrição da vaga: ");
        vaga.setDescricao(scanner.nextLine());
        System.out.print("Digite o salário da vaga: ");

        double salario = scanner.nextDouble();
        if (salario <= 0) {
            System.out.println("Salário deve ser um valor positivo. Vaga não adicionada.");
            scanner.nextLine(); // Limpa o buffer
            return;
        }
        vaga.setSalario(salario);
        scanner.nextLine(); // Limpa o buffer do scanner

        // Adiciona a vaga através do serviço
        vagaService.salvar(vaga); // Aqui o ID é atribuído dentro do método

        System.out.print("Digite o ID da empresa associada: ");
        long empresaId = scanner.nextLong();
        scanner.nextLine(); // Limpa o buffer do scanner

        // Aqui você pode querer verificar se a empresa existe antes de associar
        if (vagaService.associarEmpresa(vaga.getId(), empresaId)) {
            System.out.println("Vaga associada à empresa com sucesso!");
        } else {
            System.out.println("Erro: Empresa não encontrada. Vaga não associada.");
        }
    }

    public List<Vaga> listarVagas() {
        try {
            return vagaService.listarVagas();
        } catch (Exception e) {
            System.out.println("Erro ao listar vagas: " + e.getMessage());
            return null;
        }
    }

    public void seCandidatar(Long vagaId, Candidato candidato) {
        Vaga vaga = null; // lógica para buscar a vaga pelo ID
        if (vaga != null) {
            vaga.adicionarCandidato(candidato);
            System.out.println("Candidato " + candidato.getNome() + " se candidatou à vaga: " + vaga.getNome());
        } else {
            System.out.println("Vaga não encontrada.");
        }
    }

}
