package main.java.com.example.util;

import java.util.Scanner;

public class EntradaUtils {

	public static String lerEntradaValida(Scanner sc, String mensagem) {
	    String entrada;
	    do {
	        System.out.print(mensagem + " ");
	        entrada = sc.nextLine().trim();
	        if (entrada.isEmpty()) {
	            System.out.println("Entrada não pode ser vazia. Tente novamente.");
	        }
	    } while (entrada.isEmpty());
	    return entrada;
	}

    public static double lerEntradaValidaDouble(Scanner sc, String mensagem) {
        double entrada = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensagem);
                entrada = Double.parseDouble(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número decimal.");
            }
        }

        return entrada;
    }

    public static int lerEntradaValidaInt(Scanner sc, String mensagem) {
        int numero;
        while (true) {
            System.out.print(mensagem + " ");
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Entrada não pode ser vazia. Tente novamente.");
                continue;
            }
            try {
                numero = Integer.parseInt(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
        return numero;
    }

    public static String lerEntradaValida(Scanner sc, String mensagem, String regex) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("Entrada não pode ser vazia. Tente novamente.");
            } else if (!entrada.matches(regex)) {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        } while (entrada.isEmpty() || !entrada.matches(regex));
        return entrada;
    }

    public static String lerEntradaValidaNome(Scanner sc, String mensagem) {
        String regex = "^[a-zA-Z ]+$";
        String entrada;

        while (true) {
            System.out.print(mensagem + " "); 
            entrada = sc.nextLine().trim();  

            if (entrada.matches(regex)) {
                return entrada; 
            } else {
                System.out.println("Entrada inválida. Por favor, digite apenas letras e espaços.");
            }
        }
    }
}
