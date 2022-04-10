package marmia.menu;

import marmia.algorithm.MagicCircleOfMarmiaAlgorithm;

import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        int opcao;
        do {
            System.out.println("SISTEMA GERENCIADOR DE CÍRCULOS DE MÁRMIA");
            System.out.println("\nESCOLHA DENTRE AS OPÇÕES");
            System.out.println("[1] CRIAR CÍRCULO DE MÁRMIA");
            System.out.println("[2] SAIR");
            opcao = sc.nextInt();
            this.menu(opcao);
        } while (opcao != 2);
    }

    private void menu(int opcao) {
        switch (opcao) {
            case 1:
                this.createCircle();
                System.out.println("\n\nCÍRCULO COMPLETO! TODOS SAÚDAM O GRANDE REI");
                break;
            case 2:
                System.out.println("\nSAINDO");
                break;
            default:
                System.out.println("OPÇÃO INVÁLIDA!");
        }
    }

    private int definePopulation() {
        System.out.println("INSIRA A POPULAÇÃO DE MÁRMIA A SER SIMULADA: ");
        return sc.nextInt();
    }

    private void createCircle() {
        MagicCircleOfMarmiaAlgorithm m = new MagicCircleOfMarmiaAlgorithm();
        m.createMagicCircle(this.definePopulation());
        System.out.println(m.getKingNeighbors());
    }
}
