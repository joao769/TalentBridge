package com.example;

import com.example.model.Empresa;
import com.example.model.Vaga;
import com.example.service.EmpresaService;
import com.example.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TalentBridgeApplication implements CommandLineRunner {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private VagaService vagaService;

    public static void main(String[] args) {
        SpringApplication.run(TalentBridgeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Adicionar Empresa");
            System.out.println("2. Listar Empresas");
            System.out.println("3. Adicionar Vaga");
            System.out.println("4. Listar Vagas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    // Criando uma nova empresa
                    Empresa empresa = new Empresa();
                    System.out.print("Nome da empresa: ");
                    empresa.setNome(scanner.nextLine());
                    System.out.print("Endereço: ");
                    empresa.setEndereco(scanner.nextLine());
                    System.out.print("CNPJ: ");
                    empresa.setCnpj(scanner.nextLine());
                    empresaService.salvar(empresa); // Chama o serviço para salvar
                    System.out.println("Empresa adicionada com sucesso!");
                    break;

                case 2:
                    // Listar todas as empresas
                    empresaService.listarTodas().forEach(e -> 
                        System.out.println("ID: " + e.getId() + " | Nome: " + e.getNome())
                    );
                    break;

                case 3:
                    // Criando uma nova vaga
                    Vaga vaga = new Vaga();
                    System.out.print("Nome da vaga: ");
                    vaga.setNome(scanner.nextLine());
                    System.out.print("Descrição: ");
                    vaga.setDescricao(scanner.nextLine());
                    vagaService.salvar(vaga); // Chama o serviço para salvar
                    System.out.println("Vaga adicionada com sucesso!");
                    break;

                case 4:
                    // Listar todas as vagas
                    vagaService.listarTodas().forEach(v -> 
                        System.out.println("ID: " + v.getId() + " | Nome: " + v.getNome())
                    );
                    break;

                case 5:
                    // Opção para sair
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
