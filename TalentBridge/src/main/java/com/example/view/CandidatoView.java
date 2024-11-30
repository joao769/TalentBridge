package main.java.com.example.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import main.java.com.example.controller.CandidatoController;
import main.java.com.example.controller.CurriculoController;
import main.java.com.example.controller.VagaController;
import main.java.com.example.model.Vaga;
import main.java.com.example.util.EntradaUtils;
import main.java.com.example.util.SessionContext;

public class CandidatoView {

    private CandidatoController candidatoController;
    private CurriculoController curriculoController;
    private VagaController vagaController;
    private Scanner sc;

    public CandidatoView(CandidatoController candidatoController, CurriculoController curriculoController, VagaController vagaController) {
        this.candidatoController = candidatoController;
        this.curriculoController = curriculoController;
        this.vagaController = vagaController;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Bem vindo ---");
            System.out.println("\n1. Fazer login");
            System.out.println("2. Se cadastrar");
            System.out.println("0. Sair");
            
            int opcao = EntradaUtils.lerEntradaValidaInt(sc, "Escolha uma opção:");

            switch (opcao) {
                case 1:
                	fazerLogin();
                    break;
                case 2:
                    adicionarCandidato();
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    return;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarCandidato() {
        System.out.println("\n--- Cadastro ---");    	
        String nome = EntradaUtils.lerEntradaValidaNome(sc, "Digite seu nome:");        
        String cpf = EntradaUtils.lerEntradaValida(sc, "Digite seu CPF:", "\\d{11}");        
        String endereco = EntradaUtils.lerEntradaValida(sc, "Digite seu endereço:");       
        String telefone = EntradaUtils.lerEntradaValida(sc, "Digite seu telefone (11 dígitos):", "\\d{11}");
        String email = EntradaUtils.lerEntradaValida(sc, "Digite seu email:");  
        String senha = EntradaUtils.lerEntradaValida(sc, "Digite uma senha:");
        
        candidatoController.adicionarCandidato(nome, cpf, endereco, telefone, email, senha);
        System.out.println("\nCandidato adicionado com sucesso!");
        
    }

    private void fazerLogin() {
        System.out.println("\n--- Login Candidato ---\n");
        
        String email = EntradaUtils.lerEntradaValida(sc, "Digite seu email:");
        String senha = EntradaUtils.lerEntradaValida(sc, "Digite sua senha:");

        try {
            int candidatoId = candidatoController.fazerLogin(email, senha);
            if (candidatoId != -1) { 
                SessionContext.setUsuarioLogadoId(candidatoId);
                System.out.println("\nLogin realizado com sucesso!\n");

                if (!curriculoController.verificarCurriculo(candidatoId)) {
                    System.out.println("\nVocê ainda não possui um currículo. Vamos criar um agora.\n");
                    adicionarCurriculo(candidatoId);
                }

                menu();
            } else {
                System.out.println("\nEmail ou senha incorretos. Tente novamente.");
            }
        } catch (SQLException e) {
            System.err.println("\nErro ao realizar o login: " + e.getMessage());
        }
    }


    
    private void menu() {
    	
        while (true) {
            System.out.println("\n--- Menu Candidato ---\n");
            System.out.println("1. Atualizar o perfil");
            System.out.println("2. Atualizar o currículo");
            System.out.println("3. Vizualizar o currículo");
            System.out.println("4. Vizualizar vagas disponíveis");
            System.out.println("5. Se candidatar para uma vaga");
            System.out.println("6. Remover candidatura para vaga");
            System.out.println("7. Status da vaga aplicada");
            System.out.println("0. Sair");
            
            int opcao = EntradaUtils.lerEntradaValidaInt(sc, "Escolha uma opção:");

            switch (opcao) {
                case 1:
                	atualizarPerfil();
                	break;
                case 2:
                	atualizarCurriculo();
                    break;
                case 3:
                	vizualizarCurriculo();
                    break;
                case 4:
                	vizualizarVagas();
                    break;
                case 5:
                	candidatar();
                    break;
                case 6:
                	removerCandidatura();
                    break;
                case 7:
                	vizualizarVagaAplicada();
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    SessionContext.logout();
                    return;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }
    
    private void atualizarPerfil() {
    	
        int candidatoLogadoId = SessionContext.getUsuarioLogadoId();
        if (candidatoLogadoId == -1) {
            System.out.println("\nErro: Nenhum candidato está logado.");
            return;
        }
    	
        System.out.println("\n--- Atualizar Perfil ---\n");
        String novoNome = EntradaUtils.lerEntradaValidaNome(sc, "Digite o novo nome:");
        String novoEndereco = EntradaUtils.lerEntradaValida(sc, "Digite o novo endereço:");
        String novoTelefone = EntradaUtils.lerEntradaValida(sc, "Digite o novo telefone (11 dígitos):", "\\d{11}");
        String novoEmail = EntradaUtils.lerEntradaValida(sc, "Digite o novo email:");


        candidatoController.editarPerfil(candidatoLogadoId, novoNome, novoEndereco, novoTelefone, novoEmail);
        
        System.out.println("\nPerfil atualizado atualizado com sucesso!");
    }
    
    private void adicionarCurriculo(int candidatoId) {
        System.out.println("\n--- Criar Currículo ---\n");

        String experienciaProfissional = EntradaUtils.lerEntradaValida(sc, "Digite sua experiência profissional:");
        String formacaoAcademica = EntradaUtils.lerEntradaValida(sc, "Digite sua formação acadêmica:");
        String habilidades = EntradaUtils.lerEntradaValida(sc, "Digite suas habilidades:");

        try {
            curriculoController.adicionarCurriculo(candidatoId, experienciaProfissional, formacaoAcademica, habilidades);
            System.out.println("\nCurrículo criado com sucesso!");
        } catch (SQLException e) {
            System.err.println("\nErro ao criar currículo: " + e.getMessage());
        }
    }

    
    private void atualizarCurriculo() {
    	
        int candidatoLogadoId = SessionContext.getUsuarioLogadoId();
        if (candidatoLogadoId == -1) {
            System.out.println("Erro: Nenhum candidato está logado.");
            return;
        }
        
        System.out.println("\n--- Atualizar Currículo ---\n");
        String experiencia = EntradaUtils.lerEntradaValida(sc, "Digite sua nova experiência profissional:");
        String formacao = EntradaUtils.lerEntradaValida(sc, "Digite sua nova formação acadêmica:");
        String habilidades = EntradaUtils.lerEntradaValida(sc, "Digite suas novas habilidades:");

        curriculoController.atualizarCurriculo(candidatoLogadoId, experiencia, formacao, habilidades);
        System.out.println("\nCurrículo atualizado com sucesso.");
    }
    
    private void vizualizarCurriculo() {
    	
        int candidatoLogadoId = SessionContext.getUsuarioLogadoId();
        if (candidatoLogadoId == -1) {
            System.out.println("Erro: Nenhum candidato está logado.");
            return;
        }
    	
        curriculoController.visualizarCurriculo(candidatoLogadoId);
    }
    
    private void vizualizarVagas() {
        System.out.println("\n--- Vagas Disponíveis ---\n");
        List<Vaga> vagas = vagaController.listarVagasDisponiveis();

        if (vagas.isEmpty()) {
            System.out.println("Nenhuma vaga disponível.");
        } else {
            for (Vaga vaga : vagas) {
                System.out.println("Empresa: " + vaga.getNomeEmpresa());
                System.out.println("Id da vaga: " + vaga.getId());
                System.out.println("Título: " + vaga.getTitulo());
                System.out.println("Descrição: " + vaga.getDescricao());
                System.out.println("Cargo: " + vaga.getCargo());
                System.out.println("Carga horária: " + vaga.getCargaHoraria() + " horas semanais");
                System.out.println("Salário: " + formatarPreco(vaga.getSalario()));
                System.out.println("Status: " + vaga.getStatus() + "\n");
            }
        }
    }
    
    private static String formatarPreco(double preco) {
        return String.format("R$ %.2f", preco);
    }
    
    private void candidatar() {
        System.out.println("--- Aplicar Para Vaga ---\n");
        int candidatoLogadoId = SessionContext.getUsuarioLogadoId();
        if (candidatoLogadoId == -1) {
            System.out.println("Erro: Nenhum candidato está logado.");
            return;
        }
    	
        System.out.println("\nVagas Disponíveis:\n");
        List<Vaga> vagas = vagaController.listarVagasDisponiveis();

        if (vagas.isEmpty()) {
            System.out.println("\nNenhuma vaga disponível.");
        } else {
            for (Vaga vaga : vagas) {
                System.out.println("\nEmpresa: " + vaga.getNomeEmpresa());
                System.out.println("Id da vaga: " + vaga.getId());
                System.out.println("Título: " + vaga.getTitulo() + "\n");
            }
        }
        
        int vagaId = EntradaUtils.lerEntradaValidaInt(sc, "Digite o id da vaga:");
        vagaController.adicionarCandidato(candidatoLogadoId, vagaId);
    }
    
    private void removerCandidatura() {
    	
        int candidatoLogadoId = SessionContext.getUsuarioLogadoId();
        if (candidatoLogadoId == -1) {
            System.out.println("Erro: Nenhum candidato está logado.");
            return;
        }
    	
        System.out.println("\n--- Remover Candidatura da Vaga ---\n");
        int vagaId = EntradaUtils.lerEntradaValidaInt(sc, "Digite o id da vaga:");
        vagaController.removerCandidatura(candidatoLogadoId, vagaId);
    }
    
    private void vizualizarVagaAplicada() {
        int candidatoLogadoId = SessionContext.getUsuarioLogadoId();
        if (candidatoLogadoId == -1) {
            System.out.println("Erro: Nenhum candidato está logado.");
            return;
        }

        System.out.println("\n--- Visualizar Status da Vaga ---\n");
        int vagaId = EntradaUtils.lerEntradaValidaInt(sc, "Digite o id da vaga:");

        String status = vagaController.consultarStatusCandidatura(candidatoLogadoId, vagaId);
		System.out.println("\nStatus da candidatura para a vaga id " + vagaId + ": " + status);
    }

} 