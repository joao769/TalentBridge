package main.java.com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.com.example.controller.CandidatoController;
import main.java.com.example.controller.EmpresaController;
import main.java.com.example.controller.VagaController;
import main.java.com.example.view.CandidatoView;
import main.java.com.example.view.EmpresaView;

public class TalentBridgeApplication {

    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        CandidatoController candidatoController = new CandidatoController();
        VagaController vagaController = new VagaController();
        EmpresaController empresaController = new EmpresaController();
        CandidatoView candidatoView = new CandidatoView(candidatoController);
        EmpresaView empresaView = new EmpresaView(empresaController, vagaController);


        while (true) {
            try {
                System.out.println("Escolha uma opção:");
                System.out.println("1. Menu Empresa");
                System.out.println("2. Menu Candidato");
                System.out.println("0. Sair");

                int escolha = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner

                switch (escolha) {
                    case 1:
                        empresaView.showMenu();
                        break;
                    case 2:
                        candidatoView.showMenu();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}
