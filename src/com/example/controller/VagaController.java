package com.example.controller;

import com.example.model.Candidato;
import com.example.model.Vaga;
import com.example.service.VagaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VagaController {
    private final VagaService vagaService = new VagaService();
    private final Scanner scanner = new Scanner(System.in);

    // Método para adicionar uma nova vaga
    public void adicionarVaga() {
        try {
            System.out.print("Digite o título da vaga: ");
            String nome = scanner.nextLine();

            System.out.print("Digite a descrição da vaga: ");
            String descricao = scanner.nextLine();

            System.out.print("Digite o salário da vaga: ");
            double salario = scanner.nextDouble();
            scanner.nextLine(); // Limpar buffer do scanner

            if (salario <= 0) {
                System.out.println("Erro: Salário deve ser positivo. Operação cancelada.");
                return;
            }

            System.out.print("Digite o ID da empresa associada: ");
            long empresaId = scanner.nextLong();
            scanner.nextLine(); // Limpar buffer do scanner

            // Cria o objeto Vaga e define suas propriedades
            Vaga vaga = new Vaga(null, nome, descricao, salario, null, null); // Supondo que o ID seja atribuído automaticamente

            // Salva a vaga usando o serviço
            vagaService.salvar(vaga);
            // Agora, associa a vaga à empresa
            if (vagaService.associarEmpresa(vaga.getId(), empresaId)) {
                System.out.println("Vaga adicionada e associada à empresa com sucesso!");
            } else {
                System.out.println("Erro ao associar a vaga à empresa. Verifique o ID da empresa.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao adicionar vaga: " + e.getMessage());
        }
    }

    // Método para listar vagas existentes
    public List<Vaga> listarVagas() { // Alteração do tipo de retorno para List<Vaga>
        try {
            List<Vaga> vagas = vagaService.listarVagas();
            if (vagas.isEmpty()) {
                System.out.println("Nenhuma vaga encontrada.");
                return vagas; // Retorna a lista vazia
            } else {
                System.out.println("Vagas encontradas:");
                for (Vaga vaga : vagas) {
                    System.out.println(vaga); // Supondo que o método toString esteja implementado na classe Vaga
                }
            }
            return vagas; // Retorna a lista de vagas
        } catch (Exception e) {
            System.out.println("Erro ao listar vagas: " + e.getMessage());
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }


    // Método para um candidato se candidatar a uma vaga
    public void seCandidatar(Long vagaId, Candidato candidato) {
        try {
            Vaga vaga = vagaService.buscarVagaPorId(vagaId); // Certifique-se de que o método existe em VagaService
            if (vaga != null) {
                vaga.adicionarCandidato(candidato); // Adiciona candidato à lista interna
                vagaService.salvarCandidato(candidato, vagaId); // Salva candidato no banco de dados
                System.out.println("Candidato " + candidato.getNome() + " se candidatou à vaga: " + vaga.getNome());
            } else {
                System.out.println("Vaga não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao se candidatar à vaga: " + e.getMessage());
        }
    }
}
