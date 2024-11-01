package com.example;

import com.example.controller.EmpresaController;
import com.example.controller.VagaController;
import com.example.controller.CandidatoController;
import com.example.model.Empresa;
import com.example.model.Vaga;
import com.example.model.Candidato;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpresaController empresaController = new EmpresaController();
        VagaController vagaController = new VagaController();
        CandidatoController candidatoController = new CandidatoController();

        while (true) {
            try {
                exibirMenuPrincipal();
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
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine(); // Limpa o buffer em caso de erro
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Menu Empresa");
        System.out.println("2. Menu Candidato");
        System.out.println("3. Sair");
    }

    private static void menuEmpresa(Scanner scanner, EmpresaController empresaController, VagaController vagaController) {
        while (true) {
            try {
                exibirMenuEmpresa();
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (escolha) {
                    case 1:
                        adicionarEmpresa(scanner, empresaController);
                        break;

                    case 2:
                        listarEmpresas(empresaController);
                        break;

                    case 3:
                        vagaController.adicionarVaga(); // Presumindo que esse método está implementado corretamente
                        break;

                    case 4:
                        listarVagas(vagaController);
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine(); // Limpa o buffer em caso de erro
            }
        }
    }

    private static void exibirMenuEmpresa() {
        System.out.println("\nMenu Empresa:");
        System.out.println("1. Adicionar Empresa");
        System.out.println("2. Listar Empresas");
        System.out.println("3. Adicionar Vaga");
        System.out.println("4. Listar Vagas");
        System.out.println("5. Voltar ao Menu Principal");
    }

    private static void adicionarEmpresa(Scanner scanner, EmpresaController empresaController) {
        try {
            System.out.print("Digite o nome da empresa: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o endereço da empresa: ");
            String endereco = scanner.nextLine();
            System.out.print("Digite o CNPJ da empresa: ");
            String cnpj = scanner.nextLine();
            System.out.print("Digite o telefone da empresa: ");
            String telefone = scanner.nextLine();
            System.out.print("Digite o email da empresa: ");
            String email = scanner.nextLine();

            Empresa empresa = new Empresa(nome, endereco, cnpj, telefone, email);
            empresaController.adicionarEmpresa(empresa);
            System.out.println("Empresa adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar empresa: " + e.getMessage());
        }
    }

    private static void listarEmpresas(EmpresaController empresaController) {
        List<Empresa> empresas = empresaController.listarEmpresas();
        if (empresas != null && !empresas.isEmpty()) {
            System.out.println("Empresas cadastradas:");
            for (Empresa emp : empresas) {
                System.out.println("- " + emp.getNome() + " | " + emp.getEndereco());
            }
        } else {
            System.out.println("Nenhuma empresa cadastrada.");
        }
    }

    private static void listarVagas(VagaController vagaController) {
        try {
            List<Vaga> vagas = vagaController.listarVagas(); // Presumindo que este método retorna uma lista de Vagas
            if (vagas != null && !vagas.isEmpty()) {
                System.out.println("Vagas cadastradas:");
                for (Vaga v : vagas) {
                    System.out.printf("- %s | %s | Salário: R$ %.2f%n", v.getNome(), v.getDescricao(), v.getSalario()); // Formatação do salário
                }
            } else {
                System.out.println("Nenhuma vaga cadastrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar vagas: " + e.getMessage());
        }
    }


    private static void menuCandidato(Scanner scanner, VagaController vagaController, CandidatoController candidatoController) {
        while (true) {
            try {
                exibirMenuCandidato();
                int escolha = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (escolha) {
                    case 1:
                        seCandidatar(scanner, vagaController, candidatoController);
                        break;

                    case 2:
                        listarVagasCandidatas(scanner, candidatoController);
                        break;

                    case 3:
                        return;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                scanner.nextLine(); // Limpa o buffer em caso de erro
            }
        }
    }

    private static void exibirMenuCandidato() {
        System.out.println("\nMenu Candidato:");
        System.out.println("1. Se candidatar a uma vaga");
        System.out.println("2. Listar vagas que me candidatei");
        System.out.println("3. Voltar ao Menu Principal");
    }

    private static void seCandidatar(Scanner scanner, VagaController vagaController, CandidatoController candidatoController) {
        try {
            Candidato candidato = new Candidato();
            System.out.print("Digite seu nome: ");
            candidato.setNome(scanner.nextLine());
            System.out.print("Digite seu CPF: ");
            candidato.setCpf(scanner.nextLine());

            // Listar vagas disponíveis
            List<Vaga> vagasDisponiveis = vagaController.listarVagas();
            if (vagasDisponiveis.isEmpty()) {
                System.out.println("Não há vagas disponíveis para se candidatar.");
                return;
            }

            System.out.println("Vagas disponíveis:");
            for (Vaga vaga : vagasDisponiveis) {
                System.out.println("- ID: " + vaga.getId() + " | " + vaga.getNome() + " | " + vaga.getDescricao());
            }

            System.out.print("Digite o ID da vaga para se candidatar: ");
            Long vagaId = scanner.nextLong();
            scanner.nextLine(); // Limpa o buffer do scanner

            // Chamar o método para se candidatar
            vagaController.seCandidatar(vagaId, candidato);
            System.out.println("Candidatura realizada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao se candidatar: " + e.getMessage());
        }
    }

    private static void listarVagasCandidatas(Scanner scanner, CandidatoController candidatoController) {
        try {
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
        } catch (Exception e) {
            System.out.println("Erro ao listar candidaturas: " + e.getMessage());
        }
    }
}
