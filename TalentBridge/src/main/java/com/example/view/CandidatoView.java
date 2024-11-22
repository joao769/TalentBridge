package main.java.com.example.view;

import java.util.List;
import java.util.Scanner;

import main.java.com.example.controller.CandidatoController;
import main.java.com.example.model.Candidato;

public class CandidatoView {

    private CandidatoController candidatoController;
    private Scanner scanner;

    // Construtor
    public CandidatoView(CandidatoController candidatoController) {
        this.candidatoController = candidatoController;
        this.scanner = new Scanner(System.in);
    }

    // Método para exibir o menu de opções
    public void showMenu() {
        while (true) {
            System.out.println("\n--- Sistema de Cadastro de Candidatos ---");
            System.out.println("\n1. Adicionar Candidato");
            System.out.println("2. Listar Candidatos");
            System.out.println("3. Buscar Candidato por ID");
            System.out.println("4. Excluir Candidato");
            System.out.println("5. Editar Perfil");
            System.out.println("6. Atualizar Currículo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (escolha) {
                case 1:
                    adicionarCandidato();
                    break;
                case 2:
                    listarCandidatos();
                    break;
                case 3:
                    buscarCandidato();
                    break;
                case 4:
                    excluirCandidato();
                    break;
                case 5:
                    editarPerfil();
                    break;
                case 6:
                	atualizarCurriculo();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para adicionar um novo candidato
    private void adicionarCandidato() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Currículo: ");
        String curriculo = scanner.nextLine();
        
        System.out.print("Experiência Profissional: ");
        String experiencia = scanner.nextLine();

        Candidato candidato = new Candidato(0, nome, cpf, endereco, telefone, email, curriculo, experiencia);
        candidatoController.addCandidato(candidato);

        System.out.println("Candidato adicionado com sucesso!");
    }

    // Método para listar todos os candidatos
    private void listarCandidatos() {
        List<Candidato> candidatos = candidatoController.listCandidatos();
        if (candidatos.isEmpty()) {
            System.out.println("Nenhum candidato encontrado.");
        } else {
            System.out.println("\nLista de Candidatos:");
            for (Candidato c : candidatos) {
                System.out.println(c);
            }
        }
    }

    // Método para buscar um candidato pelo ID
    private void buscarCandidato() {
        System.out.print("Digite o ID do candidato: ");
        int id = scanner.nextInt();

        Candidato candidato = candidatoController.getCandidatoById(id);
        if (candidato != null) {
            System.out.println("\nCandidato encontrado: ");
            System.out.println(candidato);
        } else {
            System.out.println("Candidato não encontrado.");
        }
    }

    // Método para excluir um candidato
    private void excluirCandidato() {
        System.out.print("Digite o ID do candidato a ser excluído: ");
        int id = scanner.nextInt();
        
        candidatoController.deleteCandidato(id);
    }
    
    private void atualizarCurriculo() {
        System.out.print("Digite o ID do candidato que deseja mudar o curriculo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        System.out.print("Digite o novo currículo: ");
        String novoCurriculo = scanner.nextLine();
        
        candidatoController.atualizarCurriculoById(id, novoCurriculo);
    }
    
    private void editarPerfil() {
        System.out.print("Digite o ID do candidato que deseja editar o perfil: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o novo nome: ");
        String novoNome = scanner.nextLine();
        
        System.out.print("Digite o novo endereço: ");
        String novoEndereco = scanner.nextLine();
        
        System.out.print("Digite o novo telefone: ");
        String novoTelefone = scanner.nextLine();
        
        System.out.print("Digite o novo email: ");
        String novoEmail = scanner.nextLine();
        
        candidatoController.editarPerfilById(id, novoNome, novoEndereco, novoTelefone, novoEmail);
    }
} 