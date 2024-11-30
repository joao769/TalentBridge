package main.java.com.example;

import java.util.Scanner;

import main.java.com.example.controller.CandidatoController;
import main.java.com.example.controller.CurriculoController;
import main.java.com.example.controller.EmpresaController;
import main.java.com.example.controller.VagaController;
import main.java.com.example.util.EntradaUtils;
import main.java.com.example.view.CandidatoView;
import main.java.com.example.view.EmpresaView;

public class TalentBridgeApplication {

    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        CandidatoController candidatoController = new CandidatoController();
        VagaController vagaController = new VagaController(); 
        EmpresaController empresaController = new EmpresaController();
        CurriculoController curriculoController = new CurriculoController();
        CandidatoView candidatoView = new CandidatoView(candidatoController, curriculoController, vagaController);
        EmpresaView empresaView = new EmpresaView(empresaController, vagaController);

        while (true) {
            System.out.println("\r\n"
                    + " _____     _            _    ______      _     _            \r\n"
                    + "|_   _|   | |          | |   | ___ \\    (_)   | |           \r\n"
                    + "  | | __ _| | ___ _ __ | |_  | |_/ /_ __ _  __| | __ _  ___ \r\n"
                    + "  | |/ _` | |/ _ \\ '_ \\| __| | ___ \\ '__| |/ _` |/ _` |/ _ \\\r\n"
                    + "  | | (_| | |  __/ | | | |_  | |_/ / |  | | (_| | (_| |  __/\r\n"
                    + "  \\_/\\__,_|_|\\___|_| |_|\\__| \\____/|_|  |_|\\__,_|\\__, |\\___|\r\n"
                    + "                                                  __/ |     \r\n"
                    + "                                                 |___/      \r\n");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Empresa");
            System.out.println("2. Candidato");
            System.out.println("0. Sair");

            int opcao = EntradaUtils.lerEntradaValidaInt(sc, "Opção:");

            switch (opcao) {
                case 1:
                    empresaView.showMenu();
                    break;
                case 2:
                    candidatoView.showMenu();
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    sc.close(); 
                    return;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }
}
