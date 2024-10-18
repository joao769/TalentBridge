package com.example;

import com.example.controller.EmpresaController;
import com.example.controller.VagaController;
import com.example.controller.CandidatoController;
import com.example.model.Empresa;
import com.example.model.Vaga;
import com.example.model.Candidato;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpresaController empresaController = new EmpresaController();
        VagaController vagaController = new VagaController();
        CandidatoController candidatoController = new CandidatoController();

        while (true) {
            try {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Menu Empresa");
                System.out.println("2. Menu Candidato");
                System.out.println("3. Sair");

                int escolha = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (escolha) {
                    case 1:
                        menuEmpresa(scanner, empresaController, vagaController);
                        break;

                    case 2:
                        menuCandidato(scanner, vagaController, candidatoController);
                        break;

                    case 3:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void menuEmpresa(Scanner scanner, EmpresaController empresaController, VagaController vagaController) {
        while (true) {
            try {
                System.out.println("Menu Empresa:");
                System.out.println("1. Adicionar Empresa");
                System.out.println("2. Listar Empresas");
                System.out.println("3. Adicionar Vaga");
                System.out.println("4. Listar Vagas");
                System.out.println("5. Voltar ao Menu Principal");

                int escolha = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (escolha) {
                    case 1:
                        // Adicionar Empresa
                        Empresa empresa = new Empresa();
                        System.out.print("Digite o nome da empresa: ");
                        empresa.setNome(scanner.nextLine());
                        System.out.print("Digite o endereço da empresa: ");
                        empresa.setEndereco(scanner.nextLine());
                        System.out.print("Digite o CNPJ da empresa: ");
                        empresa.setCnpj(scanner.nextLine());
                        empresaController.adicionarEmpresa(empresa);
                        System.out.println("Empresa adicionada com sucesso!");
                        break;

                    case 2:
                        // Listar Empresas
                        List<Empresa> empresas = empresaController.listarEmpresas();
                        if (empresas != null && !empresas.isEmpty()) {
                            System.out.println("Empresas cadastradas:");
                            for (Empresa emp : empresas) {
                                System.out.println("- " + emp.getNome() + " | " + emp.getEndereco());
                            }
                        } else {
                            System.out.println("Nenhuma empresa cadastrada.");
                        }
                        break;

                    case 3:
                        // Adicionar Vaga
                        vagaController.adicionarVaga(); // Chamada correta do método
                        break;

                    case 4:
                        // Listar Vagas
                        List<Vaga> vagas = vagaController.listarVagas();
                        if (vagas != null && !vagas.isEmpty()) {
                            System.out.println("Vagas cadastradas:");
                            for (Vaga v : vagas) {
                                System.out.println("- " + v.getNome() + " | " + v.getDescricao());
                            }
                        } else {
                            System.out.println("Nenhuma vaga cadastrada.");
                        }
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private static void menuCandidato(Scanner scanner, VagaController vagaController, CandidatoController candidatoController) {
        while (true) {
            try {
                System.out.println("Menu Candidato:");
                System.out.println("1. Se candidatar a uma vaga");
                System.out.println("2. Listar vagas que me candidatei");
                System.out.println("3. Voltar ao Menu Principal");

                int escolha = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (escolha) {
                    case 1:
                        // Se candidatar a uma vaga
                        Candidato candidato = new Candidato();
                        System.out.print("Digite seu nome: ");
                        candidato.setNome(scanner.nextLine());
                        System.out.print("Digite seu CPF: ");
                        candidato.setCpf(scanner.nextLine());
                        vagaController.listarVagas(); // Exibe as vagas disponíveis

                        System.out.print("Digite o ID da vaga para se candidatar: ");
                        Long vagaId = scanner.nextLong();
                        vagaController.seCandidatar(vagaId, candidato); // Método para se candidatar
                        break;

                    case 2:
                        // Listar vagas que se candidatou
                        System.out.print("Digite seu CPF para listar suas candidaturas: ");
                        String cpf = scanner.nextLine();
                        List<Vaga> vagasCandidatas = candidatoController.listarVagasCandidatas(cpf);
                        if (vagasCandidatas != null && !vagasCandidatas.isEmpty()) {
                            System.out.println("Vagas às quais você se candidatou:");
                            for (Vaga v : vagasCandidatas) {
                                System.out.println("- " + v.getNome() + " | " + v.getDescricao());
                            }
                        } else {
                            System.out.println("Você não se candidatou a nenhuma vaga.");
                        }
                        break;

                    case 3:
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}
