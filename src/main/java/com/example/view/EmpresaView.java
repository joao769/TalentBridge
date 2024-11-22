package main.java.com.example.view;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.controller.EmpresaController;
import main.java.com.example.controller.VagaController;
import main.java.com.example.model.Empresa;
import main.java.com.example.model.Vaga;

public class EmpresaView {

    private EmpresaController empresaController;
    private VagaController vagaController; // Controlador de vagas
    private Scanner scanner;

    // Construtor
    public EmpresaView(EmpresaController empresaController, VagaController vagaController) {
        this.empresaController = empresaController;
        this.vagaController = vagaController;
        this.scanner = new Scanner(System.in);
    }

    // Método para exibir o menu de opções
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Sistema de Cadastro de Empresas ---");
            System.out.println("\n1. Adicionar Empresa");
            System.out.println("2. Listar Empresas");
            System.out.println("3. Buscar Empresa por ID");
            System.out.println("4. Excluir Empresa");
            System.out.println("5. Editar Empresa");
            System.out.println("6. Adicionar Vaga");
            System.out.println("7. Remover Vaga");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int escolha = scanner.nextInt();
            scanner.nextLine();
            
            switch (escolha) {
                case 1:
                    adicionarEmpresa();
                    break;
                case 2:
                    listarEmpresas();
                    break;
                case 3:
                    buscarEmpresa();
                    break;
                case 4:
                    excluirEmpresa();
                    break;
                case 5:
                    editarEmpresa();
                    break;
                case 6:
                    adicionarVaga();
                    break;
                case 7:
                    removerVaga();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarEmpresa() {
        System.out.println("\n--- Adicionar Empresa ---");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine(); 
        
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine(); 
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine(); 
        
        System.out.print("Email");
        String email = scanner.nextLine();

        Empresa empresa = new Empresa(0, nome, endereco, cnpj, telefone, email);
        empresaController.addEmpresa(empresa);

        System.out.println("Empresa adicionada com sucesso!");
    }

    private void listarEmpresas() {
        List<Empresa> empresas = empresaController.listEmpresa();
        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa encontrada.");
        } else {
            System.out.println("\nLista de Empresas:");
            for (Empresa e : empresas) {
                System.out.println(e);
            }
        }
    }

    private void buscarEmpresa() {
        System.out.print("Digite o ID da empresa: ");
        int id = scanner.nextInt();

        Empresa empresa = empresaController.getEmpresaById(id);
        if (empresa != null) {
            System.out.println("\nEmpresa encontrada: ");
            System.out.println(empresa);
        } else {
            System.out.println("Empresa não encontrada.");
        }
    }

    private void excluirEmpresa() {
        System.out.print("Digite o ID da empresa a ser excluída: ");
        int id = scanner.nextInt();

        empresaController.deleteEmpresa(id);
        System.out.println("Empresa excluída com sucesso!");
    }

    private void editarEmpresa() {
        System.out.print("Digite o ID da empresa que deseja editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        System.out.print("Digite o novo nome: ");
        String novoNome = scanner.nextLine();
        
        System.out.print("Digite o novo endereço: ");
        String novoEndereco = scanner.nextLine();
        
        System.out.print("Digite o novo telefone: ");
        String novoTelefone = scanner.nextLine();
        
        System.out.print("Digite o novo email: ");
        String novoEmail = scanner.nextLine();

        empresaController.editarEmpresaById(id, novoNome, novoEndereco, novoTelefone, novoEmail);
        System.out.println("Empresa atualizada com sucesso!");
    }

    // Métodos para gerenciar vagas

    private void adicionarVaga() {
        System.out.print("Digite o ID da empresa para adicionar a vaga: ");
        int empresaId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Descrição da Vaga: ");
        String descricao = scanner.nextLine();
        
        System.out.print("Cargo da Vaga: ");
        String cargo = scanner.nextLine();
                
        Vaga vaga = new Vaga(0, descricao, cargo, empresaId);
        vagaController.adicionarVaga(vaga, empresaId);

        System.out.println("Vaga adicionada com sucesso!");
    }

    private void removerVaga() {
        System.out.print("Digite o ID da vaga a ser removida: ");
        int vagaId = scanner.nextInt();

        vagaController.removerVaga(vagaId);
    }
}
