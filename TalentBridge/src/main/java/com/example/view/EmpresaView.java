package main.java.com.example.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import main.java.com.example.controller.EmpresaController;
import main.java.com.example.controller.VagaController;
import main.java.com.example.model.Candidato;
import main.java.com.example.model.Vaga;
import main.java.com.example.util.EntradaUtils;
import main.java.com.example.util.SessionContext;

public class EmpresaView {

    private EmpresaController empresaController;
    private VagaController vagaController; 
    private Scanner sc;

    public EmpresaView(EmpresaController empresaController, VagaController vagaController) {
        this.empresaController = empresaController;
        this.vagaController = vagaController;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Menu ---\n");
            System.out.println("1. Fazer Login");
            System.out.println("2. Se cadastrar");
            System.out.println("0. Sair");
            
            int opcao = EntradaUtils.lerEntradaValidaInt(sc, "Escolha uma opção:");

            switch (opcao) {
                case 1:
                	fazerLogin();
                    break;
                case 2:
                    adicionarEmpresa();
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    return;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }

    private void adicionarEmpresa() {
        System.out.println("\n--- Cadastro ---\n");
        
        String nome = EntradaUtils.lerEntradaValidaNome(sc, "Digite o nome da empresa:");
        String cnpj = EntradaUtils.lerEntradaValida(sc, "Digite o CNPJ da empresa:", "\\d{14}");
        String endereco = EntradaUtils.lerEntradaValida(sc, "Digite o endereço da empresa:");
        String telefone = EntradaUtils.lerEntradaValida(sc, "Digite o telefone da empresa (11 dígitos):", "\\d{11}");
        String email = EntradaUtils.lerEntradaValida(sc, "Digite o email da empresa:");
        String senha = EntradaUtils.lerEntradaValida(sc, "Digite uma senha:");

        empresaController.adicionarEmpresa(nome, cnpj, endereco, telefone, email, senha);

        System.out.println("\nEmpresa adicionada com sucesso!");
    }
    
    private void fazerLogin() {
        System.out.println("\n--- Login Empresa ---\n");
        
        String email = EntradaUtils.lerEntradaValida(sc, "Digite o email da empresa:");
        String senha = EntradaUtils.lerEntradaValida(sc, "Digite a senha da empresa:");

        try {
            int empresaId = empresaController.fazerLogin(email, senha);
            if (empresaId != -1) { 
                SessionContext.setUsuarioLogadoId(empresaId);
                System.out.println("\nLogin realizado com sucesso!");
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
            System.out.println("\n--- Menu Empresa ---\n");
            System.out.println("1. Atualizar os dados da empresa");
            System.out.println("2. Adicionar Vaga");
            System.out.println("3. Remover Vaga");
            System.out.println("4. Vizalizar os candidatos aplicados para a Vaga");
            System.out.println("5. Selecionar um candidato aplicado para a Vaga");
            System.out.println("0. Sair");
            
            int opcao = EntradaUtils.lerEntradaValidaInt(sc, "Escolha uma opção:");

            switch (opcao) {
                case 1:
                	editarEmpresa();
                	break;
                case 2:
                	adicionarVaga();
                	break;
                case 3:
                	removerVaga();
                    break;
                case 4:
                	vizualizarCandidatos();
                    break;
                case 5:
                	selecionarCandidato();
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
  
    private void editarEmpresa() {
    	
        int empresaLogadaId = SessionContext.getUsuarioLogadoId();
        if (empresaLogadaId == -1) {
            System.out.println("Erro: Nenhuma empresa está logada.");
            return;
        }
    	
        System.out.println("\n--- Atualizar Empresa ---\n");
        String novoNome = EntradaUtils.lerEntradaValidaNome(sc, "Digite o novo nome da empresa:");
        String novoEndereco = EntradaUtils.lerEntradaValida(sc, "Digite o novo endereço da empresa:");
        String novoTelefone = EntradaUtils.lerEntradaValida(sc, "Digite o novo telefone da empresa (11 dígitos):", "\\d{11}");
        String novoEmail = EntradaUtils.lerEntradaValidaNome(sc, "Digite o novo email da empresa:");
        empresaController.editarEmpresa(empresaLogadaId, novoNome, novoEndereco, novoTelefone, novoEmail);
        System.out.println("\nDados da empresa atualizada com sucesso!");
    }
    
    private void adicionarVaga() {
    	
        int empresaLogadaId = SessionContext.getUsuarioLogadoId();
        if (empresaLogadaId == -1) {
            System.out.println("Erro: Nenhuma empresa está logada.");
            return;
        }

        System.out.println("\n--- Adicionar Vaga ---\n");
        String titulo = EntradaUtils.lerEntradaValida(sc, "Digite o título da vaga:");
        String descricao = EntradaUtils.lerEntradaValida(sc, "Digite a descrição da vaga:");
        String cargo = EntradaUtils.lerEntradaValida(sc, "Digite o cargo da vaga:");
        String cargaHoraria = EntradaUtils.lerEntradaValida(sc, "Digite a carga horária da vaga:");
        double salario = EntradaUtils.lerEntradaValidaDouble(sc, "Digite o salário da vaga:");
        String status = "disponivel";

        vagaController.adicionarVaga(empresaLogadaId, titulo, descricao, cargo, cargaHoraria, salario, status);
        System.out.println("\nVaga adicionada com sucesso!");
    }

   
    private void removerVaga() {
    	
        int empresaLogadaId = SessionContext.getUsuarioLogadoId();
        if (empresaLogadaId == -1) {
            System.out.println("Erro: Nenhuma empresa está logada.");
            return;
        }
    	
        System.out.println("\n--- Remover Vaga ---");
        List<Vaga> vagas = vagaController.listarVagasPorEmpresa(empresaLogadaId);
        if (vagas.isEmpty()) {
            System.out.println("\nNenhuma vaga encontrada para sua empresa.");
            return;
        }
        System.out.println("Vagas disponíveis:");
        for (Vaga vaga : vagas) {
            System.out.println("ID: " + vaga.getId() + " | Título: " + vaga.getTitulo());
        }
        int vagaId = EntradaUtils.lerEntradaValidaInt(sc, "Digite o ID da vaga que deseja remover:");
        vagaController.removerVaga(vagaId);
        System.out.println("\nVaga removida com sucesso!");
    }

    private void vizualizarCandidatos() {
        System.out.println("\n--- Candidatos Aplicados Na Vaga ---");

        int vagaId = EntradaUtils.lerEntradaValidaInt(sc, "\nDigite o ID da vaga que deseja visualizar os candidatos aplicados:");

		String statusVaga = vagaController.consultarStatusVaga(vagaId);
		if ("fechada".equalsIgnoreCase(statusVaga) || "preenchida".equalsIgnoreCase(statusVaga)) {
		    System.out.println("\nA vaga está preenchida.");
		    return;
		}

		List<Candidato> candidatos = vagaController.listarCandidatosAplicados(vagaId);

		if (candidatos.isEmpty()) {
		    System.out.println("\nNenhum candidato se aplicou para essa vaga.");
		} else {
		    for (Candidato candidato : candidatos) {
		        System.out.println("\nCandidato ID: " + candidato.getId());
		        System.out.println("Nome: " + candidato.getNome());
		        System.out.println("E-mail: " + candidato.getEmail());

		        if (candidato.getCurriculo() != null) {
		            System.out.println("Currículo de " + candidato.getNome() + ":");
		            System.out.println("  - Experiência Profissional: " + candidato.getCurriculo().getExperienciaProfissional()); 
		            System.out.println("  - Formação Acadêmica: " + candidato.getCurriculo().getFormacaoAcademica()); 
		            System.out.println("  - Habilidades: " + candidato.getCurriculo().getHabilidades()); 
		        } else {
		            System.out.println("Currículo não disponível.");
		        }
		    }
		}
    }

    private void selecionarCandidato() {
        System.out.println("\n--- Selecionar Candidato ---\n");

        int vagaId = EntradaUtils.lerEntradaValidaInt(sc, "Digite o ID da vaga:");
        int candidatoId = EntradaUtils.lerEntradaValidaInt(sc, "Digite o ID do candidato que deseja selecionar:");

		List<Candidato> candidatos = vagaController.listarCandidatosAplicados(vagaId);
		if (candidatos.isEmpty()) {
		    System.out.println("\nNão há candidatos aplicados para esta vaga.");
		    return; 
		}

		vagaController.selecionarCandidato(vagaId, candidatoId);
		System.out.println("Candidato selecionado com sucesso!");
    }
}
